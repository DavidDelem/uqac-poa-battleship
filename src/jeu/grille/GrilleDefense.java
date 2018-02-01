package jeu.grille;

import javafx.geometry.Pos;
import jeu.bateaux.*;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GrilleDefense extends Grille {

    private List<Bateau> bateauList;

    public GrilleDefense(int tailleGrille, String nomJoueur){
        super(tailleGrille, nomJoueur);
    }

    public boolean placerBateau(String identifiantBateau, Position positionProue, Orientation orientation) {

        Bateau bateau = creerBateau(identifiantBateau, orientation, positionProue);

        if( !verifierExistanceBateau(bateau) && verifierPositionBateau(bateau) && !verifierSuperpositionBateau(bateau)){
            if(this.bateauList == null) this.bateauList = new ArrayList<>();
            this.bateauList.add(bateau);
            return true;
        }
        else return false;
    }

    public List<Bateau> bateauNonPlaces(){

        List<Bateau> allBateauList = new ArrayList<>();
        List<Bateau> bateauNonPlacesList = new ArrayList<>();


        allBateauList.add(new ContreTorpilleur());
        allBateauList.add(new Croiseur());
        allBateauList.add(new PorteAvion());
        allBateauList.add(new SousMarin());
        allBateauList.add(new Torpilleur());

        for(Bateau itemBateau : allBateauList){
            if(!verifierExistanceBateau(itemBateau)) bateauNonPlacesList.add(itemBateau);
        }

        return bateauNonPlacesList;
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

        if(this.bateauList == null) return false;
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

    private boolean verifierSuperpositionBateau(Bateau bateau) {
        List<Position> positionsPrises = new ArrayList<>();

        if (this.bateauList != null){
            for (Bateau itemBateau : this.bateauList) {
                if (bateau != itemBateau) {
                    positionsPrises.addAll(Bateau.getPositions(itemBateau));
                }
            }
        }

        for (Position position: Bateau.getPositions(bateau)) {
            if (positionsPrises.contains(position)) return true;
        }

        return false;
    }

    private List<Position> positionsTirsPossibles(){

        List<Position> tirsPossiblesTmpList = new ArrayList<>();
        List<Position> tirsPossiblesList = new ArrayList<>();

        //Recuperation des tirs possibles de chaque bateau
        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                tirsPossiblesTmpList.addAll(itemBateau.tirsPossibles());
            }
        }

        //Suppression des tirs hors de la grille
        for(Position itemPosition : tirsPossiblesTmpList){
            if(itemPosition.x >= 0
                    && itemPosition.x < this.tailleGrille
                    && itemPosition.y >= 0
                    && itemPosition.y < this.tailleGrille){
                tirsPossiblesList.add(itemPosition);
            }
        }

        return tirsPossiblesList;
    }

    private List<Position> positionsBateaux(){

        List<Position> positionsBateaux = new ArrayList<>();

        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                positionsBateaux.addAll(Bateau.getPositions(itemBateau));
            }
        }

        return positionsBateaux;
    }


    public void afficherGrille(){

        char lettre = 'A';

        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = EtatCaseGrille.VIDE;
        }

        for (Position itemPosition : this.positionsTirsPossibles()) {
            this.grille[itemPosition.x][itemPosition.y] = EtatCaseGrille.CHAMP_TIR;
        }

        for (Position itemPosition : this.positionsBateaux()) {
            this.grille[itemPosition.x][itemPosition.y] = EtatCaseGrille.BATEAU;
        }

        System.out.println("#####################################################");
        System.out.println("     Grille défense de : " + this.nomJoueur);
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
