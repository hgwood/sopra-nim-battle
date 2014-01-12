package fr.notfound.domain;

public interface Game {
    
    String opponent();
    GameStatus status();
    Board board();
    Move latestMove();
    MoveResult play(Move move);

}
