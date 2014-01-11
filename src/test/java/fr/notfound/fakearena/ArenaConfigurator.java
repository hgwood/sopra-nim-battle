package fr.notfound.fakearena;

import static com.google.common.collect.Lists.newArrayList;
import static fr.notfound.monitoring.TestUtils.localhost;

import java.net.URI;
import java.util.List;

import fr.notfound.domain.GameStatus;

public class ArenaConfigurator implements ArenaBuilder, GameBuilder {

    private static final int port = 8084;
    public final URI uri = localhost(port);
    public final String teamName = "teamName";
    public final String password = "password";
    private final String teamId = "teamId";
    private final String versusId = "versusId";
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

    @Override public ArenaServer start() {
        FakeArena arena = new FakeArena(teamId, versusId, statusSequence.iterator());
        return ArenaServer.start(port, teamName, password, teamId, versusId, arena);
    }

    @Override public GameBuilder delays(int numberOfQuery) {
        addToStatusSequence(GameStatus.NotYourTurn, numberOfQuery);
        return this;
    }
    
    private void addToStatusSequence(GameStatus status, int times) {
        for (int i = 0; i < times; i++)
            statusSequence.add(status);
    }
}
