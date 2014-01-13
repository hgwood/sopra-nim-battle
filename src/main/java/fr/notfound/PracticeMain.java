package fr.notfound;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import fr.notfound.composition.CompositionRoot;
import fr.notfound.domain.*;

public class PracticeMain {
    
    public static void main(String[] args) {
        main(args[0], args[1], args[2], parseInt(args[3]));
    }
    
    public static void main(String arenaUri, String teamName, String password, int aiLevel) {
        CompositionRoot compositionRoot = new CompositionRoot();
        Player player = compositionRoot.practicePlayer();
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        Game game = team.newPractice(AiLevel.of(aiLevel));
        GameStatus result = player.playToCompletion(game);
        String message = format("%s against bot level %s", result.toString(), aiLevel);
        System.out.println(message);
    }

}
