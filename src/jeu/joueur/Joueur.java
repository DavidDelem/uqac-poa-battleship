package jeu.joueur;

import jeu.grille.GrilleDefense;

public class Joueur implements ComportementJoueur {

    private String nom;
    private GrilleDefense grilleDefense;

    public Joueur(String nom){
        this.nom = nom;
        this.grilleDefense = new GrilleDefense(10);
    }

    public String getNom() {
        return nom;
    }

    public GrilleDefense getGrilleDefense() {
        return grilleDefense;
    }

    @Override
    public GrilleDefense initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
    }
}
