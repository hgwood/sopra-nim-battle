package fr.notfound.recording;

import java.util.Objects;

import fr.notfound.domain.*;
import fr.notfound.meta.ValueType;

@ValueType
public class RecordedMove {
    
    public final GameStatus statusBefore;
    public final Board boardBefore;
    public final Move previousMove;
    public final Move playedMove;
    public final MoveResult result;
    
    public RecordedMove(GameStatus statusBefore, Board boardBefore, Move previousMove, Move playedMove, MoveResult result) {
        this.statusBefore = statusBefore;
        this.boardBefore = boardBefore;
        this.previousMove = previousMove;
        this.playedMove = playedMove;
        this.result = result;
    }
    
    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        RecordedMove other = (RecordedMove)obj;
        return Objects.equals(statusBefore, other.statusBefore)
            && Objects.equals(boardBefore, other.boardBefore)
            && Objects.equals(previousMove, other.previousMove)
            && Objects.equals(playedMove, other.playedMove)
            && Objects.equals(result, other.result);
    }

    @Override public int hashCode() {
        return Objects.hash(statusBefore, boardBefore, previousMove, playedMove, result);
    }

}
