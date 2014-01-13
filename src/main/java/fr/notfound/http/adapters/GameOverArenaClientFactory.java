package fr.notfound.http.adapters;

import fr.notfound.composition.GameFactory;
import fr.notfound.domain.Game;
import fr.notfound.http.TextArena;

public class GameOverArenaClientFactory implements GameFactory {
    
    private final TextArena client;

    public GameOverArenaClientFactory(TextArena client) {
        this.client = client;
    }

    @Override public Game create(String gameId, String teamId) {
        return new GameOverArenaClient(client, gameId, teamId);
    }

}
