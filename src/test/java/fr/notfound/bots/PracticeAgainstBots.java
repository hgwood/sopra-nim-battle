package fr.notfound.bots;

import static fr.notfound.BattleDayParameters.*;
import static java.util.Arrays.asList;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.notfound.ApplicationRunner;
import fr.notfound.cli.CliRunner;

@RunWith(Parameterized.class)
public class PracticeAgainstBots {

    @Parameters(name = "Level {0}") 
    public static Collection<Object[]> data() {
        return asList(new Object[][] { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 } });
    }

    private final int aiLevel;
    private final ApplicationRunner application =
        new ApplicationRunner(new CliRunner(Main.class));

    public PracticeAgainstBots(int aiLevel) {
        this.aiLevel = aiLevel;
    }

    @Test public void wins() {
        application.playPractice(ArenaUri, TeamName, Password, aiLevel);
        application.showsGameWasWon();
    }

}
