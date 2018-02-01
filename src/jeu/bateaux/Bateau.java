package jeu.bateaux;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jeu.utils.Orientation;
import jeu.utils.Position;

import javax.print.attribute.standard.OrientationRequested;

public class Bateau {

    enum EtatCaseBateau{
        TOUCHE,
        PAS_TOUCHE
    }

    private String nom, identifiant;
    private int longueur, champTir;
    private Orientation orientation;
    private EtatBateau etatBateau;
    private List<EtatCaseBateau> caseBateauList;
    private Position positionProue;

    public Bateau(String nom, String identifiant, int longueur, int champTir){

        this.nom = nom;
        this.identifiant = identifiant;
        this.longueur = longueur;
        this.champTir = champTir;
        this.orientation = Orientation.NORD;
        this.etatBateau = EtatBateau.PAS_COULE;
        this.positionProue = new Position(0,0);

        caseBateauList = new ArrayList<>();
        for(int i=0; i<longueur; i++) caseBateauList.add(EtatCaseBateau.PAS_TOUCHE);
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

    public EtatBateau toucher(int indexCaseTouchee){

        if(etatBateau != EtatBateau.COULE){
            this.caseBateauList.set(indexCaseTouchee, EtatCaseBateau.TOUCHE);

            if(Collections.frequency(this.caseBateauList, EtatCaseBateau.TOUCHE) >= 2){
                this.etatBateau = EtatBateau.COULE;
            }
        }
        return this.etatBateau;
    }

    public static List<Position> getPositions(Bateau bateau) {

        List<Position> positions = new ArrayList<>();

        for(int i=0 ; i<bateau.longueur; i++){
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

        return positions;

    }

    public List<Position> tirsPossibles(){
        List<Position> tirsPossiblesList = new ArrayList<>();

        switch (this.orientation) {
            case NORD:
                for(int i=0; i<this.champTir; i++){
                    tirsPossiblesList.add(new Position(this.positionProue.x-1-i, this.positionProue.y)); // +1+i
                    tirsPossiblesList.add(new Position(this.positionProue.x+this.longueur+i, this.positionProue.y)); // -long-i

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x+j, this.positionProue.y-i-1)); // -j
                        tirsPossiblesList.add(new Position(this.positionProue.x+j, this.positionProue.y+i+1)); // -j
                    }
                }
                break;
            case SUD:
                for(int i=0; i<this.champTir; i++){
                    tirsPossiblesList.add(new Position(this.positionProue.x+1+i, this.positionProue.y)); // +1+i
                    tirsPossiblesList.add(new Position(this.positionProue.x-this.longueur-i, this.positionProue.y)); // -long-i

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x-j, this.positionProue.y-i-1)); // -j
                        tirsPossiblesList.add(new Position(this.positionProue.x-j, this.positionProue.y+i+1)); // -j
                    }
                }
                break;
            case EST:
                for(int i=0; i<this.champTir; i++){
                    tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y+1+i));
                    tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y-this.longueur-i));

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x-i-1, this.positionProue.y-j));
                        tirsPossiblesList.add(new Position(this.positionProue.x+i+1, this.positionProue.y-j));
                    }
                }
                break;
            case OUEST:
                for(int i=0; i<this.champTir; i++){
                    tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y-1-i));
                    tirsPossiblesList.add(new Position(this.positionProue.x, this.positionProue.y+this.longueur+i));

                    for(int j=0; j<this.longueur; j++){
                        tirsPossiblesList.add(new Position(this.positionProue.x-i-1, this.positionProue.y+j));
                        tirsPossiblesList.add(new Position(this.positionProue.x+i+1, this.positionProue.y+j));
                    }
                }
                break;
        }

        return tirsPossiblesList;
    }

}
