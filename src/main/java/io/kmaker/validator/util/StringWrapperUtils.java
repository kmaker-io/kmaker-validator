package io.kmaker.validator.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

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
        return Strings.CS.equals(s1, s2);
    }

    public static boolean equalsIgnoreCase(final String s1,
                                           final String s2) {
        return Strings.CI.equals(s1, s2);
    }

    public static boolean endsWith(final String s1,
                                   final String s2) {
        return Strings.CS.endsWith(s1, s2);
    }

    public static boolean startsWith(final String s1,
                                     final String s2) {
        return Strings.CS.startsWith(s1, s2);
    }

}
