package fr.notfound.rest.uri;

import java.net.URI;

public class RootedUriFactory implements UriFactory {
    
    public final UriFactory delegate;
    public final String root;

    public RootedUriFactory(UriFactory delegate, String root) {
        this.delegate = delegate;
        this.root = root;
    }

    @Override public URI get(String path) {
        return delegate.get(root + path);
    }

}
