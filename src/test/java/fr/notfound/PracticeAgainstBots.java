package fr.notfound;

import static fr.notfound.BattleDayParameters.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assume.assumeThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.notfound.cli.CliRunner;

/**
 * Runs a practice game against each bot on the battle-day arena.
 * <strong>Requires the official battle-day arena to be reachable!</strong>
 */
@RunWith(Parameterized.class)
public class PracticeAgainstBots {

    @Parameters(name = "Level {0}") 
    public static Collection<Object[]> data() {
        return asList(new Object[][] { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 } });
    }

    private final int aiLevel;
    private final ApplicationRunner application =
        new ApplicationRunner(new CliRunner(SinglePracticeMain.class));

    public PracticeAgainstBots(int aiLevel) {
        this.aiLevel = aiLevel;
    }

    @Test public void wins() {
        assumeThat(BattleMode, is(true));
        application.playPractice(ArenaUri, TeamName, Password, aiLevel);
        application.showsGameWasWon();
    }

}
