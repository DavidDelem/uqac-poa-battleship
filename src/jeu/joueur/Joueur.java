package jeu.joueur;

import jeu.Grille;

public class Joueur implements ComportementJoueur {

    private String nom;
    private Grille grille;

    public Joueur(String nom){
        this.nom = nom;
        this.grille = new Grille(10, 10);
    }

    public String getNom() {
        return nom;
    }

    public Grille getGrille() {
        return grille;
    }

    @Override
    public Grille initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
    }
}
