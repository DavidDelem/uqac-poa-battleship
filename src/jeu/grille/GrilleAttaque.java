package jeu.grille;

public class GrilleAttaque extends Grille {

    public GrilleAttaque(int tailleGrille){
        super(tailleGrille);
    }

    public void afficherGrille() {

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
                System.out.print(" " + " " + " |");
            }
            System.out.println();
            System.out.println("-------------------------------------------");
        }
    }

}
