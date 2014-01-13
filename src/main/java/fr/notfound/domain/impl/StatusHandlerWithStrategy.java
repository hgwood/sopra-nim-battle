package fr.notfound.domain.impl;

import fr.notfound.domain.*;

/**
 * Plays according to the provided {@link Strategy} each time the game has a
 * status of {@link GameStatus#YourTurn} until the game reaches a final status
 * ({@link GameStatus#Won}, {@link GameStatus#Lost} or 
 * {@link GameStatus#Canceled}), or one of its move is rejected, which is
 * interpreted as an immediate defeat.
 */
public class StatusHandlerWithStrategy implements Player {
    
    private final Strategy strategy;
    
    public StatusHandlerWithStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override public GameStatus playToCompletion(Game game) {
        GameStatus status = game.status();
        while (!status.isFinal) {
            if (status == GameStatus.YourTurn) {
                MoveResult result = game.play(strategy.pickMove(game));
                if (result == MoveResult.Rejected) {
                    return GameStatus.Lost;
                } else if (result == MoveResult.Victory) {
                    return GameStatus.Won;
                }
            } else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException("thread was interrupted while sleeping", e);
                }
            }
            status = game.status();
        }
        return status;
    }

}
