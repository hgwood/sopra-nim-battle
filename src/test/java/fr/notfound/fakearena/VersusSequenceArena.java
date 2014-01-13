package fr.notfound.fakearena;

import java.util.Iterator;

import fr.notfound.http.TextArena;

public class VersusSequenceArena implements TextArena {
    
    private final TextArena delegate;
    private final Iterator<String> versusIdSequence;
    private String currentVersusId;

    public VersusSequenceArena(TextArena delegate, Iterator<String> versusIdSequence) {
        this.delegate = delegate;
        this.versusIdSequence = versusIdSequence;
        this.currentVersusId = versusIdSequence.next();
    }

    @Override public String ping() {
        return delegate.ping();
    }

    @Override public String teamId(String teamName, String password) {
        return delegate.teamId(teamName, password);
    }

    @Override public synchronized String currentVersus(String teamId) {
        String result = currentVersusId;
        if (versusIdSequence.hasNext()) currentVersusId = versusIdSequence.next();
        return result;
    }

    @Override public String newPractice(String level, String teamId) {
        return delegate.newPractice(level, teamId);
    }

    @Override public String currentPractice(String teamId) {
        return delegate.currentPractice(teamId);
    }
    
    @Override public String opponent(String gameId, String teamId) {
        return delegate.opponent(gameId, teamId);
    }

    @Override public String status(String gameId, String teamId) {
        return delegate.status(gameId, teamId);
    }

    @Override public String board(String gameId) {
        return delegate.board(gameId);
    }

    @Override public String latestMove(String gameId) {
        return delegate.latestMove(gameId);
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        return delegate.play(gameId, teamId, x, y);
    }

}
