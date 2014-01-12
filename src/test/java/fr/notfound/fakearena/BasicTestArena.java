package fr.notfound.fakearena;

import fr.notfound.domain.*;
import fr.notfound.http.TextArena;

public class BasicTestArena implements TextArena {
    
    private final String teamId;
    private final String gameId;
    
    public BasicTestArena(String teamId, String gameId) {
        this.teamId = teamId;
        this.gameId = gameId;
    }

    @Override public String ping() {
        return "pong";
    }

    @Override public String teamId(String teamName, String password) {
        return teamId;
    }

    @Override public String currentVersus(String teamId) {
        return gameId;
    }

    @Override public String newPractice(String level, String teamId) {
        return gameId;
    }

    @Override public String currentPractice(String teamId) {
        return gameId;
    }

    @Override public String opponent(String gameId, String teamId) {
        return "opponentName";
    }

    @Override public String status(String gameId, String teamId) {
        return GameStatus.YourTurn.wireValue();
    }

    @Override public String board(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override public String latestMove(String gameId) {
        throw new UnsupportedOperationException();
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        return MoveResult.Approved.wireValue();
    }

}
