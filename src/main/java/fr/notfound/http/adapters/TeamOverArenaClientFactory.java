package fr.notfound.http.adapters;

import fr.notfound.composition.GameFactory;
import fr.notfound.composition.TeamFactory;
import fr.notfound.domain.Team;
import fr.notfound.http.TextArena;

public class TeamOverArenaClientFactory implements TeamFactory {
    
    private final TextArena client;
    private final GameFactory gameFactory;

    public TeamOverArenaClientFactory(TextArena client, GameFactory gameFactory) {
        this.client = client;
        this.gameFactory = gameFactory;
    }

    @Override public Team create(String id) {
        return new TeamOverArenaClient(client, gameFactory, id);
    }

}
