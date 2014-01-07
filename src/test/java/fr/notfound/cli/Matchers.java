package fr.notfound.cli;

import org.hamcrest.*;

public class Matchers {
    
    /**
     * Matcher that simply delegates to the specified matcher.
     * Can be used to enhance readability.
     */
    public static <T> Matcher<T> that(final Matcher<T> delegate) {
        return new TypeSafeMatcher<T>() {
            @Override public void describeTo(Description description) {
            }

            @Override protected boolean matchesSafely(T item) {
                return delegate.matches(item);
            }
        };
    }
    
    public static Matcher<String> anId() {
        return new TypeSafeMatcher<String>() {
            @Override public void describeTo(Description description) {
                description
                    .appendText("a String that matches an ID pattern");
            }

            @Override protected boolean matchesSafely(String item) {
                return item.matches("[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}");
            }
        };
    }
    
    public static Matcher<String> aBoard() {
        return new TypeSafeMatcher<String>() {
            @Override public void describeTo(Description description) {
                description
                    .appendText("a String that matches a board");
            }

            @Override protected boolean matchesSafely(String item) {
                return item.matches("NA");
            }
        };
    }
    
    public static Matcher<String> aStatus() {
        return new TypeSafeMatcher<String>() {
            @Override public void describeTo(Description description) {
                description
                    .appendText("a String that matches a status");
            }

            @Override protected boolean matchesSafely(String item) {
                return item.matches("OUI|NON|GAGNE|PERDU|ANNULE");
            }
        };
    }
    
    public static Matcher<String> aMove() {
        return new TypeSafeMatcher<String>() {
            @Override public void describeTo(Description description) {
                description
                    .appendText("a String that matches a move");
            }

            @Override protected boolean matchesSafely(String item) {
                return item.matches("coordX,coordY");
            }
        };
    }
    
    public static Matcher<String> aPlayResponse() {
        return new TypeSafeMatcher<String>() {
            @Override public void describeTo(Description description) {
                description
                    .appendText("a String that matches a move");
            }

            @Override protected boolean matchesSafely(String item) {
                return item.matches("OK|KO|GAGNE|PTT");
            }
        };
    }

}
