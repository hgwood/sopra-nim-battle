package fr.notfound;

import static com.google.common.collect.Lists.newArrayList;
import static fr.notfound.monitoring.TestUtils.localhost;

import java.net.URI;
import java.util.List;

import fr.notfound.domain.GameStatus;
import fr.notfound.fakearena.*;

public class ArenaConfigurator implements ArenaBuilder, GameBuilder {

    private static final int port = 8084;
    public final URI uri = localhost(port);
    public final String teamName = "teamName";
    public final String password = "password";
    private final String teamId = "teamId";
    private final ArenaServerBuilder arenaBuilder = ArenaServerBuilder.onPort(port).acceptTeam(teamName, password, teamId);
    private final List<GameStatus> statusSequence = newArrayList();

    @Override public GameBuilder createVersus() {
        return this;
    }

    @Override public GameBuilder thatAcceptsMoves(int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++)
            statusSequence.add(GameStatus.YourTurn);
        return this;
    }

    @Override public ArenaBuilder thenEndsWith(GameStatus status) {
        statusSequence.add(status);
        return this;
    }

    @Override public ArenaServer start() {
        return arenaBuilder.withStatusSequence((GameStatus[])statusSequence.toArray(new GameStatus[0])).start();
    }
}
