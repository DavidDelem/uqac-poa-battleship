package jeu.grille;

import jeu.utils.Etat;
import jeu.utils.Position;

public class GrilleAttaque extends Grille {

    public GrilleAttaque(String nomJoueur){
        super(nomJoueur);
    }

//    public void tirEffectue(Position position, boolean touche){
//        if(touche) this.grille[position.x][position.y] = EtatCaseGrille.TOUCHE;
//        else this.grille[position.x][position.y] = EtatCaseGrille.PAS_TOUCHE;
//    }

    public boolean tir(Position position, boolean touche) {
        if(touche) this.grille[position.x][position.y] = Etat.TOUCHE;
        else this.grille[position.x][position.y] = Etat.PAS_TOUCHE;
        return false;
    }

//    public void afficherGrille() {
//        super.afficherGrille(false);
//    }
}
