package jeu.joueur;

import jeu.Grille;

public class Joueur implements ComportementJoueur {

    private String nom;

    public Joueur(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public Grille initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
    }
}
