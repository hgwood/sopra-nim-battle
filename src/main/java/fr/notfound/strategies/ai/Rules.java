package fr.notfound.strategies.ai;

import java.util.Set;

import fr.notfound.domain.Board;
import fr.notfound.domain.Move;

public interface Rules {
    
    /**
     * Returns all moves that can be played on the given board according to
     * these rules.
     */
    Set<Move> legalMoves(Board board);

}
