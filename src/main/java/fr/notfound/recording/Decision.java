package fr.notfound.recording;

import static java.lang.String.format;

import java.util.Objects;

import fr.notfound.domain.*;
import fr.notfound.meta.ValueType;

@ValueType
public class Decision {
    
    public final GameStatus statusBefore;
    public final Board boardBefore;
    public final Move previousMove;
    public final Move playedMove;
    public final MoveResult result;
    
    public Decision(GameStatus statusBefore, Board boardBefore, Move previousMove, Move playedMove, MoveResult result) {
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
        Decision other = (Decision)obj;
        return Objects.equals(statusBefore, other.statusBefore)
            && Objects.equals(boardBefore, other.boardBefore)
            && Objects.equals(previousMove, other.previousMove)
            && Objects.equals(playedMove, other.playedMove)
            && Objects.equals(result, other.result);
    }

    @Override public int hashCode() {
        return Objects.hash(statusBefore, boardBefore, previousMove, playedMove, result);
    }
    
    @Override public String toString() {
        return format("{Decision: %s, %s, %s -> %s, %s}", statusBefore, boardBefore, previousMove, playedMove, result);
    }

}
