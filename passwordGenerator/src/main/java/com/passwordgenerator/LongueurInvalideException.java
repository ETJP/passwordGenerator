package com.passwordgenerator;

/**
 * Exception personnalisée levée quand la longueur
 * demandée est hors des limites autorisées (4–64).
 */
public class LongueurInvalideException extends Exception {
    public LongueurInvalideException(String message) {
        super(message);
    }
}
