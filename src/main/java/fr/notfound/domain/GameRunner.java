package fr.notfound.domain;

public interface GameRunner {
    
    /**
     * Plays a game until the end and return the final status of the game:
     * {@link GameStatus#Won}, {@link GameStatus#Lost} or 
     * {@link GameStatus#Canceled}.
     */
    GameStatus run(Game game);

}
