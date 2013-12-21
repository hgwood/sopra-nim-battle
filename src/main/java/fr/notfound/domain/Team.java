package fr.notfound.domain;

/**
 * A team playing in an arena.
 * <p>
 * A Team entity is always bound to one and only one arena, however
 * multiple teams answering to the same name may play in different arenas.
 */
public interface Team {
    
    /**
     * @param level level of difficulty
     * @return a newly-created practice game against an AI
     */
    Game newPractice(AiLevel level);
    
    /**
     * @return the practice game this team is currently participating to
     * @throws {@link ArenaException} if there is no such game
     */
    Game currentPractice();
    
    /**
     * @return the versus game this team is currently participating to
     * @throws {@link ArenaException} if there is no such game
     */
    Game currentVersus();

}
