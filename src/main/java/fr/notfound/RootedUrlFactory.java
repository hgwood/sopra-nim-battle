package fr.notfound;

import java.net.URL;

public class RootedUrlFactory implements UrlFactory {
    
    public final UrlFactory delegate;
    public final String root;

    public RootedUrlFactory(UrlFactory delegate, String root) {
        this.delegate = delegate;
        this.root = root;
    }

    @Override public URL get(String path) {
        return delegate.get(root + path);
    }

}
