package org.apache.commons.lang3;

import java.util.stream.IntStream;

/* loaded from: classes9.dex */
public final class IntegerRange extends NumberRange<Integer> {
    private static final long serialVersionUID = 1;

    public static IntegerRange of(int fromInclusive, int toInclusive) {
        return of(Integer.valueOf(fromInclusive), Integer.valueOf(toInclusive));
    }

    public static IntegerRange of(Integer fromInclusive, Integer toInclusive) {
        return new IntegerRange(fromInclusive, toInclusive);
    }

    private IntegerRange(Integer number1, Integer number2) {
        super(number1, number2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IntStream toIntStream() {
        return IntStream.rangeClosed(((Integer) getMinimum()).intValue(), ((Integer) getMaximum()).intValue());
    }
}
