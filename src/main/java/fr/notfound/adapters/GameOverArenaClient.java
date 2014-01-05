package fr.notfound.adapters;

import fr.notfound.domain.*;
import fr.notfound.rest.PlainTextArenaClient;

public class GameOverArenaClient implements Game {
    
    private static final String NoBoard = "NA";
    
    private final PlainTextArenaClient client;
    private final String gameId;
    private final String teamId;

    public GameOverArenaClient(PlainTextArenaClient client, String gameId, String teamId) {
        this.client = client;
        this.gameId = gameId;
        this.teamId = teamId;
    }

    @Override public GameStatus status() {
        return GameStatus.parse(client.status(gameId, teamId));
    }

    @Override public Board board() {
        String board = client.status(gameId, teamId);
        if (board.equals(NoBoard)) throw new ArenaException("no board available");
        return Board.parse(board);
    }

    @Override public Move latestMove() {
        return Move.parse(client.lastMove(gameId));
    }

    @Override public MoveResult play(Move move) {
        return MoveResult.parse(client.play(gameId, teamId, move.x, move.y));
    }
    
    @Override public String toString() {
        return "Game " + gameId;
    }

}
