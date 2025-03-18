package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.constraints.ValidCurrency;
import io.kmaker.validator.util.StringWrapperUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private String[] supportedCurrencies;

    @Override
    public void initialize(final ValidCurrency constraintAnnotation) {
        supportedCurrencies = constraintAnnotation.supportedCurrencies();
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext validatorContext) {
        if (StringWrapperUtils.isBlank(value)) {
            return false;
        }
        if (Objects.isNull(supportedCurrencies) || StringWrapperUtils.isAnyBlank(supportedCurrencies)) {
            throw new IllegalArgumentException("supportedCurrencies should not contains empty string.");
        }
        for (final var ccy : supportedCurrencies) {
            if (ccy.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
