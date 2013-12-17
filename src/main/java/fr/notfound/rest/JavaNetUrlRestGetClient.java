package fr.notfound.rest;

import static java.lang.String.format;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class JavaNetUrlRestGetClient implements RestGetClient {

    @Override public String get(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != 200)
                throw new RuntimeException(format("HTTP response code of %s was %s", url, connection.getResponseCode()));
            try (Reader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), Charsets.UTF_8))) {
                return CharStreams.toString(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
