package jeu.bateaux;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import jeu.utils.EtatBateau;
import jeu.utils.Orientation;
import jeu.utils.Position;

public class Bateau {

    enum EtatCaseBateau{
        TOUCHE,
        PAS_TOUCHE
    }

    protected int longueur, champTir;
    protected Orientation orientation;
    protected EtatBateau etatBateau;
    protected List<EtatCaseBateau> caseList;
    protected Position positionProue; //Proue : avant du bateau

    public Bateau(int longueur, int champTir, Position position){

        this.longueur = longueur;
        this.champTir = champTir;
        this.orientation = Orientation.VERTICALE;
        this.etatBateau = EtatBateau.PAS_COULE;
        this.positionProue = position;

        caseList = new ArrayList<>();
        for(int i=0; i<longueur; i++) caseList.add(EtatCaseBateau.PAS_TOUCHE);
    }

    public void deplacer(Position position){
        this.positionProue = position;
    }

    public EtatBateau getEtatBateau() {

        /*if(this.etatBateau == EtatBateau.COULE) return EtatBateau.COULE;

        int cpt = 0;
        for (EtatCaseBateau etatCaseBateau : this.caseList) {
            if (etatCaseBateau == EtatCaseBateau.TOUCHE) cpt++;
        }
        if(cpt >= 2) this.etatBateau = EtatBateau.COULE;*/

        return this.etatBateau;
    }

}
