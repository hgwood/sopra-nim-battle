package fr.notfound.adapters;

import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;
import fr.notfound.rest.PlainTextArenaClient;

public class ArenaOverArenaClient implements Arena {
    
    private final PlainTextArenaClient client;

    public ArenaOverArenaClient(PlainTextArenaClient client) {
        this.client = client;
    }

    @Override public Team join(String teamName, String password) {
        return new TeamOverArenaClient(client, client.teamId(teamName, password));
    }

}
