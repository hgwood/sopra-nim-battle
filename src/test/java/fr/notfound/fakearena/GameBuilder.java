package fr.notfound.fakearena;

import fr.notfound.domain.GameStatus;

public interface GameBuilder {

    GameBuilder acceptsMoves(int numberOfMoves);
    GameBuilder delaysTurn(int numberOfQueries);
    ArenaBuilder endsWith(GameStatus status);

}
