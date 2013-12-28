package fr.notfound;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

import com.google.common.collect.ImmutableMap;

public class Main {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(String[] args) {
        Properties urls = uris();
        
        TemplateRenderer templateRenderer = new TemplateRenderer(new VelocityEngine());
        String teamIdUrl = templateRenderer.render(urls.getProperty("teamId"), ImmutableMap.of(
            "teamName", args[argTeamName], 
            "password", args[argPassword]));
        
        UriContentReader client = new UriContentReader();
        String teamId = client.read(uri(args[argArenaUrl] + "/" + teamIdUrl));
        server = Jetty.onPort(parseInt(args[argMonitoringPort])).handle("/", teamId).start();
    }
    
    private static Properties uris() {
        Properties urls = new Properties();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("urls.properties")) {
            urls.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return urls;
    }
    
    private static URI uri(String s) {
        try {
            return new URI(s);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stop() {
        server.stop();
    }

}
