package fr.notfound.url;

import java.net.MalformedURLException;
import java.net.URL;

public class UncheckedUrlProvider implements UrlProvider {

    @Override public URL get(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}