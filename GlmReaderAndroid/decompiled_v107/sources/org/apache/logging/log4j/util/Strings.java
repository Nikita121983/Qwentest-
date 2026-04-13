package org.apache.logging.log4j.util;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes10.dex */
public final class Strings {
    private static final String COMMA_DELIMITED_RE = "\\s*,\\s*";
    public static final String EMPTY = "";
    private static final int MAX_FORMAT_BUFFER_LENGTH = 518;
    private static final ThreadLocal<StringBuilder> FORMAT_BUFFER_REF = ThreadLocal.withInitial(new java.util.function.Supplier() { // from class: org.apache.logging.log4j.util.Strings$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return Strings.m2209$r8$lambda$V_uu1X1UKpbECUj5gbjPrONSE();
        }
    });
    public static final String[] EMPTY_ARRAY = new String[0];
    public static final String LINE_SEPARATOR = System.lineSeparator();

    /* renamed from: $r8$lambda$V_uu1X1UKpbECUj-5gbjPr-ONSE, reason: not valid java name */
    public static /* synthetic */ StringBuilder m2209$r8$lambda$V_uu1X1UKpbECUj5gbjPrONSE() {
        return new StringBuilder();
    }

    public static String dquote(final String str) {
        return '\"' + str + '\"';
    }

    public static boolean isBlank(final String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String join(final Iterable<?> iterable, final char separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(final Iterator<?> iterator, final char separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String[] splitList(final String string) {
        return string != null ? string.split(COMMA_DELIMITED_RE) : new String[0];
    }

    public static String left(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    public static String quote(final String str) {
        return Chars.QUOTE + str + Chars.QUOTE;
    }

    public static String trimToNull(final String str) {
        String ts = str == null ? null : str.trim();
        if (isEmpty(ts)) {
            return null;
        }
        return ts;
    }

    private Strings() {
    }

    public static String toRootLowerCase(final String str) {
        return str.toLowerCase(Locale.ROOT);
    }

    public static String toRootUpperCase(final String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static String concat(final String str1, final String str2) {
        if (isEmpty(str1)) {
            return str2;
        }
        if (isEmpty(str2)) {
            return str1;
        }
        StringBuilder sb = FORMAT_BUFFER_REF.get();
        try {
            return sb.append(str1).append(str2).toString();
        } finally {
            StringBuilders.trimToMaxSize(sb, MAX_FORMAT_BUFFER_LENGTH);
            sb.setLength(0);
        }
    }

    public static String repeat(final String str, final int count) {
        Objects.requireNonNull(str, "str");
        if (count < 0) {
            throw new IllegalArgumentException("count");
        }
        StringBuilder sb = FORMAT_BUFFER_REF.get();
        int index = 0;
        while (true) {
            if (index < count) {
                try {
                    sb.append(str);
                    index++;
                } finally {
                }
            } else {
                return sb.toString();
            }
            StringBuilders.trimToMaxSize(sb, MAX_FORMAT_BUFFER_LENGTH);
            sb.setLength(0);
        }
    }
}
