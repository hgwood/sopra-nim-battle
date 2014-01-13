package fr.notfound;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.List;

import fr.notfound.cli.CliRunner;
import fr.notfound.domain.GameStatus;
import fr.notfound.fakearena.ArenaServer;

public class ApplicationRunner {
    
    private final CliRunner runner;
    private List<String> output = newArrayList();

    public ApplicationRunner(CliRunner runner) {
        this.runner = runner;
    }
    
    public void join(URI arenaUri, String teamName, String password, int nextGameRetryDelay) {
        output = runner.run(arenaUri.toString(), teamName, password, String.valueOf(nextGameRetryDelay));
    }
    
    public void join(ArenaServer arena) {
        join(arena.uri, arena.teamName, arena.password, 0);
    }
    
    /**
     * Plays a real practice!
     */
    public void playPractice(URI arenaUri, String teamName, String password, int aiLevel) {
        output = runner.run(arenaUri.toString(), teamName, password, String.valueOf(aiLevel));
    }
    
    /**
     * Plays a real versus!
     */
    public void playVersus(URI arenaUri, String teamName, String password) {
        join(arenaUri, teamName, password, 200);
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
