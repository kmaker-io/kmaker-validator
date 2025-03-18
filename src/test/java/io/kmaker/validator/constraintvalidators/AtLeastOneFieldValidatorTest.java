package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.AtLeastOneField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtLeastOneFieldValidatorTest {

    @AtLeastOneField(fields = { "p1", "p2"}, message = "At least p1 or p2 provided value")
    public static class POJO {
        private String p1;
        private String p2;

        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }
    }

    @Test
    void testAtLeastOneFieldValidation() {
        final var validator = ValidationUtils.getValidator();

        final var pojo = new POJO();
        var constraintViolations = validator.validate(pojo);

        assertEquals(1, constraintViolations.size());
        assertEquals("At least p1 or p2 provided value", constraintViolations.iterator().next().getMessage());

        pojo.p1 = "P1";
        constraintViolations = validator.validate(pojo);
        assertEquals(0, constraintViolations.size());

        pojo.p2 = "P2";
        pojo.p1 = null;
        constraintViolations = validator.validate(pojo);
        assertEquals(0, constraintViolations.size());

        pojo.p2 = "P2";
        pojo.p1 = "P1";
        constraintViolations = validator.validate(pojo);
        assertEquals(0, constraintViolations.size());
    }

}