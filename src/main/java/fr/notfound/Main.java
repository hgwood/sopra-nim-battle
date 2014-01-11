package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.*;

public class Main {

    public static void main(String[] args) {
        new Main().main(args[0], args[1], args[2], parseInt(args[3]));
    }
    
    public void main(String arenaUri, String teamName, String password, int numberOfGamesToPlay) {
        Strategy strategy = new CompositionRoot().strategy();
        Arena arena = new CompositionRoot().arena(arenaUri);
        Team team = arena.join(teamName, password);
        for (int i = 0; i < numberOfGamesToPlay; i++) {
            Game game = team.currentVersus();
            System.out.println(runGame(game, strategy).toString());
        }
    }
    
    public GameStatus runGame(Game game, Strategy strategy) {
        GameStatus status = game.status();
        while (!status.isFinal) {
            if (status == GameStatus.YourTurn) {
                game.play(strategy.pickMove(game));
            }
            status = game.status();
        }
        return status;
    }

}
