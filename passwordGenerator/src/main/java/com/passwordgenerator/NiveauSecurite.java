package com.passwordgenerator;

import java.awt.Color;

/**
 * Enum représentant le niveau de sécurité d'un mot de passe.
 */
public enum NiveauSecurite {
    FAIBLE("Faible", new Color(220, 53, 69)),
    MOYEN ("Moyen",  new Color(255, 193, 7)),
    FORT  ("Fort",   new Color(40, 167, 69));

    private final String label;
    private final Color  couleur;

    NiveauSecurite(String label, Color couleur) {
        this.label   = label;
        this.couleur = couleur;
    }

    public String getLabel()   { return label;   }
    public Color  getCouleur() { return couleur; }
}
