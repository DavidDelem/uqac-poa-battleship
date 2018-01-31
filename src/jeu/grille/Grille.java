package jeu.grille;


public class Grille {

    enum EtatCaseGrille{
        VIDE(' '),
        BATEAU('B'),
        BATEAU_TOUCHE('X'),
        CHAMP_TIR('*'),
        TOUCHE('O'),
        PAS_TOUCHE('X');

        private final char affichage;

        private EtatCaseGrille(char affichage) {
            this.affichage = affichage;
        }

        public char getAffichage() {
            return this.affichage;
        }
    }

    protected EtatCaseGrille[][] grille;
    protected int tailleGrille;

    public Grille(int tailleGrille){

        this.tailleGrille = tailleGrille;

        this.grille = new EtatCaseGrille[tailleGrille][tailleGrille];
        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = EtatCaseGrille.VIDE;
        }

    }

    public void afficherGrille(){
        char lettre = 'A';

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
