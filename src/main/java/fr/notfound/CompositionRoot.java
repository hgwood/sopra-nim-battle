package fr.notfound;

import org.slf4j.LoggerFactory;

import fr.notfound.adapters.HttpArena;
import fr.notfound.domain.Arena;
import fr.notfound.rest.*;
import fr.notfound.rest.uri.*;

/**
 * @see <a href="http://blog.ploeh.dk/2011/07/28/CompositionRoot/">The Definition of Composition Root</a>
 */
public class CompositionRoot {
    
    public Arena arena(String uri) {
        return new HttpArena(arenaClient(uri));
    }
    
    public PlainTextArenaClient arenaClient(String uri) {
        String uriWithEndingSlash = uri.endsWith("/") ? uri : uri + "/";
        return new LoggingArenaClient(
            new OfficialArenaClient(
                new HardCodedOfficialUriCatalog(
                    new AbsoluteUriFactory(new UncheckedUriFactory(), uriWithEndingSlash)), 
                new ApacheHttpUriContentReader()), 
            LoggerFactory.getLogger(LoggingArenaClient.class));
    }

}
