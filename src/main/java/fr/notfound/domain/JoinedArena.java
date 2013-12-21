package fr.notfound.domain;

public interface JoinedArena {
    
    Game newPractice(AiLevel level);
    Game currentPractice();
    Game currentVersus();

}
