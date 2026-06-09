package com.passwordgenerator;

import javax.swing.SwingUtilities;

/**
 * Point d'entrée de l'application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGeneratorUI::new);
    }
}
