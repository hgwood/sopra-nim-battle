package fr.notfound.strategies;

import java.util.List;

import fr.notfound.domain.*;

public class PickFirstExceptLastGroupNuke implements Strategy {

    @Override public Move pickMove(Game game) {
        Board board = game.board();
        List<Move> groups = board.groups();
        
        if (groups.size() == 1 && groups.get(0).y <= 3) {
            return groups.get(0);
        }
        if (groups.size() == 1 && groups.get(0).y < 8) {
            Move g = groups.get(0);
            switch(g.y) {
                case 7: return new Move(g.x + 2, 3);
                case 6: return new Move(g.x + 2, 2);
                case 5: return new Move(g.x + 1, 3);
                case 4: return new Move(g.x + 1, 2);
            }
        }
        int remainder = groups.get(0).y % 4;
        if (groups.size() == 1 && remainder != 0) {
            return new Move(groups.get(0).x, remainder);
        }
        if (groups.size() == 1 && remainder == 0) {
            return new Move(groups.get(0).x + groups.get(0).y / 2, 1);
        }
        
        for (Move group : groups) {
            if (group.y == 1) {
                return group;
            }
        }
        
        Move result = new Move(game.board().firstAvailable(), 1);
        return result;
    }

}
