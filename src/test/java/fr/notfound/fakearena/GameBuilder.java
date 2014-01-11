package fr.notfound.fakearena;

import fr.notfound.domain.GameStatus;

public interface GameBuilder {

    GameBuilder acceptsMoves(int numberOfMoves);
    ArenaBuilder endsWith(GameStatus status);

}
