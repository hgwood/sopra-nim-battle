package fr.notfound.strategies.ai;

import fr.notfound.domain.Board;

public interface PositionEvaluator {
    
    /**
     * Returns a number indicating how good the given board is for the player.
     */
    double evaluate(Board board);

}
