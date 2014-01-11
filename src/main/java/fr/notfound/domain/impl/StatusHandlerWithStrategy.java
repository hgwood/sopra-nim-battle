package fr.notfound.domain.impl;

import fr.notfound.domain.*;

public class StatusHandlerWithStrategy implements GameRunner {
    
    private final Strategy strategy;
    
    public StatusHandlerWithStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override public GameStatus run(Game game) {
        GameStatus status = game.status();
        while (!status.isFinal) {
            if (status == GameStatus.YourTurn) {
                MoveResult result = game.play(strategy.pickMove(game));
                if (result == MoveResult.Rejected) {
                    return GameStatus.Lost;
                } else if (result == MoveResult.Victory) {
                    return GameStatus.Won;
                }
            }
            status = game.status();
        }
        return status;
    }

}
