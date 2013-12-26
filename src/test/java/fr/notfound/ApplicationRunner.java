package fr.notfound;

import static fr.notfound.TestUtils.localhost;
import static fr.notfound.TestUtils.responseFrom;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ApplicationRunner {
    
    private static final int monitoringPort = 8085;

    public void join(String arenaUrl, String teamName, String password) {
        Main.main(new String[] { arenaUrl, teamName, password, String.valueOf(monitoringPort) });
    }

    public void showsTeamId(String gameId) {
        assertThat(responseFrom(localhost(monitoringPort)), containsString(gameId));
    }

    public void stop() {
        Main.stop();
    }

}
