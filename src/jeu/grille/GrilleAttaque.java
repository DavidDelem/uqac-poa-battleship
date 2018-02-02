package jeu.grille;

import jeu.utils.Position;

public class GrilleAttaque extends Grille {

    public GrilleAttaque(int tailleGrille, String nomJoueur){
        super(tailleGrille, nomJoueur);
    }

//    public void tirEffectue(Position position, boolean touche){
//        if(touche) this.grille[position.x][position.y] = EtatCaseGrille.TOUCHE;
//        else this.grille[position.x][position.y] = EtatCaseGrille.PAS_TOUCHE;
//    }

    public Boolean tirer(Position position) {

    }

    public void afficherGrille(){

        char lettre = 'A';

        System.out.println("#####################################################");
        System.out.println("     Grille attaque de : " + this.nomJoueur);
        System.out.println("#####################################################");

        System.out.print("    ");
        for(int i=0; i < this.tailleGrille; i++) {
            System.out.print(" " + lettre + "  ");
            lettre++;
        }
        System.out.println();
        System.out.println("-------------------------------------------");

        for(int i=0; i < this.tailleGrille; i++) {

            System.out.print((i+1<10 ? (i+1)+" " : i+1) + " |");

            for (int j = 0; j < this.tailleGrille; j++) {
                System.out.print(" " + this.grille[i][j].getAffichage() + " |");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
        }
    }


}
