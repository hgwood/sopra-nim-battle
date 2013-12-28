package fr.notfound.adapters;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import fr.notfound.domain.Team;
import fr.notfound.rest.PlainTextArenaClient;

/**
 * Not sure this test is useful...
 */
public class HttpArenaTest {
    
    public static final String teamName = "teamName";
    public static final String password = "password";
    public static final String teamId = "id";
    public final PlainTextArenaClient client = mock(PlainTextArenaClient.class);
    public final HttpArena sut = new HttpArena(client);
    
    @Test public void joinReturnsHttpTeamsWithSameClientAndTheIdTheClientReturned() {
        when(client.teamId(teamName, password)).thenReturn(teamId);
        Team result = sut.join(teamName, password);
        assertThat(result, is(instanceOf(HttpTeam.class)));
        HttpTeam effectiveResult = (HttpTeam)result;
        assertThat(effectiveResult.client, is(client));
        assertThat(effectiveResult.id, is(teamId));
    }

}
