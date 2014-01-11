package fr.notfound.fakearena;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import fr.notfound.domain.GameStatus;

public class ArenaServerBuilder implements ArenaBuilder, GameBuilder {

    private final static String teamName = "teamName";
    private final static String password = "password";
    private final static String teamId = "teamId";
    private final static String versusId = "versusId";
    
    private final List<GameStatus> statusSequence = newArrayList();

    @Override public GameBuilder createVersus() {
        return this;
    }

    @Override public GameBuilder acceptsMoves(int numberOfMoves) {
        addToStatusSequence(GameStatus.YourTurn, numberOfMoves);
        return this;
    }

    @Override public ArenaBuilder endsWith(GameStatus status) {
        statusSequence.add(status);
        return this;
    }

    @Override public GameBuilder delays(int numberOfQuery) {
        addToStatusSequence(GameStatus.NotYourTurn, numberOfQuery);
        return this;
    }
    
    @Override public ArenaServer start() {
        return ArenaServer.start(
            new StatusSequenceArena(teamId, versusId, statusSequence.iterator()), 
            teamName, password, teamId, versusId);
    }
    
    private void addToStatusSequence(GameStatus status, int times) {
        for (int i = 0; i < times; i++)
            statusSequence.add(status);
    }
}
