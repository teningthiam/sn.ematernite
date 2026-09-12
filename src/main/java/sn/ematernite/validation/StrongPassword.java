package sn.ematernite.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation du code PIN.
 * Adapté du projet de référence webgram pour e-Maternité Sénégal.
 */
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {

    String message() default
            "Le code PIN doit contenir au moins 8 caractères avec " +
                    "majuscules, minuscules, chiffres et caractères spéciaux.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}