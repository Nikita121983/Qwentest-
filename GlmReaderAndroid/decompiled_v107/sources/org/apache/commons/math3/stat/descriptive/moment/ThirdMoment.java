package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
class ThirdMoment extends SecondMoment implements Serializable {
    private static final long serialVersionUID = -7818711964045118679L;
    protected double m3;
    protected double nDevSq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThirdMoment() {
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThirdMoment(ThirdMoment original) throws NullArgumentException {
        copy(original, this);
    }

    @Override // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double d) {
        if (this.n < 1) {
            this.m1 = 0.0d;
            this.m2 = 0.0d;
            this.m3 = 0.0d;
        }
        double prevM2 = this.m2;
        super.increment(d);
        this.nDevSq = this.nDev * this.nDev;
        double n0 = this.n;
        this.m3 = (this.m3 - ((this.nDev * 3.0d) * prevM2)) + ((n0 - 1.0d) * (n0 - 2.0d) * this.nDevSq * this.dev);
    }

    @Override // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        return this.m3;
    }

    @Override // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        super.clear();
        this.m3 = Double.NaN;
        this.nDevSq = Double.NaN;
    }

    @Override // org.apache.commons.math3.stat.descriptive.moment.SecondMoment, org.apache.commons.math3.stat.descriptive.moment.FirstMoment, org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public ThirdMoment copy() {
        ThirdMoment result = new ThirdMoment();
        copy(this, result);
        return result;
    }

    public static void copy(ThirdMoment source, ThirdMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        SecondMoment.copy((SecondMoment) source, (SecondMoment) dest);
        dest.m3 = source.m3;
        dest.nDevSq = source.nDevSq;
    }
}
