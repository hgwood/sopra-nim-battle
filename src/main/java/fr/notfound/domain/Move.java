package fr.notfound.domain;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import fr.notfound.meta.ValueType;

@ValueType
public class Move {
    
    public final int x;
    public final int y;
    
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override public String toString() {
        return format("{Move: %s, %s}", x, y);
    }

    public static Move parse(String coordinates) {
        String[] coordinatesArray = coordinates.split(",");
        return new Move(parseInt(coordinatesArray[0]), parseInt(coordinatesArray[1]));
    }

}
