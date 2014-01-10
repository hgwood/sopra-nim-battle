package fr.notfound;

import static fr.notfound.monitoring.TestUtils.localhost;

import java.net.URI;

import fr.notfound.domain.GameStatus;
import fr.notfound.fakearena.ArenaServerBuilder;
import fr.notfound.fakearena.ArenaServer;

public class ArenaConfigurator {

    private static final int port = 8084;
    public final URI uri = localhost(port);
    public final String teamName = "teamName";
    public final String password = "password";
    private final String teamId = "teamId";

    private final ArenaServerBuilder arenaBuilder = ArenaServerBuilder.onPort(port).acceptTeam(teamName, password, teamId);
    private ArenaServer arena;
    
    public void createLosingGame() {
        arena = arenaBuilder.withStatusSequence(GameStatus.Lost).start();
    }
    
    public void createWinningGame() {
        arena = arenaBuilder.withStatusSequence(GameStatus.Won).start();
    }
    
    public void createWinningGameAfterThreeMoves() {
        arena = arenaBuilder.withStatusSequence(
            GameStatus.YourTurn, 
            GameStatus.YourTurn, 
            GameStatus.YourTurn, 
            GameStatus.Won).start();
    }
    
    public void createLosingGameAfterTwoMoves() {
        arena = arenaBuilder.withStatusSequence(
            GameStatus.YourTurn, 
            GameStatus.YourTurn, 
            GameStatus.Lost).start();
    }
    
    public void stop() {
        arena.stop();
    }

}
