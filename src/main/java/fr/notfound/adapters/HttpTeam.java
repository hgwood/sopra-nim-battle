package fr.notfound.adapters;

import fr.notfound.domain.*;
import fr.notfound.rest.PlainTextArenaClient;

public class HttpTeam implements Team {
    
    private static final String NoGame = "NA";
    
    public final PlainTextArenaClient client;
    public final String id;

    public HttpTeam(PlainTextArenaClient client, String id) {
        this.client = client;
        this.id = id;
    }

    @Override public Game newPractice(AiLevel level) {
        return new HttpGame(client, client.newPractice(level.toString(), id), id);
    }

    @Override public Game currentPractice() {
        String gameId = client.currentPractice(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no practice running for this team");
        return new HttpGame(client, gameId, id);
    }

    @Override public Game currentVersus() {
        String gameId = client.currentVersus(id);
        if (gameId.equals(NoGame)) throw new ArenaException("no versus running for this team");
        return new HttpGame(client, gameId, id);
    }

}
