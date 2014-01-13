package fr.notfound;

import static com.google.common.collect.Lists.newArrayList;
import static fr.notfound.BattleDayParameters.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assume.assumeThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.notfound.cli.CliRunner;

/**
 * Runs the real battle!
 * <strong>Requires the official battle-day arena to be reachable!</strong>
 */
@RunWith(Parameterized.class)
public class Tournament {

    @Parameters(name = "Opponent {0}") 
    public static Collection<Object[]> data() {
        // The data is a list of arrays each containing only one integer.
        // It's just there to force a parameterized test that runs as
        // many times as there are teams in the tournament.
        Collection<Object[]> data = newArrayList();
        for (int i = 0; i < BattleDayParameters.NumberOfOpponentTeams; i++) {
            Object[] datum = new Object[1];
            datum[0] = i + 1;
            data.add(datum);
        }
        return data;
    }

    private final ApplicationRunner application =
        new ApplicationRunner(new CliRunner(VersusMain.class));

    public Tournament(int opponent) {
        // nothing to do with the data, see comment in data()
    }

    @Test public void wins() {
        assumeThat(ArenaUri, is(notNullValue()));
        application.playVersus(ArenaUri, TeamName, Password);
        application.showsGameWasWon();
    }

}
