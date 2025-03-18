package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.ValidCurrency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyValidatorTest {

    public static class POJO {
        @ValidCurrency(supportedCurrencies = { "KHR", "USD"}, message = "Currency must KHR or USD")
        public String currency;
    }

    @Test
    void testValidateCurrency() {
        final var expectedMsg = "Currency must KHR or USD";
        final var validator = ValidationUtils.getValidator();
        final var pojo = new POJO();

        var violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        pojo.currency = "THB";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        // lower or upper or mix case, it should consider valid value
        pojo.currency = "KHR";
        violations = validator.validate(pojo);
        assertEquals(0, violations.size());

        pojo.currency = "khr";
        violations = validator.validate(pojo);
        assertEquals(0, violations.size());

        pojo.currency = "Usd";
        violations = validator.validate(pojo);
        assertEquals(0, violations.size());
    }

}