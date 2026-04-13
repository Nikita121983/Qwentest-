package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class WilcoxonSignedRankTest {
    private NaturalRanking naturalRanking;

    public WilcoxonSignedRankTest() {
        this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    public WilcoxonSignedRankTest(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        this.naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
    }

    private void ensureDataConformance(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
        if (x == null || y == null) {
            throw new NullArgumentException();
        }
        if (x.length == 0 || y.length == 0) {
            throw new NoDataException();
        }
        if (y.length != x.length) {
            throw new DimensionMismatchException(y.length, x.length);
        }
    }

    private double[] calculateDifferences(double[] x, double[] y) {
        double[] z = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            z[i] = y[i] - x[i];
        }
        return z;
    }

    private double[] calculateAbsoluteDifferences(double[] z) throws NullArgumentException, NoDataException {
        if (z == null) {
            throw new NullArgumentException();
        }
        if (z.length == 0) {
            throw new NoDataException();
        }
        double[] zAbs = new double[z.length];
        for (int i = 0; i < z.length; i++) {
            zAbs[i] = FastMath.abs(z[i]);
        }
        return zAbs;
    }

    public double wilcoxonSignedRank(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
        ensureDataConformance(x, y);
        double[] z = calculateDifferences(x, y);
        double[] zAbs = calculateAbsoluteDifferences(z);
        double[] ranks = this.naturalRanking.rank(zAbs);
        double Wplus = 0.0d;
        for (int i = 0; i < z.length; i++) {
            if (z[i] > 0.0d) {
                Wplus += ranks[i];
            }
        }
        int i2 = x.length;
        double Wminus = (((i2 + 1) * i2) / 2.0d) - Wplus;
        return FastMath.max(Wplus, Wminus);
    }

    private double calculateExactPValue(double Wmax, int N) {
        int m = 1 << N;
        int largerRankSums = 0;
        for (int i = 0; i < m; i++) {
            int rankSum = 0;
            for (int j = 0; j < N; j++) {
                if (((i >> j) & 1) == 1) {
                    rankSum += j + 1;
                }
            }
            if (rankSum >= Wmax) {
                largerRankSums++;
            }
        }
        return (largerRankSums * 2.0d) / m;
    }

    private double calculateAsymptoticPValue(double Wmin, int N) {
        double ES = ((N + 1) * N) / 4.0d;
        double VarS = (((N * 2) + 1) / 6.0d) * ES;
        double z = ((Wmin - ES) - 0.5d) / FastMath.sqrt(VarS);
        NormalDistribution standardNormal = new NormalDistribution((RandomGenerator) null, 0.0d, 1.0d);
        return standardNormal.cumulativeProbability(z) * 2.0d;
    }

    public double wilcoxonSignedRankTest(double[] x, double[] y, boolean exactPValue) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooLargeException, ConvergenceException, MaxCountExceededException {
        ensureDataConformance(x, y);
        int N = x.length;
        double Wmax = wilcoxonSignedRank(x, y);
        if (exactPValue && N > 30) {
            throw new NumberIsTooLargeException(Integer.valueOf(N), 30, true);
        }
        if (exactPValue) {
            return calculateExactPValue(Wmax, N);
        }
        double Wmin = (((N + 1) * N) / 2.0d) - Wmax;
        return calculateAsymptoticPValue(Wmin, N);
    }
}
