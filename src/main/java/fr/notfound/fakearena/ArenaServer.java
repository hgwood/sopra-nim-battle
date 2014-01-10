package fr.notfound.fakearena;

import fr.notfound.CompositionRoot;
import fr.notfound.http.server.Jetty;
import fr.notfound.http.uri.ArenaUriCatalog;

public class ArenaServer {

    private static final ArenaUriCatalog uris = new CompositionRoot().uris("");
    
    public static ArenaServer create(int port, 
        final String teamName, final String password, final String teamId, final String gameId, 
        final FakeArena arena) {
        return new ArenaServer(Jetty.onPort(port)
            .handle(uris.teamId(teamName, password), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.teamId(teamName, password);
                }
            })
            .handle(uris.currentVersus(teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.currentVersus(teamId);
                }
            })
            .handle(uris.status(gameId, teamId), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.status(gameId, teamId);
                }
            })
            .handle(uris.play(gameId, teamId, "x", "y"), new Jetty.StringHandler() {
                @Override public String handle() {
                    return arena.play(gameId, teamId, "x", "y");
                }
            })
            .start());
    }

    private final Jetty server;
    
    public ArenaServer(Jetty server) {
        this.server = server;
    }
    
    public void stop() {
        server.stop();
    }

}
