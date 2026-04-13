package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class CatmullRomSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.CatmullRomSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[][] pt;

        private SharedData() {
            this.pt = new double[4];
        }
    }

    public CatmullRomSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        double t = p[p.length - 1];
        double t2 = t * t;
        double t3 = t2 * t;
        for (int i = 0; i < p.length - 1; i++) {
            p[i] = (((((this.sharedData.pt[3][i] - this.sharedData.pt[0][i]) + ((this.sharedData.pt[1][i] - this.sharedData.pt[2][i]) * 3.0d)) * t3) + (((((this.sharedData.pt[0][i] + (this.sharedData.pt[2][i] * 2.0d)) * 2.0d) - (this.sharedData.pt[1][i] * 5.0d)) - this.sharedData.pt[3][i]) * t2) + ((this.sharedData.pt[2][i] - this.sharedData.pt[0][i]) * t)) * 0.5d) + this.sharedData.pt[1][i];
        }
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public int getSampleLimit() {
        return 1;
    }

    @Override // com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        if (this.gi.getGroupSize() < 4) {
            throw new IllegalArgumentException("Group iterator size < 4");
        }
        this.gi.set(0, 0);
        for (int i = 0; i < 4; i++) {
            this.sharedData.pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
        }
        int i2 = mp.getDimension();
        double[] d = new double[i2 + 1];
        eval(d);
        if (this.connect) {
            mp.lineTo(d);
        } else {
            mp.moveTo(d);
        }
        this.gi.set(0, 0);
        while (true) {
            int index_i = this.gi.index_i();
            int count_j = this.gi.count_j();
            for (int i3 = 0; i3 < 4; i3++) {
                if (this.gi.hasNext()) {
                    this.sharedData.pt[i3] = this.cp.getPoint(this.gi.next()).getLocation();
                } else {
                    return;
                }
            }
            this.gi.set(index_i, count_j);
            this.gi.next();
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, mp);
        }
    }
}
