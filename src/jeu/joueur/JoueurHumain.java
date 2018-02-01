package jeu.joueur;

import jeu.Partie;
import jeu.bateaux.*;
import jeu.grille.GrilleDefense;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JoueurHumain extends Joueur implements ComportementJoueur{

    protected BufferedReader br;

    public JoueurHumain(String nom){
        super(nom);
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void initialiserGrilleDefense() {

        while(this.getGrilleDefense().bateauNonPlaces().size()>0){

            this.getGrilleDefense().afficherGrille();

            System.out.println();
            System.out.println("Bateaux restants à placer :");

            for(Bateau itemBateau : this.getGrilleDefense().bateauNonPlaces()){
                for(int i=0; i<itemBateau.getLongueur(); i++) System.out.print("# ");
                System.out.print(" : "+itemBateau.getNom() + " [" + itemBateau.getIdentifiant() + "]");
                System.out.println();
            }

            System.out.println();

            String input="";

            try {
                boolean erreurSelection = true;

                while(erreurSelection){

                    System.out.println("Pour placer un bateau, indiquer la séquence IDENTIFIANT_BATEAU,COLONNE,LIGNE,ORIENTATION (Exemple CT,D,4,NORD) :");

                    input = br.readLine();

                    //Vérifier syntaxe saisie
                    //Verifier valider position bateau

                    String[] sequence = input.split(",");

                    if( !input.matches("^(CT|C|PA|SM|T),[A-J],([1-9]|10),(NORD|EST|SUD|OUEST)$") ){
                        System.out.println("Erreur : séquence invalide (syntaxe ou valeur) !");
                    } else {

                        GrilleDefense.ResultatPlacementBateau resultatPlacementBateau =
                                this.getGrilleDefense().placerBateau(sequence[0],
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

    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurHumain");
    }

}
