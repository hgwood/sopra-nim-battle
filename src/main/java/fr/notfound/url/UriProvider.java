package fr.notfound.url;

import java.net.URI;

public interface UriProvider {
    
    URI get(String path);

}
