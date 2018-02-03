package jeu;

import jeu.joueur.Joueur;
import jeu.joueur.JoueurHumain;
import jeu.joueur.JoueurMachine;
import jeu.utils.Orientation;
import jeu.utils.Position;

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

        this.joueur1.initialiserGrilleDefense();
        this.joueur2.initialiserGrilleDefense();

//        this.joueur1.getGrilleDefense().placerBateau("CT",
//                new Position(0, 0),
//                Orientation.NORD);
//        this.joueur1.getGrilleDefense().placerBateau("C",
//                new Position(0, 1),
//                Orientation.NORD);
//        this.joueur1.getGrilleDefense().placerBateau("PA",
//                new Position(0, 2),
//                Orientation.NORD);
//        this.joueur1.getGrilleDefense().placerBateau("SM",
//                new Position(0, 3),
//                Orientation.NORD);
//        this.joueur1.getGrilleDefense().placerBateau("T",
//                new Position(0, 4),
//                Orientation.NORD);
//
//        this.joueur2.getGrilleDefense().placerBateau("CT",
//                new Position(9, 9),
//                Orientation.SUD);
//        this.joueur2.getGrilleDefense().placerBateau("C",
//                new Position(9, 8),
//                Orientation.SUD);
//        this.joueur2.getGrilleDefense().placerBateau("PA",
//                new Position(9, 7),
//                Orientation.SUD);
//        this.joueur2.getGrilleDefense().placerBateau("SM",
//                new Position(9, 6),
//                Orientation.SUD);
//        this.joueur2.getGrilleDefense().placerBateau("T",
//                new Position(9, 5),
//                Orientation.SUD);

        boolean joueur1Gagne = false;
        boolean joueur2Gagne = false;

        int cptTour = 1;
        while(!joueur1Gagne && ! joueur2Gagne){
            joueur1Gagne = jouer(this.joueur1, this.joueur2, cptTour);
            joueur2Gagne = jouer(this.joueur2, this.joueur1, cptTour);
            cptTour++;
        }

    }

    private void selectionModeJeu(){
        String input="";

        System.out.println("  ____            _     _     _           _____   _       _         ");
        System.out.println(" |  _ \\          | |   | |   | |         / ____| | |     (_)        ");
        System.out.println(" | |_) |   __ _  | |_  | |_  | |   ___  | (___   | |__    _   _ __  ");
        System.out.println(" |  _ <   / _` | | __| | __| | |  / _ \\  \\___ \\  | '_ \\  | | | '_ \\ ");
        System.out.println(" | |_) | | (_| | | |_  | |_  | | |  __/  ____) | | | | | | | | |_) |");
        System.out.println(" |____/   \\__,_|  \\__|  \\__| |_|  \\___| |_____/  |_| |_| |_| | .__/ ");
        System.out.println("                                                             | |    ");
        System.out.println(" David Delemotte & Rénald Morice - UQAC 8INF957 - Hiver 2018 |_|    ");
        System.out.println();
        System.out.println();
        System.out.println("#####################################################");
        System.out.println("    Choix du mode de jeu");
        System.out.println("#####################################################");
        System.out.println();

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

    private boolean jouer(Joueur joueurJoue, Joueur joueurAdversaire, int numTour){

        System.out.println();
        System.out.println("#####################################################################################################");
        System.out.println("                                         Tour numéro " + numTour + " de "+joueurJoue.getNom());
        System.out.println("#####################################################################################################");
        System.out.println();

        /*--------------------*/
        /* Tour du joueurJoue */
        /*--------------------*/
        //Affichage des grilles du joueur
        if(joueurJoue.getClass() == JoueurHumain.class){
            ((JoueurHumain)joueurJoue).afficherGrilles();
        }

        //Récupération de la position du tir de joueurJoue
        Position positionTir = joueurJoue.recupererPositionTir();
        //MAJ Grille defense joueurAdversaire
        boolean joueurAdversaireTouche = joueurAdversaire.getGrilleDefense().verifierTirAdversaire(positionTir);
        //MAJ Grille attaque de joueurJoue
        joueurJoue.getGrilleAttaque().tir(positionTir, joueurAdversaireTouche);

        //joueurAdversaire n'a pas le droit de déplacer un bateau si joueurJoue a touché un de ses bateaux
        if(joueurAdversaireTouche) joueurAdversaire.setDroitDeplacement(false);
        else joueurAdversaire.setDroitDeplacement(true);

        //TODO : renvoyer true quand tous les bateaux adverses sont coulés !
        return false;
    }

}
