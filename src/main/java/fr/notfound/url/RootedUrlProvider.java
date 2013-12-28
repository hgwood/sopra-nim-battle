package fr.notfound.url;

import java.net.URI;

public class RootedUrlProvider implements UriProvider {
    
    public final UriProvider delegate;
    public final String root;

    public RootedUrlProvider(UriProvider delegate, String root) {
        this.delegate = delegate;
        this.root = root;
    }

    @Override public URI get(String path) {
        return delegate.get(root + path);
    }

}
