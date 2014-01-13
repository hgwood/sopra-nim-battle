package fr.notfound.fakearena;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import fr.notfound.domain.GameStatus;
import fr.notfound.http.TextArena;
import fr.notfound.http.adapters.TeamOverArenaClient;

public class ArenaServerBuilder implements ArenaBuilder, GameBuilder {

    private final static String teamName = "teamName";
    private final static String password = "password";
    private final static String teamId = "teamId";
    private final static String versusId = "versusId";
    
    private final List<String> gameIdSequence = newArrayList();
    private final List<GameStatus> statusSequence = newArrayList();
    
    @Override public ArenaBuilder delaysGame(int numberOfQueries) {
        addToSequence(gameIdSequence, TeamOverArenaClient.NoGame, numberOfQueries);
        return this;
    }

    @Override public GameBuilder createVersus() {
        gameIdSequence.add(versusId);
        return this;
    }

    @Override public GameBuilder acceptsMoves(int numberOfMoves) {
        addToSequence(statusSequence, GameStatus.YourTurn, numberOfMoves);
        return this;
    }

    @Override public ArenaBuilder endsWith(GameStatus status) {
        statusSequence.add(status);
        return this;
    }

    @Override public GameBuilder delaysTurn(int numberOfQueries) {
        addToSequence(statusSequence, GameStatus.NotYourTurn, numberOfQueries);
        return this;
    }
    
    @Override public ArenaServer start() {
        TextArena baseArena = new BasicTestArena(teamId, versusId);
        if (!gameIdSequence.isEmpty()) baseArena = new VersusSequenceArena(baseArena, gameIdSequence.iterator());
        if (!statusSequence.isEmpty()) baseArena = new StatusSequenceArena(baseArena, statusSequence.iterator());
        return ArenaServer.start(baseArena, teamName, password, teamId, versusId);
    }
    
    private <T> void addToSequence(List<T> sequence, T item, int times) { // hey look! a private method! ;)
        for (int i = 0; i < times; i++)
            sequence.add(item);
    }
    
}
