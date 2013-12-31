package fr.notfound.domain;

public enum MoveResult {
    Approved,
    Rejected,
    Victory,
    NotYourTurn;

    public static MoveResult parse(String moveResult) {
        switch (moveResult) {
            case "OK": return Approved;
            case "KO": return Rejected;
            case "GAGNE": return Victory;
            case "PTT": return NotYourTurn;
            default: throw new IllegalArgumentException("illegal move result: " + moveResult);
        }
    }

}
