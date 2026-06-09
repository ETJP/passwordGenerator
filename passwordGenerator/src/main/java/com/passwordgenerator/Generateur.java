package com.passwordgenerator;

/**
 * Interface définissant le contrat de tout générateur
 * de mot de passe.
 */
public interface Generateur {
    MotDePasse generer(int longueur, boolean majuscules,
                       boolean chiffres, boolean symboles)
            throws LongueurInvalideException;
}
