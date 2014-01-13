package fr.notfound.monitoring;

import static java.lang.Integer.parseInt;

import java.io.PrintWriter;
import java.io.Writer;

import fr.notfound.composition.CompositionRoot;
import fr.notfound.domain.*;
import fr.notfound.http.server.Jetty;
import fr.notfound.recording.Decision;
import fr.notfound.recording.GameRecorder;

public class MainWithMonitoring {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(final String[] args) {
        Arena arena = new CompositionRoot().arena(args[argArenaUrl]);
        final Team team = arena.join(args[argTeamName], args[argPassword]);
        final GameRecorder game = new GameRecorder(team.currentVersus());
        
        server = Jetty.onPort(parseInt(args[argMonitoringPort]))
            .handle("/", new Jetty.WriterHandler() {
                @Override public void handle(Writer responseWriter) {
                    PrintWriter responsePrintWriter = new PrintWriter(responseWriter);
                    responsePrintWriter.println(args[argTeamName]);
                    responsePrintWriter.println(args[argPassword]);
                    responsePrintWriter.println(team.toString());
                    responsePrintWriter.println(game.toString());
                    for (Decision move : game.getRecordedMoves()) {
                        responsePrintWriter.println(move);
                    }
                }
            })
            .handle("/play/gameId/0/1", new Runnable() { // TODO magical constants tied to FakeSingleGameArena
                @Override public void run() {
                    game.play(new Move(0, 1)); // TODO magical constants!
                }
            })
            .handle("/play/gameId/2/3", new Runnable() { // TODO magical constants!
                @Override public void run() {
                    game.play(new Move(2, 3)); // TODO magical constants!
                }
            })
            .start();
    }

    public static void stop() {
        server.stop();
    }

}
