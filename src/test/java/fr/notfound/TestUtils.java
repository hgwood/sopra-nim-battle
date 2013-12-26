package fr.notfound;

public class TestUtils {
    
    public static String localhost(int port) {
        return "http://localhost:" + port;
    }

    public static String responseFrom(String url) {
        return new RestClient().get(url);
    }

}
