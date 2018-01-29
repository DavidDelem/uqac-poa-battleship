package jeu.joueur;

import jeu.Grille;

public class JoueurHumain extends Joueur implements ComportementJoueur{

    public JoueurHumain(String nom){
        super(nom);
    }

    @Override
    public Grille initialiserMaGrille() {
        return null;
    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurHumain");
    }

}
