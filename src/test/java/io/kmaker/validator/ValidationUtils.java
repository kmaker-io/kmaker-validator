package io.kmaker.validator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static Validator getValidator() {
        try (final var factory = Validation.buildDefaultValidatorFactory()) {
            return factory.getValidator();
        }
    }
}
