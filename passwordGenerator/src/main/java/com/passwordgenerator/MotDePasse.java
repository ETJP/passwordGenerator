package com.passwordgenerator;

/**
 * Représente un mot de passe généré.
 * Évalue automatiquement son niveau de sécurité.
 */
public class MotDePasse {
    private final String         contenu;
    private final int            longueur;
    private final NiveauSecurite niveau;

    public MotDePasse(String contenu) {
        this.contenu  = contenu;
        this.longueur = contenu.length();
        this.niveau   = evaluerNiveau();
    }

    private NiveauSecurite evaluerNiveau() {
        boolean hasMaj    = contenu.chars().anyMatch(Character::isUpperCase);
        boolean hasMin    = contenu.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit  = contenu.chars().anyMatch(Character::isDigit);
        boolean hasSymbol = contenu.chars().anyMatch(c -> !Character.isLetterOrDigit(c));

        int score = 0;
        if (longueur >= 12)   score++;
        if (longueur >= 16)   score++;
        if (hasMaj && hasMin) score++;
        if (hasDigit)         score++;
        if (hasSymbol)        score++;

        if (score >= 4) return NiveauSecurite.FORT;
        if (score >= 2) return NiveauSecurite.MOYEN;
        return NiveauSecurite.FAIBLE;
    }

    public String         getContenu() { return contenu; }
    public NiveauSecurite getNiveau()  { return niveau;  }
}
