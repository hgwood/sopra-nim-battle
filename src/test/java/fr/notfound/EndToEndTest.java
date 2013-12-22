package fr.notfound;

import org.junit.Test;

public class EndToEndTest {
    
    private static final String teamName = "teamName";
    private static final String password = "password";
    private final FakeSingleGameArena arena = new FakeSingleGameArena();
    private final ApplicationRunner application = new ApplicationRunner();
    
    @Test public void test() {
        String gameId = arena.start(teamName, password);
        application.join(arena.url, teamName, password);
        application.showsGame(gameId);
        application.stop();
        arena.stop();
    }

}
