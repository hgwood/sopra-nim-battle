package fr.notfound;

import java.net.MalformedURLException;
import java.net.URL;

public class UncheckedUrlFactory implements UrlFactory {

    @Override public URL get(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
