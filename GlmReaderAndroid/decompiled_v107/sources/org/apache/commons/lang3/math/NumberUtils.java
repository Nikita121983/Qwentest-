package org.apache.commons.lang3.math;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes9.dex */
public class NumberUtils {
    public static final Long LONG_ZERO = 0L;
    public static final Long LONG_ONE = 1L;
    public static final Long LONG_MINUS_ONE = -1L;
    public static final Integer INTEGER_ZERO = 0;
    public static final Integer INTEGER_ONE = 1;
    public static final Integer INTEGER_TWO = 2;
    public static final Integer INTEGER_MINUS_ONE = -1;
    public static final Short SHORT_ZERO = 0;
    public static final Short SHORT_ONE = 1;
    public static final Short SHORT_MINUS_ONE = -1;
    public static final Byte BYTE_ZERO = (byte) 0;
    public static final Byte BYTE_ONE = (byte) 1;
    public static final Byte BYTE_MINUS_ONE = (byte) -1;
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);
    public static final Long LONG_INT_MAX_VALUE = 2147483647L;
    public static final Long LONG_INT_MIN_VALUE = -2147483648L;

    public static int compare(byte x, byte y) {
        return x - y;
    }

    public static int compare(int x, int y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static int compare(long x, long y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static int compare(short x, short y) {
        if (x == y) {
            return 0;
        }
        return x < y ? -1 : 1;
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }

    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            throw new NumberFormatException("An empty string is not a valid number");
        }
        int pos = 0;
        int radix = 10;
        boolean negate = false;
        char char0 = str.charAt(0);
        if (char0 == '-') {
            negate = true;
            pos = 1;
        } else if (char0 == '+') {
            pos = 1;
        }
        if (str.startsWith("0x", pos) || str.startsWith("0X", pos)) {
            radix = 16;
            pos += 2;
        } else if (str.startsWith("#", pos)) {
            radix = 16;
            pos++;
        } else if (str.startsWith("0", pos) && str.length() > pos + 1) {
            radix = 8;
            pos++;
        }
        BigInteger value = new BigInteger(str.substring(pos), radix);
        return negate ? value.negate() : value;
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x018b, code lost:
    
        if (isZero(r14, r13) != false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01a6, code lost:
    
        if (isZero(r14, r13) != false) goto L110;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:55:0x012b. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:90:0x019a A[Catch: NumberFormatException -> 0x01aa, TryCatch #6 {NumberFormatException -> 0x01aa, blocks: (B:88:0x0190, B:90:0x019a, B:92:0x01a2), top: B:87:0x0190 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Number createNumber(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.createNumber(java.lang.String):java.lang.Number");
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    private static String getMantissa(String str, int stopPos) {
        char firstChar = str.charAt(0);
        ?? isSign = isSign(firstChar);
        int length = str.length();
        if (length <= isSign || length < stopPos) {
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return str.substring(isSign != 0 ? 1 : 0, stopPos);
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x00ec, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x007f, code lost:
    
        if (r8 >= r0.length) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0087, code lost:
    
        if (org.apache.commons.lang3.CharUtils.isAsciiNumeric(r0[r8]) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0089, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x008c, code lost:
    
        if (r0[r8] == 'e') goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0090, code lost:
    
        if (r0[r8] != 'E') goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0095, code lost:
    
        if (r0[r8] != '.') goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0097, code lost:
    
        if (r4 != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0099, code lost:
    
        if (r3 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x009c, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009d, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x009e, code lost:
    
        if (r5 != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00a4, code lost:
    
        if (r0[r8] == 'd') goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00aa, code lost:
    
        if (r0[r8] == 'D') goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b0, code lost:
    
        if (r0[r8] == 'f') goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b6, code lost:
    
        if (r0[r8] != 'F') goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b8, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00bd, code lost:
    
        if (r0[r8] == 'l') goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c3, code lost:
    
        if (r0[r8] != 'L') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c6, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c7, code lost:
    
        if (r6 == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00c9, code lost:
    
        if (r3 != false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00cb, code lost:
    
        if (r4 != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00ce, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00cf, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00d0, code lost:
    
        if (r5 != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00d2, code lost:
    
        if (r6 == false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00d5, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isCreatable(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.NumberUtils.isCreatable(java.lang.String):boolean");
    }

    public static boolean isDigits(String str) {
        return StringUtils.isNumeric(str);
    }

    @Deprecated
    public static boolean isNumber(String str) {
        return isCreatable(str);
    }

    public static boolean isParsable(String str) {
        if (StringUtils.isEmpty(str) || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            return withDecimalsParsing(str, 1);
        }
        return withDecimalsParsing(str, 0);
    }

    private static boolean isSign(char ch) {
        return ch == '-' || ch == '+';
    }

    private static boolean isZero(String mant, String dec) {
        return isAllZeros(mant) && isAllZeros(dec);
    }

    public static byte max(byte... array) {
        validateArray(array);
        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static byte max(byte a, byte b, byte c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static double max(double... array) {
        validateArray(array);
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (!Double.isNaN(array[j])) {
                if (array[j] > max) {
                    max = array[j];
                }
            } else {
                return Double.NaN;
            }
        }
        return max;
    }

    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }

    public static float max(float... array) {
        validateArray(array);
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (!Float.isNaN(array[j])) {
                if (array[j] > max) {
                    max = array[j];
                }
            } else {
                return Float.NaN;
            }
        }
        return max;
    }

    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }

    public static int max(int... array) {
        validateArray(array);
        int max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        return max;
    }

    public static int max(int a, int b, int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static long max(long... array) {
        validateArray(array);
        long max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        return max;
    }

    public static long max(long a, long b, long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static short max(short... array) {
        validateArray(array);
        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static short max(short a, short b, short c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static byte min(byte... array) {
        validateArray(array);
        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static byte min(byte a, byte b, byte c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static double min(double... array) {
        validateArray(array);
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (!Double.isNaN(array[i])) {
                if (array[i] < min) {
                    min = array[i];
                }
            } else {
                return Double.NaN;
            }
        }
        return min;
    }

    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static float min(float... array) {
        validateArray(array);
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (!Float.isNaN(array[i])) {
                if (array[i] < min) {
                    min = array[i];
                }
            } else {
                return Float.NaN;
            }
        }
        return min;
    }

    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int min(int... array) {
        validateArray(array);
        int min = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }
        return min;
    }

    public static int min(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static long min(long... array) {
        validateArray(array);
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static long min(long a, long b, long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static short min(short... array) {
        validateArray(array);
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static short min(short a, short b, short c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte defaultValue) {
        try {
            return Byte.parseByte(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    public static double toDouble(BigDecimal value) {
        return toDouble(value, 0.0d);
    }

    public static double toDouble(BigDecimal value, double defaultValue) {
        return value == null ? defaultValue : value.doubleValue();
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float defaultValue) {
        try {
            return Float.parseFloat(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int toInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    public static long toLong(String str) {
        return toLong(str, 0L);
    }

    public static long toLong(String str, long defaultValue) {
        try {
            return Long.parseLong(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(BigDecimal value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.setScale(scale, roundingMode == null ? RoundingMode.HALF_EVEN : roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Double value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Double value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(value.doubleValue()), scale, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(Float value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(Float value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(BigDecimal.valueOf(value.floatValue()), scale, roundingMode);
    }

    public static BigDecimal toScaledBigDecimal(String value) {
        return toScaledBigDecimal(value, INTEGER_TWO.intValue(), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal toScaledBigDecimal(String value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return toScaledBigDecimal(createBigDecimal(value), scale, roundingMode);
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short defaultValue) {
        try {
            return Short.parseShort(str);
        } catch (RuntimeException e) {
            return defaultValue;
        }
    }

    private static void validateArray(Object array) {
        Objects.requireNonNull(array, "array");
        Validate.isTrue(Array.getLength(array) != 0, "Array cannot be empty.", new Object[0]);
    }

    private static boolean withDecimalsParsing(String str, int beginIdx) {
        int decimalPoints = 0;
        for (int i = beginIdx; i < str.length(); i++) {
            char ch = str.charAt(i);
            boolean isDecimalPoint = ch == '.';
            if (isDecimalPoint) {
                decimalPoints++;
            }
            if (decimalPoints > 1) {
                return false;
            }
            if (!isDecimalPoint && !Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public NumberUtils() {
    }
}
