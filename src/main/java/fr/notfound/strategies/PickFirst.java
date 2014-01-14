package fr.notfound.strategies;

import fr.notfound.domain.*;

public class PickFirst implements Strategy {

    @Override public Move pickMove(Game game) {
        return new Move(game.board().firstAvailable(), 1);
    }

}
