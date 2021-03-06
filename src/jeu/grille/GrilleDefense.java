package jeu.grille;

import jeu.bateaux.*;
import jeu.utils.Etat;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GrilleDefense extends Grille {

    private List<Bateau> bateauList;

    /**
     * Initialise la grille de défense
     *
     * @param nomJoueur le nom du joueur
     * @return
     */

    public GrilleDefense(String nomJoueur){
        super(nomJoueur);
    }

    /**
     * Place un nouveau bateau à partir d'un identifiant, d'une position et d'une orientation
     * Si le placement est invalide, renvoie la cause du problème pour pouvoir informer l'utilisateur
     *
     * @param identifiantBateau l'identifiant du bateau à placer
     * @param positionProue la position de la proue du bateau à placer
     * @param  orientation l'orientation du bateau
     * @return le résultat suite au placement du bateau (succès ou raison de l'echec)
     */

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

    /**
     * Déplace un bateau existant à partir d'un identifiant, d'une orientation et d'un nombre de cases
     * Si le nouveau placement est invalide, renvoie la cause du problème pour pouvoir informer l'utilisateur
     *
     * @param identifiantBateau l'identifiant du bateau à déplacer
     * @param orientation la direction dans laquelle déplcer le bateau
     * @param nbCases le nombre de cases duquel le bateau doit être déplacer
     * @return le résultat suite au déplacement du bateau (succès ou raison de l'echec)
     */

    public ResultatPlacementBateau deplacerBateau(String identifiantBateau, Orientation orientation, int nbCases) {

        int indexBateauDeplace = -1;
        Position positionProueInitiale = null;
        Position positionProueFinale = null;

        // On récupére le bateau que l'utilisateur veut déplacer

        for(int i = 0; i < this.bateauList.size(); i++) {
            if(this.bateauList.get(i).getIdentifiant().equals(identifiantBateau)) {
                indexBateauDeplace = i;
                positionProueInitiale = this.bateauList.get(i).getPositionProue();
            }
        }

        // On vérifie que le déplacement est autorisé
        // Si oui, en fonction de l'orientation du déplacement, on lui donne sa nouvelle position

        if(nbCases < 0 || nbCases > 2 || positionProueInitiale == null) {
            return ResultatPlacementBateau.HORS_ZONE_DEPLACEMENT;
        } else {
            switch (orientation) {
                case OUEST:
                    positionProueFinale = new Position(positionProueInitiale.x, positionProueInitiale.y - nbCases);
                    break;
                case EST:
                    positionProueFinale = new Position(positionProueInitiale.x, positionProueInitiale.y + nbCases);
                    break;
                case NORD:
                    positionProueFinale = new Position(positionProueInitiale.x - nbCases, positionProueInitiale.y);
                    break;
                case SUD:
                    positionProueFinale = new Position(positionProueInitiale.x + nbCases, positionProueInitiale.y);
                    break;
            }
            this.bateauList.get(indexBateauDeplace).setPositionProue(positionProueFinale);
        }

        // On contrôle la validité de la nouvelle position: dans les limites de la carte et sans superposition.
        // Si ce n'est pas valide on réinitialise la position initiale

        if(!verifierPositionBateau(bateauList.get(indexBateauDeplace))) {
            bateauList.get(indexBateauDeplace).setPositionProue(positionProueInitiale);
            return ResultatPlacementBateau.HORS_GRILLE;
        } else if(verifierSuperpositionBateau(bateauList.get(indexBateauDeplace))) {
            bateauList.get(indexBateauDeplace).setPositionProue(positionProueInitiale);
            return ResultatPlacementBateau.SUPERPOSITION;
        }

        return ResultatPlacementBateau.OK;
    }


    /**
     * Renvoie la liste des bateaux qui n'ont pas encore été placés sur la grille
     *
     * @return une liste de bateaux
     */

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

    /**
     * Créé un bateau sur la grilleen l'ajoutant à la liste des bateaux
     * à partir de son identifiant, de la position de sa proue et d'une orientation
     *
     * @param identifiantBateau l'identifiant du bateau à placer
     * @param positionProue la position de la proue du bateau à placer
     * @param  orientation l'orientation du bateau
     * @return une liste de bateaux
     */

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

    /**
     * Vérifie si un bateau existe dans la liste des bateaux, donc sur la grille
     *
     * @param bateau un bateau
     * @return true si il existe, false sinon
     */

    private boolean verifierExistanceBateau(Bateau bateau){

        if(this.bateauList == null) return false;
        for (Bateau itemBateau: this.bateauList) {
            if(bateau.getClass().equals( itemBateau.getClass())) return true;
        }

        return false;
    }

    /**
     * Contrôle que la totalité du bateau soit bien dans les limites de la grille
     *
     * @param bateau un bateau
     * @return false si la position est bonne, true sinon
     */

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

    /**
     * Contrôle que le bateau ne soit pas superposé à un autre bateau sur la grille
     *
     * @param bateau un bateau
     * @return true si pas de superposition, false sinon
     */

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

    /**
     * Renvoie la liste de toutes les positions de tir possible de tous les bateaux présents sur la carte
     *
     * @return une liste de positions de tirs possibles
     */

    public List<Position> positionsTirsPossibles(){

        List<Position> tirsPossiblesTmpList = new ArrayList<>();
        List<Position> tirsPossiblesList = new ArrayList<>();

        // Récuperation des tirs possibles de chaque bateau

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

    /**
     * Renvoie une liste des positions de tous les bateaux présents sur la carte
     *
     * @param etatCaseBateau récupérer uniquement les cases non touchées ou uniquement les cases touchées
     * @return une liste de positions
     */

    private List<Position> positionsBateaux(Etat etatCaseBateau){

        List<Position> positionsBateaux = new ArrayList<>();

        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                positionsBateaux.addAll(Bateau.getPositions(itemBateau, etatCaseBateau));
            }
        }

        return positionsBateaux;
    }

    /**
     * Vérifie un tir réalisé par un adversaire
     *
     * @param positionTir les coordonnées dur tir
     * @return true si le tir est un succès, false si le tir est un echec
     */

    public boolean verifierTirAdversaire(Position positionTir){

        Bateau bateauCoule = null;
        boolean touche = false;

        if(this.bateauList != null) {
            for (Bateau itemBateau : this.bateauList) {
                for(Position itemPositionBateau : Bateau.getPositions(itemBateau, null)){
                    if(itemPositionBateau.equals(positionTir)){
                        touche = true;
                        if(itemBateau.toucherCouler(positionTir)) bateauCoule = itemBateau;
                    }
                }
            }
            if(bateauCoule != null) this.bateauList.remove(bateauCoule);
        }

        if(touche) {
            System.out.println("Bateau touché !");
            if(bateauCoule != null) System.out.println(bateauCoule.getNom() + " de l'adversaire coulé !");
            return true;
        } else {
            System.out.println("Tir dans le vide !");
            return false;
        }

    }

    /**
     * Détermine le nombre de bateaux actuellement présents sur la grille
     *
     * @return le nombre de bateau
     */

    public int getNombreBateau() {
        return bateauList.size();
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

    }

    public List<Bateau> getBateauList() {
        return bateauList;
    }
}
