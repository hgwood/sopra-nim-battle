package fr.notfound.strategies.ai;

import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import fr.notfound.domain.*;

/**
 * This strategy evaluates all immediate legal moves and picks the best one.
 */
public class ShortSightedStrategy implements Strategy {
    
    private final Rules rules;
    private final PositionEvaluator positionEvaluator;

    public ShortSightedStrategy(Rules rules, PositionEvaluator positionEvaluator) {
        this.rules = rules;
        this.positionEvaluator = positionEvaluator;
    }

    @Override public Move pickMove(Game game) {
        final Board board = game.board();
        Set<Move> legalMoves = rules.legalMoves(board);
        return Ordering.natural().onResultOf(new Function<Move, Double>() {
            @Override public Double apply(Move move) {
                double a = positionEvaluator.evaluate(board.apply(move));
                return a;
            }
        }).max(legalMoves);
    }

}
