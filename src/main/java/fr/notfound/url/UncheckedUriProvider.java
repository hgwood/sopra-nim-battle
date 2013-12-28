package fr.notfound.url;

import java.net.URI;
import java.net.URISyntaxException;

public class UncheckedUriProvider implements UriProvider {

    @Override public URI get(String path) {
        try {
            return new URI(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
