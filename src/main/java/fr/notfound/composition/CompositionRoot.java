package fr.notfound.composition;

import org.slf4j.LoggerFactory;

import fr.notfound.domain.*;
import fr.notfound.domain.impl.StatusHandlerWithStrategy;
import fr.notfound.http.*;
import fr.notfound.http.adapters.*;
import fr.notfound.http.uri.*;
import fr.notfound.strategies.*;
import fr.notfound.time.ThreadDelay;

/**
 * @see <a href="http://blog.ploeh.dk/2011/07/28/CompositionRoot/">The Definition of Composition Root</a>
 */
public class CompositionRoot {
    
    private final int retryDelayWhenNoGameAvailable;
    private final int numberOfAttemptsToRetrieveGame;
    private final int retryDelayWhenNotYourTurn;
    
    public CompositionRoot() {
        this(0, 1, 0);
    }

    public CompositionRoot(int retryDelayWhenNoGameAvailable, int numberOfAttemptsToRetrieveGame, int retryDelayWhenNotYourTurn) {
        this.retryDelayWhenNoGameAvailable = retryDelayWhenNoGameAvailable;
        this.numberOfAttemptsToRetrieveGame = numberOfAttemptsToRetrieveGame;
        this.retryDelayWhenNotYourTurn = retryDelayWhenNotYourTurn;
    }
    
    public Arena arena(String uri) {
        TextArena client = arenaClient(uri);
        return new ArenaOverArenaClient(client, 
            new TeamOverArenaClientWithRetryFactory(
                new TeamOverArenaClientFactory(client, 
                    new GameOverArenaClientFactory(client)), 
                retryDelayWhenNoGameAvailable, numberOfAttemptsToRetrieveGame));
    }
    
    public TextArena arenaClient(String uri) {
        return new TextArenaLogger(
            new TextArenaClient(uris(uri), new ApacheHttpUriContentReader()), 
            LoggerFactory.getLogger(TextArenaLogger.class));
    }
    
    public ArenaUriCatalog uris(String rootUri) {
        String rootUriWithEndingSlash = rootUri.endsWith("/") ? rootUri : rootUri + "/";
        return new HardCodedOfficialUriCatalog(
            new AbsoluteUriFactory(new UncheckedUriFactory(), rootUriWithEndingSlash));
    }
    
    public Player practicePlayer() {
        return player(new PickFirstExceptLastGroupNuke());
    }
    
    public Player versusPlayer() {
        return player(new PickFirstExceptLastGroupNuke());
    }
    
    private Player player(Strategy strategy) {
        return new StatusHandlerWithStrategy(strategy, new ThreadDelay(retryDelayWhenNotYourTurn));
    }
    
}
