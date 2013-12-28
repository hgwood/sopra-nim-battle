package fr.notfound.rest.uri;

import java.net.URI;
import java.net.URISyntaxException;

public class UncheckedUriFactory implements UriFactory {

    @Override public URI get(String path) {
        try {
            return new URI(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
