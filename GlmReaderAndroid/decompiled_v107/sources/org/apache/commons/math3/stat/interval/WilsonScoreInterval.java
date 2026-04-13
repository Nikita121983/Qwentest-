package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class WilsonScoreInterval implements BinomialConfidenceInterval {
    @Override // org.apache.commons.math3.stat.interval.BinomialConfidenceInterval
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double alpha = (1.0d - confidenceLevel) / 2.0d;
        NormalDistribution normalDistribution = new NormalDistribution();
        double z = normalDistribution.inverseCumulativeProbability(1.0d - alpha);
        double zSquared = FastMath.pow(z, 2);
        double mean = numberOfSuccesses / numberOfTrials;
        double factor = 1.0d / (((1.0d / numberOfTrials) * zSquared) + 1.0d);
        double modifiedSuccessRatio = ((1.0d / (numberOfTrials * 2)) * zSquared) + mean;
        double difference = FastMath.sqrt(((1.0d / numberOfTrials) * mean * (1.0d - mean)) + ((1.0d / (FastMath.pow(numberOfTrials, 2) * 4.0d)) * zSquared)) * z;
        double lowerBound = factor * (modifiedSuccessRatio - difference);
        double upperBound = factor * (modifiedSuccessRatio + difference);
        return new ConfidenceInterval(lowerBound, upperBound, confidenceLevel);
    }
}
