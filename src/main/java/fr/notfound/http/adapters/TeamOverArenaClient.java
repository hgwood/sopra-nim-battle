package fr.notfound.http.adapters;

import fr.notfound.composition.GameFactory;
import fr.notfound.domain.*;
import fr.notfound.http.TextArena;

public class TeamOverArenaClient implements Team {
    
    public static final String NoGame = "NA";
    
    public final TextArena client;
    private final GameFactory gameFactory;
    public final String id;

    public TeamOverArenaClient(TextArena client, GameFactory gameFactory, String id) {
        this.client = client;
        this.gameFactory = gameFactory;
        this.id = id;
    }

    @Override public Game newPractice(AiLevel level) {
        return gameFactory.create(client.newPractice(String.valueOf(level.value), id), id);
    }

    @Override public Game currentPractice() {
        String gameId = client.currentPractice(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no practice running for this team");
        return gameFactory.create(gameId, id);
    }

    @Override public Game currentVersus() {
        String gameId = client.currentVersus(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no versus running for this team");
        return gameFactory.create(gameId, id);
    }
    
    @Override public String toString() {
        return "Team " + id;
    }

}
