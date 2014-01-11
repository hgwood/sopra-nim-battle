package fr.notfound.domain;

/**
 * A strategy picks a move depending on the state of a game. A strategy
 * should not change the state of the game (i.e. call {@link Game#play(Move)}).
 */
public interface Strategy {
    
    Move pickMove(Game game);

}
