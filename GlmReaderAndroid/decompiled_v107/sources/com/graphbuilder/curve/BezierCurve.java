package com.graphbuilder.curve;

import com.graphbuilder.math.PascalsTriangle;

/* loaded from: classes.dex */
public class BezierCurve extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.BezierCurve.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final PascalsTriangle pascalsTriangle;
    private int sampleLimit;
    private final SharedData sharedData;
    private double t_max;
    private double t_min;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[] a;

        private SharedData() {
            this.a = new double[0];
        }
    }

    public BezierCurve(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.pascalsTriangle = new PascalsTriangle();
        this.t_min = 0.0d;
        this.t_max = 1.0d;
        this.sampleLimit = 1;
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        BezierCurve bezierCurve = this;
        double t = p[p.length - 1];
        int numPts = bezierCurve.gi.getGroupSize();
        if (numPts > bezierCurve.sharedData.a.length) {
            bezierCurve.sharedData.a = new double[numPts * 2];
        }
        bezierCurve.sharedData.a[numPts - 1] = 1.0d;
        double b = 1.0d;
        double one_minus_t = 1.0d - t;
        for (int i = numPts - 2; i >= 0; i--) {
            bezierCurve.sharedData.a[i] = bezierCurve.sharedData.a[i + 1] * one_minus_t;
        }
        bezierCurve.gi.set(0, 0);
        int i2 = 0;
        while (i2 < numPts) {
            double pt = bezierCurve.pascalsTriangle.nCr(numPts - 1, i2);
            if (!Double.isInfinite(pt) && !Double.isNaN(pt)) {
                double gravity = bezierCurve.sharedData.a[i2] * b * pt;
                double[] d = bezierCurve.cp.getPoint(bezierCurve.gi.next()).getLocation();
                for (int j = 0; j < p.length - 1; j++) {
                    p[j] = p[j] + (d[j] * gravity);
                }
            }
            b *= t;
            i2++;
            bezierCurve = this;
        }
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public int getSampleLimit() {
        return this.sampleLimit;
    }

    public void setSampleLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Sample-limit >= 0 required.");
        }
        this.sampleLimit = limit;
    }

    public void setInterval(double t_min, double t_max) {
        if (t_min > t_max) {
            throw new IllegalArgumentException("t_min <= t_max required.");
        }
        this.t_min = t_min;
        this.t_max = t_max;
    }

    public double t_min() {
        return this.t_min;
    }

    public double t_max() {
        return this.t_max;
    }

    @Override // com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("group iterator not in range");
        }
        int n = mp.getDimension();
        double[] d = new double[n + 1];
        d[n] = this.t_min;
        eval(d);
        if (this.connect) {
            mp.lineTo(d);
        } else {
            mp.moveTo(d);
        }
        BinaryCurveApproximationAlgorithm.genPts(this, this.t_min, this.t_max, mp);
    }

    @Override // com.graphbuilder.curve.Curve
    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            this.sharedData.a = new double[0];
        }
    }
}
