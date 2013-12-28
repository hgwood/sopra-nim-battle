package fr.notfound;

import static fr.notfound.TestUtils.localhost;
import static fr.notfound.TestUtils.contentOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;

public class ApplicationRunner {
    
    private static final int monitoringPort = 8085;

    public void join(URI arenaUri, String teamName, String password) {
        Main.main(new String[] { arenaUri.toString(), teamName, password, String.valueOf(monitoringPort) });
    }

    public void showsTeamId(String gameId) {
        assertThat(contentOf(localhost(monitoringPort)), containsString(gameId));
    }

    public void stop() {
        Main.stop();
    }

}
