package fr.notfound;

import org.junit.After;
import org.junit.Test;

import fr.notfound.cli.CliRunner;

public class EndToEndTest {
    
    private final ApplicationRunner application = 
        new ApplicationRunner(new CliRunner(Main.class));
    private final ArenaConfigurator arena = new ArenaConfigurator();
    
    @After public void after() {
        arena.stop();
    }
    
    @Test public void joinGameThenLoses() {
        arena.createLosingGame();
        application.join(arena);
        application.showsGameWasLost();
        
    }
    
    @Test public void joinGameThenWins() {
        arena.createWinningGame();
        application.join(arena);
        application.showsGameWasWon();
    }
    
    @Test public void joinGameThenPlaysTwoMovesThenLoses() {
        arena.createLosingGameAfterTwoMoves();
        application.join(arena);
        application.showsGameWasLost();
    }
    
    @Test public void joinGameThenPlaysThreeMovesThenWins() {
        arena.createWinningGameAfterThreeMoves();
        application.join(arena);
        application.showsGameWasWon();
    }

}
