package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.*;

public class Main {

    public static void main(String[] args) {
        Arena arena = new CompositionRoot().arena(args[0]);
        Team team = arena.join(args[1], args[2]);
        for (int i = 0; i < parseInt(args[3]); i++) {
            Game game = team.currentVersus();
            GameStatus status = game.status();
            while (status != GameStatus.Won && status != GameStatus.Lost && status != GameStatus.Canceled) {
                if (status == GameStatus.YourTurn) {
                    game.play(new Move(0, 0));
                }
                status = game.status();
            }
            System.out.println(status.toString());
        }
    }

}
