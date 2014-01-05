package fr.notfound.recording;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;

import fr.notfound.domain.*;

public class GameRecorder implements Game {
    
    private final Game gameBeingRecorded;
    private final List<RecordedMove> recordedMoves = newArrayList();

    public GameRecorder(Game recorded) {
        this.gameBeingRecorded = recorded;
    }
    
    public List<RecordedMove> getRecordedMoves() {
        return Collections.unmodifiableList(recordedMoves);
    }

    @Override public GameStatus status() {
        return gameBeingRecorded.status();
    }

    @Override public Board board() {
        return gameBeingRecorded.board();
    }

    @Override public Move latestMove() {
        return gameBeingRecorded.latestMove();
    }

    @Override public MoveResult play(Move move) {
        GameStatus statusBefore = gameBeingRecorded.status();
        Board boardBefore = gameBeingRecorded.board();
        Move previousMove = gameBeingRecorded.latestMove();
        MoveResult result = gameBeingRecorded.play(move);
        recordedMoves.add(new RecordedMove(statusBefore, boardBefore, previousMove, move, result));
        return result;
    }

}
