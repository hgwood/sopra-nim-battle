package fr.notfound.monitoring;

import static fr.notfound.TestUtils.contentOf;
import static fr.notfound.TestUtils.localhost;
import static java.lang.String.format;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;

import fr.notfound.monitoring.MainWithMonitoring;

public class ApplicationRunner {
    
    private static final int monitoringPort = 8085;
    private static final URI root = localhost(monitoringPort);

    public void join(URI arenaUri, String teamName, String password) {
        MainWithMonitoring.main(new String[] { arenaUri.toString(), teamName, password, String.valueOf(monitoringPort) });
    }

    public void showsTeamId(String teamId) {
        assertThat(contentOf(root), containsString(teamId));
    }

    public void showsGameId(String gameId) {
        assertThat(contentOf(root), containsString(gameId));
    }
    
    public void showsMove(String x, String y) {
        assertThat(contentOf(root), allOf(containsString(x), containsString(y)));
    }
    
    public void play(String gameId, String x, String y) {
        contentOf(URI.create(root + format("/play/%s/%s/%s", gameId, x, y)));
    }
    
    public void stop() {
        MainWithMonitoring.stop();
    }

}
