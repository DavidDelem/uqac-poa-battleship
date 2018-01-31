package jeu.grille;

import jeu.utils.Position;

public class GrilleAttaque extends Grille {

    public GrilleAttaque(int tailleGrille){
        super(tailleGrille);
    }

    public void tirEffectue(Position position, boolean touche){
        if(touche) this.grille[position.x][position.y] = EtatCaseGrille.TOUCHE;
        else this.grille[position.x][position.y] = EtatCaseGrille.PAS_TOUCHE;
    }


}
