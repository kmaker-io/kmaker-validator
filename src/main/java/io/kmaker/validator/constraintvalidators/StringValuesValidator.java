package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.constraints.ValidStringValues;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringValuesValidator implements ConstraintValidator<ValidStringValues, String> {

    private String[] validValues;

    @Override
    public void initialize(final ValidStringValues constraintAnnotation) {
        validValues = constraintAnnotation.allowedValues();
    }

    @Override
    public boolean isValid(final String s,
                           final ConstraintValidatorContext validatorContext) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        return Arrays.asList(validValues).contains(s);
    }
}
