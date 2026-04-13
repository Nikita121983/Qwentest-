package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class NURBSpline extends BSpline {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.NURBSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData;
    private boolean useWeightVector;
    private ValueVector weightVector;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[] nw;
        private double[] weight;

        private SharedData() {
            this.nw = new double[0];
            this.weight = new double[0];
        }
    }

    public NURBSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.weightVector = new ValueVector(new double[]{1.0d, 1.0d, 1.0d, 1.0d}, 4);
        this.useWeightVector = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.BSpline, com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        int dim = p.length - 1;
        double t = p[dim];
        double sum2 = 0.0d;
        int numPts = this.gi.getGroupSize();
        for (int i = 0; i < numPts; i++) {
            this.sharedData.nw[i] = N(t, i) * this.sharedData.weight[i];
            sum2 += this.sharedData.nw[i];
        }
        if (sum2 == 0.0d) {
            sum2 = 1.0d;
        }
        for (int i2 = 0; i2 < dim; i2++) {
            double sum1 = 0.0d;
            this.gi.set(0, 0);
            for (int j = 0; j < numPts; j++) {
                sum1 += this.sharedData.nw[j] * this.cp.getPoint(this.gi.next()).getLocation()[i2];
            }
            p[i2] = sum1 / sum2;
        }
    }

    public ValueVector getWeightVector() {
        return this.weightVector;
    }

    public void setWeightVector(ValueVector v) {
        if (v == null) {
            throw new IllegalArgumentException("Weight-vector cannot be null.");
        }
        this.weightVector = v;
    }

    public boolean getUseWeightVector() {
        return this.useWeightVector;
    }

    public void setUseWeightVector(boolean b) {
        this.useWeightVector = b;
    }

    @Override // com.graphbuilder.curve.BSpline, com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int numPts = this.gi.getGroupSize();
        if (this.sharedData.nw.length < numPts) {
            this.sharedData.nw = new double[numPts * 2];
            this.sharedData.weight = new double[numPts * 2];
        }
        if (this.useWeightVector) {
            if (this.weightVector.size() != numPts) {
                throw new IllegalArgumentException("weightVector.size(" + this.weightVector.size() + ") != group iterator size(" + numPts + ")");
            }
            for (int i = 0; i < numPts; i++) {
                this.sharedData.weight[i] = this.weightVector.get(i);
                if (this.sharedData.weight[i] < 0.0d) {
                    throw new IllegalArgumentException("Negative weight not allowed");
                }
            }
        } else {
            for (int i2 = 0; i2 < numPts; i2++) {
                this.sharedData.weight[i2] = 1.0d;
            }
        }
        super.appendTo(mp);
    }

    @Override // com.graphbuilder.curve.BSpline, com.graphbuilder.curve.Curve
    public void resetMemory() {
        super.resetMemory();
        if (this.sharedData.nw.length > 0) {
            this.sharedData.nw = new double[0];
            this.sharedData.weight = new double[0];
        }
    }
}
