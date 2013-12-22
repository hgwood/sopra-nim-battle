package fr.notfound.cli;

import static java.util.Arrays.copyOfRange;

import java.lang.reflect.Method;

import fr.notfound.Arena;
import fr.notfound.OfficialArena;
import fr.notfound.rest.JavaNetUrlRestGetClient;
import fr.notfound.url.*;

/**
 * Command-line interface that prints the result of calling methods of the
 * {@link Arena} interface on the official battle arena. This interface was
 * designed to be used by humans to quickly query and test the official arena
 * without having to write long and complicated URLs.
 * <p>
 * Usage: method-name [arg0 [arg1 [arg2...
 * <p>
 * method-name: name of a method of the {@link Arena} interface<br/>
 * argX: arguments to pass to the method
 * <p>
 * Example: status theGame theTeam
 */
public class ReflectCliToOfficialArena {
    
    public static final String root = "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test/";
    public static final int ArgMethod = 0;
    
    public static void main(String[] args) throws Exception {
        Arena arena = new OfficialArena(
            new HardCodedStringFormatUrlCatalog(
                new RootedUrlProvider(
                    new UncheckedUrlProvider(), 
                    root)), 
            new JavaNetUrlRestGetClient());
        for (Method method : Arena.class.getMethods()) {
            if (method.getName().equals(args[ArgMethod])) {
                String response = (String)method.invoke(arena, (Object[])copyOfRange(args, 1, args.length));
                System.out.println(response);
            }
        }
    }

}