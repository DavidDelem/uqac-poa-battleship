package jeu.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Orientation {
    OUEST,
    EST,
    NORD,
    SUD;

    private static final List<Orientation> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = values.size();
    private static final Random random = new Random();

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

    /**
     * Renvoie une orientation random
     *
     * @return Valeur aléatoire de l'énum Orientation
     */
    public static Orientation orientationRandom()  {
        return values.get(random.nextInt(size));
    }
}