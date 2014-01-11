package fr.notfound.fakearena;

public interface ArenaBuilder {
    
    GameBuilder createVersus();
    ArenaServer start();

}
