package jeu;

import jeu.bateaux.*;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    enum EtatCaseGrille{
        VIDE,
        BATEAU
    }

    private EtatCaseGrille[][] grille;
    private List<Bateau> bateauList;
    private int tailleGrille;

    public Grille (int tailleGrille){

        this.tailleGrille = tailleGrille;

        this.grille = new EtatCaseGrille[tailleGrille][tailleGrille];
        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = EtatCaseGrille.VIDE;
        }

        this.bateauList = new ArrayList<>();
        this.bateauList.add(new ContreTorpilleur());
        this.bateauList.add(new Croiseur());
        this.bateauList.add(new PorteAvion());
        this.bateauList.add(new SousMarin());
        this.bateauList.add(new Torpilleur());
    }

    public boolean placerBateau(String identifiantBateau, Position positionProue, Orientation orientation) {


        // Récupération du bateau

        Bateau bateau;

        switch (identifiantBateau) {
            case "CT":
                bateau = this.bateauList.get(0);
                break;
            case "C":
                bateau = this.bateauList.get(1);
                break;
            case "PA":
                bateau = this.bateauList.get(2);
                break;
            case "SM":
                bateau = this.bateauList.get(3);
                break;
            case "T":
                bateau = this.bateauList.get(4);
                break;
            default:
                return false;
        }

        // Bateau dans les limites de la grille

        switch (orientation) {
            case NORD:
                if(positionProue.y + bateau.getLongueur() > this.tailleGrille) return false;
                break;
            case EST:
                if(positionProue.x - bateau.getLongueur() < 0) return false;
                break;
            case SUD:
                if(positionProue.y - bateau.getLongueur() < 0) return false;
                break;
            case OUEST:
                if(positionProue.x + bateau.getLongueur() > this.tailleGrille) return false;
                break;
            default:
                return false;
        }

        // Test de non superposition des bateau

        List<Position> positionsPrises = new ArrayList<>();

        for (Bateau itemBateau: this.bateauList) {
            if(bateau != itemBateau) {
                positionsPrises.addAll(Bateau.getPositions(itemBateau));
            }
        }

        for (Position position: Bateau.getPositions(bateau)) {
            if (positionsPrises.contains(position)) return false;
        }

        bateau.setPositionProue(positionProue);
        bateau.setOrientation(orientation);
        return true;
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
