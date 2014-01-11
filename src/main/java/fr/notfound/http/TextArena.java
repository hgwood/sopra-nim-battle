package fr.notfound.http;

public interface TextArena {

    String ping();
    String teamId(String teamName, String password);
    
    String currentVersus(String teamId);
    String newPractice(String level, String teamId);
    String currentPractice(String teamId);
    
    String status(String gameId, String teamId);
    String board(String gameId);
    String lastMove(String gameId);
    String play(String gameId, String teamId, String x, String y);

}
