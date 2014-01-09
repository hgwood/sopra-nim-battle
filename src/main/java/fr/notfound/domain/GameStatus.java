package fr.notfound.domain;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

public enum GameStatus {
    YourTurn,
    NotYourTurn,
    Won,
    Lost,
    Canceled;
    
    public String wireValue() {
        return wireValues.get(this);
    }
    
    private static final BiMap<GameStatus, String> wireValues = ImmutableBiMap.of(
            YourTurn, "OUI",
            NotYourTurn, "NON",
            Won, "GAGNE",
            Lost, "PERDU",
            Canceled, "ANNULE");

    public static GameStatus parse(String status) {
        GameStatus memoryValue = wireValues.inverse().get(status);
        if (memoryValue == null) throw new IllegalArgumentException("illegal game status: " + status);
        return memoryValue;
    }

}
