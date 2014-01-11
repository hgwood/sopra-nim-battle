package fr.notfound.strategies;

import fr.notfound.domain.*;

public class AlwaysZeroZero implements Strategy {

    @Override public Move pickMove(Game game) {
        return new Move(0, 0);
    }

}
