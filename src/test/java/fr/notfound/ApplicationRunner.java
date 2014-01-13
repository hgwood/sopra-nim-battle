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
    
    /**
     * Intended for real practice games.
     */
    public void playPractice(URI arenaUri, String teamName, String password, int aiLevel) {
        runPractice(arenaUri.toString(), teamName, password, aiLevel, 50, 1, 50);
    }
    
    /**
     * Intended for real versus games.
     */
    public void playVersus(URI arenaUri, String teamName, String password) {
        runVersus(arenaUri.toString(), teamName, password, 50, 50, 50);
    }
    
    /**
     * Intended for fake versus games on a local server.
     */
    public void playVersus(ArenaServer arena) {
        runVersus(arena.uri.toString(), arena.teamName, arena.password, 0, 5, 0);
    }
    
    private void runVersus(String arenaUri, String teamName, String password, 
        int retryDelayWhenNoGameAvailable, int numberOfAttemptsToRetrieveGame, int retryDelayWhenNotYourTurn) {
        output = runner.run(arenaUri, teamName, password, 
            String.valueOf(retryDelayWhenNoGameAvailable),
            String.valueOf(numberOfAttemptsToRetrieveGame),
            String.valueOf(retryDelayWhenNotYourTurn));
    }
    
    private void runPractice(String arenaUri, String teamName, String password, int aiLevel,
        int retryDelayWhenNoGameAvailable, int numberOfAttemptsToRetrieveGame, int retryDelayWhenNotYourTurn) {
        output = runner.run(arenaUri, teamName, password, 
            String.valueOf(aiLevel),
            String.valueOf(retryDelayWhenNoGameAvailable),
            String.valueOf(numberOfAttemptsToRetrieveGame),
            String.valueOf(retryDelayWhenNotYourTurn));
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

}
