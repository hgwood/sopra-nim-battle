package fr.notfound;

import static fr.notfound.domain.GameStatus.*;

import org.junit.After;
import org.junit.Test;

import fr.notfound.cli.CliRunner;
import fr.notfound.fakearena.ArenaConfigurator;
import fr.notfound.fakearena.ArenaServer;

public class EndToEndTest {
    
    private final ApplicationRunner application = 
        new ApplicationRunner(new CliRunner(Main.class));
    private final ArenaConfigurator arena = new ArenaConfigurator();
    private ArenaServer server;
    
    @After public void after() {
        server.stop();
    }
    
    @Test public void joinGameThenLoses() {
        server = arena.createVersus().endsWith(Lost).start();
        application.join(arena);
        application.showsGameWasLost();
        
    }
    
    @Test public void joinGameThenWins() {
        server = arena.createVersus().endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }
    
    @Test public void joinGameThenPlaysTwoMovesThenLoses() {
        server = arena.createVersus().acceptsMoves(2).endsWith(Lost).start();
        application.join(arena);
        application.showsGameWasLost();
    }
    
    @Test public void joinGameThenPlaysThreeMovesThenWins() {
        server = arena.createVersus().acceptsMoves(3).endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }
    
    @Test public void joinGameThenIgnoresNonPlayingStatusesThenWins() {
        server = arena.createVersus().delays(3).endsWith(Won).start();
        application.join(arena);
        application.showsGameWasWon();
    }

}
