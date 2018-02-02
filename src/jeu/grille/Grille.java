package jeu.grille;


import jeu.utils.Etat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Grille {


    protected Etat[][] grille;
    protected int tailleGrille;
    protected String nomJoueur;

    public Grille(int tailleGrille, String nomJoueur){

        this.tailleGrille = tailleGrille;
        this.nomJoueur = nomJoueur;

        this.grille = new Etat[tailleGrille][tailleGrille];
        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = Etat.VIDE;
        }

    }

    public void afficherGrille(boolean grilleDefense){
        char lettre = 'A';

        System.out.println();
        System.out.print("    ");
        for(int i=0; i < this.tailleGrille; i++) {
            System.out.print(" " + lettre + "  ");
            lettre++;
        }
        System.out.println();
        System.out.println("-------------------------------------------");

        for(int i=0; i < this.tailleGrille; i++) {

            System.out.print((i+1<10 ? (i+1)+" " : i+1) + " |");

            for (int j = 0; j < this.tailleGrille; j++) {
                //TODO : AFFICHER GRILLE DEFENSE OU GRILLE ATTAQUE
                System.out.print(" " + this.grille[i][j].getAffichage() + " |");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
        }
    }

}
