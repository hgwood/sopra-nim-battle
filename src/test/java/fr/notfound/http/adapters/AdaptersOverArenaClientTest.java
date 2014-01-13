package fr.notfound.http.adapters;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;

import fr.notfound.composition.GameFactory;
import fr.notfound.composition.TeamFactory;
import fr.notfound.domain.AiLevel;
import fr.notfound.domain.GameStatus;
import fr.notfound.http.TextArena;

public class AdaptersOverArenaClientTest {
    
    private static final String teamName = "teamName";
    private static final String password = "password";
    private static final String teamId = "teamId";
    private static final String gameId = "gameId";
    private static final AiLevel aiLevel = AiLevel.of(0);
    private static final GameStatus status = GameStatus.Won;
    
    private final TextArena client = mock(TextArena.class);
    private final TeamFactory teamFactory = mock(TeamFactory.class);
    private final GameFactory gameFactory = mock(GameFactory.class);
    private final ArenaOverArenaClient arenaSut = new ArenaOverArenaClient(client, teamFactory);
    private final TeamOverArenaClient teamSut = new TeamOverArenaClient(client, gameFactory, teamId);
    private final GameOverArenaClient gameSut = new GameOverArenaClient(client, gameId, teamId);
    
    @Test public void arenaCreatesTeamWithCorrectId() {
        when(client.teamId(teamName, password)).thenReturn(teamId);
        arenaSut.join(teamName, password);
        verify(teamFactory).create(teamId);
    }
    
    @Test public void teamCreatesPracticeWithCorrectTeamAndGameIds() {
        when(client.newPractice(String.valueOf(aiLevel.value), teamId)).thenReturn(gameId);
        teamSut.newPractice(aiLevel);
        verify(gameFactory).create(gameId, teamId);
    }
    
    @Test public void teamReturnsVersusWithCorrectTeamAndGameIds() {
        when(client.currentVersus(teamId)).thenReturn(gameId);
        teamSut.currentVersus();
        verify(gameFactory).create(gameId, teamId);
    }
    
    @Test public void gameReturnsCorrectStatus() {
        when(client.status(gameId, teamId)).thenReturn(status.wireValue());
        GameStatus result = gameSut.status();
        assertThat(result, is(status));
    }

}
