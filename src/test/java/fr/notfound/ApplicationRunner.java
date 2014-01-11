package fr.notfound;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import fr.notfound.cli.CliRunner;
import fr.notfound.domain.GameStatus;
import fr.notfound.fakearena.ArenaServer;

public class ApplicationRunner {
    
    private final CliRunner runner;
    private List<String> output;

    public ApplicationRunner(CliRunner runner) {
        this.runner = runner;
    }
    
    public void join(ArenaServer arena) {
        join(arena, 1);
    }

    public void join(ArenaServer arena, int numberOfGamesToPlay) {
        output = runner.run(arena.uri.toString(), arena.teamName, arena.password, String.valueOf(numberOfGamesToPlay));
    }

    public void showsGameWasLost() {
        assertThat(output, hasItem(containsString(GameStatus.Lost.toString())));
    }

    public void showsGameWasWon() {
        assertThat(output, hasItem(containsString(GameStatus.Won.toString())));
    }
    
    public void showsGameWasCanceled() {
        assertThat(output, hasItem(containsString(GameStatus.Canceled.toString())));
    }

    public void showsResult(GameStatus... results) {
        List<String> resultsAsWireValues = newArrayList();
        for (GameStatus result : results)
            resultsAsWireValues.add(result.toString());
        assertThat(output, hasItems((String[])resultsAsWireValues.toArray(new String[0])));
    }

}
