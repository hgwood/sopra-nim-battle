package fr.notfound;

import java.util.UUID;

public class FakeSingleGameArena {
    
    public final String url = "http://localhost:8084";
    
    private Jetty server;

    public String start(String teamName, String password) {
        String id = UUID.randomUUID().toString();
        server = Jetty.onPort(8084)
            .handle("/player/getIdEquipe/" + teamName + "/" + password, id)
            .start();
        return id;
    }

    public void stop() {
        server.stop();
    }

}
