package jeu;

import jeu.joueur.Joueur;
import jeu.joueur.JoueurHumain;
import jeu.joueur.JoueurMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Partie {

    enum ModeJeu{
        PERSONNE,
        MACHINE
    }

    private JoueurHumain joueur1;
    private Joueur joueur2;
    private ModeJeu modeJeu;
    private BufferedReader br;

    public Partie() {
        // Demander si le joueur joue contre un autre ou contre la machine
        // Initiliser les grilles
        // tant qu'il n'y a pas de gagnant, laisser joueur les joueurs chacun leur tour

        this.br = new BufferedReader(new InputStreamReader(System.in));

        this.joueur1 = new JoueurHumain("J1");

        selectionModeJeu();
        switch (this.modeJeu){
            case PERSONNE:
                this.joueur2 = new JoueurHumain("J2");
                break;
            case MACHINE:
                this.joueur2 = new JoueurMachine("J2-IA");
                break;
        }
//
//        this.joueur1.initialiserGrilleDefense();
        this.joueur2.initialiserGrilleDefense();

        /*boolean joueur1Gagne = false;
        boolean joueur2Gagne = false;

        while(!joueur1Gagne && ! joueur2Gagne){
            this.joueur1.jouer();
            this.joueur2.jouer();
        }*/

    }

    private void selectionModeJeu(){

        String input="";

        try {
            boolean erreurSelection = true;

            while(erreurSelection){
                System.out.println("Choisir le mode de jeu :");
                System.out.println("A : Jouer contre un autre joueur humain");
                System.out.println("B : Jouer contre une IA");
                System.out.println("C : Quitter");

                input = br.readLine();

                if( input.toUpperCase().equals("A") || input.toUpperCase().equals("B") || input.toUpperCase().equals("C")){
                    erreurSelection = false;
                }
                else System.out.println("Erreur : entrée invalide");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(input.toUpperCase().equals("A")) this.modeJeu = ModeJeu.PERSONNE;
        if(input.toUpperCase().equals("B")) this.modeJeu = ModeJeu.MACHINE;
        if(input.toUpperCase().equals("C")) System.exit(0);
    }
}
