package fr.notfound;

import org.slf4j.LoggerFactory;

import fr.notfound.adapters.ArenaOverArenaClient;
import fr.notfound.domain.Arena;
import fr.notfound.http.*;
import fr.notfound.http.uri.*;

/**
 * @see <a href="http://blog.ploeh.dk/2011/07/28/CompositionRoot/">The Definition of Composition Root</a>
 */
public class CompositionRoot {
    
    public Arena arena(String uri) {
        return new ArenaOverArenaClient(arenaClient(uri));
    }
    
    public PlainTextArenaClient arenaClient(String uri) {
        return new LoggingArenaClient(
            new OfficialArenaClient(uris(uri), new ApacheHttpUriContentReader()), 
            LoggerFactory.getLogger(LoggingArenaClient.class));
    }
    
    public ArenaUriCatalog uris(String rootUri) {
        String rootUriWithEndingSlash = rootUri.endsWith("/") ? rootUri : rootUri + "/";
        return new HardCodedOfficialUriCatalog(
            new AbsoluteUriFactory(new UncheckedUriFactory(), rootUriWithEndingSlash));
    }

}
