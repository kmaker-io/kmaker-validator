package io.kmaker.validator.constraintvalidators;

import io.kmaker.validator.constraints.AtLeastOneField;
import io.kmaker.validator.util.StringWrapperUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Objects;

@SuppressWarnings("ALL")
public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, Object> {
    private String[] fields;

    @Override
    public void initialize(final AtLeastOneField constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(final Object value,
                           final ConstraintValidatorContext validatorContext) {
        if (Objects.isNull(value)) {
            return false;
        }
        for (final var field : fields) {
            try {
                final var fieldValue = PropertyUtils.getProperty(value, field);
                if (Objects.nonNull(fieldValue) && !(value instanceof String)) {
                    return true;
                }
                if (StringWrapperUtils.isNotBlank((String) fieldValue)) {
                    return true;
                }
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
