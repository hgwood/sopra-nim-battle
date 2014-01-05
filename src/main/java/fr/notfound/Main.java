package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;

public class Main {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(String[] args) {
        Arena arena = new CompositionRoot().arena(args[argArenaUrl]);
        Team team = arena.join(args[argTeamName], args[argPassword]);
        server = Jetty.onPort(parseInt(args[argMonitoringPort])).handle("/", team.toString()).start();
    }

    public static void stop() {
        server.stop();
    }

}
