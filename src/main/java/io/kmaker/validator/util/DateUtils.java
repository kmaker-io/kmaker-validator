package io.kmaker.validator.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DateUtils {

    private DateUtils() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDate today() {
        return LocalDate.now();
    }
}
