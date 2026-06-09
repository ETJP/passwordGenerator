package com.passwordgenerator;

import java.security.SecureRandom;

/**
 * Classe abstraite fournissant les utilitaires communs
 * aux générateurs concrets.
 */
public abstract class GenerateurBase implements Generateur {

    protected static final String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
    protected static final String MAJUSCULES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static final String CHIFFRES   = "0123456789";
    protected static final String SYMBOLES   = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    protected final SecureRandom random = new SecureRandom();

    /** Vérifie que la longueur est dans les bornes [4, 64]. */
    protected void validerLongueur(int longueur) throws LongueurInvalideException {
        if (longueur < 4)
            throw new LongueurInvalideException("La longueur minimale est 4 caractères.");
        if (longueur > 64)
            throw new LongueurInvalideException("La longueur maximale est 64 caractères.");
    }

    /** Construit l'alphabet selon les options choisies. */
    protected String construireAlphabet(boolean majuscules,
                                        boolean chiffres,
                                        boolean symboles) {
        StringBuilder alphabet = new StringBuilder(MINUSCULES);
        if (majuscules) alphabet.append(MAJUSCULES);
        if (chiffres)   alphabet.append(CHIFFRES);
        if (symboles)   alphabet.append(SYMBOLES);
        return alphabet.toString();
    }
}
