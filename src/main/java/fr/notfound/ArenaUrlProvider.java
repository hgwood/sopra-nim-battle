package fr.notfound;

import java.net.URL;

public interface ArenaUrlProvider {
    
    URL ping();
    URL teamId(String teamName, String password);
    
    URL currentVersus(String teamId);
    URL newPractice(String level, String teamId);
    URL currentPractice(String teamId);
    
    URL status(String gameId, String teamId);
    URL board(String gameId);
    URL lastMove(String gameId);
    URL play(String gameId, String teamId, String x, String y);

}
