package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.ValidationUtils;
import io.kmaker.validator.constraints.ValidPeriod;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PeriodValidatorTest {

    @ValidPeriod(startDateField = "startDate", endDateField = "endDate", message = "startDate must before endDate")
    public static class POJO1 {
        private LocalDate startDate;
        private LocalDate endDate;

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }

    @ValidPeriod(startDateField = "startDate", endDateField = "endDate", message = "startDate must before endDate")
    public static class POJO2 {
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }
    }

    @ValidPeriod(startDateField = "startDate", endDateField = "endDate", message = "startDate must before endDate")
    public static class POJO3 {
        private LocalDateTime startDate;
        private LocalDate endDate;

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = ValidationUtils.getValidator();
    }

    @Test
    void testValidateFieldLocalDate() {
        final var msg = "startDate must before endDate";
        final var pojo = new POJO1();
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDate.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDate.now().minusDays(10);
        pojo.endDate = LocalDate.now().minusDays(11);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDate.now().minusDays(10);
        pojo.endDate = LocalDate.now().minusDays(9);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.startDate = LocalDate.now().minusDays(10);
        pojo.endDate = LocalDate.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());
    }

    @Test
    void testValidateFieldLocalDateTime() {
        final var msg = "startDate must before endDate";
        final var pojo = new POJO2();
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        pojo.endDate = LocalDateTime.now().minusDays(11);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        pojo.endDate = LocalDateTime.now().minusDays(9);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        pojo.endDate = LocalDateTime.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());
    }

    @Test
    void testValidateFieldMixLocalDateTime() {
        final var msg = "startDate must before endDate";
        final var pojo = new POJO3();
        var constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        pojo.endDate = LocalDate.now().minusDays(11);
        constraints = validator.validate(pojo);
        assertEquals(1, constraints.size());
        assertEquals(msg, constraints.iterator().next().getMessage());

        pojo.startDate = LocalDateTime.now().minusDays(10);
        pojo.endDate = LocalDate.now().minusDays(9);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());

        pojo.startDate = LocalDate.now().minusDays(10).atTime(0, 0);
        pojo.endDate = LocalDate.now().minusDays(10);
        constraints = validator.validate(pojo);
        assertEquals(0, constraints.size());
    }

}