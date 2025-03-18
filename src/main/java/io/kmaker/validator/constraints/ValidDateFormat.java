package io.kmaker.validator.constraints;

import io.kmaker.validator.constraintvalidators.DateFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateFormatValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateFormat {

    String message() default "Invalid date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    ISO iso() default ISO.DATE;

    String pattern();

    boolean optional() default false;

    enum ISO {
        DATE,
        TIME,
        DATE_TIME
    }
}
