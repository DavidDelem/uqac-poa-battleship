package jeu.utils;

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

    public static int convertirColonne(char lettre){
        return lettre - 65;
    }
}
