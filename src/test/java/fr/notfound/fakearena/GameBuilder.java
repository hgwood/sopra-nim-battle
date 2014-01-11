package fr.notfound.fakearena;

import fr.notfound.domain.GameStatus;

public interface GameBuilder {

    GameBuilder thatAcceptsMoves(int numberOfMoves);
    ArenaBuilder thenEndsWith(GameStatus status);

}
