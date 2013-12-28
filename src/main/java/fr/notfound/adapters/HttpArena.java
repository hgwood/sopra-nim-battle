package fr.notfound.adapters;

import fr.notfound.PlainTextArenaClient;
import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;

public class HttpArena implements Arena {
    
    private final PlainTextArenaClient client;

    public HttpArena(PlainTextArenaClient client) {
        this.client = client;
    }

    @Override public Team join(String teamName, String password) {
        return new HttpTeam(client, client.teamId(teamName, password));
    }

}
