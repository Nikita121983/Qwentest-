package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.FDistribution;

/* loaded from: classes10.dex */
public class ClopperPearsonInterval implements BinomialConfidenceInterval {
    @Override // org.apache.commons.math3.stat.interval.BinomialConfidenceInterval
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        double d;
        double lowerBound;
        double upperBound;
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double alpha = (1.0d - confidenceLevel) / 2.0d;
        FDistribution distributionLowerBound = new FDistribution(((numberOfTrials - numberOfSuccesses) + 1) * 2, numberOfSuccesses * 2);
        double fValueLowerBound = distributionLowerBound.inverseCumulativeProbability(1.0d - alpha);
        if (numberOfSuccesses > 0) {
            d = 1.0d;
            double lowerBound2 = numberOfSuccesses / (numberOfSuccesses + (((numberOfTrials - numberOfSuccesses) + 1) * fValueLowerBound));
            lowerBound = lowerBound2;
        } else {
            d = 1.0d;
            lowerBound = 0.0d;
        }
        FDistribution distributionUpperBound = new FDistribution((numberOfSuccesses + 1) * 2, (numberOfTrials - numberOfSuccesses) * 2);
        double fValueUpperBound = distributionUpperBound.inverseCumulativeProbability(d - alpha);
        if (numberOfSuccesses > 0) {
            double upperBound2 = ((numberOfSuccesses + 1) * fValueUpperBound) / ((numberOfTrials - numberOfSuccesses) + ((numberOfSuccesses + 1) * fValueUpperBound));
            upperBound = upperBound2;
        } else {
            upperBound = 0.0d;
        }
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}
