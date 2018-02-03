package jeu.utils;

public enum Etat{
    VIDE(' '),
    BATEAU_NON_TOUCHE('#'),
    BATEAU_TOUCHE('X'),
    CHAMP_TIR('*'),
    TOUCHE('O'),
    PAS_TOUCHE('X');

    private final char affichage;

    private Etat(char affichage) {
        this.affichage = affichage;
    }

    public char getAffichage() {
        return this.affichage;
    }
}