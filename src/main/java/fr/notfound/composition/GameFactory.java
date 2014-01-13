package fr.notfound.composition;

import fr.notfound.domain.Game;

public interface GameFactory {
    
    Game create(String gameId, String teamId);

}
