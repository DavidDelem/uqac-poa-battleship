package jeu.grille;

import javafx.geometry.Pos;
import jeu.bateaux.*;
import jeu.utils.Etat;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GrilleDefense extends Grille {

    private List<Bateau> bateauList;

    public GrilleDefense(String nomJoueur){
        super(nomJoueur);
    }

    public ResultatPlacementBateau placerBateau(String identifiantBateau, Position positionProue, Orientation orientation) {

        Bateau bateau = creerBateau(identifiantBateau, orientation, positionProue);

        if(verifierExistanceBateau(bateau)) {
            return ResultatPlacementBateau.DEJA_PLACE;
        }
        else if(!verifierPositionBateau(bateau)) {
            return ResultatPlacementBateau.HORS_GRILLE;
        }
        else if(verifierSuperpositionBateau(bateau)) {
            return ResultatPlacementBateau.SUPERPOSITION;
        }
        else{
            if(this.bateauList == null) this.bateauList = new ArrayList<>();
            this.bateauList.add(bateau);
            return ResultatPlacementBateau.OK;
        }
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
                if(bateau.getPositionProue().x + bateau.getLongueur() - 1 < this.tailleGrille) return true;
                break;
            case EST:
                if(bateau.getPositionProue().y - bateau.getLongueur() + 1 >= 0) return true;
                break;
            case SUD:
                if(bateau.getPositionProue().x - bateau.getLongueur() + 1 >= 0) return true;
                break;
            case OUEST:
                if(bateau.getPositionProue().y + bateau.getLongueur() - 1 < this.tailleGrille) return true;
                break;
            default:
                return false;
        }

        return false;
    }

    private boolean verifierSuperpositionBateau(Bateau bateau) {
        List<Position> positionsPrises = new ArrayList<>();

        if (this.bateauList != null){
            for (Bateau itemBateau : this.bateauList) {
                if (bateau != itemBateau) {
                    positionsPrises.addAll(Bateau.getPositions(itemBateau, null));
                }
            }
        }

        for (Position position: Bateau.getPositions(bateau, null)) {
            for(Position positionPrise: positionsPrises) {
                if(position.equals(positionPrise)) return true;
            }
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

    private List<Position> positionsBateaux(Etat etatCaseBateau){

        List<Position> positionsBateaux = new ArrayList<>();

        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                positionsBateaux.addAll(Bateau.getPositions(itemBateau, etatCaseBateau));
            }
        }

        return positionsBateaux;
    }

    public boolean verifierTirAdversaire(Position positionTir){

        Bateau bateauCoule = null;

        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                for(Position itemPositionBateau : Bateau.getPositions(itemBateau, Etat.BATEAU_NON_TOUCHE)){
                    if(itemPositionBateau.equals(positionTir)){
                        System.out.println("Bateau touché !");
                        if(itemBateau.toucher(positionTir)) bateauCoule = itemBateau;
                        System.out.println(itemBateau.getNom() + " de l'adversaire coulé !");
                        return true;
                    }
                    else System.out.println("Tir dans le vide !");
                }
            }

            if(bateauCoule != null) this.bateauList.remove(bateauCoule);
        }

        return false;
    }


    public void mettreAJourGrille(){

        for(int i=0; i<this.tailleGrille; i++) {
            for(int j=0; j<this.tailleGrille; j++) this.grille[i][j] = Etat.VIDE;
        }

        for (Position itemPosition : this.positionsTirsPossibles()) {
            this.grille[itemPosition.x][itemPosition.y] = Etat.CHAMP_TIR;
        }

        for (Position itemPosition : this.positionsBateaux(Etat.BATEAU_NON_TOUCHE)) {

            this.grille[itemPosition.x][itemPosition.y] = Etat.BATEAU_NON_TOUCHE;
        }

        for (Position itemPosition : this.positionsBateaux(Etat.BATEAU_TOUCHE)) {

            this.grille[itemPosition.x][itemPosition.y] = Etat.BATEAU_TOUCHE;
        }

//        super.afficherGrille(true);
    }

}
