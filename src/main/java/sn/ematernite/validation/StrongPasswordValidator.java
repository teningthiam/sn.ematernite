package sn.ematernite.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validateur du code PIN / mot de passe.
 * Identique au projet de référence webgram.
 */
public class StrongPasswordValidator
        implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {
        if (value == null) return false;
        // Au moins : 1 chiffre, 1 minuscule, 1 majuscule,
        // 1 caractère spécial, 8 caractères minimum
        return value.matches(
                "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])" +
                        "(?=.*[@#$%^&+=!*()]).{8,}$");
    }
}