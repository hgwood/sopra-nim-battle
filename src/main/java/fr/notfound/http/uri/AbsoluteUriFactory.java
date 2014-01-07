package fr.notfound.http.uri;

import java.net.URI;

public class AbsoluteUriFactory implements UriFactory {
    
    public final UriFactory delegate;
    public final String root;

    public AbsoluteUriFactory(UriFactory delegate, String root) {
        this.delegate = delegate;
        this.root = root;
    }

    @Override public URI get(String relativeUri) {
        return delegate.get(root + relativeUri);
    }

}
