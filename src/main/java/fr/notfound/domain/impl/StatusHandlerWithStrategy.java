package fr.notfound.domain.impl;

import fr.notfound.domain.*;
import fr.notfound.time.Delay;

/**
 * Plays according to the provided {@link Strategy} each time the game has a
 * status of {@link GameStatus#YourTurn} until the game reaches a final status
 * ({@link GameStatus#Won}, {@link GameStatus#Lost} or 
 * {@link GameStatus#Canceled}), or one of its move is rejected, which is
 * interpreted as an immediate defeat.
 */
public class StatusHandlerWithStrategy implements Player {
    
    private final Strategy strategy;
    private final Delay retryDelayWhenNotYourTurn;
    
    public StatusHandlerWithStrategy(Strategy strategy, Delay retryDelayWhenNotYourTurn) {
        this.strategy = strategy;
        this.retryDelayWhenNotYourTurn = retryDelayWhenNotYourTurn;
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
            } else retryDelayWhenNotYourTurn.trigger();
            status = game.status();
        }
        return status;
    }

}
