package jeu.utils;

public enum Orientation {
    OUEST,
    EST,
    NORD,
    SUD;

    /**
     * Renvoie une enum orientation à partir d'une string
     *
     * @param orientation   une orientation
     * @return une orientation sous forme d'enum
     */

    public static Orientation convertirOrientation(String orientation){
        switch (orientation){
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
