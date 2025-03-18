package io.kmaker.validator.constraints;

import io.kmaker.validator.constraintvalidators.StringValuesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StringValuesValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStringValues {

    String message() default "Invalid provided value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowedValues();
}
