package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class CubicBSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.CubicBSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private boolean interpolateEndpoints;
    private final SharedData sharedData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[] b;
        private int numPoints;
        private double[][] pt;
        private int section;

        private SharedData() {
            this.section = 0;
            this.numPoints = 0;
            this.pt = new double[4];
            this.b = new double[4];
        }

        static /* synthetic */ int access$308(SharedData x0) {
            int i = x0.section;
            x0.section = i + 1;
            return i;
        }
    }

    public CubicBSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.interpolateEndpoints = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        int i;
        double t = p[p.length - 1];
        double t2 = t * t;
        double t3 = t2 * t;
        double u = 1.0d - t;
        double u2 = u * u;
        double u3 = u2 * u;
        if (this.sharedData.numPoints == 4) {
            this.sharedData.b[0] = u2 * u;
            this.sharedData.b[1] = u2 * 3.0d * t;
            this.sharedData.b[2] = 3.0d * u * t2;
            this.sharedData.b[3] = t3;
        } else if (this.sharedData.numPoints == 5) {
            if (this.sharedData.section == 0) {
                this.sharedData.b[0] = u3;
                this.sharedData.b[1] = (((7.0d * t3) / 4.0d) - ((9.0d * t2) / 2.0d)) + (t * 3.0d);
                this.sharedData.b[2] = (-t3) + ((3.0d * t2) / 2.0d);
                this.sharedData.b[3] = t3 / 4.0d;
            } else {
                this.sharedData.b[0] = u3 / 4.0d;
                this.sharedData.b[1] = (-u3) + ((u2 * 3.0d) / 2.0d);
                this.sharedData.b[2] = (((7.0d * u3) / 4.0d) - ((9.0d * u2) / 2.0d)) + (3.0d * u);
                this.sharedData.b[3] = t3;
            }
        } else if (this.sharedData.numPoints == 6) {
            if (this.sharedData.section == 0) {
                this.sharedData.b[0] = u3;
                this.sharedData.b[1] = (((7.0d * t3) / 4.0d) - ((9.0d * t2) / 2.0d)) + (t * 3.0d);
                this.sharedData.b[2] = (((-11.0d) * t3) / 12.0d) + ((3.0d * t2) / 2.0d);
                this.sharedData.b[3] = t3 / 6.0d;
            } else if (this.sharedData.section == 1) {
                this.sharedData.b[0] = u3 / 4.0d;
                this.sharedData.b[1] = (((7.0d * t3) / 12.0d) - ((5.0d * t2) / 4.0d)) + (t / 4.0d) + 0.5833333333333334d;
                this.sharedData.b[2] = (((-7.0d) * t3) / 12.0d) + (t2 / 2.0d) + (t / 2.0d) + 0.16666666666666666d;
                this.sharedData.b[3] = t3 / 4.0d;
            } else {
                this.sharedData.b[0] = u3 / 6.0d;
                this.sharedData.b[1] = (((-11.0d) * u3) / 12.0d) + ((u2 * 3.0d) / 2.0d);
                this.sharedData.b[2] = (((7.0d * u3) / 4.0d) - ((9.0d * u2) / 2.0d)) + (3.0d * u);
                this.sharedData.b[3] = t3;
            }
        } else if (this.sharedData.section == 0) {
            this.sharedData.b[0] = u3;
            this.sharedData.b[1] = (((7.0d * t3) / 4.0d) - ((9.0d * t2) / 2.0d)) + (t * 3.0d);
            this.sharedData.b[2] = (((-11.0d) * t3) / 12.0d) + ((3.0d * t2) / 2.0d);
            this.sharedData.b[3] = t3 / 6.0d;
        } else if (this.sharedData.section == 1) {
            this.sharedData.b[0] = u3 / 4.0d;
            this.sharedData.b[1] = (((7.0d * t3) / 12.0d) - ((5.0d * t2) / 4.0d)) + (t / 4.0d) + 0.5833333333333334d;
            this.sharedData.b[2] = ((-t3) / 2.0d) + (t2 / 2.0d) + (t / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[3] = t3 / 6.0d;
        } else if (this.sharedData.section == 2) {
            this.sharedData.b[0] = u3 / 6.0d;
            this.sharedData.b[1] = ((t3 / 2.0d) - t2) + 0.6666666666666666d;
            this.sharedData.b[2] = ((((-t3) + t2) + t) / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[3] = t3 / 6.0d;
        } else if (this.sharedData.section == 3) {
            this.sharedData.b[0] = u3 / 6.0d;
            this.sharedData.b[1] = ((-u3) / 2.0d) + (u2 / 2.0d) + (u / 2.0d) + 0.16666666666666666d;
            this.sharedData.b[2] = (((7.0d * u3) / 12.0d) - ((5.0d * u2) / 4.0d)) + (u / 4.0d) + 0.5833333333333334d;
            this.sharedData.b[3] = t3 / 4.0d;
        } else {
            this.sharedData.b[0] = u3 / 6.0d;
            this.sharedData.b[1] = (((-11.0d) * u3) / 12.0d) + ((u2 * 3.0d) / 2.0d);
            this.sharedData.b[2] = (((7.0d * u3) / 4.0d) - ((9.0d * u2) / 2.0d)) + (3.0d * u);
            this.sharedData.b[3] = t3;
        }
        int i2 = 0;
        while (i2 < 4) {
            int j = 0;
            while (true) {
                i = i2;
                int i3 = p.length;
                if (j < i3 - 1) {
                    p[j] = p[j] + (this.sharedData.pt[i][j] * this.sharedData.b[i]);
                    j++;
                    i2 = i;
                }
            }
            i2 = i + 1;
        }
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public int getSampleLimit() {
        return 1;
    }

    public void setInterpolateEndpoints(boolean b) {
        this.interpolateEndpoints = b;
    }

    public boolean getInterpolateEndpoints() {
        return this.interpolateEndpoints;
    }

    @Override // com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int n = this.gi.getGroupSize();
        if (n < 4) {
            throw new IllegalArgumentException("Group iterator size < 4");
        }
        if (this.interpolateEndpoints) {
            this.sharedData.numPoints = n;
            this.sharedData.section = 0;
        } else {
            this.sharedData.numPoints = -1;
            this.sharedData.section = 2;
        }
        this.gi.set(0, 0);
        int index_i = 0;
        int count_j = 0;
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
        int j = 3;
        while (true) {
            MultiPath mp2 = mp;
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, mp2);
            j++;
            if (j != n) {
                this.gi.set(index_i, count_j);
                this.gi.next();
                index_i = this.gi.index_i();
                count_j = this.gi.count_j();
                for (int i3 = 0; i3 < 4; i3++) {
                    this.sharedData.pt[i3] = this.cp.getPoint(this.gi.next()).getLocation();
                }
                if (this.interpolateEndpoints) {
                    if (n >= 7) {
                        if (this.sharedData.section != 2) {
                            SharedData.access$308(this.sharedData);
                        }
                        if (this.sharedData.section == 2 && j == n - 2) {
                            SharedData.access$308(this.sharedData);
                        }
                    } else {
                        SharedData.access$308(this.sharedData);
                    }
                }
                mp = mp2;
            } else {
                return;
            }
        }
    }
}
