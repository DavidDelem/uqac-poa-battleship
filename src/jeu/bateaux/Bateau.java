package jeu.bateaux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jeu.utils.Etat;
import jeu.utils.Orientation;
import jeu.utils.Position;

public class Bateau {

    protected String nom, identifiant;
    protected int longueur, champTir;
    protected boolean champDeTirVertical;
    protected boolean coule;
    protected Orientation orientation;
    protected List<Etat> caseBateauList;
    protected Position positionProue;

    /**
     * Initialise un bateau
     *
     * @param nom le nom du bateau
     * @param identifiant l'identifiant du bateau
     * @param longueur longueur du bateau
     * @param champTir longueur du champ de tir en nombre de cases
     * @param champTirVertical true pour champ de tir est vertical, false pour horizontal
     * @return
     */

    public Bateau(String nom, String identifiant, int longueur, int champTir, boolean champTirVertical) {

        this.nom = nom;
        this.identifiant = identifiant;
        this.longueur = longueur;
        this.champTir = champTir;
        this.champDeTirVertical = champTirVertical;
        this.orientation = Orientation.NORD;
        this.positionProue = new Position(0,0);
        this.coule = false;

        caseBateauList = new ArrayList<>();
        for(int i=0; i<longueur; i++) caseBateauList.add(Etat.BATEAU_NON_TOUCHE);
    }

    public int getChampTir() {
        return champTir;
    }

    public boolean getChampDeTirVertical() {
        return champDeTirVertical;
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setPositionProue(Position positionProue){
        this.positionProue = positionProue;
    }

    public Position getPositionProue() {
        return positionProue;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public String getNom() {
        return nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Touche un bateau et met son état à coulé si nécessaire
     *
     * @param position la position du touché
     * @return  true si le bateau est coulé, false sinon
     */

    public boolean toucherCouler(Position position){

        if(!this.coule){

            switch (this.orientation) {
                case NORD:
                    this.caseBateauList.set(position.x-this.positionProue.x,Etat.BATEAU_TOUCHE);
                    break;
                case SUD:
                    this.caseBateauList.set(this.positionProue.x-position.x,Etat.BATEAU_TOUCHE);
                    break;
                case OUEST:
                    this.caseBateauList.set(position.y-this.positionProue.y,Etat.BATEAU_TOUCHE);
                    break;
                case EST:
                    this.caseBateauList.set(this.positionProue.y-position.y,Etat.BATEAU_TOUCHE);
                    break;
            }

            if(Collections.frequency(this.caseBateauList, Etat.BATEAU_TOUCHE) >= 2){
                this.coule = true;
            }
        }

        return this.coule;
    }

    /**
     * Récupération de la liste des positions d'un bateau (cases prises par le bateau)
     *
     * @param bateau le bateau dont on veut connaitre les positions
     * @param etatCaseBateau récupérer uniquement les cases non touchées ou uniquement les cases touchées
     * @return  Une liste des positions du bateau
     */

    public static List<Position> getPositions(Bateau bateau, Etat etatCaseBateau) {

        List<Position> positions = new ArrayList<>();

        for(int i=0 ; i<bateau.longueur; i++){

            if(etatCaseBateau == null || bateau.caseBateauList.get(i) == etatCaseBateau){
                switch (bateau.orientation) {
                    case NORD:
                        positions.add(new Position(bateau.positionProue.x+i, bateau.positionProue.y));
                        break;
                    case EST:
                        positions.add(new Position(bateau.positionProue.x, bateau.positionProue.y-i));
                        break;
                    case SUD:
                        positions.add(new Position(bateau.positionProue.x-i, bateau.positionProue.y));
                        break;
                    case OUEST:
                        positions.add(new Position(bateau.positionProue.x, bateau.positionProue.y+i));
                        break;
                }
            }
        }

        return positions;
    }

    /**
     * Récupération de la liste des tirs possibles pour un bateau
     * en fonction de la taille de son champ de tir et de son orientation
     *
     * @return  Une liste des positions de tir possibles
     */

    public List<Position> tirsPossibles(){
        List<Position> tirsPossiblesList = new ArrayList<>();

        switch (this.orientation) {
            case NORD:
                for(int i=0; i<this.champTir; i++){
                    if(this.champDeTirVertical) {
                        tirsPossiblesList.add(new Position(this.positionProue.x-1-i, this.positionProue.y));
                        tirsPossiblesList.add(new Position(this.positionProue.x+this.longueur+i, this.positionProue.y));
                    }

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x+j, this.positionProue.y)); //position bateau

                        if(!this.champDeTirVertical) {
                            tirsPossiblesList.add(new Position(this.positionProue.x + j, this.positionProue.y - i - 1));
                            tirsPossiblesList.add(new Position(this.positionProue.x + j, this.positionProue.y + i + 1));
                        }
                    }
                }
                break;
            case SUD:
                for(int i=0; i<this.champTir; i++){
                    if(this.champDeTirVertical) {
                        tirsPossiblesList.add(new Position(this.positionProue.x + 1 + i, this.positionProue.y));
                        tirsPossiblesList.add(new Position(this.positionProue.x - this.longueur - i, this.positionProue.y));
                    }

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x-j, this.positionProue.y)); //position bateau
                        if(!this.champDeTirVertical) {
                            tirsPossiblesList.add(new Position(this.positionProue.x - j, this.positionProue.y - i - 1));
                            tirsPossiblesList.add(new Position(this.positionProue.x - j, this.positionProue.y + i + 1));
                        }
                    }
                }
                break;
            case EST:
                for(int i=0; i<this.champTir; i++){
                    if(this.champDeTirVertical) {
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y + 1 + i));
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y - this.longueur - i));
                    }

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y-j)); //position bateau
                        if(!this.champDeTirVertical) {
                            tirsPossiblesList.add(new Position(this.positionProue.x - i - 1, this.positionProue.y - j));
                            tirsPossiblesList.add(new Position(this.positionProue.x + i + 1, this.positionProue.y - j));
                        }
                    }
                }
                break;
            case OUEST:
                for(int i=0; i<this.champTir; i++){
                    if(this.champDeTirVertical) {
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y - 1 - i));
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y + this.longueur + i));
                    }

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y+j)); //position bateau
                        if(!this.champDeTirVertical) {
                            tirsPossiblesList.add(new Position(this.positionProue.x - i - 1, this.positionProue.y + j));
                            tirsPossiblesList.add(new Position(this.positionProue.x + i + 1, this.positionProue.y + j));
                        }
                    }
                }
                break;
        }

        if(this.getClass() == ContreTorpilleur.class){
            return tirsPossiblesList;
        }

        return tirsPossiblesList;
    }

}
