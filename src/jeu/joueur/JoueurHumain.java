package jeu.joueur;

import jeu.Partie;
import jeu.bateaux.*;
import jeu.grille.Grille;
import jeu.grille.GrilleDefense;
import jeu.grille.ResultatPlacementBateau;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JoueurHumain extends Joueur{

    protected BufferedReader br;

    /**
     * Initialise le joueur humain
     *
     * @param nom le nom du joueur
     * @return
     */

    public JoueurHumain(String nom){
        super(nom);
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Initialise la grille de défence
     *
     * @return
     */

    @Override
    public void initialiserGrilleDefense() {

        System.out.println();
        System.out.println("#####################################################");
        System.out.println("     Initialisation grille défense " + this.nom);
        System.out.println("#####################################################");

        while(this.grilleDefense.bateauNonPlaces().size()>0){

            this.afficherGrilles();

            System.out.println();
            System.out.println("Bateaux restants à placer :");

            for(Bateau itemBateau : this.grilleDefense.bateauNonPlaces()){
                for(int i=0; i<itemBateau.getLongueur(); i++) System.out.print("# ");
                System.out.print(" : "+itemBateau.getNom() + " [" + itemBateau.getIdentifiant() + "]");
                System.out.println();
            }

            String input="";

            try {
                boolean erreurSelection = true;

                while(erreurSelection){

                    System.out.println();
                    System.out.println("Pour placer un bateau, indiquer la séquence IDENTIFIANT_BATEAU,COLONNE,LIGNE,ORIENTATION (Exemple CT,D,4,NORD) :");

                    input = br.readLine();

                    //Vérifier syntaxe saisie
                    //Verifier valider position bateau

                    String[] sequence = input.split(",");

                    if( !input.matches("^(CT|C|PA|SM|T|ct|c|pa|sm|t),([A-J]|[a-j]),([1-9]|10),(NORD|EST|SUD|OUEST)$") ){
                        System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                    } else {

                        ResultatPlacementBateau resultatPlacementBateau =
                                this.grilleDefense.placerBateau(sequence[0].toUpperCase(),
                                new Position(Integer.parseInt(sequence[2])-1, Position.convertirColonneInt((sequence[1].toUpperCase()).charAt(0))),
                                Orientation.convertirOrientation(sequence[3]));

                        switch (resultatPlacementBateau) {
                            case HORS_GRILLE:
                                System.out.println("Erreur : Bateau en dehors de la grille !");
                                break;
                            case SUPERPOSITION:
                                System.out.println("Erreur : bateau superposé !");
                                break;
                            case DEJA_PLACE:
                                System.out.println("Erreur : Bateau déjà placé !");
                                break;
                            case OK:
                                erreurSelection = false;
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //Aficher la grille de défense après le placement du dernier bateau
        this.afficherGrilles();
    }

    /**
     * Demande au joueur de tirer et prends en compte le tir
     * En cas d'erreur de saisie, l'utilisateur est invité à recommencer
     *
     * @return la position du tir réalisé
     */

    @Override
    public Position recupererPositionTir() {
        Position position = new Position(0,0);

        String input="";

        try {
            boolean erreurSelection = true;

            while(erreurSelection){

                System.out.println("Effectuer un tir en indiquant la séquence COLONNE,LIGNE (Exemple B,5) :");

                input = br.readLine();

                if( !input.matches("^([A-J]|[a-j]),([1-9]|10)$") ){
                    System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                } else {

                    String[] sequence = input.split(",");
                    position.x = Integer.parseInt(sequence[1]) - 1;
                    position.y = Position.convertirColonneInt((sequence[0].toUpperCase()).charAt(0));

                    for(Position itemPosition : this.grilleDefense.positionsTirsPossibles()) {
                        if(itemPosition.equals(position))  {
                            erreurSelection = false;
                            break;
                        }
                    }

                    if(erreurSelection) System.out.println("Erreur : tir hors de portée !");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return position;
    }

    /**
     * Permet à l'utilisateur de déplacer son bateau si il le souhaite et si il en a le droit
     * En cas d'erreur de saisie il est invité à recommencer
     *
     * @return true si la personne veut déplacer le bateau, false si la personne ne veut pas déplacer le bateau
     */

    @Override
    public boolean gererDeplacementBateau() {
        Position position = new Position(0,0);

        String input="";

        try {
            boolean erreurSelection = true;

            while(erreurSelection) {

                System.out.println("Vous pouvez déplacer un bateau, souhaitez-vous réaliser un déplacement ? [Y pour oui, N pour non] :");

                input = br.readLine();

                if( !input.matches("^(Y|N|y|n)$") ){
                    System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                } else {
                    if(input.equals("N") || input.equals("n")) return false;
                    else erreurSelection = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            boolean erreurSelection = true;

            while(erreurSelection) {

                System.out.println("Liste des positions bateaux :");
                for(Bateau itemBateau : this.grilleDefense.getBateauList()){
                    System.out.println("- ["+itemBateau.getIdentifiant()+"] " + itemBateau.getNom() + " : "
                            + Position.convertirColonneChar(itemBateau.getPositionProue().y) +","
                            + (itemBateau.getPositionProue().x+1) +","
                            + itemBateau.getOrientation());
                }
                System.out.println("Indiquez le bateau à déplacer [CT,OUEST,2] :");

                input = br.readLine();

                if( !input.matches("^(CT|C|PA|SM|T|ct|c|pa|sm|t),(NORD|EST|SUD|OUEST),([0-2])$") ){
                    System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                } else {

                    String[] sequence = input.split(",");

                    ResultatPlacementBateau resultatPlacementBateau =
                            this.grilleDefense.deplacerBateau(sequence[0].toUpperCase(),
                                    Orientation.convertirOrientation(sequence[1]),
                                    Integer.parseInt(sequence[2]));

                    switch (resultatPlacementBateau) {
                        case HORS_GRILLE:
                            System.out.println("Erreur : Bateau en dehors de la grille !");
                            break;
                        case SUPERPOSITION:
                            System.out.println("Erreur : bateau superposé !");
                            break;
                        case OK:
                            erreurSelection = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     * Affiche les deux grilles du joueur
     *
     * @return
     */

    public void afficherGrilles() {

        char lettre = 'A';

        this.grilleDefense.mettreAJourGrille();

        System.out.println();
        System.out.print("-------------GRILLE DE DEFENSE-------------");
        System.out.print("              ");
        System.out.println("-------------GRILLE D'ATTAQUE--------------");

        System.out.println();
        System.out.print("    ");
        for(int i=0; i < Grille.tailleGrille; i++) {
            System.out.print(" " + lettre + "  ");
            lettre++;
        }

        System.out.print("                 ");
        lettre = 'A';

        for(int i=0; i < Grille.tailleGrille; i++) {
            System.out.print(" " + lettre + "  ");
            lettre++;
        }

        System.out.println();
        System.out.print("-------------------------------------------");
        System.out.print("              ");
        System.out.println("-------------------------------------------");

        for(int i=0; i < Grille.tailleGrille; i++) {

            System.out.print((i+1<10 ? (i+1)+" " : i+1) + " |");

            for (int j = 0; j < Grille.tailleGrille; j++) {
                System.out.print(" " + this.grilleDefense.getEtatPosition(i,j).getAffichage() + " |");
            }

            System.out.print("             ");

            System.out.print((i+1<10 ? (i+1)+" " : i+1) + " |");

            for (int j = 0; j < Grille.tailleGrille; j++) {
                System.out.print(" " + this.grilleAttaque.getEtatPosition(i,j).getAffichage() + " |");
            }

            System.out.println();
            System.out.print("-------------------------------------------");
            System.out.print("              ");
            System.out.println("-------------------------------------------");
        }
    }

}
