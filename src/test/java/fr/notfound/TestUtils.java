package fr.notfound;

import java.net.URI;
import java.net.URISyntaxException;

public class TestUtils {
    
    public static URI localhost(int port) {
        try {
            return new URI("http://localhost:" + port);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String contentOf(URI url) {
        return new UriContentReader().read(url);
    }

}
