package org.apache.commons.lang3.math;

import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.StructuredDataId;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public final class Fraction extends Number implements Comparable<Fraction> {
    private static final long serialVersionUID = 65382027393090L;
    private final int denominator;
    private transient int hashCode;
    private final int numerator;
    private transient String toProperString;
    private transient String toString;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);

    private static int addAndCheck(int x, int y) {
        long s = x + y;
        if (s < -2147483648L || s > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) s;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x008b, code lost:
    
        return getReducedFraction(((r4 * r6) + r5) * r23, r6);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.commons.lang3.math.Fraction getFraction(double r29) {
        /*
            r0 = 0
            int r0 = (r29 > r0 ? 1 : (r29 == r0 ? 0 : -1))
            if (r0 >= 0) goto L8
            r0 = -1
            goto L9
        L8:
            r0 = 1
        L9:
            double r2 = java.lang.Math.abs(r29)
            r4 = 4746794007244308480(0x41dfffffffc00000, double:2.147483647E9)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 > 0) goto L94
            boolean r4 = java.lang.Double.isNaN(r2)
            if (r4 != 0) goto L94
            int r4 = (int) r2
            double r5 = (double) r4
            double r2 = r2 - r5
            r5 = 0
            r6 = 1
            r7 = 1
            r8 = 0
            int r9 = (int) r2
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = (double) r9
            double r12 = r2 - r12
            r14 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r16 = 1
        L30:
            r17 = r14
            r29 = r2
            r19 = 1
            double r1 = r10 / r12
            int r1 = (int) r1
            r2 = r12
            r20 = r2
            double r2 = (double) r1
            double r2 = r2 * r12
            double r2 = r10 - r2
            int r22 = r9 * r7
            r23 = r0
            int r0 = r22 + r5
            int r22 = r9 * r8
            r24 = r1
            int r1 = r22 + r6
            r25 = r2
            double r2 = (double) r0
            r27 = r2
            double r2 = (double) r1
            double r2 = r27 / r2
            double r27 = r29 - r2
            double r14 = java.lang.Math.abs(r27)
            r9 = r24
            r10 = r20
            r12 = r25
            r5 = r7
            r6 = r8
            r7 = r0
            r8 = r1
            r22 = r0
            int r0 = r16 + 1
            int r16 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
            r27 = r2
            r2 = 25
            if (r16 <= 0) goto L80
            r3 = 10000(0x2710, float:1.4013E-41)
            if (r1 > r3) goto L80
            if (r1 <= 0) goto L80
            if (r0 < r2) goto L79
            goto L80
        L79:
            r2 = r29
            r16 = r0
            r0 = r23
            goto L30
        L80:
            if (r0 == r2) goto L8c
            int r2 = r4 * r6
            int r2 = r2 + r5
            int r2 = r2 * r23
            org.apache.commons.lang3.math.Fraction r2 = getReducedFraction(r2, r6)
            return r2
        L8c:
            java.lang.ArithmeticException r2 = new java.lang.ArithmeticException
            java.lang.String r3 = "Unable to convert double to fraction"
            r2.<init>(r3)
            throw r2
        L94:
            r23 = r0
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "The value must not be greater than Integer.MAX_VALUE or NaN"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.Fraction.getFraction(double):org.apache.commons.lang3.math.Fraction");
    }

    public static Fraction getFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction getFraction(int whole, int numerator, int denominator) {
        long numeratorValue;
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (denominator < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        }
        if (numerator < 0) {
            throw new ArithmeticException("The numerator must not be negative");
        }
        if (whole < 0) {
            numeratorValue = (whole * denominator) - numerator;
        } else {
            long numeratorValue2 = whole;
            numeratorValue = (numeratorValue2 * denominator) + numerator;
        }
        if (numeratorValue < -2147483648L || numeratorValue > 2147483647L) {
            throw new ArithmeticException("Numerator too large to represent as an Integer.");
        }
        return new Fraction((int) numeratorValue, denominator);
    }

    public static Fraction getFraction(String str) {
        Objects.requireNonNull(str, "str");
        if (str.indexOf(46) >= 0) {
            return getFraction(Double.parseDouble(str));
        }
        int pos = str.indexOf(32);
        if (pos > 0) {
            int whole = Integer.parseInt(str.substring(0, pos));
            String str2 = str.substring(pos + 1);
            int pos2 = str2.indexOf(47);
            if (pos2 < 0) {
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            int numer = Integer.parseInt(str2.substring(0, pos2));
            int denom = Integer.parseInt(str2.substring(pos2 + 1));
            return getFraction(whole, numer, denom);
        }
        int pos3 = str.indexOf(47);
        if (pos3 < 0) {
            return getFraction(Integer.parseInt(str), 1);
        }
        int numer2 = Integer.parseInt(str.substring(0, pos3));
        int denom2 = Integer.parseInt(str.substring(pos3 + 1));
        return getFraction(numer2, denom2);
    }

    public static Fraction getReducedFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (numerator == 0) {
            return ZERO;
        }
        if (denominator == Integer.MIN_VALUE && (numerator & 1) == 0) {
            numerator /= 2;
            denominator /= 2;
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = greatestCommonDivisor(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        if ((r5 & 1) != 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0035, code lost:
    
        r0 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003c, code lost:
    
        if ((r0 & 1) != 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0041, code lost:
    
        if (r0 <= 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
    
        r5 = -r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        r0 = (r6 - r5) / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004a, code lost:
    
        if (r0 != 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004f, code lost:
    
        return (-r5) * (1 << r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0045, code lost:
    
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x003e, code lost:
    
        r0 = r0 / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0037, code lost:
    
        r0 = -(r5 / 2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int greatestCommonDivisor(int r5, int r6) {
        /*
            java.lang.String r0 = "overflow: gcd is 2^31"
            if (r5 == 0) goto L57
            if (r6 != 0) goto L7
            goto L57
        L7:
            int r1 = java.lang.Math.abs(r5)
            r2 = 1
            if (r1 == r2) goto L56
            int r1 = java.lang.Math.abs(r6)
            if (r1 != r2) goto L15
            goto L56
        L15:
            if (r5 <= 0) goto L18
            int r5 = -r5
        L18:
            if (r6 <= 0) goto L1b
            int r6 = -r6
        L1b:
            r1 = 0
        L1c:
            r3 = r5 & 1
            r4 = 31
            if (r3 != 0) goto L2f
            r3 = r6 & 1
            if (r3 != 0) goto L2f
            if (r1 >= r4) goto L2f
            int r5 = r5 / 2
            int r6 = r6 / 2
            int r1 = r1 + 1
            goto L1c
        L2f:
            if (r1 == r4) goto L50
            r0 = r5 & 1
            if (r0 != r2) goto L37
            r0 = r6
            goto L3a
        L37:
            int r0 = r5 / 2
            int r0 = -r0
        L3a:
            r3 = r0 & 1
            if (r3 != 0) goto L41
            int r0 = r0 / 2
            goto L3a
        L41:
            if (r0 <= 0) goto L45
            int r5 = -r0
            goto L46
        L45:
            r6 = r0
        L46:
            int r3 = r6 - r5
            int r0 = r3 / 2
            if (r0 != 0) goto L3a
            int r3 = -r5
            int r2 = r2 << r1
            int r3 = r3 * r2
            return r3
        L50:
            java.lang.ArithmeticException r2 = new java.lang.ArithmeticException
            r2.<init>(r0)
            throw r2
        L56:
            return r2
        L57:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r1) goto L67
            if (r6 == r1) goto L67
            int r0 = java.lang.Math.abs(r5)
            int r1 = java.lang.Math.abs(r6)
            int r0 = r0 + r1
            return r0
        L67:
            java.lang.ArithmeticException r1 = new java.lang.ArithmeticException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.Fraction.greatestCommonDivisor(int, int):int");
    }

    private static int mulAndCheck(int x, int y) {
        long m = x * y;
        if (m < -2147483648L || m > 2147483647L) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int) m;
    }

    private static int mulPosAndCheck(int x, int y) {
        long m = x * y;
        if (m > 2147483647L) {
            throw new ArithmeticException("overflow: mulPos");
        }
        return (int) m;
    }

    private static int subAndCheck(int x, int y) {
        long s = x - y;
        if (s < -2147483648L || s > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) s;
    }

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction abs() {
        if (this.numerator >= 0) {
            return this;
        }
        return negate();
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    private Fraction addSub(Fraction fraction, boolean isAdd) {
        Objects.requireNonNull(fraction, "fraction");
        if (this.numerator == 0) {
            return isAdd ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int d1 = greatestCommonDivisor(this.denominator, fraction.denominator);
        if (d1 == 1) {
            int uvp = mulAndCheck(this.numerator, fraction.denominator);
            int upv = mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(isAdd ? addAndCheck(uvp, upv) : subAndCheck(uvp, upv), mulPosAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger uvp2 = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
        BigInteger upv2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
        BigInteger t = isAdd ? uvp2.add(upv2) : uvp2.subtract(upv2);
        int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
        int d2 = tmodd1 == 0 ? d1 : greatestCommonDivisor(tmodd1, d1);
        BigInteger w = t.divide(BigInteger.valueOf(d2));
        if (w.bitLength() > 31) {
            throw new ArithmeticException("overflow: numerator too large after multiply");
        }
        return new Fraction(w.intValue(), mulPosAndCheck(this.denominator / d1, fraction.denominator / d2));
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction other) {
        if (this == other) {
            return 0;
        }
        if (this.numerator == other.numerator && this.denominator == other.denominator) {
            return 0;
        }
        long first = this.numerator * other.denominator;
        long second = other.numerator * this.denominator;
        return Long.compare(first, second);
    }

    public Fraction divideBy(Fraction fraction) {
        Objects.requireNonNull(fraction, "fraction");
        if (fraction.numerator == 0) {
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
        return multiplyBy(fraction.invert());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction other = (Fraction) obj;
        return getNumerator() == other.getNumerator() && getDenominator() == other.getDenominator();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.numerator / this.denominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = Objects.hash(Integer.valueOf(this.denominator), Integer.valueOf(this.numerator));
        }
        return this.hashCode;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator / this.denominator;
    }

    public Fraction invert() {
        if (this.numerator == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        }
        if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: can't negate numerator");
        }
        if (this.numerator < 0) {
            return new Fraction(-this.denominator, -this.numerator);
        }
        return new Fraction(this.denominator, this.numerator);
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator / this.denominator;
    }

    public Fraction multiplyBy(Fraction fraction) {
        Objects.requireNonNull(fraction, "fraction");
        if (this.numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int d1 = greatestCommonDivisor(this.numerator, fraction.denominator);
        int d2 = greatestCommonDivisor(fraction.numerator, this.denominator);
        return getReducedFraction(mulAndCheck(this.numerator / d1, fraction.numerator / d2), mulPosAndCheck(this.denominator / d2, fraction.denominator / d1));
    }

    public Fraction negate() {
        if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: too large to negate");
        }
        return new Fraction(-this.numerator, this.denominator);
    }

    public Fraction pow(int power) {
        if (power == 1) {
            return this;
        }
        if (power == 0) {
            return ONE;
        }
        if (power < 0) {
            if (power == Integer.MIN_VALUE) {
                return invert().pow(2).pow(-(power / 2));
            }
            return invert().pow(-power);
        }
        Fraction f = multiplyBy(this);
        if (power % 2 == 0) {
            return f.pow(power / 2);
        }
        return f.pow(power / 2).multiplyBy(this);
    }

    public Fraction reduce() {
        if (this.numerator == 0) {
            return equals(ZERO) ? this : ZERO;
        }
        int gcd = greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
        if (gcd == 1) {
            return this;
        }
        return getFraction(this.numerator / gcd, this.denominator / gcd);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    public String toProperString() {
        if (this.toProperString == null) {
            if (this.numerator == 0) {
                this.toProperString = "0";
            } else if (this.numerator == this.denominator) {
                this.toProperString = "1";
            } else if (this.numerator == this.denominator * (-1)) {
                this.toProperString = StructuredDataId.RESERVED;
            } else {
                if ((this.numerator > 0 ? -this.numerator : this.numerator) < (-this.denominator)) {
                    int properNumerator = getProperNumerator();
                    if (properNumerator == 0) {
                        this.toProperString = Integer.toString(getProperWhole());
                    } else {
                        this.toProperString = getProperWhole() + StringUtils.SPACE + properNumerator + PackagingURIHelper.FORWARD_SLASH_STRING + getDenominator();
                    }
                } else {
                    this.toProperString = getNumerator() + PackagingURIHelper.FORWARD_SLASH_STRING + getDenominator();
                }
            }
        }
        return this.toProperString;
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = getNumerator() + PackagingURIHelper.FORWARD_SLASH_STRING + getDenominator();
        }
        return this.toString;
    }
}
