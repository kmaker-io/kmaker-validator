package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.ValidDateFormat;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatValidatorTest {

    public static class POJO {
        @ValidDateFormat(pattern = "yyyy-MM-dd", message = "Provided value not valid format with 'yyyy-MM-dd'")
        public String date;
    }

    public static class POJO2 {
        @ValidDateFormat(pattern = "yyyy-MM-dd", message = "Provided value not valid format with 'yyyy-MM-dd'", optional = true)
        public String optionDateField;
    }

    private Validator validator;

    @BeforeEach
    void setup() {
        this.validator = ValidationUtils.getValidator();
    }

    @Test
    void testValidateDateFormat() {
        final var expectedMsg = "Provided value not valid format with 'yyyy-MM-dd'";
        final var pojo = new POJO();

        var violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        pojo.date = "invalid value";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        pojo.date = "22-01-22";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        pojo.date = "2022-14-01";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(expectedMsg, violations.iterator().next().getMessage());

        pojo.date = "2022-01-14";
        violations = validator.validate(pojo);
        assertEquals(0, violations.size());
    }

    @Test
    void testValidateDateFormatOptionField() {
        final var pojo = new POJO2();

        // it is optional, but if it provided value, it must correct format
        var violations = validator.validate(pojo);
        assertEquals(0, violations.size());

        pojo.optionDateField = "invalid value";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals("Provided value not valid format with 'yyyy-MM-dd'", violations.iterator().next().getMessage());

        pojo.optionDateField = "22-01-22";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals(1, violations.size());
        assertEquals("Provided value not valid format with 'yyyy-MM-dd'", violations.iterator().next().getMessage());

        pojo.optionDateField = "2022-14-01";
        violations = validator.validate(pojo);
        assertEquals(1, violations.size());
        assertEquals("Provided value not valid format with 'yyyy-MM-dd'", violations.iterator().next().getMessage());

        pojo.optionDateField = "2022-01-14";
        violations = validator.validate(pojo);
        assertEquals(0, violations.size());
    }
}