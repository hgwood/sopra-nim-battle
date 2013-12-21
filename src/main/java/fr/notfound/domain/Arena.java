package fr.notfound.domain;

/**
 * An arena that is open for teams to join.
 * <p>
 * This type is the entry-point to the domain API. Arenas must be joined
 * before games can be created and played inside them.
 */
public interface Arena {
    
    /**
     * Join this arena using the specified credentials. Arenas may ignore 
     * the credentials or use them to authenticate teams. Arenas may reject
     * teams, in which case this method should raise an {@link ArenaException}.
     * 
     * @return a {@link Team} that is able to participate in games in this arena
     * @throws {@link ArenaException} if the team is not allowed into this arena
     */
    Team join(String teamName, String password);

}
