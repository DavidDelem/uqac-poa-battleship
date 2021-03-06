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

    /**
     * Lance la partie
     *
     * @return
     */

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

        boolean joueur1Gagne = false;
        boolean joueur2Gagne = false;

        int cptTour = 1;
        while(!joueur1Gagne && ! joueur2Gagne){
            joueur1Gagne = jouer(this.joueur1, this.joueur2, cptTour);
            if(!joueur1Gagne) joueur2Gagne = jouer(this.joueur2, this.joueur1, cptTour);
            cptTour++;
        }

        if(joueur1Gagne) {
            System.out.println(" __      __  _____    _____   _______    ____    _____   _____    ______     _   _   _ ");
            System.out.println(" \\ \\    / / |_   _|  / ____| |__   __|  / __ \\  |_   _| |  __ \\  |  ____|   | | | | | |");
            System.out.println("  \\ \\  / /    | |   | |         | |    | |  | |   | |   | |__) | | |__      | | | | | |");
            System.out.println("   \\ \\/ /     | |   | |         | |    | |  | |   | |   |  _  /  |  __|     | | | | | |");
            System.out.println("    \\  /     _| |_  | |____     | |    | |__| |  _| |_  | | \\ \\  | |____    |_| |_| |_|");
            System.out.println("     \\/     |_____|  \\_____|    |_|     \\____/  |_____| |_|  \\_\\ |______|   (_) (_) (_)");
            System.out.println("                                                             ");
            System.out.println("                     Pour joueur1: " + joueur1.getNom());
        } else if(joueur2Gagne) {
            System.out.println(" __      __  _____    _____   _______    ____    _____   _____    ______     _   _   _ ");
            System.out.println(" \\ \\    / / |_   _|  / ____| |__   __|  / __ \\  |_   _| |  __ \\  |  ____|   | | | | | |");
            System.out.println("  \\ \\  / /    | |   | |         | |    | |  | |   | |   | |__) | | |__      | | | | | |");
            System.out.println("   \\ \\/ /     | |   | |         | |    | |  | |   | |   |  _  /  |  __|     | | | | | |");
            System.out.println("    \\  /     _| |_  | |____     | |    | |__| |  _| |_  | | \\ \\  | |____    |_| |_| |_|");
            System.out.println("     \\/     |_____|  \\_____|    |_|     \\____/  |_____| |_|  \\_\\ |______|   (_) (_) (_)");
            System.out.println("                                                             ");
            System.out.println("                     Pour joueur2: " + joueur2.getNom());
        }

    }

    /**
     * Permet la sélection du mode de jeu
     * contre un humain ou contre l'IA
     *
     * @return
     */

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

    /**
     * Permet de jouer un tour
     *
     * @param joueurJoue le joueur qui joue à ce tour
     * @param joueurAdversaire le joueur adversaire
     * @param numTour le numéro du tour
     *
     * @return true si le joueur à gagné, false sinon
     */

    private boolean jouer(Joueur joueurJoue, Joueur joueurAdversaire, int numTour){

        System.out.println();
        System.out.println("#####################################################################################################");
        System.out.println("                  Tour numéro " + numTour + " de "+joueurJoue.getNom() + " : " + joueurAdversaire.getGrilleDefense().getNombreBateau()+" bateau(x) à couler");
        System.out.println("#####################################################################################################");
        System.out.println();

        /*--------------------*/
        /* Tour du joueurJoue */
        /*--------------------*/
        //Affichage des grilles du joueur
        if(joueurJoue.getClass() == JoueurHumain.class){
            ((JoueurHumain)joueurJoue).afficherGrilles();
        }

        // Déplacement du bateau
        if(numTour == 1){
            System.out.println("Pas de déplacement possible au tour 1 !");
        }
        else if (!joueurJoue.getDroitDeplacement()){
            System.out.println("Vous avez été touché au tour précédent, vous ne pouvez pas déplacer de bateau !");
        }
        else if(joueurJoue.gererDeplacementBateau() && joueurJoue.getClass() == JoueurHumain.class){
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

        if(joueurAdversaire.getGrilleDefense().getNombreBateau() == 0) return true;
        return false;
    }

}
