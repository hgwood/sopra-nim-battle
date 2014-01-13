package fr.notfound;

import static java.lang.Integer.parseInt;
import fr.notfound.domain.*;

public class MultipleVersusMain {

    public static void main(String[] args) {
        new MultipleVersusMain(args[0], parseInt(args[4]))
            .play(args[1], args[2], parseInt(args[3]));
    }
    
    private final String arenaUri;
    private final int nextGameRetryDelay;
    
    public MultipleVersusMain(String arenaUri, int nextGameRetryDelay) {
        this.arenaUri = arenaUri;
        this.nextGameRetryDelay = nextGameRetryDelay;
    }
    
    public void play(String teamName, String password, int numberOfGamesToPlay) {
        CompositionRoot compositionRoot = new CompositionRoot();
        Arena arena = compositionRoot.arena(arenaUri);
        Team team = arena.join(teamName, password);
        Player runner = compositionRoot.versusPlayer();
        for (int i = 0; i < numberOfGamesToPlay; i++) {
            Game game = retrieveVersus(team);
            System.out.println(runner.playToCompletion(game).toString());
        }
    }
    
    private Game retrieveVersus(Team team) {
        while (true) {
            try {
                return team.currentVersus();
            } catch (ArenaException e) {
                try {
                    Thread.sleep(nextGameRetryDelay);
                } catch (InterruptedException interruption) {
                    throw new RuntimeException("thread interrupted before a game could be retrieved!", interruption);
                }
                continue;
            }
        }
    }

}
