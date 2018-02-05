package jeu.utils;

public class Position {

    public int x,y;

    /**
     * Initialise l'objet position
     *
     * @return
     */

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Initialise l'objet position avec deux valeurs
     *
     * @param x la position en x
     * @param y la position en y
     * @return
     */

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Transforme un char correspondant à une colonne en entier correspondant
     *
     * @param lettre la lettre de la colonne
     * @return l'entier correspondant à la colonne
     */

    public static int convertirColonneInt(char lettre){
        return lettre - 65;
    }

    /**
     * Transforme un int correspondant à une colonne en char correspondant
     *
     * @param nombre le nombre de la colonne
     * @return le char à la colonne
     */

    public static char convertirColonneChar(int nombre){
        return (char)(65 + nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }
}
