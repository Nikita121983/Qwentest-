package org.apache.poi.ss.format;

/* loaded from: classes10.dex */
public class SimpleFraction {
    private final int denominator;
    private final int numerator;

    public static SimpleFraction buildFractionExactDenominator(double val, int exactDenom) {
        int num = (int) Math.round(exactDenom * val);
        return new SimpleFraction(num, exactDenom);
    }

    public static SimpleFraction buildFractionMaxDenominator(double value, int maxDenominator) {
        return buildFractionMaxDenominator(value, 0.0d, maxDenominator, 100);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0153, code lost:
    
        throw new java.lang.IllegalStateException(r16 + r41 + r26 + r7 + r25 + r12 + ")");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static org.apache.poi.ss.format.SimpleFraction buildFractionMaxDenominator(double r41, double r43, int r45, int r46) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.format.SimpleFraction.buildFractionMaxDenominator(double, double, int, int):org.apache.poi.ss.format.SimpleFraction");
    }

    public SimpleFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }
}
