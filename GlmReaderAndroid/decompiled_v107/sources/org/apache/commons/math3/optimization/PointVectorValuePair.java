package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

@Deprecated
/* loaded from: classes10.dex */
public class PointVectorValuePair extends Pair<double[], double[]> implements Serializable {
    private static final long serialVersionUID = 20120513;

    public PointVectorValuePair(double[] point, double[] value) {
        this(point, value, true);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PointVectorValuePair(double[] r3, double[] r4, boolean r5) {
        /*
            r2 = this;
            r0 = 0
            if (r5 == 0) goto Le
            if (r3 != 0) goto L7
            r1 = r0
            goto Lf
        L7:
            java.lang.Object r1 = r3.clone()
            double[] r1 = (double[]) r1
            goto Lf
        Le:
            r1 = r3
        Lf:
            if (r5 == 0) goto L1b
            if (r4 != 0) goto L14
            goto L1c
        L14:
            java.lang.Object r0 = r4.clone()
            double[] r0 = (double[]) r0
            goto L1c
        L1b:
            r0 = r4
        L1c:
            r2.<init>(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.PointVectorValuePair.<init>(double[], double[], boolean):void");
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

    @Override // org.apache.commons.math3.util.Pair
    public double[] getValue() {
        double[] v = (double[]) super.getValue();
        if (v == null) {
            return null;
        }
        return (double[]) v.clone();
    }

    public double[] getValueRef() {
        return (double[]) super.getValue();
    }

    private Object writeReplace() {
        return new DataTransferObject(getKey(), getValue());
    }

    /* loaded from: classes10.dex */
    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120513;
        private final double[] point;
        private final double[] value;

        DataTransferObject(double[] point, double[] value) {
            this.point = (double[]) point.clone();
            this.value = (double[]) value.clone();
        }

        private Object readResolve() {
            return new PointVectorValuePair(this.point, this.value, false);
        }
    }
}
