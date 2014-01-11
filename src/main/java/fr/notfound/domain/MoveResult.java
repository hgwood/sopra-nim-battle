package fr.notfound.domain;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

public enum MoveResult {
    Approved,
    Rejected,
    Victory,
    NotYourTurn;

    public String wireValue() {
        return wireValues.get(this);
    }
    
    private static final BiMap<MoveResult, String> wireValues = ImmutableBiMap.of(
        Approved, "OK",
        Rejected, "KO",
        Victory, "GAGNE",
        NotYourTurn, "PTT");

    public static MoveResult parse(String moveResult) {
        MoveResult memoryValue = wireValues.inverse().get(moveResult);
        if (memoryValue == null) throw new IllegalArgumentException("illegal move result: " + moveResult);
        return memoryValue;
    }

}
