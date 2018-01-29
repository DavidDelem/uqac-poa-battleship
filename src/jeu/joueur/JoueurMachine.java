package jeu.joueur;

import jeu.Grille;

public class JoueurMachine extends Joueur implements ComportementJoueur {

    public JoueurMachine(String nom){
        super(nom);
    }

    @Override
    public Grille initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurMachine");
    }
}
