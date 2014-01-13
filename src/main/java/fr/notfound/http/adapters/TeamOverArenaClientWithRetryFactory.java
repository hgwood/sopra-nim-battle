package fr.notfound.http.adapters;

import fr.notfound.composition.TeamFactory;
import fr.notfound.domain.Team;
import fr.notfound.time.ThreadDelay;

public class TeamOverArenaClientWithRetryFactory implements TeamFactory {
    
    private final TeamFactory delegate;
    private final int retryDelay;
    private final int numberOfAttempts;

    public TeamOverArenaClientWithRetryFactory(TeamFactory delegate, int retryDelay, int numberOfAttemps) {
        this.delegate = delegate;
        this.retryDelay = retryDelay;
        this.numberOfAttempts = numberOfAttemps;
    }

    @Override public Team create(String id) {
        return new RetryWhenNoVersusAvailable(delegate.create(id), new ThreadDelay(retryDelay), numberOfAttempts);
    }

}
