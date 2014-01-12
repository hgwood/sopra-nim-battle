package fr.notfound.cli;

import static java.util.Arrays.copyOfRange;

import java.lang.reflect.Method;

import fr.notfound.BattleDayParameters;
import fr.notfound.CompositionRoot;
import fr.notfound.http.TextArena;

/**
 * Command-line interface that prints the result of calling methods of the
 * {@link TextArena} interface on the official battle arena. This interface was
 * designed to be used by humans to quickly query and test the official arena
 * without having to write long and complicated URLs.
 * <p>
 * Usage: method-name [arg0 [arg1 [arg2...
 * <p>
 * method-name: name of a method of the {@link TextArena} interface<br/>
 * argX: arguments to pass to the method
 * <p>
 * Example: status theGame theTeam
 */
public class ReflectCliToOfficialArena {
    
    public static final String Root = BattleDayParameters.ArenaUri.toString();
    public static final int ArgMethod = 0;
    
    public static void main(String[] args) throws Exception {
        TextArena arena = new CompositionRoot().arenaClient(Root);
        for (Method method : TextArena.class.getMethods()) {
            if (method.getName().equals(args[ArgMethod])) {
                String response = (String)method.invoke(arena, (Object[])copyOfRange(args, 1, args.length));
                System.out.println(response);
            }
        }
    }

}
