package fr.notfound;

import org.junit.Test;

/**
 * End-to-end tests for the monitoring user interface.
 * <p>
 * These tests startup a HTTP server to impersonate an arena, then launch the
 * application, feeding it the parameters to connect to the fake arena. They
 * will then hit the application's monitoring server and check that the desired
 * content is there.
 */
public class MonitoringEndToEndTest {
    
    private static final String teamName = "teamName";
    private static final String password = "password";
    private final FakeSingleGameArena arena = new FakeSingleGameArena();
    private final ApplicationRunner application = new ApplicationRunner();
    
    @Test public void showTeamId() {
        String teamId = arena.start(teamName, password);
        application.join(arena.uri, teamName, password);
        application.showsTeamId(teamId);
        application.stop();
        arena.stop();
    }

}
