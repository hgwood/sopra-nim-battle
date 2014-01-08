package fr.notfound.cli;

import static java.util.Arrays.asList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.google.common.base.Charsets;

/**
 * Runs the main method's of a specified class.
 */
public class CliRunner {
    
    private final Class<?> mainClass;
    
    public CliRunner(Class<?> mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * 
     * @param args will be forwarded to the main method
     * @return the standard output as list of lines
     * @throws {@link NoSuchMethodException} if the mainClass has no main method
     */
    public List<String> run(String... args) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            mainClass.getDeclaredMethod("main", String[].class).invoke(null, (Object)args);
            return asList(out.toString(Charsets.UTF_8.name()).split("\\r\\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
