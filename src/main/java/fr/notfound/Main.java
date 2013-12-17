package fr.notfound;

import static java.util.Arrays.copyOfRange;

import java.lang.reflect.Method;

import fr.notfound.rest.JavaNetUrlRestGetClient;
import fr.notfound.url.*;

public class Main {
    
    public static final int ArgMethod = 0;
    
    public static void main(String[] args) throws Exception {
        String root = "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test/";
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
