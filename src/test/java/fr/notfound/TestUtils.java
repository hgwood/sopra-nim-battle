package fr.notfound;

public class TestUtils {

    public static String responseFrom(String url) {
        return new RestClient().get(url);
    }

}
