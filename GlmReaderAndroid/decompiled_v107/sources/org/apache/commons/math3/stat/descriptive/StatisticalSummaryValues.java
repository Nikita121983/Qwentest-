package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class StatisticalSummaryValues implements Serializable, StatisticalSummary {
    private static final long serialVersionUID = -5108854841843722536L;
    private final double max;
    private final double mean;
    private final double min;
    private final long n;
    private final double sum;
    private final double variance;

    public StatisticalSummaryValues(double mean, double variance, long n, double max, double min, double sum) {
        this.mean = mean;
        this.variance = variance;
        this.n = n;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMax() {
        return this.max;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMean() {
        return this.mean;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMin() {
        return this.min;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public long getN() {
        return this.n;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getSum() {
        return this.sum;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getStandardDeviation() {
        return FastMath.sqrt(this.variance);
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getVariance() {
        return this.variance;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof StatisticalSummaryValues)) {
            return false;
        }
        StatisticalSummaryValues stat = (StatisticalSummaryValues) object;
        return Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()) && Precision.equalsIncludingNaN(stat.getMin(), getMin()) && Precision.equalsIncludingNaN((float) stat.getN(), (float) getN()) && Precision.equalsIncludingNaN(stat.getSum(), getSum()) && Precision.equalsIncludingNaN(stat.getVariance(), getVariance());
    }

    public int hashCode() {
        int result = MathUtils.hash(getMax()) + 31;
        return (((((((((result * 31) + MathUtils.hash(getMean())) * 31) + MathUtils.hash(getMin())) * 31) + MathUtils.hash(getN())) * 31) + MathUtils.hash(getSum())) * 31) + MathUtils.hash(getVariance());
    }

    public String toString() {
        StringBuffer outBuffer = new StringBuffer();
        outBuffer.append("StatisticalSummaryValues:").append(StringUtils.LF);
        outBuffer.append("n: ").append(getN()).append(StringUtils.LF);
        outBuffer.append("min: ").append(getMin()).append(StringUtils.LF);
        outBuffer.append("max: ").append(getMax()).append(StringUtils.LF);
        outBuffer.append("mean: ").append(getMean()).append(StringUtils.LF);
        outBuffer.append("std dev: ").append(getStandardDeviation()).append(StringUtils.LF);
        outBuffer.append("variance: ").append(getVariance()).append(StringUtils.LF);
        outBuffer.append("sum: ").append(getSum()).append(StringUtils.LF);
        return outBuffer.toString();
    }
}
