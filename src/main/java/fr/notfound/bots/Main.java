package fr.notfound.bots;

import static java.lang.Integer.parseInt;
import fr.notfound.CompositionRoot;
import fr.notfound.domain.*;

public class Main {
    
    public static void main(String[] args) {
        new Main().main(args[0], args[1], args[2], parseInt(args[3]));
    }
    
    public void main(String arenaUri, String teamName, String password, int aiLevel) {
        CompositionRoot compositionRoot = new CompositionRoot();
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        Game game = team.newPractice(AiLevel.of(aiLevel));
        GameRunner runner = compositionRoot.gameRunner();
        GameStatus gameResult = runner.run(game);
        System.out.println(gameResult.toString());
    }

}
