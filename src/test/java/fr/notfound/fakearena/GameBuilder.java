package fr.notfound.fakearena;

import fr.notfound.domain.GameStatus;

public interface GameBuilder {

    GameBuilder acceptsMoves(int numberOfMoves);
    GameBuilder delays(int numberOfQuery);
    ArenaBuilder endsWith(GameStatus status);

}
