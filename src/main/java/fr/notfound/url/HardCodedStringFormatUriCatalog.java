package fr.notfound.url;

import static java.lang.String.format;
import java.net.URI;

public class HardCodedStringFormatUriCatalog implements ArenaUriCatalog {
    
    public final UriProvider factory;

    public HardCodedStringFormatUriCatalog(UriProvider factory) {
        this.factory = factory;
    }

    @Override public URI ping() {
        return factory.get("ping");
    }

    @Override public URI teamId(String teamName, String password) {
        return factory.get(format("player/getIdEquipe/%s/%s", teamName, password));
    }

    @Override public URI currentVersus(String teamId) {
        return factory.get(format("versus/next/%s", teamId));
    }

    @Override public URI newPractice(String level, String teamId) {
        return factory.get(format("practice/new/%s/%s", level, teamId));
    }

    @Override public URI currentPractice(String teamId) {
        return factory.get(format("practice/next/%s", teamId));
    }

    @Override public URI status(String gameId, String teamId) {
        return factory.get(format("game/status/%s/%s", gameId, teamId));
    }

    @Override public URI board(String gameId) {
        return factory.get(format("game/board/%s", gameId));
    }

    @Override public URI lastMove(String gameId) {
        return factory.get(format("game/getlastmove/%s", gameId));
    }

    @Override public URI play(String gameId, String teamId, String x, String y) {
        return factory.get(format("game/play/%s/%s/%s/%s", gameId, teamId, x, y));
    }

}
