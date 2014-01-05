package fr.notfound.meta;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Target;

/**
 * Expresses the programmer's intent that the annotated type produces value 
 * objects, even if their implementation is incorrect or incomplete.
 * <p>
 * Value objects are immutable and implement equals and hashCode so that there 
 * are identified by their value (rather than their identity).
 */
@Target(TYPE)
public @interface ValueType {

}
