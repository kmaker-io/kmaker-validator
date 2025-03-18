package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.constraints.ValidPeriod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.PropertyUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PeriodValidator implements ConstraintValidator<ValidPeriod, Object> {

    private String startDate;
    private String endDate;

    @Override
    public void initialize(final ValidPeriod constraintAnnotation) {
        startDate = constraintAnnotation.startDateField();
        endDate = constraintAnnotation.endDateField();
    }

    @Override
    public boolean isValid(final Object object,
                           final ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(object)) {
            return false;
        }

        final var startObj = getPropertyValue(object, startDate);
        final var endObj = getPropertyValue(object, endDate);

        if (Objects.isNull(startObj) || Objects.isNull(endObj)) {
            return false;
        }

        if (notSupportType(startObj) || notSupportType(endObj)) {
            throw new IllegalArgumentException("startDateField or endDateField must type of LocalDate or LocalDatetime");
        }

        if (startObj instanceof LocalDate startDateTime) {
            if (endObj instanceof LocalDate endDateTime) {
                return isValid(startDateTime, endDateTime);
            } else if (endObj instanceof LocalDateTime endDateTime) {
                return isValid(startDateTime, endDateTime.toLocalDate());
            }
        } else if (startObj instanceof LocalDateTime startDateTime) {
            if (endObj instanceof LocalDateTime endDateTime) {
                return isValid(startDateTime, endDateTime);
            } else if (endObj instanceof LocalDate endDateTime) {
                return isValid(startDateTime, endDateTime.atTime(0, 0));
            }
        }

        return false;
    }

    private boolean notSupportType(final Object object) {
        return !(object instanceof LocalDate) && !(object instanceof LocalDateTime);
    }

    private boolean isValid(final LocalDate startDate,
                            final LocalDate endDate) {
        return endDate.isEqual(startDate) || endDate.isAfter(startDate);
    }

    private boolean isValid(final LocalDateTime startDate,
                            final LocalDateTime endDate) {
        return endDate.isEqual(startDate) || endDate.isAfter(startDate);
    }

    private Object getPropertyValue(final Object object,
                                    final String props) {
        try {
            return PropertyUtils.getProperty(object, props);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
