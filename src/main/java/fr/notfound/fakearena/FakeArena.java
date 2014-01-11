package fr.notfound.fakearena;

import java.util.Iterator;

import fr.notfound.domain.GameStatus;
import fr.notfound.http.PlainTextArenaClient;

public class FakeArena implements PlainTextArenaClient {

    private final String teamId;
    private final String versusId;
    private final Iterator<GameStatus> statusSequence;
    private GameStatus currentStatus;

    public FakeArena(String teamId, String versusId, Iterator<GameStatus> statusSequence) {
        this.teamId = teamId;
        this.versusId = versusId;
        this.statusSequence = statusSequence;
        this.currentStatus = statusSequence.next();
    }

    @Override public String ping() {
        return "pong";
    }

    @Override public String teamId(String teamName, String password) {
        return teamId;
    }

    @Override public String currentVersus(String teamId) {
        return versusId;
    }

    @Override public String newPractice(String level, String teamId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override public String currentPractice(String teamId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override public String status(String gameId, String teamId) {
        return currentStatus.wireValue();
    }

    @Override public String board(String gameId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override public String lastMove(String gameId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override public synchronized String play(String gameId, String teamId, String x, String y) {
        currentStatus = statusSequence.next();
        return "OK";
    }

}
