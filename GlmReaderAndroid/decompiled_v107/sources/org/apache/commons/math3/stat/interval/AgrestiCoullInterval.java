package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class AgrestiCoullInterval implements BinomialConfidenceInterval {
    @Override // org.apache.commons.math3.stat.interval.BinomialConfidenceInterval
    public ConfidenceInterval createInterval(int numberOfTrials, int numberOfSuccesses, double confidenceLevel) {
        IntervalUtils.checkParameters(numberOfTrials, numberOfSuccesses, confidenceLevel);
        double alpha = (1.0d - confidenceLevel) / 2.0d;
        NormalDistribution normalDistribution = new NormalDistribution();
        double z = normalDistribution.inverseCumulativeProbability(1.0d - alpha);
        double zSquared = FastMath.pow(z, 2);
        double modifiedNumberOfTrials = numberOfTrials + zSquared;
        double modifiedSuccessesRatio = (1.0d / modifiedNumberOfTrials) * (numberOfSuccesses + (0.5d * zSquared));
        double difference = FastMath.sqrt((1.0d / modifiedNumberOfTrials) * modifiedSuccessesRatio * (1.0d - modifiedSuccessesRatio)) * z;
        return new ConfidenceInterval(modifiedSuccessesRatio - difference, modifiedSuccessesRatio + difference, confidenceLevel);
    }
}
