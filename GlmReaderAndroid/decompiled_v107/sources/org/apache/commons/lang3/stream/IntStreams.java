package org.apache.commons.lang3.stream;

import java.util.stream.IntStream;

/* loaded from: classes9.dex */
public class IntStreams {
    @SafeVarargs
    public static IntStream of(int... values) {
        return values == null ? IntStream.empty() : IntStream.of(values);
    }

    public static IntStream range(int endExclusive) {
        return IntStream.range(0, endExclusive);
    }

    public static IntStream rangeClosed(int endInclusive) {
        return IntStream.rangeClosed(0, endInclusive);
    }

    @Deprecated
    public IntStreams() {
    }
}
