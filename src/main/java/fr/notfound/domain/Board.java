package fr.notfound.domain;

import static java.lang.String.format;

import java.util.Objects;

import fr.notfound.meta.ValueType;

@ValueType
public class Board {
    
    private final String representation;

    public Board(String board) {
        this.representation = board;
    }
    
    public Board apply(Move move) {
        return new Board(format("%s + %s", toString(), move.toString()));
    }
    
    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Board other = (Board)obj;
        return Objects.equals(representation, other.representation);
    }
    
    @Override public int hashCode() {
        return Objects.hashCode(representation);
    }
    
    @Override public String toString() {
        return "Board " + representation;
    }

    public static Board parse(String board) {
        return new Board(board);
    }

}
