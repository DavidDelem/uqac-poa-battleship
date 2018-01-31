package jeu.joueur;

import jeu.grille.GrilleDefense;

public class JoueurHumain extends Joueur implements ComportementJoueur{

    public JoueurHumain(String nom){
        super(nom);
    }

    @Override
    public GrilleDefense initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurHumain");
    }

}
