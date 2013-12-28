package fr.notfound.rest;

import static java.lang.String.format;
import static java.net.HttpURLConnection.HTTP_OK;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class JavaNetUrlRestGetClient implements RestGetClient {

    @Override public String get(URI uri) {
        try {
            HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != HTTP_OK)
                throw new RuntimeException(format("HTTP response code of %s was %s", uri, connection.getResponseCode()));
            try (Reader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), Charsets.UTF_8))) {
                return CharStreams.toString(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
