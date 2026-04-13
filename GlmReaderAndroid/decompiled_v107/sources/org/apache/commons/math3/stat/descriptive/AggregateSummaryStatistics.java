package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.NullArgumentException;

/* loaded from: classes10.dex */
public class AggregateSummaryStatistics implements StatisticalSummary, Serializable {
    private static final long serialVersionUID = -8207112444016386906L;
    private final SummaryStatistics statistics;
    private final SummaryStatistics statisticsPrototype;

    public AggregateSummaryStatistics() {
        this(new SummaryStatistics());
    }

    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics) throws NullArgumentException {
        this(prototypeStatistics, prototypeStatistics == null ? null : new SummaryStatistics(prototypeStatistics));
    }

    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics, SummaryStatistics initialStatistics) {
        this.statisticsPrototype = prototypeStatistics == null ? new SummaryStatistics() : prototypeStatistics;
        this.statistics = initialStatistics == null ? new SummaryStatistics() : initialStatistics;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMax() {
        double max;
        synchronized (this.statistics) {
            max = this.statistics.getMax();
        }
        return max;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMean() {
        double mean;
        synchronized (this.statistics) {
            mean = this.statistics.getMean();
        }
        return mean;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getMin() {
        double min;
        synchronized (this.statistics) {
            min = this.statistics.getMin();
        }
        return min;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public long getN() {
        long n;
        synchronized (this.statistics) {
            n = this.statistics.getN();
        }
        return n;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getStandardDeviation() {
        double standardDeviation;
        synchronized (this.statistics) {
            standardDeviation = this.statistics.getStandardDeviation();
        }
        return standardDeviation;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getSum() {
        double sum;
        synchronized (this.statistics) {
            sum = this.statistics.getSum();
        }
        return sum;
    }

    @Override // org.apache.commons.math3.stat.descriptive.StatisticalSummary
    public double getVariance() {
        double variance;
        synchronized (this.statistics) {
            variance = this.statistics.getVariance();
        }
        return variance;
    }

    public double getSumOfLogs() {
        double sumOfLogs;
        synchronized (this.statistics) {
            sumOfLogs = this.statistics.getSumOfLogs();
        }
        return sumOfLogs;
    }

    public double getGeometricMean() {
        double geometricMean;
        synchronized (this.statistics) {
            geometricMean = this.statistics.getGeometricMean();
        }
        return geometricMean;
    }

    public double getSumsq() {
        double sumsq;
        synchronized (this.statistics) {
            sumsq = this.statistics.getSumsq();
        }
        return sumsq;
    }

    public double getSecondMoment() {
        double secondMoment;
        synchronized (this.statistics) {
            secondMoment = this.statistics.getSecondMoment();
        }
        return secondMoment;
    }

    public StatisticalSummary getSummary() {
        StatisticalSummaryValues statisticalSummaryValues;
        synchronized (this.statistics) {
            statisticalSummaryValues = new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
        }
        return statisticalSummaryValues;
    }

    public SummaryStatistics createContributingStatistics() {
        SummaryStatistics contributingStatistics = new AggregatingSummaryStatistics(this.statistics);
        SummaryStatistics.copy(this.statisticsPrototype, contributingStatistics);
        return contributingStatistics;
    }

    public static StatisticalSummaryValues aggregate(Collection<? extends StatisticalSummary> statistics) {
        double variance;
        if (statistics == null) {
            return null;
        }
        Iterator<? extends StatisticalSummary> iterator = statistics.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        StatisticalSummary current = iterator.next();
        long n = current.getN();
        double min = current.getMin();
        double sum = current.getSum();
        double max = current.getMax();
        double var = current.getVariance();
        double d = 1.0d;
        double m2 = (n - 1.0d) * var;
        double min2 = min;
        double sum2 = sum;
        double max2 = max;
        double mean = current.getMean();
        while (iterator.hasNext()) {
            StatisticalSummary current2 = iterator.next();
            if (current2.getMin() < min2 || Double.isNaN(min2)) {
                min2 = current2.getMin();
            }
            if (current2.getMax() > max2 || Double.isNaN(max2)) {
                max2 = current2.getMax();
            }
            sum2 += current2.getSum();
            double oldN = n;
            double curN = current2.getN();
            n = (long) (n + curN);
            double meanDiff = current2.getMean() - mean;
            double d2 = d;
            mean = sum2 / n;
            double curM2 = current2.getVariance() * (curN - d2);
            m2 = m2 + curM2 + ((((meanDiff * meanDiff) * oldN) * curN) / n);
            d = d2;
            iterator = iterator;
        }
        if (n == 0) {
            variance = Double.NaN;
        } else if (n == 1) {
            variance = 0.0d;
        } else {
            variance = m2 / (n - 1);
        }
        return new StatisticalSummaryValues(mean, variance, n, max2, min2, sum2);
    }

    /* loaded from: classes10.dex */
    private static class AggregatingSummaryStatistics extends SummaryStatistics {
        private static final long serialVersionUID = 1;
        private final SummaryStatistics aggregateStatistics;

        AggregatingSummaryStatistics(SummaryStatistics aggregateStatistics) {
            this.aggregateStatistics = aggregateStatistics;
        }

        @Override // org.apache.commons.math3.stat.descriptive.SummaryStatistics
        public void addValue(double value) {
            super.addValue(value);
            synchronized (this.aggregateStatistics) {
                this.aggregateStatistics.addValue(value);
            }
        }

        @Override // org.apache.commons.math3.stat.descriptive.SummaryStatistics
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof AggregatingSummaryStatistics)) {
                return false;
            }
            AggregatingSummaryStatistics stat = (AggregatingSummaryStatistics) object;
            return super.equals(stat) && this.aggregateStatistics.equals(stat.aggregateStatistics);
        }

        @Override // org.apache.commons.math3.stat.descriptive.SummaryStatistics
        public int hashCode() {
            return super.hashCode() + 123 + this.aggregateStatistics.hashCode();
        }
    }
}
