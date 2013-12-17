package fr.notfound;

import static java.lang.String.format;
import java.net.URL;

public class HardCodedStringFormatUrlProvider implements ArenaUrlProvider {
    
    public final UrlFactory factory;

    public HardCodedStringFormatUrlProvider(UrlFactory factory) {
        this.factory = factory;
    }

    @Override public URL ping() {
        return factory.get("ping");
    }

    @Override public URL teamId(String teamName, String password) {
        return factory.get(format("player/getIdEquipe/%s/%s", teamName, password));
    }

    @Override public URL currentVersus(String teamId) {
        return factory.get(format("versus/next/%s", teamId));
    }

    @Override public URL newPractice(String level, String teamId) {
        return factory.get(format("practice/new/%s/%s", level, teamId));
    }

    @Override public URL currentPractice(String teamId) {
        return factory.get(format("practice/next/%s", teamId));
    }

    @Override public URL status(String gameId, String teamId) {
        return factory.get(format("game/status/%s/%s", gameId, teamId));
    }

    @Override public URL board(String gameId) {
        return factory.get(format("game/board/%s", gameId));
    }

    @Override public URL lastMove(String gameId) {
        return factory.get(format("game/getlastmove/%s", gameId));
    }

    @Override public URL play(String gameId, String teamId, String x, String y) {
        return factory.get(format("game/play/%s/%s/%s/%s", gameId, teamId, x, y));
    }

}
