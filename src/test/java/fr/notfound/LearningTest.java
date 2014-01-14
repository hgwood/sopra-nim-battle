package fr.notfound;
import static fr.notfound.BattleDayParameters.*;

import org.junit.Test;

import fr.notfound.composition.CompositionRoot;
import fr.notfound.domain.*;

public class LearningTest {

    private final Arena arena = new CompositionRoot().arena(ArenaUri.toString());
    
    @Test public void test() {
        Team team = arena.join(TeamName, Password);
        Game game = team.newPractice(AiLevel.of(0));
        Board board = game.board();
        System.out.println(board);
        System.out.println(board.representation.length());
        MoveResult result = game.play(new Move(board.firstAvailable(), 1));
        System.out.println(result);
        board = game.board();
        System.out.println(board);
        System.out.println(board.representation.length());
    }

}
