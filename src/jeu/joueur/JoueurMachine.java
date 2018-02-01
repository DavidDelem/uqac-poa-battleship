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
        int x = random.nextInt(10 - 1 + 1) + 1;
        int y = random.nextInt(10 - 1 + 1) + 1;
        Orientation orientation = Orientation.values()[random.nextInt(Orientation.values().length)];

        for(Bateau itemBateau : this.getGrilleDefense().bateauNonPlaces()) {

            while(!this.getGrilleDefense().placerBateau(itemBateau.getIdentifiant(), new Position(x, y), orientation)); {
                x = random.nextInt(10 - 1 + 1) + 1;
                y = random.nextInt(10 - 1 + 1) + 1;
                orientation = Orientation.values()[new Random().nextInt(Orientation.values().length)];
            }

        }

        System.out.println("L'IA à placé tout ses bateaux");

    }

    @Override
    public void jouer() {
        System.out.println("Joue depuis JoueurMachine");
    }
}
