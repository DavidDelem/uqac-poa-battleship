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

    private int longueur, champTir;
    private Orientation orientation;
    private EtatBateau etatBateau;
    private List<EtatCaseBateau> caseBateauList;
    private Position positionProue;

    public Bateau(int longueur, int champTir){

        this.longueur = longueur;
        this.champTir = champTir;
        this.orientation = Orientation.NORD;
        this.etatBateau = EtatBateau.PAS_COULE;
        this.positionProue = new Position(-1,-1);

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

        if(bateau.positionProue.x != -1 && bateau.positionProue.y != -1) {
            for(int i=0 ; i<bateau.longueur; i++){
                switch (bateau.orientation) {
                    case NORD:
                        positions.add(new Position(bateau.positionProue.x, bateau.positionProue.y+i));
                        break;
                    case EST:
                        positions.add(new Position(bateau.positionProue.x-i, bateau.positionProue.y));
                        break;
                    case SUD:
                        positions.add(new Position(bateau.positionProue.x, bateau.positionProue.y-i));
                        break;
                    case OUEST:
                        positions.add(new Position(bateau.positionProue.x+i, bateau.positionProue.y));
                        break;
                }
            }
        }

        return positions;

    }

}
