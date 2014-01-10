package fr.notfound;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.List;

import fr.notfound.cli.CliRunner;
import fr.notfound.domain.GameStatus;

public class ApplicationRunner {
    
    private final CliRunner runner;
    private List<String> output;

    public ApplicationRunner(CliRunner runner) {
        this.runner = runner;
    }

    public void join(ArenaConfigurator arena) {
        output = runner.run(arena.uri.toString(), arena.teamName, arena.password);
    }

    public void showsGameWasLost() {
        assertThat(output, hasItem(containsString(GameStatus.Lost.toString())));
    }

    public void showsGameWasWon() {
        assertThat(output, hasItem(containsString(GameStatus.Won.toString())));
    }

}
