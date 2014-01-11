package fr.notfound.fakearena;

import static java.util.Arrays.asList;
import fr.notfound.domain.GameStatus;

public class ArenaServerBuilder {
    
    public static ArenaServerBuilder onPort(int port) {
        return new ArenaServerBuilder(port);
    }
    
    private final int port;
    private String teamName = "defaultTestTeamName";
    private String password = "defaultTestPassword";
    private String teamId = "defaultTestTeamId";
    private String versusId = "defaultTestVersusId";
    private GameStatus[] statusSequence = new GameStatus[] {};
    
    private ArenaServerBuilder(int port) {
        this.port = port;
    }
    
    public ArenaServerBuilder acceptTeam(String teamName, String password, String teamId) {
        this.teamName = teamName;
        this.password = password;
        this.teamId = teamId;
        return this;
    }
    
    public ArenaServerBuilder withStatusSequence(GameStatus... statusSequence) {
        this.statusSequence = statusSequence;
        return this;
    }
    
    public ArenaServer start() {
        FakeArena arena = new FakeArena(teamId, versusId, asList(statusSequence).iterator());
        return ArenaServer.start(port, teamName, password, teamId, versusId, arena);
    }

}
