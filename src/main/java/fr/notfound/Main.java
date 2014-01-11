package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.*;

public class Main {

    public static void main(String[] args) {
        new Main().main(args[0], args[1], args[2], parseInt(args[3]));
    }
    
    public void main(String arenaUri, String teamName, String password, int numberOfGamesToPlay) {
        CompositionRoot compositionRoot = new CompositionRoot();
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        GameRunner runner = compositionRoot.gameRunner();
        for (int i = 0; i < numberOfGamesToPlay; i++) {
            Game game = team.currentVersus();
            System.out.println(runner.run(game).toString());
        }
    }

}
