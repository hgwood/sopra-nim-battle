package fr.notfound.adapters;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import fr.notfound.domain.AiLevel;
import fr.notfound.domain.GameStatus;
import fr.notfound.http.TextArena;
import fr.notfound.http.adapters.ArenaOverArenaClient;

public class AdaptersOverArenaClientTest {
    
    public static final String teamName = "teamName";
    public static final String password = "password";
    public static final String teamId = "teamId";
    public static final String gameId = "gameId";
    public static final AiLevel aiLevel = AiLevel.of(0);
    
    public final TextArena client = mock(TextArena.class);
    public final ArenaOverArenaClient sut = new ArenaOverArenaClient(client);
    
    @Test public void communicatesThroughTheSameClientAllTheWay() {
        when(client.teamId(teamName, password)).thenReturn(teamId);
        when(client.newPractice(String.valueOf(aiLevel.value), teamId)).thenReturn(gameId);
        when(client.status(gameId, teamId)).thenReturn("GAGNE");
        
        GameStatus result = sut
            .join(teamName, password)
            .newPractice(aiLevel)
            .status();
        
        assertThat(result, is(GameStatus.Won));
    }

}
