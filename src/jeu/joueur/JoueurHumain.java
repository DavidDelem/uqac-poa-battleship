package jeu.joueur;

import jeu.Partie;
import jeu.bateaux.*;
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

    public JoueurHumain(String nom){
        super(nom);
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void initialiserGrilleDefense() {

        System.out.println();
        System.out.println("#####################################################");
        System.out.println("     Initialisation grille défense " + this.nom);
        System.out.println("#####################################################");

        while(this.grilleDefense.bateauNonPlaces().size()>0){

            this.grilleDefense.afficherGrille();

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

                    if( !input.matches("^(CT|C|PA|SM|T),[A-J],([1-9]|10),(NORD|EST|SUD|OUEST)$") ){
                        System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                    } else {

                        ResultatPlacementBateau resultatPlacementBateau =
                                this.grilleDefense.placerBateau(sequence[0],
                                new Position(Integer.parseInt(sequence[2])-1, Position.convertirColonne(sequence[1].charAt(0))),
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
        this.grilleDefense.afficherGrille();
    }

    @Override
    public Position recupererPositionTir() {
        Position position = new Position(0,0);

        String input="";

        try {
            boolean erreurSelection = true;

            while(erreurSelection){

                System.out.println("Effectuer un tir en indiquant la séquence COLONNE,LIGNE (Exemple B,5) :");

                input = br.readLine();

                if( !input.matches("^[A-J],([1-9]|10)$") ){
                    System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                } else {
                    String[] sequence = input.split(",");
                    position.x = Position.convertirColonne(sequence[0].charAt(0));
                    position.y = Integer.parseInt(sequence[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return position;
    }

}
