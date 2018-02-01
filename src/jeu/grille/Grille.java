package jeu.grille;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Grille {

    enum EtatCaseGrille{
        VIDE(' '),
        BATEAU('B'),
        BATEAU_TOUCHE('X'),
        CHAMP_TIR('*'),
        TOUCHE('O'),
        PAS_TOUCHE('X');

        private final char affichage;

        private EtatCaseGrille(char affichage) {
            this.affichage = affichage;
        }

        public char getAffichage() {
            return this.affichage;
        }
    }

    protected EtatCaseGrille[][] grille;
    protected int tailleGrille;
    protected String nomJoueur;

    public Grille(int tailleGrille, String nomJoueur){

        this.tailleGrille = tailleGrille;
        this.nomJoueur = nomJoueur;

        this.grille = new EtatCaseGrille[tailleGrille][tailleGrille];
        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = EtatCaseGrille.VIDE;
        }

    }

}
