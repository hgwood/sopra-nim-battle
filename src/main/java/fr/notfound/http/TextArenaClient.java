package fr.notfound.http;

import fr.notfound.http.uri.ArenaUriCatalog;

public class TextArenaClient implements TextArena {
    
    public final ArenaUriCatalog uris;
    public final UriContentReader client;
    
    public TextArenaClient(ArenaUriCatalog uris, UriContentReader client) {
        this.uris = uris;
        this.client = client;
    }

    @Override public String ping() {
        return client.read(uris.ping());
    }

    @Override public String teamId(String teamName, String password) {
        return client.read(uris.teamId(teamName, password));
    }

    @Override public String currentVersus(String teamId) {
        return client.read(uris.currentVersus(teamId));
    }

    @Override public String newPractice(String level, String teamId) {
        return client.read(uris.newPractice(level, teamId));
    }

    @Override public String currentPractice(String teamId) {
        return client.read(uris.currentPractice(teamId));
    }
    
    @Override public String opponent(String gameId, String teamId) {
        return client.read(uris.opponent(gameId, teamId));
    }

    @Override public String status(String gameId, String teamId) {
        return client.read(uris.status(gameId, teamId));
    }

    @Override public String board(String gameId) {
        return client.read(uris.board(gameId));
    }

    @Override public String latestMove(String gameId) {
        return client.read(uris.lastMove(gameId));
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        return client.read(uris.play(gameId, teamId, x, y));
    }

}
