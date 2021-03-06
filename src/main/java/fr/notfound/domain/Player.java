package fr.notfound.domain;

public interface Player {
    
    /**
     * Plays a game until the end and return the final status of the game:
     * {@link GameStatus#Won}, {@link GameStatus#Lost} or 
     * {@link GameStatus#Canceled}.
     */
    GameStatus playToCompletion(Game game);

}
