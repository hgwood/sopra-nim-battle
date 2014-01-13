package fr.notfound.composition;

import fr.notfound.domain.Team;

public interface TeamFactory {
    
    Team create(String id);

}
