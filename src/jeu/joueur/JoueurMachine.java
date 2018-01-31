package jeu.joueur;

import jeu.grille.GrilleDefense;

public class JoueurMachine extends Joueur implements ComportementJoueur {

    public JoueurMachine(String nom){
        super(nom);
    }

    @Override
    public GrilleDefense initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurMachine");
    }
}
