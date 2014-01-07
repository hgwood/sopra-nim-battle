package fr.notfound.cli;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static fr.notfound.cli.Matchers.*;

import org.junit.Test;

/**
 * Call every {@link PlainTextArenaClient} method on the official arena through the
 * {@link ReflectCliToOfficialArena} and check the content of the response is of
 * the expected form. <strong>Requires the official arena to be reachable!</strong>
 */
public class OfficialArenaTestApiTest {

    private final ReflectCliToOfficialArenaRunner application = new ReflectCliToOfficialArenaRunner();

    @Test public void pingPong() throws Exception {
        assertThat(application.run("ping"), hasItem("pong"));
    }

    @Test public void teamId() throws Exception {
        assertThat(application.run("teamId", "0", "0"), hasItem(that(is(anId()))));
    }

    @Test public void currentVersus() throws Exception {
        assertThat(application.run("currentVersus", "0"), hasItem(that(is(anId()))));
    }

    @Test public void newPractice() throws Exception {
        assertThat(application.run("newPractice", "0", "0"), hasItem(that(is(anId()))));
    }

    @Test public void currentPractice() throws Exception {
        assertThat(application.run("currentPractice", "0"), hasItem(that(is(anId()))));
    }

    @Test public void status() throws Exception {
        assertThat(application.run("status", "0", "0"), hasItem(that(is(aStatus()))));
    }

    @Test public void board() throws Exception {
        assertThat(application.run("board", "0"), hasItem(that(is(aBoard()))));
    }

    @Test public void lastMove() throws Exception {
        assertThat(application.run("lastMove", "0"), hasItem(that(is(aMove()))));
    }

    @Test public void play() throws Exception {
        assertThat(application.run("play", "0", "0", "0", "0"), hasItem(that(is(aPlayResponse()))));
    }

}
