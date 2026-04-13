package org.apache.commons.lang3;

/* loaded from: classes9.dex */
public final class DoubleRange extends NumberRange<Double> {
    private static final long serialVersionUID = 1;

    public static DoubleRange of(double fromInclusive, double toInclusive) {
        return of(Double.valueOf(fromInclusive), Double.valueOf(toInclusive));
    }

    public static DoubleRange of(Double fromInclusive, Double toInclusive) {
        return new DoubleRange(fromInclusive, toInclusive);
    }

    private DoubleRange(Double number1, Double number2) {
        super(number1, number2, null);
    }
}
