package org.apache.commons.math3.stat.inference;

import java.lang.reflect.Array;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class GTest {
    public double g(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        double d;
        double d2;
        if (expected.length < 2) {
            throw new DimensionMismatchException(expected.length, 2);
        }
        if (expected.length != observed.length) {
            throw new DimensionMismatchException(expected.length, observed.length);
        }
        MathArrays.checkPositive(expected);
        MathArrays.checkNonNegative(observed);
        double sumExpected = 0.0d;
        double sumObserved = 0.0d;
        for (int i = 0; i < observed.length; i++) {
            sumExpected += expected[i];
            sumObserved += observed[i];
        }
        double ratio = 1.0d;
        boolean rescale = false;
        if (FastMath.abs(sumExpected - sumObserved) > 1.0E-5d) {
            ratio = sumObserved / sumExpected;
            rescale = true;
        }
        double sum = 0.0d;
        for (int i2 = 0; i2 < observed.length; i2++) {
            if (rescale) {
                d = observed[i2];
                d2 = expected[i2] * ratio;
            } else {
                d = observed[i2];
                d2 = expected[i2];
            }
            double dev = FastMath.log(d / d2);
            sum += observed[i2] * dev;
        }
        return 2.0d * sum;
    }

    public double gTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution((RandomGenerator) null, expected.length - 1.0d);
        return 1.0d - distribution.cumulativeProbability(g(expected, observed));
    }

    public double gTestIntrinsic(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution((RandomGenerator) null, expected.length - 2.0d);
        return 1.0d - distribution.cumulativeProbability(g(expected, observed));
    }

    public boolean gTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return gTest(expected, observed) < alpha;
    }

    private double entropy(long[][] k) {
        double h = 0.0d;
        double sum_k = 0.0d;
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                sum_k += k[i][j];
            }
        }
        for (int i2 = 0; i2 < k.length; i2++) {
            for (int j2 = 0; j2 < k[i2].length; j2++) {
                if (k[i2][j2] != 0) {
                    double p_ij = k[i2][j2] / sum_k;
                    h += FastMath.log(p_ij) * p_ij;
                }
            }
        }
        return -h;
    }

    private double entropy(long[] k) {
        double h = 0.0d;
        double sum_k = 0.0d;
        for (long j : k) {
            sum_k += j;
        }
        for (int i = 0; i < k.length; i++) {
            if (k[i] != 0) {
                double p_i = k[i] / sum_k;
                h += FastMath.log(p_i) * p_i;
            }
        }
        return -h;
    }

    public double gDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        if (observed1.length < 2) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (observed1.length != observed2.length) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);
        long countSum1 = 0;
        long countSum2 = 0;
        long[] collSums = new long[observed1.length];
        long[][] k = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 2, observed1.length);
        for (int i = 0; i < observed1.length; i++) {
            if (observed1[i] == 0 && observed2[i] == 0) {
                throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i));
            }
            countSum1 += observed1[i];
            countSum2 += observed2[i];
            collSums[i] = observed1[i] + observed2[i];
            k[0][i] = observed1[i];
            k[1][i] = observed2[i];
        }
        if (countSum1 == 0 || countSum2 == 0) {
            throw new ZeroException();
        }
        long[] rowSums = {countSum1, countSum2};
        double sum = countSum1 + countSum2;
        return 2.0d * sum * ((entropy(rowSums) + entropy(collSums)) - entropy(k));
    }

    public double rootLogLikelihoodRatio(long k11, long k12, long k21, long k22) {
        double llr = gDataSetsComparison(new long[]{k11, k12}, new long[]{k21, k22});
        double sqrt = FastMath.sqrt(llr);
        if (k11 / (k11 + k12) < k21 / (k21 + k22)) {
            return -sqrt;
        }
        return sqrt;
    }

    public double gTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution((RandomGenerator) null, observed1.length - 1.0d);
        return 1.0d - distribution.cumulativeProbability(gDataSetsComparison(observed1, observed2));
    }

    public boolean gTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return gTestDataSetsComparison(observed1, observed2) < alpha;
    }
}
