package fr.notfound.domain;

import static java.lang.String.format;
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
    
    @Override public String toString() {
        return "Board " + representation;
    }

    public static Board parse(String board) {
        return new Board(board);
    }

}
