package fr.notfound;

import static fr.notfound.TestUtils.localhost;
import static java.util.UUID.randomUUID;

import java.net.URI;

public class FakeSingleGameArena {
    
    public final int port = 8084;
    public final URI uri = localhost(port);
    
    private Jetty server;

    public String start(String teamName, String password) {
        String id = randomUUID().toString();
        server = Jetty.onPort(port)
            .handle("/player/getIdEquipe/" + teamName + "/" + password, id)
            .start();
        return id;
    }

    public void stop() {
        server.stop();
    }

}
