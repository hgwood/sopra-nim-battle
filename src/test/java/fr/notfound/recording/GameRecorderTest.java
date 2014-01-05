package fr.notfound.recording;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import fr.notfound.domain.*;

public class GameRecorderTest {
    
    private static final String x = "x";
    private static final String y = "y";
    private static final Move previousMove = new Move(x ,y);
    private static final Move move = new Move(x, y);
    private static final GameStatus statusBefore = GameStatus.Won;
    private static final Board boardBefore = new Board();
    private static final MoveResult moveResult = MoveResult.Approved;
    private static final RecordedMove expectedRecordedMove = 
        new RecordedMove(statusBefore, boardBefore, previousMove, move, moveResult);
    
    private final Game game = mock(Game.class);
    private final GameRecorder sut = new GameRecorder(game);
    
    @Test public void noRecordedMoveAfterConstruction() {
        assertThat(sut.getRecordedMoves(), is(empty()));
    }
    
    @Test public void gameDataIsGatheredBeforePlaying() {
        sut.play(move);
        InOrder inOrder = Mockito.inOrder(game);
        inOrder.verify(game).status();
        inOrder.verify(game).board();
        inOrder.verify(game).latestMove();
        inOrder.verify(game).play(any(Move.class));
        inOrder.verifyNoMoreInteractions();
    }
    
    @Test public void recordsAllGameData() {
        when(game.status()).thenReturn(statusBefore);
        when(game.board()).thenReturn(boardBefore);
        when(game.latestMove()).thenReturn(previousMove);
        when(game.play(move)).thenReturn(moveResult);
        
        sut.play(move);
        List<RecordedMove> result = sut.getRecordedMoves();
        assertThat(result, hasSize(equalTo(1)));
        assertThat(result.get(0), is(equalTo(expectedRecordedMove)));
    }

}
