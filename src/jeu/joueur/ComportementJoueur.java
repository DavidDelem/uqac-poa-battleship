package jeu.joueur;

import jeu.grille.GrilleDefense;

public interface ComportementJoueur {
    
    public GrilleDefense initialiserMaGrille();
    public void jouer();
}
