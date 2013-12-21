package fr.notfound.domain;

public interface Game {
    
    GameStatus status();
    Board board();
    Move latestMove();
    MoveResult play(Move move);

}
