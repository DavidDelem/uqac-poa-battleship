package jeu.grille;

import jeu.bateaux.*;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GrilleDefense extends Grille{

    private List<Bateau> bateauList;

    public GrilleDefense(int tailleGrille){
        super(tailleGrille);

    }

    public boolean placerBateau(String identifiantBateau, Position positionProue, Orientation orientation) {

        Bateau bateau = creerBateau(identifiantBateau, orientation, positionProue);

        if( !verifierExistanceBateau(bateau) && verifierPositionBateau(bateau) && verifierSuperpositionBateau(bateau)){
            this.bateauList.add(bateau);
            return true;
        }
        else return false;
    }

    private Bateau creerBateau(String identifiantBateau, Orientation orientation, Position positionProue){

        Bateau bateau = null;

        switch (identifiantBateau) {
            case "CT":
                bateau = new ContreTorpilleur();
                break;
            case "C":
                bateau = new Croiseur();
                break;
            case "PA":
                bateau = new PorteAvion();
                break;
            case "SM":
                bateau = new SousMarin();
                break;
            case "T":
                bateau = new Torpilleur();
                break;
        }

        if(bateau != null){
            bateau.setPositionProue(positionProue);
            bateau.setOrientation(orientation);
            return bateau;

        }
        else return null;

    }

    private boolean verifierExistanceBateau(Bateau bateau){

        for (Bateau itemBateau: this.bateauList) {
            if(bateau.getClass().equals( itemBateau.getClass())) return true;
        }

        return false;
    }

    private boolean verifierPositionBateau(Bateau bateau){

        switch (bateau.getOrientation()) {
            case NORD:
                if(bateau.getPositionProue().y + bateau.getLongueur() > this.tailleGrille) return false;
                break;
            case EST:
                if(bateau.getPositionProue().x - bateau.getLongueur() < 0) return false;
                break;
            case SUD:
                if(bateau.getPositionProue().y - bateau.getLongueur() < 0) return false;
                break;
            case OUEST:
                if(bateau.getPositionProue().x + bateau.getLongueur() > this.tailleGrille) return false;
                break;
            default:
                return false;
        }

        return true;
    }

    private boolean verifierSuperpositionBateau(Bateau bateau){
        List<Position> positionsPrises = new ArrayList<>();

        for (Bateau itemBateau: this.bateauList) {
            if(bateau != itemBateau) {
                positionsPrises.addAll(Bateau.getPositions(itemBateau));
            }
        }

        for (Position position: Bateau.getPositions(bateau)) {
            if (positionsPrises.contains(position)) return false;
        }

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
