package io.kmaker.validator.util;

import org.apache.commons.lang3.StringUtils;

public final class StringWrapperUtils {

    private StringWrapperUtils() {
    }

    public static boolean isAnyBlank(final String... s) {
        return StringUtils.isAnyBlank(s);
    }

    public static boolean isBlank(final String s) {
        return StringUtils.isBlank(s);
    }

    public static boolean isNotBlank(final String s) {
        return StringUtils.isNotBlank(s);
    }

    public static boolean equals(final String s1,
                                 final String s2) {
        return StringUtils.equals(s1, s2);
    }

    public static boolean equalsIgnoreCase(final String s1,
                                           final String s2) {
        return StringUtils.equalsIgnoreCase(s1, s2);
    }

    public static boolean endsWith(final String s1,
                                   final String s2) {
        return StringUtils.endsWith(s1, s2);
    }

    public static boolean endsWithIgnoreCase(final String s1,
                                             final String s2) {
        return StringUtils.endsWithIgnoreCase(s1, s2);
    }

    public static boolean startsWith(final String s1,
                                     final String s2) {
        return StringUtils.startsWith(s1, s2);
    }

    public static boolean startsWithIgnoreCase(final String s1,
                                               final String s2) {
        return StringUtils.startsWithIgnoreCase(s1, s2);
    }
}
