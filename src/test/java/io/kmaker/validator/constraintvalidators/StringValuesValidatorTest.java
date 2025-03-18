package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.ValidStringValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValuesValidatorTest {

    public static class POJO {
        @ValidStringValues(allowedValues = {"A", "B", "C"}, message = "Allowed value for [A, B, C]")
        public String field;
    }

    @Test
    void testAllowedValue() {
        final var msg = "Allowed value for [A, B, C]";
        final var validator = ValidationUtils.getValidator();
        final var pojo = new POJO();
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.field = "";
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.field = "D";
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.field = "A";
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.field = "B";
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.field = "C";
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

    }
}