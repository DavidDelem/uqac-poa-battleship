package jeu.grille;

import jeu.utils.Etat;
import jeu.utils.Position;

public class GrilleAttaque extends Grille {

    /**
     * Initialise la grille d'attaque
     *
     * @param nomJoueur le nom du joueur
     * @return
     */

    public GrilleAttaque(String nomJoueur){
        super(nomJoueur);
    }

    /**
     * Enregistre un tir
     *
     * @param position la position du tir
     * @param touche le résultat du tir
     * @return
     */

    public boolean tir(Position position, boolean touche) {
        if(touche) this.grille[position.x][position.y] = Etat.TOUCHE;
        else this.grille[position.x][position.y] = Etat.PAS_TOUCHE;
        return false;
    }
}
