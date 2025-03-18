package io.kmaker.validator.constraints;

import io.kmaker.validator.constraintvalidators.BackdateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BackdateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBackDate {

    String message() default "Invalid period";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String period();
}
