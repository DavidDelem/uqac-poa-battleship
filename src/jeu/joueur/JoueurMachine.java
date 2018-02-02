package jeu.joueur;

import jeu.bateaux.Bateau;
import jeu.grille.ResultatPlacementBateau;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.Random;

public class JoueurMachine extends Joueur{

    public JoueurMachine(String nom){
        super(nom);
    }

    @Override
    public void initialiserGrilleDefense() {

        Random random = new Random();
        int x = random.nextInt(9);
        int y = random.nextInt(9);
        Orientation orientation = Orientation.values()[random.nextInt(Orientation.values().length)];

        for(Bateau itemBateau : this.grilleDefense.bateauNonPlaces()) {

            while(this.grilleDefense.placerBateau(itemBateau.getIdentifiant(), new Position(x, y), orientation) != ResultatPlacementBateau.OK) {
                x = random.nextInt(9);
                y = random.nextInt(9);
                orientation = Orientation.values()[new Random().nextInt(Orientation.values().length)];
            }
        }

        this.grilleDefense.afficherGrille();
        System.out.println("L'IA à placé tout ses bateaux");

    }


    @Override
    public Position recupererPositionTir() {

        Random random = new Random();
        Position position = new Position(random.nextInt(9), random.nextInt(9));
        return position;

    }
}
