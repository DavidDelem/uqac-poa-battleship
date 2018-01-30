package jeu;

import jeu.bateaux.*;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    enum EtatCaseGrille{
        VIDE,
        BATEAU
    }

    private EtatCaseGrille[][] grille;
    private List<Bateau> bateauList;

    public Grille (int nbLignes, int nbColonnes){

        this.grille = new EtatCaseGrille[nbLignes][nbColonnes];
        for(int i=0; i<this.grille.length; i++) {
            for(int j=0; j<this.grille[i].length; j++) this.grille[i][j] = EtatCaseGrille.VIDE;
        }

        this.bateauList = new ArrayList<>();
        this.bateauList.add(new ContreTorpilleur());
        this.bateauList.add(new Croiseur());
        this.bateauList.add(new PorteAvion());
        this.bateauList.add(new SousMarin());
        this.bateauList.add(new Torpilleur());
    }

}
