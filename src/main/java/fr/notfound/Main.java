package fr.notfound;

import static java.lang.Integer.parseInt;

import org.slf4j.LoggerFactory;

import fr.notfound.adapters.HttpArena;
import fr.notfound.domain.Arena;
import fr.notfound.domain.Team;
import fr.notfound.rest.*;
import fr.notfound.rest.uri.*;

public class Main {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(String[] args) {
        Arena arena = new HttpArena(
            new LoggingArenaClient(
                new OfficialArenaClient(
                    new HardCodedOfficialUriCatalog(
                        new AbsoluteUriFactory(new UncheckedUriFactory(), args[argArenaUrl] + "/")), 
                    new ApacheHttpUriContentReader()), 
                LoggerFactory.getLogger(LoggingArenaClient.class)));
        
        Team team = arena.join(args[argTeamName], args[argPassword]);
        server = Jetty.onPort(parseInt(args[argMonitoringPort])).handle("/", team.toString()).start();
    }

    public static void stop() {
        server.stop();
    }

}
