package fr.notfound;

import java.net.URI;

public class BattleDayParameters {
    
    public static final URI TestArenaUri = URI.create(
        "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test/");
    
    // FIXME: Enable and correct on battle day
    public static final boolean BattleMode = false;
    public static final URI ArenaUri = TestArenaUri;
    public static final String TeamName = "404";
    public static final String Password = "password";
    public static final int NumberOfOpponentTeams = 4;

}
