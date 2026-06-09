package com.passwordgenerator;

/**
 * Générateur avancé : garantit la présence d'au moins un
 * caractère de chaque type activé, puis mélange le résultat.
 */
public class GenerateurAvance extends GenerateurBase {

    @Override
    public MotDePasse generer(int longueur, boolean majuscules,
                              boolean chiffres, boolean symboles)
            throws LongueurInvalideException {

        validerLongueur(longueur);
        StringBuilder sb = new StringBuilder();

        // Au moins 1 caractère de chaque type activé
        sb.append(MINUSCULES.charAt(random.nextInt(MINUSCULES.length())));
        if (majuscules) sb.append(MAJUSCULES.charAt(random.nextInt(MAJUSCULES.length())));
        if (chiffres) sb.append(CHIFFRES.charAt(random.nextInt(CHIFFRES.length())));
        if (symboles) sb.append(SYMBOLES.charAt(random.nextInt(SYMBOLES.length())));

        // Remplissage aléatoire jusqu'à la longueur voulue
        String alphabet = construireAlphabet(majuscules, chiffres, symboles);
        while (sb.length() < longueur) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        // Mélange (Fisher-Yates)
        char[] chars = sb.toString().toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new MotDePasse(new String(chars));
    }
}