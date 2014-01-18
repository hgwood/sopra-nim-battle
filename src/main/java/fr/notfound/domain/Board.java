package fr.notfound.domain;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

import fr.notfound.meta.ValueType;

@ValueType
public class Board {
    
    public final String representation;

    public Board(String board) {
        this.representation = board;
    }
    
    public Board apply(Move move) {
        return new Board(format("%s + %s", toString(), move.toString()));
    }
    
    public int firstAvailable() {
        return representation.indexOf("1");
    }
    
    public int length() {
        return representation.length();
    }
    
    public List<Move> groups() {
        //Iterable<String> groupsOfOnes = Splitter.on("0").omitEmptyStrings().split(representation);
        List<Move> groups = newArrayList();
        int currentGroupIndex = 0;
        int currentGroupSize = 0;
        boolean capturing = false;
        for (int i = 0; i < representation.length(); i++) {
            if (representation.charAt(i) == '1') {
                if (capturing) {
                    currentGroupSize += 1;
                } else {
                    capturing = true;
                    currentGroupIndex = i;
                    currentGroupSize = 1;
                }
                if (i == representation.length() - 1) {
                    groups.add(new Move(currentGroupIndex, currentGroupSize));
                }
            } else {
                if (capturing) {
                    capturing = false;
                    groups.add(new Move(currentGroupIndex, currentGroupSize));
                }
            }
            
        }
        
        return groups;
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
