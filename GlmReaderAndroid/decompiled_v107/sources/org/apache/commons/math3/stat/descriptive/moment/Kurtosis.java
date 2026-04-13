package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class Kurtosis extends AbstractStorelessUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = 2784465764798260919L;
    protected boolean incMoment;
    protected FourthMoment moment;

    public Kurtosis() {
        this.incMoment = true;
        this.moment = new FourthMoment();
    }

    public Kurtosis(FourthMoment m4) {
        this.incMoment = false;
        this.moment = m4;
    }

    public Kurtosis(Kurtosis original) throws NullArgumentException {
        copy(original, this);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.incMoment) {
            this.moment.increment(d);
        }
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        if (this.moment.getN() <= 3) {
            return Double.NaN;
        }
        double variance = this.moment.m2 / (this.moment.n - 1);
        if (this.moment.n <= 3 || variance < 1.0E-19d) {
            return 0.0d;
        }
        double n = this.moment.n;
        double kurtosis = ((((n + 1.0d) * n) * this.moment.getResult()) - (((this.moment.m2 * 3.0d) * this.moment.m2) * (n - 1.0d))) / (((((n - 1.0d) * (n - 2.0d)) * (n - 3.0d)) * variance) * variance);
        return kurtosis;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        if (this.incMoment) {
            this.moment.clear();
        }
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.moment.getN();
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] values, int begin, int length) throws MathIllegalArgumentException {
        double[] dArr = values;
        int i = begin;
        if (!test(values, begin, length) || length <= 3) {
            return Double.NaN;
        }
        Variance variance = new Variance();
        variance.incrementAll(dArr, i, length);
        double mean = variance.moment.m1;
        double stdDev = FastMath.sqrt(variance.getResult());
        double accum3 = 0.0d;
        int i2 = begin;
        while (i2 < i + length) {
            accum3 += FastMath.pow(dArr[i2] - mean, 4.0d);
            i2++;
            dArr = values;
            i = begin;
        }
        double accum32 = accum3 / FastMath.pow(stdDev, 4.0d);
        double n0 = length;
        double coefficientOne = ((n0 + 1.0d) * n0) / (((n0 - 1.0d) * (n0 - 2.0d)) * (n0 - 3.0d));
        double termTwo = (FastMath.pow(n0 - 1.0d, 2.0d) * 3.0d) / ((n0 - 2.0d) * (n0 - 3.0d));
        double kurt = (coefficientOne * accum32) - termTwo;
        return kurt;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public Kurtosis copy() {
        Kurtosis result = new Kurtosis();
        copy(this, result);
        return result;
    }

    public static void copy(Kurtosis source, Kurtosis dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.moment = source.moment.copy();
        dest.incMoment = source.incMoment;
    }
}
