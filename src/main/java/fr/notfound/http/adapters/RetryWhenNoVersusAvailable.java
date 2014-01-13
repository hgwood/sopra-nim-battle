package fr.notfound.http.adapters;

import static java.lang.String.format;
import fr.notfound.domain.*;
import fr.notfound.time.Delay;

/**
 * Adds to an existing {@link Team} the ability to wait and retry when
 * there are no current versus game available.
 */
public class RetryWhenNoVersusAvailable implements Team {
    
    private final Team delegate;
    private final Delay retryDelay;
    private final int numberOfAttempts;

    public RetryWhenNoVersusAvailable(Team delegate, Delay retryDelay, int numberOfAttempts) {
        this.delegate = delegate;
        this.retryDelay = retryDelay;
        this.numberOfAttempts = numberOfAttempts;
    }

    @Override public Game newPractice(AiLevel level) {
        return delegate.newPractice(level);
    }

    @Deprecated @Override public Game currentPractice() {
        throw new UnsupportedOperationException();
    }

    @Override public Game currentVersus() {
        for (int i = 0; i < numberOfAttempts; i++) {
            try {
                return delegate.currentVersus(); 
            } catch (ArenaException e) {
                retryDelay.trigger();
            }
        }
        throw new ArenaException(
            format("no game could be retrieved after %s attemps", numberOfAttempts));
    }

    @Override public String toString() {
        return format("{%s with retry}", delegate.toString());
    }

}
