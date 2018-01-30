package jeu.bateaux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jeu.utils.Orientation;
import jeu.utils.Position;

public class Bateau {

    enum EtatCaseBateau{
        TOUCHE,
        PAS_TOUCHE
    }

    private int longueur, champTir;
    private Orientation orientation;
    private EtatBateau etatBateau;
    private List<EtatCaseBateau> caseBateauList;
    private Position position;

    public Bateau(int longueur, int champTir){

        this.longueur = longueur;
        this.champTir = champTir;
        this.orientation = Orientation.NORD;
        this.etatBateau = EtatBateau.PAS_COULE;
        this.position = new Position(-1,-1);

        caseBateauList = new ArrayList<>();
        for(int i=0; i<longueur; i++) caseBateauList.add(EtatCaseBateau.PAS_TOUCHE);
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
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

}
