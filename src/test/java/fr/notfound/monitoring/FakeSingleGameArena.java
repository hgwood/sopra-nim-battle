package fr.notfound.monitoring;

import static fr.notfound.TestUtils.localhost;

import java.net.URI;

import fr.notfound.CompositionRoot;
import fr.notfound.http.server.Jetty;
import fr.notfound.http.uri.*;

public class FakeSingleGameArena {
    
    public static final int port = 8084;
    public final URI uri = localhost(port);
    
    private final ArenaUriCatalog uris = new CompositionRoot().uris("/");
    
    private final String teamName;
    private final String password;
    private final String teamId;
    private final String gameId;
    private final Jetty server;

    public FakeSingleGameArena(String teamName, String password, String teamId, String gameId) {
        this.teamName = teamName;
        this.password = password;
        this.teamId = teamId;
        this.gameId = gameId;
        this.server = Jetty.onPort(port);
    }

    public void start() {
        server
            .handle(uris.teamId(teamName, password), teamId)
            .handle(uris.currentVersus(teamId), gameId)
            .handle(uris.status(gameId, teamId), "OUI") // magical constant!
            .handle(uris.board(gameId), "board")
            .handle(uris.lastMove(gameId), "1,1")
            .handle(uris.play(gameId, teamId, "x", "y"), "OK") // magical constants!
            .handle(uris.play(gameId, teamId, "z", "t"), "OK") // magical constants!
            .start();
    }

    public void stop() {
        server.stop();
    }

}
