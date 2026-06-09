package com.passwordgenerator;

/**
 * Générateur simple : pioche aléatoirement dans l'alphabet
 * sans garantie de présence de chaque type de caractère.
 */
public class GenerateurSimple extends GenerateurBase {

    @Override
    public MotDePasse generer(int longueur, boolean majuscules,
                              boolean chiffres, boolean symboles)
            throws LongueurInvalideException {

        validerLongueur(longueur);
        String alphabet = construireAlphabet(majuscules, chiffres, symboles);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return new MotDePasse(sb.toString());
    }
}