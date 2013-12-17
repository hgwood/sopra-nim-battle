package fr.notfound;

import fr.notfound.rest.RestGetClient;
import fr.notfound.url.ArenaUrlCatalog;

public class OfficialArena implements Arena {
    
    public final ArenaUrlCatalog urls;
    public final RestGetClient client;
    
    public OfficialArena(ArenaUrlCatalog urls, RestGetClient client) {
        this.urls = urls;
        this.client = client;
    }

    @Override public String ping() {
        return client.get(urls.ping());
    }

    @Override public String teamId(String teamName, String password) {
        return client.get(urls.teamId(teamName, password));
    }

    @Override public String currentVersus(String teamId) {
        return client.get(urls.currentVersus(teamId));
    }

    @Override public String newPractice(String level, String teamId) {
        return client.get(urls.newPractice(level, teamId));
    }

    @Override public String currentPractice(String teamId) {
        return client.get(urls.currentPractice(teamId));
    }

    @Override public String status(String gameId, String teamId) {
        return client.get(urls.status(gameId, teamId));
    }

    @Override public String board(String gameId) {
        return client.get(urls.board(gameId));
    }

    @Override public String lastMove(String gameId) {
        return client.get(urls.lastMove(gameId));
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        return client.get(urls.play(gameId, teamId, x, y));
    }

}
