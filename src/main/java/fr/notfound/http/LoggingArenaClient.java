package fr.notfound.http;

import org.slf4j.Logger;

public class LoggingArenaClient implements PlainTextArenaClient {
    
    private final PlainTextArenaClient delegate;
    private final Logger logger;

    public LoggingArenaClient(PlainTextArenaClient delegate, Logger logger) {
        this.delegate = delegate;
        this.logger = logger;
    }

    @Override public String ping() {
        logger.info("ping()");
        String result = delegate.ping();
        logger.info("ping() -> \"{}\"", result);
        return result;
    }

    @Override public String teamId(String teamName, String password) {
        logger.info("teamId(teamName: \"{}\", password: \"{}\")", teamName, password);
        String result = delegate.teamId(teamName, password);
        logger.info("teamId(teamName: \"{}\", password: \"{}\") -> \"{}\"", teamName, password, result);
        return result;
    }

    @Override public String currentVersus(String teamId) {
        logger.info("currentVersus(teamId: \"{}\")", teamId);
        String result = delegate.currentVersus(teamId);
        logger.info("currentVersus(teamId: \"{}\") -> \"{}\"", teamId, result);
        return result;
    }

    @Override public String newPractice(String level, String teamId) {
        logger.info("newPractice(level: \"{}\", teamId: \"{}\")", level, teamId);
        String result = delegate.teamId(level, teamId);
        logger.info("newPractice(level: \"{}\", teamId: \"{}\") -> \"{}\"", level, teamId, result);
        return result;
    }

    @Override public String currentPractice(String teamId) {
        logger.info("currentPractice(teamId: \"{}\")", teamId);
        String result = delegate.currentPractice(teamId);
        logger.info("currentPractice(teamId: \"{}\") -> \"{}\"", teamId, result);
        return result;
    }

    @Override public String status(String gameId, String teamId) {
        logger.info("status(gameId: \"{}\", teamId: \"{}\")", gameId, teamId);
        String result = delegate.status(gameId, teamId);
        logger.info("status(gameId: \"{}\", teamId: \"{}\") -> \"{}\"", gameId, teamId, result);
        return result;
    }

    @Override public String board(String gameId) {
        logger.info("board(gameId: \"{}\")", gameId);
        String result = delegate.board(gameId);
        logger.info("board(gameId: \"{}\") -> \"{}\"", gameId, result);
        return result;
    }

    @Override public String lastMove(String gameId) {
        logger.info("lastMove(gameId: \"{}\")", gameId);
        String result = delegate.lastMove(gameId);
        logger.info("lastMove(gameId: \"{}\") -> \"{}\"", gameId, result);
        return result;
    }

    @Override public String play(String gameId, String teamId, String x, String y) {
        logger.info("play(gameId: \"{}\", teamId: \"{}\", x: \"{}\", y: \"{}\")", gameId, teamId, x, y);
        String result = delegate.play(gameId, teamId, x, y);
        logger.info("play(gameId: \"{}\", teamId: \"{}\", x: \"{}\", y: \"{}\") -> \"{}\"", gameId, teamId, x, y, result);
        return result;
    }

}
