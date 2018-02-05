package jeu.utils;

public enum Etat{
    VIDE(' '),
    //Grille attaque
    TOUCHE('O'),
    PAS_TOUCHE('X'),
    //Grille défense
    BATEAU_NON_TOUCHE('#'),
    BATEAU_TOUCHE('X'),
    CHAMP_TIR('*');

    private final char affichage;

    /**
     * Initialise l'état
     *
     * @param affichage l'état
     * @return
     */

    private Etat(char affichage) {
        this.affichage = affichage;
    }

    public char getAffichage() {
        return this.affichage;
    }
}