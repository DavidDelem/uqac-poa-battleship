package jeu.utils;

public enum Orientation {
    OUEST,
    EST,
    NORD,
    SUD;

    public static Orientation convertirOrientation(String colonne){
        switch (colonne){
            case "NORD":
                return Orientation.NORD;
            case "SUD":
                return Orientation.SUD;
            case "EST":
                return Orientation.EST;
            case "OUEST":
                return Orientation.OUEST;
            default:
                return null;
        }
    }
}
