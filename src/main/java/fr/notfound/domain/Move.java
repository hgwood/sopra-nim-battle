package fr.notfound.domain;

import static java.lang.String.format;
import fr.notfound.meta.ValueType;

@ValueType
public class Move {
    
    public final String x;
    public final String y;
    
    public Move(String x, String y) {
        this.x = x;
        this.y = y;
    }
    
    @Override public String toString() {
        return format("{Move: %s, %s}", x, y);
    }

    public static Move parse(String coordinates) {
        String[] coordinatesArray = coordinates.split(",");
        return new Move(coordinatesArray[0], coordinatesArray[1]);
    }

}
