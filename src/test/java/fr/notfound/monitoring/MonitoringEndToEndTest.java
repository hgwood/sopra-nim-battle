package fr.notfound.monitoring;

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
    private static final String x = "x";
    private static final String y = "y";
    private static final String z = "z";
    private static final String t = "t";
    
    private final FakeSingleGameArena arena = new FakeSingleGameArena(teamName, password, teamId, gameId);
    private final ApplicationRunner application = new ApplicationRunner();
    
    @Test public void showsTeamId() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.showsTeamId(teamId);
        application.stop();
        arena.stop();
    }
    
    @Test public void showsGameId() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.showsGameId(gameId);
        application.stop();
        arena.stop();
    }
    
    @Test public void showsMoves() {
        arena.start();
        application.join(arena.uri, teamName, password);
        application.play(gameId, x, y);
        application.showsMove(x, y);
        application.play(gameId, z, t);
        application.showsMove(z, t);
        application.stop();
        arena.stop();
    }

}
