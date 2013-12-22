package fr.notfound;

import static fr.notfound.TestUtils.responseFrom;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ApplicationRunner {
    
    private static final String monitoringPort = "8085";

    public void join(String arenaUrl, String teamName, String password) {
        Main.main(new String[] { arenaUrl, teamName, password, monitoringPort });
    }

    public void showsGame(String gameId) {
        assertThat(responseFrom("http://localhost:" + monitoringPort), containsString(gameId));
    }

    public void stop() {
        Main.stop();
    }

}
