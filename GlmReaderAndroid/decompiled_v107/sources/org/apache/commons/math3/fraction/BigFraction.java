package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.poi.ss.util.IEEEDouble;

/* loaded from: classes10.dex */
public class BigFraction extends Number implements FieldElement<BigFraction>, Comparable<BigFraction>, Serializable {
    private static final long serialVersionUID = -5630213147331578515L;
    private final BigInteger denominator;
    private final BigInteger numerator;
    public static final BigFraction TWO = new BigFraction(2);
    public static final BigFraction ONE = new BigFraction(1);
    public static final BigFraction ZERO = new BigFraction(0);
    public static final BigFraction MINUS_ONE = new BigFraction(-1);
    public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);
    public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);
    public static final BigFraction ONE_HALF = new BigFraction(1, 2);
    public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);
    public static final BigFraction ONE_THIRD = new BigFraction(1, 3);
    public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);
    public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);
    public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);
    public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);
    public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);
    private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100);

    public BigFraction(BigInteger num) {
        this(num, BigInteger.ONE);
    }

    public BigFraction(BigInteger num, BigInteger den) {
        MathUtils.checkNotNull(num, LocalizedFormats.NUMERATOR, new Object[0]);
        MathUtils.checkNotNull(den, LocalizedFormats.DENOMINATOR, new Object[0]);
        if (den.signum() == 0) {
            throw new ZeroException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (num.signum() == 0) {
            this.numerator = BigInteger.ZERO;
            this.denominator = BigInteger.ONE;
            return;
        }
        BigInteger gcd = num.gcd(den);
        if (BigInteger.ONE.compareTo(gcd) < 0) {
            num = num.divide(gcd);
            den = den.divide(gcd);
        }
        if (den.signum() == -1) {
            num = num.negate();
            den = den.negate();
        }
        this.numerator = num;
        this.denominator = den;
    }

    public BigFraction(double value) throws MathIllegalArgumentException {
        if (Double.isNaN(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION, new Object[0]);
        }
        if (Double.isInfinite(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION, new Object[0]);
        }
        long bits = Double.doubleToLongBits(value);
        long sign = Long.MIN_VALUE & bits;
        long exponent = 9218868437227405312L & bits;
        long m = IEEEDouble.FRAC_MASK & bits;
        m = exponent != 0 ? m | IEEEDouble.FRAC_ASSUMED_HIGH_BIT : m;
        m = sign != 0 ? -m : m;
        int k = ((int) (exponent >> 52)) - 1075;
        while ((9007199254740990L & m) != 0 && (1 & m) == 0) {
            m >>= 1;
            k++;
        }
        if (k < 0) {
            this.numerator = BigInteger.valueOf(m);
            this.denominator = BigInteger.ZERO.flipBit(-k);
        } else {
            this.numerator = BigInteger.valueOf(m).multiply(BigInteger.ZERO.flipBit(k));
            this.denominator = BigInteger.ONE;
        }
    }

    public BigFraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
        this(value, epsilon, Integer.MAX_VALUE, maxIterations);
    }

    private BigFraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
        long q2;
        long p2;
        long overflow = 2147483647L;
        long a0 = (long) FastMath.floor(value);
        if (FastMath.abs(a0) > 2147483647L) {
            throw new FractionConversionException(value, a0, 1L);
        }
        if (FastMath.abs(a0 - value) < epsilon) {
            this.numerator = BigInteger.valueOf(a0);
            this.denominator = BigInteger.ONE;
            return;
        }
        int n = 0;
        boolean stop = false;
        long a02 = a0;
        long a03 = 0;
        long q1 = a0;
        double r0 = value;
        long p1 = 1;
        long q12 = 1;
        long q0 = 0;
        while (true) {
            n++;
            double r1 = 1.0d / (r0 - a02);
            long a1 = (long) FastMath.floor(r1);
            long a12 = (a1 * q1) + p1;
            long p22 = a1 * q12;
            q2 = p22 + q0;
            if (a12 > overflow) {
                p2 = a12;
                break;
            }
            if (q2 > overflow) {
                p2 = a12;
                break;
            }
            long overflow2 = overflow;
            p2 = a12;
            double convergent = a12 / q2;
            if (n < maxIterations && FastMath.abs(convergent - value) > epsilon && q2 < maxDenominator) {
                long p0 = q1;
                q0 = q12;
                a02 = a1;
                r0 = r1;
                q12 = q2;
                q1 = p2;
                p1 = p0;
            } else {
                stop = true;
            }
            if (stop) {
                break;
            }
            overflow = overflow2;
            a03 = p2;
        }
        if (epsilon != 0.0d || FastMath.abs(q12) >= maxDenominator) {
            throw new FractionConversionException(value, p2, q2);
        }
        if (n >= maxIterations) {
            throw new FractionConversionException(value, maxIterations);
        }
        if (q2 < maxDenominator) {
            this.numerator = BigInteger.valueOf(p2);
            this.denominator = BigInteger.valueOf(q2);
        } else {
            this.numerator = BigInteger.valueOf(q1);
            this.denominator = BigInteger.valueOf(q12);
        }
    }

    public BigFraction(double value, int maxDenominator) throws FractionConversionException {
        this(value, 0.0d, maxDenominator, 100);
    }

    public BigFraction(int num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public BigFraction(int num, int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public BigFraction(long num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public BigFraction(long num, long den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public static BigFraction getReducedFraction(int numerator, int denominator) {
        if (numerator == 0) {
            return ZERO;
        }
        return new BigFraction(numerator, denominator);
    }

    public BigFraction abs() {
        return this.numerator.signum() == 1 ? this : negate();
    }

    public BigFraction add(BigInteger bg) throws NullArgumentException {
        MathUtils.checkNotNull(bg);
        if (this.numerator.signum() == 0) {
            return new BigFraction(bg);
        }
        if (bg.signum() == 0) {
            return this;
        }
        return new BigFraction(this.numerator.add(this.denominator.multiply(bg)), this.denominator);
    }

    public BigFraction add(int i) {
        return add(BigInteger.valueOf(i));
    }

    public BigFraction add(long l) {
        return add(BigInteger.valueOf(l));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction add(BigFraction fraction) {
        BigInteger num;
        BigInteger den;
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return fraction;
        }
        if (this.denominator.equals(fraction.denominator)) {
            num = this.numerator.add(fraction.numerator);
            den = this.denominator;
        } else {
            num = this.numerator.multiply(fraction.denominator).add(fraction.numerator.multiply(this.denominator));
            den = this.denominator.multiply(fraction.denominator);
        }
        if (num.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(num, den);
    }

    public BigDecimal bigDecimalValue() {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
    }

    public BigDecimal bigDecimalValue(int roundingMode) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), roundingMode);
    }

    public BigDecimal bigDecimalValue(int scale, int roundingMode) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), scale, roundingMode);
    }

    @Override // java.lang.Comparable
    public int compareTo(BigFraction object) {
        int lhsSigNum = this.numerator.signum();
        int rhsSigNum = object.numerator.signum();
        if (lhsSigNum != rhsSigNum) {
            return lhsSigNum > rhsSigNum ? 1 : -1;
        }
        if (lhsSigNum == 0) {
            return 0;
        }
        BigInteger nOd = this.numerator.multiply(object.denominator);
        BigInteger dOn = this.denominator.multiply(object.numerator);
        return nOd.compareTo(dOn);
    }

    public BigFraction divide(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bg.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (this.numerator.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(this.numerator, this.denominator.multiply(bg));
    }

    public BigFraction divide(int i) {
        return divide(BigInteger.valueOf(i));
    }

    public BigFraction divide(long l) {
        return divide(BigInteger.valueOf(l));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction divide(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (this.numerator.signum() == 0) {
            return ZERO;
        }
        return multiply(fraction.reciprocal());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        double result = this.numerator.doubleValue() / this.denominator.doubleValue();
        if (Double.isNaN(result)) {
            int shift = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Double.MAX_VALUE);
            return this.numerator.shiftRight(shift).doubleValue() / this.denominator.shiftRight(shift).doubleValue();
        }
        return result;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BigFraction)) {
            return false;
        }
        BigFraction rhs = ((BigFraction) other).reduce();
        BigFraction thisOne = reduce();
        boolean ret = thisOne.numerator.equals(rhs.numerator) && thisOne.denominator.equals(rhs.denominator);
        return ret;
    }

    @Override // java.lang.Number
    public float floatValue() {
        float result = this.numerator.floatValue() / this.denominator.floatValue();
        if (Double.isNaN(result)) {
            int shift = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Float.MAX_VALUE);
            return this.numerator.shiftRight(shift).floatValue() / this.denominator.shiftRight(shift).floatValue();
        }
        return result;
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    public int getDenominatorAsInt() {
        return this.denominator.intValue();
    }

    public long getDenominatorAsLong() {
        return this.denominator.longValue();
    }

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public int getNumeratorAsInt() {
        return this.numerator.intValue();
    }

    public long getNumeratorAsLong() {
        return this.numerator.longValue();
    }

    public int hashCode() {
        return ((this.numerator.hashCode() + 629) * 37) + this.denominator.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    public BigFraction multiply(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException();
        }
        if (this.numerator.signum() == 0 || bg.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(bg.multiply(this.numerator), this.denominator);
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction multiply(int i) {
        if (i == 0 || this.numerator.signum() == 0) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf(i));
    }

    public BigFraction multiply(long l) {
        if (l == 0 || this.numerator.signum() == 0) {
            return ZERO;
        }
        return multiply(BigInteger.valueOf(l));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction multiply(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (this.numerator.signum() == 0 || fraction.numerator.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(this.numerator.multiply(fraction.numerator), this.denominator.multiply(fraction.denominator));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction negate() {
        return new BigFraction(this.numerator.negate(), this.denominator);
    }

    public double percentageValue() {
        return multiply(ONE_HUNDRED).doubleValue();
    }

    public BigFraction pow(int exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent < 0) {
            return new BigFraction(this.denominator.pow(-exponent), this.numerator.pow(-exponent));
        }
        return new BigFraction(this.numerator.pow(exponent), this.denominator.pow(exponent));
    }

    public BigFraction pow(long exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent < 0) {
            return new BigFraction(ArithmeticUtils.pow(this.denominator, -exponent), ArithmeticUtils.pow(this.numerator, -exponent));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
    }

    public BigFraction pow(BigInteger exponent) {
        if (exponent.signum() == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent.signum() == -1) {
            BigInteger eNeg = exponent.negate();
            return new BigFraction(ArithmeticUtils.pow(this.denominator, eNeg), ArithmeticUtils.pow(this.numerator, eNeg));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
    }

    public double pow(double exponent) {
        return FastMath.pow(this.numerator.doubleValue(), exponent) / FastMath.pow(this.denominator.doubleValue(), exponent);
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction reciprocal() {
        return new BigFraction(this.denominator, this.numerator);
    }

    public BigFraction reduce() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        if (BigInteger.ONE.compareTo(gcd) < 0) {
            return new BigFraction(this.numerator.divide(gcd), this.denominator.divide(gcd));
        }
        return this;
    }

    public BigFraction subtract(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException();
        }
        if (bg.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return new BigFraction(bg.negate());
        }
        return new BigFraction(this.numerator.subtract(this.denominator.multiply(bg)), this.denominator);
    }

    public BigFraction subtract(int i) {
        return subtract(BigInteger.valueOf(i));
    }

    public BigFraction subtract(long l) {
        return subtract(BigInteger.valueOf(l));
    }

    @Override // org.apache.commons.math3.FieldElement
    public BigFraction subtract(BigFraction fraction) {
        BigInteger num;
        BigInteger den;
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return fraction.negate();
        }
        if (this.denominator.equals(fraction.denominator)) {
            num = this.numerator.subtract(fraction.numerator);
            den = this.denominator;
        } else {
            num = this.numerator.multiply(fraction.denominator).subtract(fraction.numerator.multiply(this.denominator));
            den = this.denominator.multiply(fraction.denominator);
        }
        return new BigFraction(num, den);
    }

    public String toString() {
        if (BigInteger.ONE.equals(this.denominator)) {
            String str = this.numerator.toString();
            return str;
        }
        if (BigInteger.ZERO.equals(this.numerator)) {
            return "0";
        }
        String str2 = this.numerator + " / " + this.denominator;
        return str2;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Field<BigFraction> getField() {
        return BigFractionField.getInstance();
    }
}
