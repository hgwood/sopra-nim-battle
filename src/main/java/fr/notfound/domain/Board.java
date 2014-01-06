package fr.notfound.domain;

import fr.notfound.meta.ValueType;

@ValueType
public class Board {
    
    private final String representation;

    public Board(String board) {
        this.representation = board;
    }
    
    @Override public String toString() {
        return "Board " + representation;
    }

    public static Board parse(String board) {
        return new Board(board);
    }

}
