package fr.notfound.domain;

import fr.notfound.meta.ValueObject;

@ValueObject
public class Move {
    
    public final String x;
    public final String y;
    
    public Move(String x, String y) {
        this.x = x;
        this.y = y;
    }

}
