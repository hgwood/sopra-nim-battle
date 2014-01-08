package fr.notfound;

import static fr.notfound.monitoring.TestUtils.*;

import java.net.URI;

import fr.notfound.http.server.Jetty;
import fr.notfound.http.uri.ArenaUriCatalog;

public class FakeArena {

    private static final int port = 8084;
    public final URI uri = localhost(port);
    public final String teamName = "teamName";
    public final String password = "password";
    public final String teamId = "teamId";
    public final String gameId = "gameId";
    
    private final ArenaUriCatalog uris = new CompositionRoot().uris("");
    private final Jetty server = Jetty.onPort(port);
    
    public void createLosingGame() {
        server
            .handle(uris.teamId(teamName, password), teamId)
            .handle(uris.currentVersus(teamId), gameId)
            .handle(uris.status(gameId, teamId), "PERDU")
            .start(); 
    }
    
    public void createWinningGame() {
        server
            .handle(uris.teamId(teamName, password), teamId)
            .handle(uris.currentVersus(teamId), gameId)
            .handle(uris.status(gameId, teamId), "GAGNE")
            .start(); 
    }
    
    public void stop() {
        server.stop();
    }

}
