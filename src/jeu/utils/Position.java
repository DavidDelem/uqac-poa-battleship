package jeu.utils;

import java.util.Objects;

public class Position {

    public int x,y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static int convertirColonneInt(char lettre){
        return lettre - 65;
    }

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
