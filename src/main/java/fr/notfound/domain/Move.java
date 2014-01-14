package fr.notfound.domain;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

import java.util.Objects;

import fr.notfound.meta.ValueType;

@ValueType
public class Move {
    
    public final int x;
    public final int y;
    
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Move other = (Move)obj;
        return x == other.x && y == other.y;
    }
    
    @Override public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override public String toString() {
        return format("{Move: %s, %s}", x, y);
    }

    public static Move parse(String coordinates) {
        String[] coordinatesArray = coordinates.split(",");
        return new Move(parseInt(coordinatesArray[0]), parseInt(coordinatesArray[1]));
    }

}
