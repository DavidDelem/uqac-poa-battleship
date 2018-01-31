package jeu.grille;


abstract class Grille {

    enum EtatCaseGrille{
        VIDE,
        BATEAU,
        TOUCHE,
        PAS_TOUCHE
    }

    protected GrilleDefense.EtatCaseGrille[][] grille;
    protected int tailleGrille;

    public Grille(int tailleGrille){

        this.tailleGrille = tailleGrille;

        this.grille = new GrilleDefense.EtatCaseGrille[tailleGrille][tailleGrille];
        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = GrilleDefense.EtatCaseGrille.VIDE;
        }

    }

    abstract void afficherGrille();

}
