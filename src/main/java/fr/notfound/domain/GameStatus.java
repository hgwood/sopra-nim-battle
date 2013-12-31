package fr.notfound.domain;

public enum GameStatus {
    YourTurn,
    NotYourTurn,
    Won,
    Lost,
    Canceled;

    public static GameStatus parse(String status) {
        switch (status) {
            case "OUI": return YourTurn;
            case "NON": return NotYourTurn;
            case "GAGNE": return Won;
            case "PERDU": return Lost;
            case "ANNULE": return Canceled;
            default: throw new IllegalArgumentException("illegal game status: " + status);
        }
    }

}
