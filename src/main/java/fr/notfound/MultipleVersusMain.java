package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.composition.CompositionRoot;
import fr.notfound.domain.*;

public class MultipleVersusMain {
    
    private static CompositionRoot compositionRoot;

    public static void main(String[] args) {
        compositionRoot = new CompositionRoot(parseInt(args[3]));
        new MultipleVersusMain(args[0])
            .play(args[1], args[2]);
    }
    
    private final String arenaUri;
    
    public MultipleVersusMain(String arenaUri) {
        this.arenaUri = arenaUri;
    }
    
    public void play(String teamName, String password) {
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        Player runner = compositionRoot.versusPlayer();
        Game game = team.currentVersus();
        System.out.println(runner.playToCompletion(game).toString());
        
    }

}
