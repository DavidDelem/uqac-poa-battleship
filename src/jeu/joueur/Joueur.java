package jeu.joueur;

import jeu.grille.GrilleAttaque;
import jeu.grille.GrilleDefense;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Joueur implements ComportementJoueur {

    private String nom;
    private GrilleDefense grilleDefense;
    private GrilleAttaque grilleAttaque;

    public Joueur(String nom){
        this.nom = nom;
        this.grilleDefense = new GrilleDefense(10, this.nom);
        this.grilleAttaque = new GrilleAttaque(10, this.nom);
    }

    public String getNom() {
        return nom;
    }

    public GrilleDefense getGrilleDefense() {
        return grilleDefense;
    }

    public GrilleAttaque getGrilleAttaque() {
        return grilleAttaque;
    }

    @Override
    public void initialiserGrilleDefense() {
    }

    @Override
    public void jouer() {
    }
}
