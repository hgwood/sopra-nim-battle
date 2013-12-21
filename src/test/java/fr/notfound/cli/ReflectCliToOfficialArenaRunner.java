package fr.notfound.cli;

import static java.util.Arrays.asList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.google.common.base.Charsets;

import fr.notfound.cli.ReflectCliToOfficialArena;

public class ReflectCliToOfficialArenaRunner {

    public List<String> run(String... args) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(out));
            ReflectCliToOfficialArena.main(args);
            return asList(out.toString(Charsets.UTF_8.name()).split("\\r\\n"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
