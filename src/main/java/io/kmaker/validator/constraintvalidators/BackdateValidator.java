package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.constraints.ValidBackDate;
import io.kmaker.validator.util.DateUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

public class BackdateValidator implements ConstraintValidator<ValidBackDate, TemporalAccessor> {

    private String periodStr;

    @Override
    public void initialize(final ValidBackDate constraintAnnotation) {
        this.periodStr = constraintAnnotation.period();
    }

    @Override
    public boolean isValid(final TemporalAccessor providedDate,
                           final ConstraintValidatorContext validatorContext) {
        if (Objects.isNull(providedDate)) {
            return false;
        }

        final var period = Period.parse(periodStr);
        final var allowedBackDate = DateUtils.now()
                .minusDays(period.getDays())
                .minusMonths(period.getMonths())
                .minusMonths(period.getYears())
                .toLocalDate();

        final LocalDate dateToCheck;
        if (providedDate instanceof LocalDate providedLocalDate) {
            dateToCheck = providedLocalDate;
        } else if (providedDate instanceof LocalDateTime providedLocalDatetime) {
            dateToCheck = providedLocalDatetime.toLocalDate();
        } else {
            throw new IllegalArgumentException("Supported only LocalDate or LocalDateTime");
        }

        return dateToCheck.isAfter(allowedBackDate) || dateToCheck.isEqual(allowedBackDate);
    }
}
