package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class MannWhitneyUTest {
    private NaturalRanking naturalRanking;

    public MannWhitneyUTest() {
        this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    public MannWhitneyUTest(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        this.naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
    }

    private void ensureDataConformance(double[] x, double[] y) throws NullArgumentException, NoDataException {
        if (x == null || y == null) {
            throw new NullArgumentException();
        }
        if (x.length == 0 || y.length == 0) {
            throw new NoDataException();
        }
    }

    private double[] concatenateSamples(double[] x, double[] y) {
        double[] z = new double[x.length + y.length];
        System.arraycopy(x, 0, z, 0, x.length);
        System.arraycopy(y, 0, z, x.length, y.length);
        return z;
    }

    public double mannWhitneyU(double[] x, double[] y) throws NullArgumentException, NoDataException {
        ensureDataConformance(x, y);
        double[] z = concatenateSamples(x, y);
        double[] ranks = this.naturalRanking.rank(z);
        double sumRankX = 0.0d;
        for (int i = 0; i < x.length; i++) {
            sumRankX += ranks[i];
        }
        int i2 = x.length;
        double U1 = sumRankX - ((i2 * (x.length + 1)) / 2);
        double U2 = (x.length * y.length) - U1;
        return FastMath.max(U1, U2);
    }

    private double calculateAsymptoticPValue(double Umin, int n1, int n2) throws ConvergenceException, MaxCountExceededException {
        long n1n2prod = n1 * n2;
        double EU = n1n2prod / 2.0d;
        double VarU = (((n1 + n2) + 1) * n1n2prod) / 12.0d;
        double z = (Umin - EU) / FastMath.sqrt(VarU);
        NormalDistribution standardNormal = new NormalDistribution((RandomGenerator) null, 0.0d, 1.0d);
        return standardNormal.cumulativeProbability(z) * 2.0d;
    }

    public double mannWhitneyUTest(double[] x, double[] y) throws NullArgumentException, NoDataException, ConvergenceException, MaxCountExceededException {
        ensureDataConformance(x, y);
        double Umax = mannWhitneyU(x, y);
        double Umin = (x.length * y.length) - Umax;
        return calculateAsymptoticPValue(Umin, x.length, y.length);
    }
}
