package org.apache.commons.lang3;

import java.util.Objects;
import java.util.function.IntFunction;

/* loaded from: classes9.dex */
public class CharUtils {
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final char NUL = 0;
    private static final String[] CHAR_STRING_ARRAY = (String[]) ArrayUtils.setAll(new String[128], new IntFunction() { // from class: org.apache.commons.lang3.CharUtils$$ExternalSyntheticLambda0
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            String valueOf;
            valueOf = String.valueOf((char) i);
            return valueOf;
        }
    });
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static int compare(char x, char y) {
        return x - y;
    }

    public static boolean isAscii(char ch) {
        return ch < 128;
    }

    public static boolean isAsciiAlpha(char ch) {
        return isAsciiAlphaUpper(ch) || isAsciiAlphaLower(ch);
    }

    public static boolean isAsciiAlphaLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public static boolean isAsciiAlphanumeric(char ch) {
        return isAsciiAlpha(ch) || isAsciiNumeric(ch);
    }

    public static boolean isAsciiAlphaUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isAsciiControl(char ch) {
        return ch < ' ' || ch == 127;
    }

    public static boolean isAsciiNumeric(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isAsciiPrintable(char ch) {
        return ch >= ' ' && ch < 127;
    }

    public static boolean isHex(char ch) {
        return isAsciiNumeric(ch) || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F');
    }

    public static boolean isOctal(char ch) {
        return ch >= '0' && ch <= '7';
    }

    public static char toChar(Character ch) {
        return ((Character) Objects.requireNonNull(ch, "ch")).charValue();
    }

    public static char toChar(Character ch, char defaultValue) {
        return ch != null ? ch.charValue() : defaultValue;
    }

    public static char toChar(String str) {
        Validate.notEmpty(str, "The String must not be empty", new Object[0]);
        return str.charAt(0);
    }

    public static char toChar(String str, char defaultValue) {
        return StringUtils.isEmpty(str) ? defaultValue : str.charAt(0);
    }

    @Deprecated
    public static Character toCharacterObject(char c) {
        return Character.valueOf(c);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static int toIntValue(char ch) {
        if (!isAsciiNumeric(ch)) {
            throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
        }
        return ch - '0';
    }

    public static int toIntValue(char ch, int defaultValue) {
        return isAsciiNumeric(ch) ? ch - '0' : defaultValue;
    }

    public static int toIntValue(Character ch) {
        return toIntValue(toChar(ch));
    }

    public static int toIntValue(Character ch, int defaultValue) {
        return ch != null ? toIntValue(ch.charValue(), defaultValue) : defaultValue;
    }

    public static String toString(char ch) {
        if (ch < CHAR_STRING_ARRAY.length) {
            return CHAR_STRING_ARRAY[ch];
        }
        return String.valueOf(ch);
    }

    public static String toString(Character ch) {
        if (ch != null) {
            return toString(ch.charValue());
        }
        return null;
    }

    public static String unicodeEscaped(char ch) {
        return "\\u" + HEX_DIGITS[(ch >> '\f') & 15] + HEX_DIGITS[(ch >> '\b') & 15] + HEX_DIGITS[(ch >> 4) & 15] + HEX_DIGITS[ch & 15];
    }

    public static String unicodeEscaped(Character ch) {
        if (ch != null) {
            return unicodeEscaped(ch.charValue());
        }
        return null;
    }

    @Deprecated
    public CharUtils() {
    }
}
