package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.*;

public class Main {

    public static void main(String[] args) {
        new Main().main(args[0], args[1], args[2], parseInt(args[3]));
    }
    
    public void main(String arenaUri, String teamName, String password, int numberOfGamesToPlay) {
        Arena arena = new CompositionRoot().arena(arenaUri);
        Team team = arena.join(teamName, password);
        for (int i = 0; i < numberOfGamesToPlay; i++) {
            Game game = team.currentVersus();
            System.out.println(runGame(game).toString());
        }
    }
    
    public GameStatus runGame(Game game) {
        GameStatus status = game.status();
        while (status != GameStatus.Won && status != GameStatus.Lost && status != GameStatus.Canceled) {
            if (status == GameStatus.YourTurn) {
                game.play(new Move(0, 0));
            }
            status = game.status();
        }
        return status;
    }

}
