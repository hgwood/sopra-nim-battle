package fr.notfound.adapters;

import fr.notfound.domain.*;
import fr.notfound.http.TextArena;

public class TeamOverArenaClient implements Team {
    
    private static final String NoGame = "NA";
    
    public final TextArena client;
    public final String id;

    public TeamOverArenaClient(TextArena client, String id) {
        this.client = client;
        this.id = id;
    }

    @Override public Game newPractice(AiLevel level) {
        return new GameOverArenaClient(client, client.newPractice(String.valueOf(level.value), id), id);
    }

    @Override public Game currentPractice() {
        String gameId = client.currentPractice(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no practice running for this team");
        return new GameOverArenaClient(client, gameId, id);
    }

    @Override public Game currentVersus() {
        String gameId = client.currentVersus(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no versus running for this team");
        return new GameOverArenaClient(client, gameId, id);
    }
    
    @Override public String toString() {
        return "Team " + id;
    }

}
