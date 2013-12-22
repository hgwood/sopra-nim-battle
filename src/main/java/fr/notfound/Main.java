package fr.notfound;

import static java.lang.Integer.parseInt;

public class Main {

    private static final int argArenaUrl = 0;
    private static final int argTeamName = 1;
    private static final int argPassword = 2;
    private static final int argMonitoringPort = 3;

    private static Jetty server;

    public static void main(String[] args) {
        RestClient client = new RestClient();
        String teamId = client.get(
            args[argArenaUrl] + "/player/getIdEquipe/" + args[argTeamName] + "/" + args[argPassword]);
        server = Jetty.onPort(parseInt(args[argMonitoringPort])).handle("/", teamId).start();
    }

    public static void stop() {
        server.stop();
    }

}
