package fr.notfound.recording;

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

}
