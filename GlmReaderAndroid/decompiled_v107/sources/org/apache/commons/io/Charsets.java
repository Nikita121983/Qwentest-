package org.apache.commons.io;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public class Charsets {

    @Deprecated
    public static final Charset ISO_8859_1;
    private static final SortedMap<String, Charset> STANDARD_CHARSET_MAP;

    @Deprecated
    public static final Charset US_ASCII;

    @Deprecated
    public static final Charset UTF_16;

    @Deprecated
    public static final Charset UTF_16BE;

    @Deprecated
    public static final Charset UTF_16LE;

    @Deprecated
    public static final Charset UTF_8;

    static {
        SortedMap<String, Charset> standardCharsetMap = new TreeMap<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        standardCharsetMap.put(StandardCharsets.ISO_8859_1.name(), StandardCharsets.ISO_8859_1);
        standardCharsetMap.put(StandardCharsets.US_ASCII.name(), StandardCharsets.US_ASCII);
        standardCharsetMap.put(StandardCharsets.UTF_16.name(), StandardCharsets.UTF_16);
        standardCharsetMap.put(StandardCharsets.UTF_16BE.name(), StandardCharsets.UTF_16BE);
        standardCharsetMap.put(StandardCharsets.UTF_16LE.name(), StandardCharsets.UTF_16LE);
        standardCharsetMap.put(StandardCharsets.UTF_8.name(), StandardCharsets.UTF_8);
        STANDARD_CHARSET_MAP = Collections.unmodifiableSortedMap(standardCharsetMap);
        ISO_8859_1 = StandardCharsets.ISO_8859_1;
        US_ASCII = StandardCharsets.US_ASCII;
        UTF_16 = StandardCharsets.UTF_16;
        UTF_16BE = StandardCharsets.UTF_16BE;
        UTF_16LE = StandardCharsets.UTF_16LE;
        UTF_8 = StandardCharsets.UTF_8;
    }

    public static boolean isAlias(Charset charset, final String charsetName) {
        if (charsetName != null) {
            if (!charset.name().equalsIgnoreCase(charsetName)) {
                Stream<String> stream = charset.aliases().stream();
                Objects.requireNonNull(charsetName);
                if (stream.anyMatch(new Predicate() { // from class: org.apache.commons.io.Charsets$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        boolean equalsIgnoreCase;
                        equalsIgnoreCase = charsetName.equalsIgnoreCase((String) obj);
                        return equalsIgnoreCase;
                    }
                })) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isUTF8(Charset charset) {
        return isUTF8Alias(toCharset(charset).name());
    }

    private static boolean isUTF8Alias(String charsetName) {
        return isAlias(StandardCharsets.UTF_8, charsetName);
    }

    public static SortedMap<String, Charset> requiredCharsets() {
        return STANDARD_CHARSET_MAP;
    }

    public static Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset toCharset(Charset charset, Charset defaultCharset) {
        return charset == null ? defaultCharset : charset;
    }

    public static Charset toCharset(String charsetName) throws UnsupportedCharsetException {
        return toCharset(charsetName, Charset.defaultCharset());
    }

    public static Charset toCharset(String charsetName, Charset defaultCharset) throws UnsupportedCharsetException {
        return charsetName == null ? defaultCharset : Charset.forName(charsetName);
    }

    public static Charset toCharsetDefault(String charsetName, Charset defaultCharset) {
        try {
            return toCharset(charsetName);
        } catch (RuntimeException e) {
            return toCharset(defaultCharset);
        }
    }

    @Deprecated
    public Charsets() {
    }
}
