package jeu.joueur;

import jeu.grille.GrilleAttaque;
import jeu.grille.GrilleDefense;
import jeu.utils.Position;

public class Joueur {

    protected String nom;
    protected boolean droitDeplacement;
    protected GrilleDefense grilleDefense;
    protected GrilleAttaque grilleAttaque;

    public Joueur(String nom){
        this.nom = nom;
        this.grilleDefense = new GrilleDefense(10, this.nom);
        this.grilleAttaque = new GrilleAttaque(10, this.nom);
        this.droitDeplacement = false;
    }

    public String getNom() {
        return nom;
    }

    public boolean getDroitDeplacement() {
        return droitDeplacement;
    }

    public void setDroitDeplacement(boolean droitDeplacement) {
        this.droitDeplacement = droitDeplacement;
    }

    public GrilleDefense getGrilleDefense() {
        return grilleDefense;
    }

    public GrilleAttaque getGrilleAttaque() {
        return grilleAttaque;
    }

    public void initialiserGrilleDefense() {
    }

    public Position recupererPositionTir() {
        return null;
    }
}
