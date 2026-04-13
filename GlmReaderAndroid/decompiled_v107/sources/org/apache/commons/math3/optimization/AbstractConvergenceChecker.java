package org.apache.commons.math3.optimization;

import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes10.dex */
public abstract class AbstractConvergenceChecker<PAIR> implements ConvergenceChecker<PAIR> {
    private final double absoluteThreshold;
    private final double relativeThreshold;

    @Deprecated
    private static final double DEFAULT_RELATIVE_THRESHOLD = Precision.EPSILON * 100.0d;

    @Deprecated
    private static final double DEFAULT_ABSOLUTE_THRESHOLD = Precision.SAFE_MIN * 100.0d;

    @Override // org.apache.commons.math3.optimization.ConvergenceChecker
    public abstract boolean converged(int i, PAIR pair, PAIR pair2);

    @Deprecated
    public AbstractConvergenceChecker() {
        this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
        this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
    }

    public AbstractConvergenceChecker(double relativeThreshold, double absoluteThreshold) {
        this.relativeThreshold = relativeThreshold;
        this.absoluteThreshold = absoluteThreshold;
    }

    public double getRelativeThreshold() {
        return this.relativeThreshold;
    }

    public double getAbsoluteThreshold() {
        return this.absoluteThreshold;
    }
}
