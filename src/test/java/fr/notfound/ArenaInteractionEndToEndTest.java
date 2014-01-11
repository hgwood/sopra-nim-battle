package fr.notfound;

import static fr.notfound.domain.GameStatus.*;

import org.junit.After;
import org.junit.Test;

import fr.notfound.cli.CliRunner;
import fr.notfound.fakearena.ArenaServerBuilder;
import fr.notfound.fakearena.ArenaServer;

/**
 * Tests how the application interacts with the arena and manages games. They
 * don't make any assumptions on the strategy used.
 * <p>
 * Theses tests are fully end-to-end: they start a HTTP server to fake out an 
 * arena, then run the application code through the main method.
 */
public class ArenaInteractionEndToEndTest {
    
    private final ApplicationRunner application = 
        new ApplicationRunner(new CliRunner(Main.class));
    private final ArenaServerBuilder builder = new ArenaServerBuilder();
    private ArenaServer arena;
    
    @After public void after() {
        arena.stop();
    }
    
    @Test public void joinGameThenLoses() {
        arena = builder.createVersus().endsWith(Lost).start();
        application.join(arena);
        application.showsGameWasLost();
    }
    
    @Test public void joinGameThenWins() {
        arena = builder.createVersus().endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }
    
    @Test public void joinGameThenAcknowledgeCancelation() {
        arena = builder.createVersus().endsWith(Canceled).start();
        application.join(arena);
        application.showsGameWasCanceled();
    }
    
    @Test public void joinGameThenPlaysTwoMovesThenLoses() {
        arena = builder.createVersus().acceptsMoves(2).endsWith(Lost).start();
        application.join(arena);
        application.showsGameWasLost();
    }
    
    @Test public void joinGameThenPlaysThreeMovesThenWins() {
        arena = builder.createVersus().acceptsMoves(3).endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }
    
    @Test public void joinGameThenIgnoresNonPlayingStatusesThenWins() {
        arena = builder.createVersus().delays(3).endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }

}
