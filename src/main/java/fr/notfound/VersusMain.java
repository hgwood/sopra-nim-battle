package fr.notfound;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import fr.notfound.composition.CompositionRoot;
import fr.notfound.domain.*;

public class VersusMain {

    public static void main(String[] args) {
        main(args[0], args[1], args[2], 
            parseInt(args[3]), 
            parseInt(args[4]), 
            parseInt(args[5]));
    }
    
    public static void main(
        String arenaUri, String teamName, String password, 
        int retryDelayWhenNoGameAvailable,
        int numberOfAttemptsToRetrieveGame,
        int retryDelayWhenNotYourTurn) {
        CompositionRoot compositionRoot = new CompositionRoot(
            retryDelayWhenNoGameAvailable, 
            numberOfAttemptsToRetrieveGame, 
            retryDelayWhenNotYourTurn);
        Player player = compositionRoot.versusPlayer();
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        Game game = team.currentVersus();
        GameStatus result = player.playToCompletion(game);
        String message = format("%s against %s", result.toString(), game.opponent());
        System.out.println(message);
    }

}
