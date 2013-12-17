package fr.notfound;

import static java.util.Arrays.asList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.google.common.base.Charsets;

public class ApplicationRunner {

    public List<String> run(String... args) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            Main.main(args);
            return asList(out.toString(Charsets.UTF_8.name()).split("\\r\\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
