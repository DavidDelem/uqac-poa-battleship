package jeu.grille;


import jeu.utils.Etat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Grille {

    public static final int tailleGrille = 10;
    protected Etat[][] grille;
    protected String nomJoueur;

    public Grille(String nomJoueur){

        this.nomJoueur = nomJoueur;

        this.grille = new Etat[tailleGrille][tailleGrille];
        for(int i=0; i<tailleGrille; i++) {
            for(int j=0; j<tailleGrille; j++) this.grille[i][j] = Etat.VIDE;
        }

    }

    public Etat getEtatPosition(int x, int y) {
        return grille[x][y];
    }
}
