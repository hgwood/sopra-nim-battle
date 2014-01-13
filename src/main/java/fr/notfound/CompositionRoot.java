package fr.notfound;

import org.slf4j.LoggerFactory;

import fr.notfound.adapters.ArenaOverArenaClient;
import fr.notfound.domain.*;
import fr.notfound.domain.impl.StatusHandlerWithStrategy;
import fr.notfound.http.*;
import fr.notfound.http.uri.*;
import fr.notfound.strategies.AlwaysZeroZero;

/**
 * @see <a href="http://blog.ploeh.dk/2011/07/28/CompositionRoot/">The Definition of Composition Root</a>
 */
public class CompositionRoot {
    
    public Arena arena(String uri) {
        return new ArenaOverArenaClient(arenaClient(uri));
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
    
    public Player player() {
        return new StatusHandlerWithStrategy(strategy());
    }
    
    public Strategy strategy() {
        return new AlwaysZeroZero();
    }

}
