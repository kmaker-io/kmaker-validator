package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.ValidBackDate;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BackdateValidatorTest {

    static class POJO1 {
        @ValidBackDate(period = "P0Y0M90D", message = "Provided data are allow backdate only 3 months")
        public LocalDateTime startDate;
    }

    static class POJO2 {
        @ValidBackDate(period = "P0Y0M90D", message = "Provided data are allow backdate only 3 months")
        public LocalDate startDate;
    }

    private Validator validator;

    @BeforeEach
    void setup() {
        this.validator = ValidationUtils.getValidator();
    }

    @Test
    void testLocalDateTimePeriodValidation() {
        final var pojo = new POJO1();
        pojo.startDate = LocalDateTime.of(LocalDate.of(2024, 12, 1), LocalTime.now());
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals("Provided data are allow backdate only 3 months", constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(80);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.startDate = LocalDateTime.now().minusDays(90);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());
    }

    @Test
    void testLocalDatePeriodValidation() {
        final var pojo = new POJO2();
        pojo.startDate = LocalDate.of(2024, 12, 1);
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals("Provided data are allow backdate only 3 months", constraints.iterator().next().getMessage());

        pojo.startDate = LocalDate.now().minusDays(80);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.startDate = LocalDate.now().minusDays(90);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());
    }

}