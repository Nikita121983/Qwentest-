package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class ChiSquareTest {
    public double chiSquare(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
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
        double sumSq = 0.0d;
        for (int i2 = 0; i2 < observed.length; i2++) {
            if (rescale) {
                double dev = observed[i2] - (expected[i2] * ratio);
                d = dev * dev;
                d2 = expected[i2] * ratio;
            } else {
                double dev2 = observed[i2] - expected[i2];
                d = dev2 * dev2;
                d2 = expected[i2];
            }
            sumSq += d / d2;
        }
        return sumSq;
    }

    public double chiSquareTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution((RandomGenerator) null, expected.length - 1.0d);
        return 1.0d - distribution.cumulativeProbability(chiSquare(expected, observed));
    }

    public boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return chiSquareTest(expected, observed) < alpha;
    }

    public double chiSquare(long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        long[][] jArr = counts;
        checkArray(counts);
        int nRows = jArr.length;
        int nCols = jArr[0].length;
        double[] rowSum = new double[nRows];
        double[] colSum = new double[nCols];
        double total = 0.0d;
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                rowSum[row] = rowSum[row] + jArr[row][col];
                colSum[col] = colSum[col] + jArr[row][col];
                total += jArr[row][col];
            }
        }
        double sumSq = 0.0d;
        int row2 = 0;
        while (row2 < nRows) {
            int col2 = 0;
            while (col2 < nCols) {
                double expected = (rowSum[row2] * colSum[col2]) / total;
                sumSq += ((jArr[row2][col2] - expected) * (jArr[row2][col2] - expected)) / expected;
                col2++;
                jArr = counts;
                nRows = nRows;
            }
            row2++;
            jArr = counts;
        }
        return sumSq;
    }

    public double chiSquareTest(long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        checkArray(counts);
        double df = (counts.length - 1.0d) * (counts[0].length - 1.0d);
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(df);
        return 1.0d - distribution.cumulativeProbability(chiSquare(counts));
    }

    public boolean chiSquareTest(long[][] counts, double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return chiSquareTest(counts) < alpha;
    }

    public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        long countSum1;
        double dev;
        if (observed1.length < 2) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (observed1.length != observed2.length) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);
        long countSum12 = 0;
        long countSum2 = 0;
        double weight = 0.0d;
        for (int i = 0; i < observed1.length; i++) {
            countSum12 += observed1[i];
            countSum2 += observed2[i];
        }
        long j = 0;
        if (countSum12 == 0 || countSum2 == 0) {
            throw new ZeroException();
        }
        boolean unequalCounts = countSum12 != countSum2;
        if (unequalCounts) {
            weight = FastMath.sqrt(countSum12 / countSum2);
        }
        double sumSq = 0.0d;
        int i2 = 0;
        while (true) {
            long j2 = j;
            if (i2 < observed1.length) {
                if (observed1[i2] != j2) {
                    countSum1 = countSum12;
                } else {
                    if (observed2[i2] == j2) {
                        throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i2));
                    }
                    countSum1 = countSum12;
                }
                long countSum13 = observed1[i2];
                double obs1 = countSum13;
                double obs2 = observed2[i2];
                if (unequalCounts) {
                    dev = (obs1 / weight) - (obs2 * weight);
                } else {
                    dev = obs1 - obs2;
                }
                sumSq += (dev * dev) / (obs1 + obs2);
                i2++;
                j = j2;
                countSum12 = countSum1;
            } else {
                return sumSq;
            }
        }
    }

    public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution((RandomGenerator) null, observed1.length - 1.0d);
        return 1.0d - distribution.cumulativeProbability(chiSquareDataSetsComparison(observed1, observed2));
    }

    public boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0d || alpha > 0.5d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), 0, Double.valueOf(0.5d));
        }
        return chiSquareTestDataSetsComparison(observed1, observed2) < alpha;
    }

    private void checkArray(long[][] in) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        if (in.length < 2) {
            throw new DimensionMismatchException(in.length, 2);
        }
        if (in[0].length < 2) {
            throw new DimensionMismatchException(in[0].length, 2);
        }
        MathArrays.checkRectangular(in);
        MathArrays.checkNonNegative(in);
    }
}
