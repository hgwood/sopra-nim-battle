package fr.notfound;

import fr.notfound.domain.*;

public class Main {
    
    public static void main(String[] args) {
        Arena arena = new CompositionRoot().arena(args[0]);
        Team team = arena.join(args[1], args[2]);
        Game game = team.currentVersus();
        System.out.println(game.status().toString());
    }

}
