package jeu.grille;


import jeu.utils.Etat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Grille {

    public static final int tailleGrille = 10;
    protected Etat[][] grille;
    protected String nomJoueur;

    /**
     * Initialise la grille
     *
     * @param nomJoueur le nom du joueur
     * @return
     */

    public Grille(String nomJoueur){

        this.nomJoueur = nomJoueur;

        this.grille = new Etat[tailleGrille][tailleGrille];
        for(int i=0; i<tailleGrille; i++) {
            for(int j=0; j<tailleGrille; j++) this.grille[i][j] = Etat.VIDE;
        }

    }

    /**
     * Récupére l'état de la grille à une position
     *
     * @param x la position en x
     * @param y la position en y
     * @return l'état de la grille à la position demandée
     */

    public Etat getEtatPosition(int x, int y) {
        return grille[x][y];
    }
}
