package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class CardinalSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.CardinalSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private double alpha;
    private final SharedData sharedData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[][] pt;

        private SharedData() {
            this.pt = new double[4];
        }
    }

    public CardinalSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.alpha = 0.5d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] dArr) {
        boolean z = true;
        double d = dArr[dArr.length - 1];
        double d2 = d * d;
        double d3 = d2 * d;
        double d4 = ((d3 * 2.0d) - (d2 * 3.0d)) + 1.0d;
        double d5 = ((-2.0d) * d3) + (3.0d * d2);
        double d6 = this.alpha * ((d3 - (2.0d * d2)) + d);
        double d7 = this.alpha * (d3 - d2);
        int i = 0;
        while (true) {
            boolean z2 = z;
            if (i < dArr.length - 1) {
                dArr[i] = (this.sharedData.pt[z2 ? 1 : 0][i] * d4) + (this.sharedData.pt[2][i] * d5) + ((this.sharedData.pt[2][i] - this.sharedData.pt[0][i]) * d6) + ((this.sharedData.pt[3][i] - this.sharedData.pt[z2 ? 1 : 0][i]) * d7);
                i++;
                z = z2 ? 1 : 0;
            } else {
                return;
            }
        }
    }

    public double getAlpha() {
        return this.alpha;
    }

    public void setAlpha(double a) {
        this.alpha = a;
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public int getSampleLimit() {
        return 1;
    }

    @Override // com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("group iterator not in range");
        }
        if (this.gi.getGroupSize() < 4) {
            throw new IllegalArgumentException("more than 4 groups required");
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
