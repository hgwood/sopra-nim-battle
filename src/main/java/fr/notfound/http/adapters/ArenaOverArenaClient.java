package fr.notfound.http.adapters;

import fr.notfound.composition.TeamFactory;
import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;
import fr.notfound.http.TextArena;

public class ArenaOverArenaClient implements Arena {
    
    private final TextArena client;
    private final TeamFactory teamFactory;

    public ArenaOverArenaClient(TextArena client, TeamFactory teamFactory) {
        this.client = client;
        this.teamFactory = teamFactory;
    }

    @Override public Team join(String teamName, String password) {
        return teamFactory.create(client.teamId(teamName, password));
    }

}
