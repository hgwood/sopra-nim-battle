package fr.notfound;

import java.net.URI;

import fr.notfound.http.ApacheHttpUriContentReader;

public class TestUtils {

    public static URI localhost(int port) {
        return URI.create("http://localhost:" + port);
    }

    public static String contentOf(URI url) {
        return new ApacheHttpUriContentReader().read(url);
    }

}
