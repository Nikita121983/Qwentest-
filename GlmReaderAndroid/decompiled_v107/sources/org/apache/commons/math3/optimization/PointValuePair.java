package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

@Deprecated
/* loaded from: classes10.dex */
public class PointValuePair extends Pair<double[], Double> implements Serializable {
    private static final long serialVersionUID = 20120513;

    public PointValuePair(double[] point, double value) {
        this(point, value, true);
    }

    public PointValuePair(double[] point, double value, boolean copyArray) {
        super(copyArray ? point == null ? null : (double[]) point.clone() : point, Double.valueOf(value));
    }

    public double[] getPoint() {
        double[] p = getKey();
        if (p == null) {
            return null;
        }
        return (double[]) p.clone();
    }

    public double[] getPointRef() {
        return getKey();
    }

    private Object writeReplace() {
        return new DataTransferObject(getKey(), getValue().doubleValue());
    }

    /* loaded from: classes10.dex */
    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120513;
        private final double[] point;
        private final double value;

        DataTransferObject(double[] point, double value) {
            this.point = (double[]) point.clone();
            this.value = value;
        }

        private Object readResolve() {
            return new PointValuePair(this.point, this.value, false);
        }
    }
}
