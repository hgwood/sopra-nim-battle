package fr.notfound.adapters;

import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;
import fr.notfound.http.TextArena;

public class ArenaOverArenaClient implements Arena {
    
    private final TextArena client;

    public ArenaOverArenaClient(TextArena client) {
        this.client = client;
    }

    @Override public Team join(String teamName, String password) {
        return new TeamOverArenaClient(client, client.teamId(teamName, password));
    }

}
