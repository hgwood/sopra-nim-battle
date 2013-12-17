package fr.notfound.url;

import java.net.URL;

public class RootedUrlProvider implements UrlProvider {
    
    public final UrlProvider delegate;
    public final String root;

    public RootedUrlProvider(UrlProvider delegate, String root) {
        this.delegate = delegate;
        this.root = root;
    }

    @Override public URL get(String path) {
        return delegate.get(root + path);
    }

}
