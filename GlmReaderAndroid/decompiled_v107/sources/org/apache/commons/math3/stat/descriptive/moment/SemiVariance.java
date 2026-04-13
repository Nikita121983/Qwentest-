package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class SemiVariance extends AbstractUnivariateStatistic implements Serializable {
    private static final long serialVersionUID = -2653430366886024994L;
    private boolean biasCorrected;
    private Direction varianceDirection;
    public static final Direction UPSIDE_VARIANCE = Direction.UPSIDE;
    public static final Direction DOWNSIDE_VARIANCE = Direction.DOWNSIDE;

    public SemiVariance() {
        this.biasCorrected = true;
        this.varianceDirection = Direction.DOWNSIDE;
    }

    public SemiVariance(boolean biasCorrected) {
        this.biasCorrected = true;
        this.varianceDirection = Direction.DOWNSIDE;
        this.biasCorrected = biasCorrected;
    }

    public SemiVariance(Direction direction) {
        this.biasCorrected = true;
        this.varianceDirection = Direction.DOWNSIDE;
        this.varianceDirection = direction;
    }

    public SemiVariance(boolean corrected, Direction direction) {
        this.biasCorrected = true;
        this.varianceDirection = Direction.DOWNSIDE;
        this.biasCorrected = corrected;
        this.varianceDirection = direction;
    }

    public SemiVariance(SemiVariance original) throws NullArgumentException {
        this.biasCorrected = true;
        this.varianceDirection = Direction.DOWNSIDE;
        copy(original, this);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public SemiVariance copy() {
        SemiVariance result = new SemiVariance();
        copy(this, result);
        return result;
    }

    public static void copy(SemiVariance source, SemiVariance dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.biasCorrected = source.biasCorrected;
        dest.varianceDirection = source.varianceDirection;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] values, int start, int length) throws MathIllegalArgumentException {
        double m = new Mean().evaluate(values, start, length);
        return evaluate(values, m, this.varianceDirection, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, Direction direction) throws MathIllegalArgumentException {
        double m = new Mean().evaluate(values);
        return evaluate(values, m, direction, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff) throws MathIllegalArgumentException {
        return evaluate(values, cutoff, this.varianceDirection, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff, Direction direction) throws MathIllegalArgumentException {
        return evaluate(values, cutoff, direction, this.biasCorrected, 0, values.length);
    }

    public double evaluate(double[] values, double cutoff, Direction direction, boolean corrected, int start, int length) throws MathIllegalArgumentException {
        test(values, start, length);
        if (values.length == 0) {
            return Double.NaN;
        }
        if (values.length == 1) {
            return 0.0d;
        }
        boolean booleanDirection = direction.getDirection();
        double sumsq = 0.0d;
        for (int i = start; i < length; i++) {
            if ((values[i] > cutoff) == booleanDirection) {
                double dev = values[i] - cutoff;
                sumsq += dev * dev;
            }
        }
        if (corrected) {
            return sumsq / (length - 1.0d);
        }
        return sumsq / length;
    }

    public boolean isBiasCorrected() {
        return this.biasCorrected;
    }

    public void setBiasCorrected(boolean biasCorrected) {
        this.biasCorrected = biasCorrected;
    }

    public Direction getVarianceDirection() {
        return this.varianceDirection;
    }

    public void setVarianceDirection(Direction varianceDirection) {
        this.varianceDirection = varianceDirection;
    }

    /* loaded from: classes10.dex */
    public enum Direction {
        UPSIDE(true),
        DOWNSIDE(false);

        private boolean direction;

        Direction(boolean b) {
            this.direction = b;
        }

        boolean getDirection() {
            return this.direction;
        }
    }
}
