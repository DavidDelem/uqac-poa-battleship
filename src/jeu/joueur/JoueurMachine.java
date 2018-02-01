package jeu.joueur;

import jeu.bateaux.Bateau;
import jeu.grille.GrilleDefense;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.Random;

public class JoueurMachine extends Joueur implements ComportementJoueur {

    public JoueurMachine(String nom){
        super(nom);
    }

    @Override
    public void initialiserGrilleDefense() {

        Random random = new Random();
        int x = random.nextInt(9);
        int y = random.nextInt(9);
        Orientation orientation = Orientation.values()[random.nextInt(Orientation.values().length)];

        for(Bateau itemBateau : this.getGrilleDefense().bateauNonPlaces()) {

            while(this.getGrilleDefense().placerBateau(itemBateau.getIdentifiant(), new Position(x, y), orientation) != GrilleDefense.ResultatPlacementBateau.OK) {
                x = random.nextInt(9);
                y = random.nextInt(9);
                orientation = Orientation.values()[new Random().nextInt(Orientation.values().length)];
            }

        }

        this.getGrilleDefense().afficherGrille();

        System.out.println("L'IA à placé tout ses bateaux");

    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurMachine");
    }
}
