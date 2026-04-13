package org.apache.commons.math3.stat.inference;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class KolmogorovSmirnovTest {
    protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20d;
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;

    @Deprecated
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10d;

    @Deprecated
    protected static final int SMALL_SAMPLE_PRODUCT = 200;
    private final RandomGenerator rng;

    public KolmogorovSmirnovTest() {
        this.rng = new Well19937c();
    }

    @Deprecated
    public KolmogorovSmirnovTest(RandomGenerator rng) {
        this.rng = rng;
    }

    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data, boolean exact) {
        return 1.0d - cdf(kolmogorovSmirnovStatistic(distribution, data), data.length, exact);
    }

    public double kolmogorovSmirnovStatistic(RealDistribution distribution, double[] data) {
        checkArray(data);
        int n = data.length;
        double nd = n;
        double[] dataCopy = new double[n];
        System.arraycopy(data, 0, dataCopy, 0, n);
        Arrays.sort(dataCopy);
        double d = 0.0d;
        for (int i = 1; i <= n; i++) {
            double yi = distribution.cumulativeProbability(dataCopy[i - 1]);
            double currD = FastMath.max(yi - ((i - 1) / nd), (i / nd) - yi);
            if (currD > d) {
                d = currD;
            }
        }
        return d;
    }

    public double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) {
        double[] xa;
        double[] ya;
        long lengthProduct = x.length * y.length;
        if (lengthProduct < 10000 && hasTies(x, y)) {
            xa = MathArrays.copyOf(x);
            ya = MathArrays.copyOf(y);
            fixTies(xa, ya);
        } else {
            xa = x;
            ya = y;
        }
        return lengthProduct < 10000 ? exactP(kolmogorovSmirnovStatistic(xa, ya), x.length, y.length, strict) : approximateP(kolmogorovSmirnovStatistic(x, y), x.length, y.length);
    }

    public double kolmogorovSmirnovTest(double[] x, double[] y) {
        return kolmogorovSmirnovTest(x, y, true);
    }

    public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
        return integralKolmogorovSmirnovStatistic(x, y) / (x.length * y.length);
    }

    private long integralKolmogorovSmirnovStatistic(double[] x, double[] y) {
        checkArray(x);
        checkArray(y);
        double[] sx = MathArrays.copyOf(x);
        double[] sy = MathArrays.copyOf(y);
        Arrays.sort(sx);
        Arrays.sort(sy);
        int n = sx.length;
        int m = sy.length;
        int rankX = 0;
        int rankY = 0;
        long curD = 0;
        long supD = 0;
        do {
            double z = Double.compare(sx[rankX], sy[rankY]) <= 0 ? sx[rankX] : sy[rankY];
            while (rankX < n && Double.compare(sx[rankX], z) == 0) {
                rankX++;
                curD += m;
            }
            while (rankY < m && Double.compare(sy[rankY], z) == 0) {
                rankY++;
                curD -= n;
            }
            if (curD > supD) {
                supD = curD;
            } else if ((-curD) > supD) {
                supD = -curD;
            }
            if (rankX >= n) {
                break;
            }
        } while (rankY < m);
        return supD;
    }

    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data) {
        return kolmogorovSmirnovTest(distribution, data, false);
    }

    public boolean kolmogorovSmirnovTest(RealDistribution distribution, double[] data, double alpha) {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return kolmogorovSmirnovTest(distribution, data) < alpha;
    }

    public double bootstrap(double[] x, double[] y, int iterations, boolean strict) {
        int xLength = x.length;
        int yLength = y.length;
        double[] combined = new double[xLength + yLength];
        System.arraycopy(x, 0, combined, 0, xLength);
        System.arraycopy(y, 0, combined, xLength, yLength);
        EnumeratedRealDistribution dist = new EnumeratedRealDistribution(this.rng, combined);
        long d = integralKolmogorovSmirnovStatistic(x, y);
        int greaterCount = 0;
        int equalCount = 0;
        for (int i = 0; i < iterations; i++) {
            double[] curX = dist.sample(xLength);
            double[] curY = dist.sample(yLength);
            long curD = integralKolmogorovSmirnovStatistic(curX, curY);
            if (curD > d) {
                greaterCount++;
            } else if (curD == d) {
                equalCount++;
            }
        }
        return (strict ? greaterCount : greaterCount + equalCount) / iterations;
    }

    public double bootstrap(double[] x, double[] y, int iterations) {
        return bootstrap(x, y, iterations, true);
    }

    public double cdf(double d, int n) throws MathArithmeticException {
        return cdf(d, n, false);
    }

    public double cdfExact(double d, int n) throws MathArithmeticException {
        return cdf(d, n, true);
    }

    public double cdf(double d, int n, boolean exact) throws MathArithmeticException {
        double ninv = 1.0d / n;
        double ninvhalf = 0.5d * ninv;
        if (d <= ninvhalf) {
            return 0.0d;
        }
        if (ninvhalf < d && d <= ninv) {
            double res = 1.0d;
            double f = (2.0d * d) - ninv;
            for (int i = 1; i <= n; i++) {
                res *= i * f;
            }
            return res;
        }
        if (1.0d - ninv <= d && d < 1.0d) {
            return 1.0d - (Math.pow(1.0d - d, n) * 2.0d);
        }
        if (1.0d <= d) {
            return 1.0d;
        }
        if (!exact) {
            if (n <= 140) {
                return roundedK(d, n);
            }
            return pelzGood(d, n);
        }
        return exactK(d, n);
    }

    private double exactK(double d, int n) throws MathArithmeticException {
        int k = (int) Math.ceil(n * d);
        FieldMatrix<BigFraction> H = createExactH(d, n);
        FieldMatrix<BigFraction> Hpower = H.power(n);
        BigFraction pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= n; i++) {
            pFrac = pFrac.multiply(i).divide(n);
        }
        return pFrac.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d, int n) {
        int k = (int) Math.ceil(n * d);
        RealMatrix H = createRoundedH(d, n);
        RealMatrix Hpower = H.power(n);
        double pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= n; i++) {
            pFrac *= i / n;
        }
        return pFrac;
    }

    public double pelzGood(double d, int n) {
        double z2;
        double d2;
        double sqrtN = FastMath.sqrt(n);
        double z = d * sqrtN;
        double z22 = d * d * n;
        double z4 = z22 * z22;
        double z6 = z4 * z22;
        double z8 = z4 * z4;
        double sum = 0.0d;
        double sqrtN2 = 9.869604401089358d / (8.0d * z22);
        int k = 1;
        while (true) {
            if (k >= 100000) {
                z2 = z22;
                break;
            }
            z2 = z22;
            double kTerm = (k * 2) - 1;
            double increment = FastMath.exp((-sqrtN2) * kTerm * kTerm);
            sum += increment;
            if (increment <= sum * 1.0E-10d) {
                break;
            }
            k++;
            z22 = z2;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double ret = (FastMath.sqrt(6.283185307179586d) * sum) / z;
        double ret2 = 2.0d;
        double twoZ2 = z2 * 2.0d;
        double sum2 = 0.0d;
        int k2 = 0;
        while (true) {
            if (k2 >= 100000) {
                d2 = ret2;
                break;
            }
            d2 = ret2;
            double kTerm2 = k2 + 0.5d;
            double kTerm22 = kTerm2 * kTerm2;
            double increment2 = ((kTerm22 * 9.869604401089358d) - z2) * FastMath.exp((kTerm22 * (-9.869604401089358d)) / twoZ2);
            sum2 += increment2;
            if (FastMath.abs(increment2) < FastMath.abs(sum2) * 1.0E-10d) {
                break;
            }
            k2++;
            ret2 = d2;
        }
        if (k2 == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double sqrtHalfPi = FastMath.sqrt(1.5707963267948966d);
        double ret3 = ret + ((sum2 * sqrtHalfPi) / ((3.0d * z4) * sqrtN));
        double z4Term = d2 * z4;
        double z6Term = 6.0d * z6;
        double z2Term = z2 * 5.0d;
        double sum3 = 0.0d;
        int k3 = 0;
        while (k3 < 100000) {
            double z2Term2 = z2Term;
            double z2Term3 = k3;
            double kTerm3 = z2Term3 + 0.5d;
            double kTerm23 = kTerm3 * kTerm3;
            double increment3 = (z6Term + z4Term + ((z4Term - z2Term2) * 9.869604401089358d * kTerm23) + ((1.0d - twoZ2) * 97.40909103400243d * kTerm23 * kTerm23)) * FastMath.exp((kTerm23 * (-9.869604401089358d)) / twoZ2);
            sum3 += increment3;
            if (FastMath.abs(increment3) < FastMath.abs(sum3) * 1.0E-10d) {
                break;
            }
            k3++;
            z2Term = z2Term2;
        }
        int i = 100000;
        if (k3 == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double kTerm24 = 0.0d;
        int k4 = 1;
        while (true) {
            if (k4 >= i) {
                break;
            }
            double sum22 = kTerm24;
            double sum23 = k4 * k4;
            double kTerm25 = sum23 * 9.869604401089358d;
            double increment4 = kTerm25 * FastMath.exp((sum23 * (-9.869604401089358d)) / twoZ2);
            double sum24 = sum22 + increment4;
            double sum25 = FastMath.abs(increment4);
            if (sum25 >= FastMath.abs(sum24) * 1.0E-10d) {
                k4++;
                kTerm24 = sum24;
                i = 100000;
            } else {
                kTerm24 = sum24;
                break;
            }
        }
        if (k4 == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double sum26 = kTerm24;
        double sum27 = n;
        double ret4 = ret3 + ((sqrtHalfPi / sum27) * ((sum3 / ((((36.0d * z2) * z2) * z2) * z)) - (sum26 / ((18.0d * z2) * z))));
        double pi6 = 961.3891935753043d;
        double sum4 = 0.0d;
        int k5 = 0;
        while (k5 < 100000) {
            double pi62 = pi6;
            double pi63 = k5;
            double kTerm4 = pi63 + 0.5d;
            double kTerm26 = kTerm4 * kTerm4;
            double kTerm42 = kTerm26 * kTerm26;
            double kTerm6 = kTerm42 * kTerm26;
            double increment5 = ((((((961.3891935753043d * kTerm6) * (5.0d - (z2 * 30.0d))) + ((kTerm42 * 97.40909103400243d) * (((-60.0d) * z2) + (212.0d * z4)))) + ((kTerm26 * 9.869604401089358d) * ((135.0d * z4) - (96.0d * z6)))) - (30.0d * z6)) - (90.0d * z8)) * FastMath.exp((kTerm26 * (-9.869604401089358d)) / twoZ2);
            sum4 += increment5;
            if (FastMath.abs(increment5) < FastMath.abs(sum4) * 1.0E-10d) {
                break;
            }
            k5++;
            pi6 = pi62;
        }
        int i2 = 100000;
        if (k5 == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double kTerm27 = 0.0d;
        int k6 = 1;
        while (true) {
            if (k6 >= i2) {
                break;
            }
            double sum28 = kTerm27;
            double sum29 = k6 * k6;
            double increment6 = (((-97.40909103400243d) * sum29 * sum29) + (29.608813203268074d * sum29 * z2)) * FastMath.exp((sum29 * (-9.869604401089358d)) / twoZ2);
            double sum210 = sum28 + increment6;
            if (FastMath.abs(increment6) < FastMath.abs(sum210) * 1.0E-10d) {
                kTerm27 = sum210;
                break;
            }
            k6++;
            kTerm27 = sum210;
            i2 = 100000;
        }
        if (k6 == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double sum211 = kTerm27;
        double sum212 = n;
        return ((sqrtHalfPi / (sum212 * sqrtN)) * ((sum4 / ((3240.0d * z6) * z4)) + (sum211 / (108.0d * z6)))) + ret4;
    }

    private FieldMatrix<BigFraction> createExactH(double d, int n) throws NumberIsTooLargeException, FractionConversionException {
        BigFraction h;
        int k = (int) Math.ceil(n * d);
        int m = (k * 2) - 1;
        double hDouble = k - (n * d);
        if (hDouble >= 1.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(hDouble), Double.valueOf(1.0d), false);
        }
        try {
            h = new BigFraction(hDouble, KS_SUM_CAUCHY_CRITERION, 10000);
        } catch (FractionConversionException e) {
            try {
                h = new BigFraction(hDouble, 1.0E-10d, 10000);
            } catch (FractionConversionException e2) {
                h = new BigFraction(hDouble, 1.0E-5d, 10000);
            }
        }
        BigFraction[][] Hdata = (BigFraction[][]) Array.newInstance((Class<?>) BigFraction.class, m, m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if ((i - j) + 1 < 0) {
                    Hdata[i][j] = BigFraction.ZERO;
                } else {
                    Hdata[i][j] = BigFraction.ONE;
                }
            }
        }
        BigFraction[] hPowers = new BigFraction[m];
        hPowers[0] = h;
        for (int i2 = 1; i2 < m; i2++) {
            hPowers[i2] = h.multiply(hPowers[i2 - 1]);
        }
        for (int i3 = 0; i3 < m; i3++) {
            Hdata[i3][0] = Hdata[i3][0].subtract(hPowers[i3]);
            Hdata[m - 1][i3] = Hdata[m - 1][i3].subtract(hPowers[(m - i3) - 1]);
        }
        if (h.compareTo(BigFraction.ONE_HALF) == 1) {
            Hdata[m - 1][0] = Hdata[m - 1][0].add(h.multiply(2).subtract(1).pow(m));
        }
        for (int i4 = 0; i4 < m; i4++) {
            for (int j2 = 0; j2 < i4 + 1; j2++) {
                if ((i4 - j2) + 1 > 0) {
                    for (int g = 2; g <= (i4 - j2) + 1; g++) {
                        Hdata[i4][j2] = Hdata[i4][j2].divide(g);
                    }
                }
            }
        }
        return new Array2DRowFieldMatrix(BigFractionField.getInstance(), Hdata);
    }

    private RealMatrix createRoundedH(double d, int n) throws NumberIsTooLargeException {
        int k = (int) Math.ceil(n * d);
        int m = (k * 2) - 1;
        double h = k - (n * d);
        if (h >= 1.0d) {
            throw new NumberIsTooLargeException(Double.valueOf(h), Double.valueOf(1.0d), false);
        }
        double[][] Hdata = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if ((i - j) + 1 >= 0) {
                    Hdata[i][j] = 1.0d;
                } else {
                    Hdata[i][j] = 0.0d;
                }
            }
        }
        double[] hPowers = new double[m];
        hPowers[0] = h;
        for (int i2 = 1; i2 < m; i2++) {
            hPowers[i2] = hPowers[i2 - 1] * h;
        }
        for (int i3 = 0; i3 < m; i3++) {
            Hdata[i3][0] = Hdata[i3][0] - hPowers[i3];
            double[] dArr = Hdata[m - 1];
            dArr[i3] = dArr[i3] - hPowers[(m - i3) - 1];
        }
        if (Double.compare(h, 0.5d) > 0) {
            double[] dArr2 = Hdata[m - 1];
            dArr2[0] = dArr2[0] + FastMath.pow((2.0d * h) - 1.0d, m);
        }
        for (int i4 = 0; i4 < m; i4++) {
            for (int j2 = 0; j2 < i4 + 1; j2++) {
                if ((i4 - j2) + 1 > 0) {
                    for (int g = 2; g <= (i4 - j2) + 1; g++) {
                        double[] dArr3 = Hdata[i4];
                        dArr3[j2] = dArr3[j2] / g;
                    }
                }
            }
        }
        return MatrixUtils.createRealMatrix(Hdata);
    }

    private void checkArray(double[] array) {
        if (array == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        }
        if (array.length < 2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(array.length), 2);
        }
    }

    public double ksSum(double t, double tolerance, int maxIterations) {
        if (t == 0.0d) {
            return 0.0d;
        }
        double x = (-2.0d) * t * t;
        int sign = -1;
        long i = 1;
        double partialSum = 0.5d;
        double delta = 1.0d;
        while (delta > tolerance && i < maxIterations) {
            delta = FastMath.exp(i * x * i);
            partialSum += sign * delta;
            sign *= -1;
            i++;
        }
        if (i == maxIterations) {
            throw new TooManyIterationsException(Integer.valueOf(maxIterations));
        }
        return 2.0d * partialSum;
    }

    private static long calculateIntegralD(double d, int n, int m, boolean strict) {
        long nm = n * m;
        long upperBound = (long) FastMath.ceil((d - 1.0E-12d) * nm);
        long lowerBound = (long) FastMath.floor((1.0E-12d + d) * nm);
        if (strict && lowerBound == upperBound) {
            return 1 + upperBound;
        }
        return upperBound;
    }

    public double exactP(double d, int n, int m, boolean strict) {
        return 1.0d - (n(m, n, m, n, calculateIntegralD(d, m, n, strict), strict) / CombinatoricsUtils.binomialCoefficientDouble(n + m, m));
    }

    public double approximateP(double d, int n, int m) {
        double dm = m;
        double dn = n;
        return 1.0d - ksSum(d * FastMath.sqrt((dm * dn) / (dm + dn)), KS_SUM_CAUCHY_CRITERION, 100000);
    }

    static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(boolean[] b, int numberOfTrueValues, RandomGenerator rng) {
        Arrays.fill(b, true);
        for (int k = numberOfTrueValues; k < b.length; k++) {
            int r = rng.nextInt(k + 1);
            b[b[r] ? r : k] = false;
        }
    }

    public double monteCarloP(double d, int n, int m, boolean strict, int iterations) {
        return integralMonteCarloP(calculateIntegralD(d, n, m, strict), n, m, iterations);
    }

    private double integralMonteCarloP(long d, int n, int m, int iterations) {
        int nn = FastMath.max(n, m);
        int mm = FastMath.min(n, m);
        int sum = nn + mm;
        int tail = 0;
        boolean[] b = new boolean[sum];
        for (int i = 0; i < iterations; i++) {
            fillBooleanArrayRandomlyWithFixedNumberTrueValues(b, nn, this.rng);
            long curD = 0;
            int j = 0;
            while (true) {
                if (j >= b.length) {
                    break;
                }
                if (b[j]) {
                    curD += mm;
                    if (curD < d) {
                        j++;
                    } else {
                        tail++;
                        break;
                    }
                } else {
                    curD -= nn;
                    if (curD > (-d)) {
                        j++;
                    } else {
                        tail++;
                        break;
                    }
                }
            }
        }
        return tail / iterations;
    }

    private static void fixTies(double[] x, double[] y) {
        boolean ties;
        double[] values = MathArrays.unique(MathArrays.concatenate(x, y));
        if (values.length == x.length + y.length) {
            return;
        }
        double minDelta = 1.0d;
        double prev = values[0];
        for (int i = 1; i < values.length; i++) {
            double delta = prev - values[i];
            if (delta < minDelta) {
                minDelta = delta;
            }
            prev = values[i];
        }
        double minDelta2 = minDelta / 2.0d;
        RealDistribution dist = new UniformRealDistribution(new JDKRandomGenerator(100), -minDelta2, minDelta2);
        int ct = 0;
        do {
            jitter(x, dist);
            jitter(y, dist);
            ties = hasTies(x, y);
            ct++;
            if (!ties) {
                break;
            }
        } while (ct < 1000);
        if (ties) {
            throw new MathInternalError();
        }
    }

    private static boolean hasTies(double[] x, double[] y) {
        HashSet<Double> values = new HashSet<>();
        for (double d : x) {
            if (!values.add(Double.valueOf(d))) {
                return true;
            }
        }
        for (double d2 : y) {
            if (!values.add(Double.valueOf(d2))) {
                return true;
            }
        }
        return false;
    }

    private static void jitter(double[] data, RealDistribution dist) {
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] + dist.sample();
        }
    }

    private static int c(int i, int j, int m, int n, long cmn, boolean strict) {
        return strict ? FastMath.abs((((long) i) * ((long) n)) - (((long) j) * ((long) m))) <= cmn ? 1 : 0 : FastMath.abs((((long) i) * ((long) n)) - (((long) j) * ((long) m))) < cmn ? 1 : 0;
    }

    private static double n(int i, int j, int m, int n, long cnm, boolean strict) {
        double[] lag = new double[n];
        double last = 0.0d;
        for (int k = 0; k < n; k++) {
            lag[k] = c(0, k + 1, m, n, cnm, strict);
        }
        for (int k2 = 1; k2 <= i; k2++) {
            double last2 = c(k2, 0, m, n, cnm, strict);
            last = last2;
            for (int l = 1; l <= j; l++) {
                lag[l - 1] = c(k2, l, m, n, cnm, strict) * (lag[l - 1] + last);
                last = lag[l - 1];
            }
        }
        return last;
    }
}
