package fr.notfound.url;

import java.net.URI;

public interface ArenaUriCatalog {
    
    URI ping();
    URI teamId(String teamName, String password);
    
    URI currentVersus(String teamId);
    URI newPractice(String level, String teamId);
    URI currentPractice(String teamId);
    
    URI status(String gameId, String teamId);
    URI board(String gameId);
    URI lastMove(String gameId);
    URI play(String gameId, String teamId, String x, String y);

}
