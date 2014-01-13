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
    
    public static final String teamName = "teamName";
    public static final String password = "password";
    public static final String teamId = "teamId";
    public static final String gameId = "gameId";
    public static final AiLevel aiLevel = AiLevel.of(0);
    public static final GameStatus status = GameStatus.Won;
    
    public final TextArena client = mock(TextArena.class);
    public final TeamFactory teamFactory = mock(TeamFactory.class);
    public final GameFactory gameFactory = mock(GameFactory.class);
    public final ArenaOverArenaClient arenaSut = new ArenaOverArenaClient(client, teamFactory);
    public final TeamOverArenaClient teamSut = new TeamOverArenaClient(client, gameFactory, teamId);
    public final GameOverArenaClient gameSut = new GameOverArenaClient(client, gameId, teamId);
    
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
