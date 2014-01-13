package fr.notfound.monitoring;

import org.junit.After;
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
    private static final String teamId = "teamId";
    private static final String gameId = "gameId";
    private static final int x = 0;
    private static final int y = 1;
    private static final int z = 2;
    private static final int t = 3;
    
    private final FakeSingleGameArena arena = new FakeSingleGameArena(teamName, password, teamId, gameId);
    private final ApplicationRunner application = new ApplicationRunner();
    
    @After public void stop() {
        application.stop();
        arena.stop();
    }
    
    @Test public void showsTeamId() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.showsTeamId(teamId);
    }
    
    @Test public void showsGameId() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.showsGameId(gameId);
    }
    
    @Test public void showsMoves() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.play(gameId, x, y);
        application.showsMove(x, y);
        application.play(gameId, z, t);
        application.showsMove(z, t);
    }

}
