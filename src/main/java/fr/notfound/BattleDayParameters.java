package fr.notfound;

import java.net.URI;

public class BattleDayParameters {
    
    public static final URI TestArenaUri = URI.create(
        "http://ec2-54-200-12-98.us-west-2.compute.amazonaws.com/csnbattlearena/webservices/test/");
    
    // FIXME: Enable and correct on battle day
    public static final URI ArenaUri = URI.create("http://192.168.2.2:8080/csnbattlearena/webservices/battle");
    public static final String TeamName = "404";
    public static final String Password = "";
    public static final int NumberOfOpponentTeams = 5;
    
    public static final int RetryDelay = 100;

}
