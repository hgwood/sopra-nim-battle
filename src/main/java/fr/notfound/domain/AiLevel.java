package fr.notfound.domain;

import fr.notfound.meta.ValueObject;

@ValueObject
public class AiLevel {
    
    public final String value;

    public AiLevel(String value) {
        this.value = value;
    }

}
