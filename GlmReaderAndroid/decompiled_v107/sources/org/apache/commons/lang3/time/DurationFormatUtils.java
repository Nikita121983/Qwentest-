package org.apache.commons.lang3.time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DurationFormatUtils;

/* loaded from: classes9.dex */
public class DurationFormatUtils {
    static final String H = "H";
    private static final int HOURS_PER_DAY = 24;
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.SSS'S'";
    static final String M = "M";
    private static final int MINUTES_PER_HOUR = 60;
    static final String S = "S";
    private static final int SECONDS_PER_MINUTES = 60;
    static final String d = "d";
    static final String m = "m";
    static final String s = "s";
    static final String y = "y";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class Token {
        private static final Token[] EMPTY_ARRAY = new Token[0];
        private int count = 1;
        private int optionalIndex;
        private final CharSequence value;

        static boolean containsTokenWithValue(Token[] tokens, final Object value) {
            return Stream.of((Object[]) tokens).anyMatch(new Predicate() { // from class: org.apache.commons.lang3.time.DurationFormatUtils$Token$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DurationFormatUtils.Token.lambda$containsTokenWithValue$0(value, (DurationFormatUtils.Token) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$containsTokenWithValue$0(Object value, Token token) {
            return token.getValue() == value;
        }

        Token(CharSequence value, boolean optional, int optionalIndex) {
            this.optionalIndex = -1;
            this.value = (CharSequence) Objects.requireNonNull(value, "value");
            if (optional) {
                this.optionalIndex = optionalIndex;
            }
        }

        public boolean equals(Object obj2) {
            if (!(obj2 instanceof Token)) {
                return false;
            }
            Token tok2 = (Token) obj2;
            if (this.value.getClass() != tok2.value.getClass() || this.count != tok2.count) {
                return false;
            }
            if (this.value instanceof StringBuilder) {
                return this.value.toString().equals(tok2.value.toString());
            }
            if (this.value instanceof Number) {
                return this.value.equals(tok2.value);
            }
            return this.value == tok2.value;
        }

        int getCount() {
            return this.count;
        }

        Object getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        void increment() {
            this.count++;
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }
    }

    static String format(Token[] tokens, long years, long months, long days, long hours, long minutes, long seconds, long milliseconds, boolean padWithZeros) {
        int optionalStart;
        int optionalIndex;
        StringBuilder buffer;
        boolean lastOutputSeconds;
        boolean lastOutputZero;
        boolean lastOutputZero2;
        long j = years;
        long j2 = months;
        StringBuilder buffer2 = new StringBuilder();
        boolean lastOutputSeconds2 = false;
        boolean lastOutputZero3 = false;
        int optionalIndex2 = -1;
        boolean firstOptionalNonLiteral = false;
        boolean inOptional = false;
        int length = tokens.length;
        int optionalIndex3 = -1;
        int i = 0;
        while (i < length) {
            Token token = tokens[i];
            int i2 = i;
            Object value = token.getValue();
            int i3 = length;
            boolean isLiteral = value instanceof StringBuilder;
            int count = token.getCount();
            if (optionalIndex3 != token.optionalIndex) {
                int optionalIndex4 = token.optionalIndex;
                if (optionalIndex4 > -1) {
                    optionalStart = buffer2.length();
                    lastOutputZero3 = false;
                    firstOptionalNonLiteral = false;
                    inOptional = true;
                    optionalIndex3 = optionalIndex4;
                } else {
                    optionalIndex3 = optionalIndex4;
                    inOptional = false;
                    optionalStart = optionalIndex2;
                }
            } else {
                optionalStart = optionalIndex2;
            }
            if (!isLiteral) {
                optionalIndex = optionalIndex3;
                buffer = buffer2;
                if (value.equals(y)) {
                    lastOutputSeconds2 = false;
                    boolean lastOutputZero4 = j == 0;
                    if (inOptional && lastOutputZero4) {
                        lastOutputZero2 = lastOutputZero4;
                    } else {
                        lastOutputZero2 = lastOutputZero4;
                        buffer.append(paddedValue(j, padWithZeros, count));
                    }
                    lastOutputZero3 = lastOutputZero2;
                } else if (value.equals(M)) {
                    boolean lastOutputZero5 = j2 == 0;
                    if (!inOptional || !lastOutputZero5) {
                        buffer.append(paddedValue(j2, padWithZeros, count));
                    }
                    lastOutputSeconds2 = false;
                    lastOutputZero3 = lastOutputZero5;
                } else {
                    if (value.equals(d)) {
                        lastOutputSeconds = false;
                        lastOutputZero = days == 0;
                        if (!inOptional || !lastOutputZero) {
                            buffer.append(paddedValue(days, padWithZeros, count));
                        }
                    } else if (value.equals(H)) {
                        lastOutputSeconds = false;
                        lastOutputZero = hours == 0;
                        if (!inOptional || !lastOutputZero) {
                            buffer.append(paddedValue(hours, padWithZeros, count));
                        }
                    } else if (value.equals(m)) {
                        lastOutputSeconds = false;
                        lastOutputZero = minutes == 0;
                        if (!inOptional || !lastOutputZero) {
                            buffer.append(paddedValue(minutes, padWithZeros, count));
                        }
                    } else if (value.equals(s)) {
                        boolean lastOutputZero6 = seconds == 0;
                        if (inOptional && lastOutputZero6) {
                            lastOutputSeconds2 = true;
                        } else {
                            lastOutputSeconds2 = true;
                            buffer.append(paddedValue(seconds, padWithZeros, count));
                        }
                        lastOutputZero3 = lastOutputZero6;
                    } else if (value.equals(S)) {
                        boolean lastOutputZero7 = milliseconds == 0;
                        if (!inOptional || !lastOutputZero7) {
                            if (lastOutputSeconds2) {
                                int width = padWithZeros ? Math.max(3, count) : 3;
                                buffer.append(paddedValue(milliseconds, true, width));
                            } else {
                                buffer.append(paddedValue(milliseconds, padWithZeros, count));
                            }
                        }
                        lastOutputSeconds2 = false;
                        lastOutputZero3 = lastOutputZero7;
                    }
                    lastOutputSeconds2 = lastOutputSeconds;
                    lastOutputZero3 = lastOutputZero;
                }
            } else if (inOptional && lastOutputZero3) {
                optionalIndex = optionalIndex3;
                buffer = buffer2;
            } else {
                optionalIndex = optionalIndex3;
                buffer = buffer2;
                buffer.append(value.toString());
            }
            if (inOptional && !isLiteral && !firstOptionalNonLiteral) {
                if (lastOutputZero3) {
                    buffer.delete(optionalStart, buffer.length());
                }
                firstOptionalNonLiteral = true;
            }
            j2 = months;
            i = i2 + 1;
            buffer2 = buffer;
            optionalIndex3 = optionalIndex;
            length = i3;
            j = years;
            optionalIndex2 = optionalStart;
        }
        return buffer2.toString();
    }

    public static String formatDuration(long durationMillis, String format) {
        return formatDuration(durationMillis, format, true);
    }

    public static String formatDuration(long durationMillis, String format, boolean padWithZeros) {
        long milliseconds;
        long minutes;
        long milliseconds2;
        long seconds;
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, durationMillis, "durationMillis must not be negative");
        Token[] tokens = lexx(format);
        long days = 0;
        long hours = 0;
        long milliseconds3 = durationMillis;
        if (Token.containsTokenWithValue(tokens, d)) {
            days = milliseconds3 / 86400000;
            milliseconds3 -= 86400000 * days;
        }
        if (Token.containsTokenWithValue(tokens, H)) {
            hours = milliseconds3 / DateUtils.MILLIS_PER_HOUR;
            milliseconds3 -= DateUtils.MILLIS_PER_HOUR * hours;
        }
        if (!Token.containsTokenWithValue(tokens, m)) {
            long j = milliseconds3;
            milliseconds = 0;
            minutes = j;
        } else {
            long minutes2 = milliseconds3 / DateUtils.MILLIS_PER_MINUTE;
            long milliseconds4 = milliseconds3 - (DateUtils.MILLIS_PER_MINUTE * minutes2);
            milliseconds = minutes2;
            minutes = milliseconds4;
        }
        if (!Token.containsTokenWithValue(tokens, s)) {
            milliseconds2 = minutes;
            seconds = 0;
        } else {
            long seconds2 = minutes / 1000;
            milliseconds2 = minutes - (1000 * seconds2);
            seconds = seconds2;
        }
        return format(tokens, 0L, 0L, days, hours, milliseconds, seconds, milliseconds2, padWithZeros);
    }

    public static String formatDurationHMS(long durationMillis) {
        return formatDuration(durationMillis, "HH:mm:ss.SSS");
    }

    public static String formatDurationISO(long durationMillis) {
        return formatDuration(durationMillis, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDurationWords(long durationMillis, boolean suppressLeadingZeroElements, boolean suppressTrailingZeroElements) {
        String duration = formatDuration(durationMillis, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (suppressLeadingZeroElements) {
            duration = StringUtils.SPACE + duration;
            String tmp = Strings.CS.replaceOnce(duration, " 0 days", "");
            if (tmp.length() != duration.length()) {
                duration = tmp;
                String tmp2 = Strings.CS.replaceOnce(duration, " 0 hours", "");
                if (tmp2.length() != duration.length()) {
                    duration = Strings.CS.replaceOnce(tmp2, " 0 minutes", "");
                }
            }
            if (!duration.isEmpty()) {
                duration = duration.substring(1);
            }
        }
        if (suppressTrailingZeroElements) {
            String text = duration;
            String tmp3 = Strings.CS.replaceOnce(text, " 0 seconds", "");
            if (tmp3.length() != duration.length()) {
                duration = tmp3;
                String tmp4 = Strings.CS.replaceOnce(duration, " 0 minutes", "");
                if (tmp4.length() != duration.length()) {
                    duration = tmp4;
                    String tmp5 = Strings.CS.replaceOnce(duration, " 0 hours", "");
                    if (tmp5.length() != duration.length()) {
                        duration = Strings.CS.replaceOnce(tmp5, " 0 days", "");
                    }
                }
            }
        }
        return Strings.CS.replaceOnce(Strings.CS.replaceOnce(Strings.CS.replaceOnce(Strings.CS.replaceOnce(StringUtils.SPACE + duration, " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriod(long startMillis, long endMillis, String format) {
        return formatPeriod(startMillis, endMillis, format, true, TimeZone.getDefault());
    }

    public static String formatPeriod(long startMillis, long endMillis, String format, boolean padWithZeros, TimeZone timezone) {
        int months;
        int years;
        long milliseconds;
        int seconds;
        Validate.isTrue(startMillis <= endMillis, "startMillis must not be greater than endMillis", new Object[0]);
        Token[] tokens = lexx(format);
        Calendar start = Calendar.getInstance(timezone);
        start.setTime(new Date(startMillis));
        Calendar end = Calendar.getInstance(timezone);
        end.setTime(new Date(endMillis));
        long milliseconds2 = end.get(14) - start.get(14);
        int seconds2 = end.get(13) - start.get(13);
        int minutes = end.get(12) - start.get(12);
        int hours = end.get(11) - start.get(11);
        int i = 5;
        int days = end.get(5) - start.get(5);
        int months2 = end.get(2) - start.get(2);
        int years2 = end.get(1) - start.get(1);
        while (milliseconds2 < 0) {
            milliseconds2 += 1000;
            seconds2--;
        }
        while (seconds2 < 0) {
            seconds2 += 60;
            minutes--;
        }
        while (minutes < 0) {
            minutes += 60;
            hours--;
        }
        while (hours < 0) {
            hours += 24;
            days--;
        }
        if (Token.containsTokenWithValue(tokens, M)) {
            while (days < 0) {
                days += start.getActualMaximum(i);
                months2--;
                start.add(2, 1);
                i = 5;
            }
            while (months2 < 0) {
                months2 += 12;
                years2--;
            }
            if (!Token.containsTokenWithValue(tokens, y) && years2 != 0) {
                while (years2 != 0) {
                    months2 += years2 * 12;
                    years2 = 0;
                }
                months = months2;
                years = years2;
            } else {
                months = months2;
                years = years2;
            }
        } else {
            if (!Token.containsTokenWithValue(tokens, y)) {
                int target = end.get(1);
                if (months2 < 0) {
                    target--;
                }
                for (int i2 = 1; start.get(i2) != target; i2 = 1) {
                    int days2 = days + (start.getActualMaximum(6) - start.get(6));
                    if ((start instanceof GregorianCalendar) && start.get(2) == 1 && start.get(5) == 29) {
                        days2++;
                    }
                    start.add(1, 1);
                    days = days2 + start.get(6);
                }
                years2 = 0;
            }
            while (start.get(2) != end.get(2)) {
                days += start.getActualMaximum(5);
                start.add(2, 1);
            }
            int months3 = 0;
            while (days < 0) {
                days += start.getActualMaximum(5);
                months3--;
                start.add(2, 1);
            }
            months = months3;
            years = years2;
        }
        if (!Token.containsTokenWithValue(tokens, d)) {
            hours += days * 24;
            days = 0;
        }
        if (!Token.containsTokenWithValue(tokens, H)) {
            minutes += hours * 60;
            hours = 0;
        }
        if (!Token.containsTokenWithValue(tokens, m)) {
            seconds2 += minutes * 60;
            minutes = 0;
        }
        if (Token.containsTokenWithValue(tokens, s)) {
            milliseconds = milliseconds2;
            seconds = seconds2;
        } else {
            milliseconds = milliseconds2 + (seconds2 * 1000);
            seconds = 0;
        }
        long milliseconds3 = years;
        return format(tokens, milliseconds3, months, days, hours, minutes, seconds, milliseconds, padWithZeros);
    }

    public static String formatPeriodISO(long startMillis, long endMillis) {
        return formatPeriod(startMillis, endMillis, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    static Token[] lexx(String format) {
        ArrayList<Token> list = new ArrayList<>(format.length());
        boolean inLiteral = false;
        StringBuilder buffer = null;
        Token previous = null;
        boolean inOptional = false;
        int optionalIndex = -1;
        for (int i = 0; i < format.length(); i++) {
            char ch = format.charAt(i);
            if (inLiteral && ch != '\'') {
                buffer.append(ch);
            } else {
                String value = null;
                switch (ch) {
                    case '\'':
                        if (inLiteral) {
                            buffer = null;
                            inLiteral = false;
                            break;
                        } else {
                            buffer = new StringBuilder();
                            list.add(new Token(buffer, inOptional, optionalIndex));
                            inLiteral = true;
                            break;
                        }
                    case 'H':
                        value = H;
                        break;
                    case 'M':
                        value = M;
                        break;
                    case 'S':
                        value = S;
                        break;
                    case '[':
                        if (inOptional) {
                            throw new IllegalArgumentException("Nested optional block at index: " + i);
                        }
                        optionalIndex++;
                        inOptional = true;
                        break;
                    case ']':
                        if (!inOptional) {
                            throw new IllegalArgumentException("Attempting to close unopened optional block at index: " + i);
                        }
                        inOptional = false;
                        break;
                    case 'd':
                        value = d;
                        break;
                    case 'm':
                        value = m;
                        break;
                    case 's':
                        value = s;
                        break;
                    case 'y':
                        value = y;
                        break;
                    default:
                        if (buffer == null) {
                            buffer = new StringBuilder();
                            list.add(new Token(buffer, inOptional, optionalIndex));
                        }
                        buffer.append(ch);
                        break;
                }
                if (value != null) {
                    if (previous != null && previous.getValue().equals(value)) {
                        previous.increment();
                    } else {
                        Token token = new Token(value, inOptional, optionalIndex);
                        list.add(token);
                        previous = token;
                    }
                    buffer = null;
                }
            }
        }
        if (inLiteral) {
            throw new IllegalArgumentException("Unmatched quote in format: " + format);
        }
        if (!inOptional) {
            return (Token[]) list.toArray(Token.EMPTY_ARRAY);
        }
        throw new IllegalArgumentException("Unmatched optional in format: " + format);
    }

    private static String paddedValue(long value, boolean padWithZeros, int count) {
        String longString = Long.toString(value);
        return padWithZeros ? StringUtils.leftPad(longString, count, '0') : longString;
    }

    @Deprecated
    public DurationFormatUtils() {
    }
}
