package fr.notfound.strategies.ai;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import fr.notfound.domain.*;

public class ShortSightedStrategyTest {
    
    private static final Board board = new Board("");
    private static final Move move0 = new Move(0, 0);
    private static final Move move1 = new Move(1, 1);
    private static final Move move2 = new Move(2, 2);
    private static final Set<Move> moves = ImmutableSet.of(move0, move1, move2);
    
    private final Rules rules = mock(Rules.class);
    private final PositionEvaluator positionEvaluator = mock(PositionEvaluator.class);
    private final ShortSightedStrategy sut = new ShortSightedStrategy(rules, positionEvaluator);
    private final Game game = mock(Game.class);
    
    @Test public void picksTheBestMove1() {
        when(game.board()).thenReturn(board);
        when(rules.legalMoves(board)).thenReturn(moves);
        when(positionEvaluator.evaluate(board.apply(move0))).thenReturn(0d);
        when(positionEvaluator.evaluate(board.apply(move1))).thenReturn(1d);
        when(positionEvaluator.evaluate(board.apply(move2))).thenReturn(2d);
        assertThat(sut.pickMove(game), is(move2));
    }
    
    @Test public void picksTheBestMove2() {
        when(game.board()).thenReturn(board);
        when(rules.legalMoves(board)).thenReturn(moves);
        when(positionEvaluator.evaluate(board.apply(move0))).thenReturn(1d);
        when(positionEvaluator.evaluate(board.apply(move1))).thenReturn(2d);
        when(positionEvaluator.evaluate(board.apply(move2))).thenReturn(0d);
        assertThat(sut.pickMove(game), is(move1));
    }

}
