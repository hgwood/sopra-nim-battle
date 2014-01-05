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
    private static final GameStatus status = GameStatus.Won;
    private static final Board board = new Board();
    private static final Move previousMove = new Move(x ,y);
    private static final Move thisMove = new Move(x, y);
    private static final MoveResult moveResult = MoveResult.Approved;
    
    private final Game game = mock(Game.class);
    private final GameRecorder sut = new GameRecorder(game);
    
    @Test public void noRecordedMoveAfterConstruction() {
        assertThat(sut.getRecordedMoves(), is(empty()));
    }
    
    @Test public void gameDataIsGatheredBeforePlaying() {
        sut.play(thisMove);
        InOrder inOrder = Mockito.inOrder(game);
        inOrder.verify(game).status();
        inOrder.verify(game).board();
        inOrder.verify(game).latestMove();
        inOrder.verify(game).play(any(Move.class));
        inOrder.verifyNoMoreInteractions();
    }
    
    @Test public void recordsAllGameData() {
        when(game.status()).thenReturn(status);
        when(game.board()).thenReturn(board);
        when(game.latestMove()).thenReturn(previousMove);
        when(game.play(thisMove)).thenReturn(moveResult);
        
        sut.play(thisMove);
        List<RecordedMove> result = sut.getRecordedMoves();
        assertThat(result, hasSize(equalTo(1)));
        assertThat(result.get(0).statusBefore, is(status));
        assertThat(result.get(0).boardBefore, is(board));
        assertThat(result.get(0).previousMove, is(previousMove));
        assertThat(result.get(0).playedMove, is(thisMove));
        assertThat(result.get(0).result, is(moveResult));
    }

}
