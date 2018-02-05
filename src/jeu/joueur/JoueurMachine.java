package jeu.joueur;

import jeu.bateaux.Bateau;
import jeu.grille.ResultatPlacementBateau;
import jeu.utils.Orientation;
import jeu.utils.Position;

import java.util.Random;

public class JoueurMachine extends Joueur{

    /**
     * Initialise le joueur IA
     *
     * @param nom le nom du joueur
     * @return
     */

    public JoueurMachine(String nom){
        super(nom);
    }

    /**
     * Initialisation de la grille de défence de l'IA
     * Les positions des bateaux sont choisi aléatoirement
     *
     * @return
     */

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

        System.out.println("L'IA à placé tout ses bateaux");

    }

    /**
     * Permet à l'IA de tirer
     * La position choisie est aléatoire
     *
     * @return la position choisie
     */

    @Override
    public Position recupererPositionTir() {

        Random random = new Random();
        Position position = new Position(random.nextInt(9), random.nextInt(9));
        return position;

    }

    /**
     * Permet à l'IA de déplacer un bateau
     * Décision aléatoire et position choisie aléatoire
     *
     * @return true si l'IA veut déplacer le bateau, false si l'IA ne veut pas déplacer le bateau
     */

    @Override
    public boolean gererDeplacementBateau() {
        return false;
    }
}
