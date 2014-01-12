package fr.notfound.fakearena;

public interface ArenaBuilder {
    
    ArenaBuilder delaysGame(int numberOfQueries);
    GameBuilder createVersus();
    ArenaServer start();

}
