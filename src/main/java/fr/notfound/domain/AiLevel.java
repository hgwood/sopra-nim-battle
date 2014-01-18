package fr.notfound.domain;

import fr.notfound.meta.ValueType;

@ValueType
public class AiLevel {
    
    public static AiLevel of(int value) {
        if (value < 0 || value > 6) {
            throw new IllegalArgumentException("AI level must be in [0,6]");
        }
        return new AiLevel(value);
    }
    
    public final int value;

    private AiLevel(int value) {
        this.value = value;
    }
    
    @Override public String toString() {
        return String.format("{AiLevel: %s}", value);
    }

}
