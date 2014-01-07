package fr.notfound;

import static java.lang.Integer.parseInt;

import java.io.*;

import fr.notfound.domain.*;
import fr.notfound.recording.GameRecorder;
import fr.notfound.recording.Decision;

public class Main {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(final String[] args) {
        Arena arena = new CompositionRoot().arena(args[argArenaUrl]);
        final Team team = arena.join(args[argTeamName], args[argPassword]);
        final GameRecorder game = new GameRecorder(team.currentVersus());
        //game.play(new Move("0", "0"));
        //game.play(new Move("42", "42"));
        
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
            .handle("/play/gameId/x/y", new Runnable() {
                @Override public void run() {
                    game.play(new Move("x", "y"));
                }
            })
            .handle("/play/gameId/z/t", new Runnable() {
                @Override public void run() {
                    game.play(new Move("z", "t"));
                }
            })
            .start();
    }

    public static void stop() {
        server.stop();
    }

}
