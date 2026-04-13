package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class LagrangeCurve extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.LagrangeCurve.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private int baseIndex;
    private int baseLength;
    private boolean interpolateFirst;
    private boolean interpolateLast;
    private ValueVector knotVector;
    private final SharedData sharedData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[][] pt;

        private SharedData() {
            this.pt = new double[0];
        }
    }

    public LagrangeCurve(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.knotVector = new ValueVector(new double[]{0.0d, 0.3333333333333333d, 0.6666666666666666d, 1.0d}, 4);
        this.baseIndex = 1;
        this.baseLength = 1;
        this.interpolateFirst = false;
        this.interpolateLast = false;
        this.sharedData = SHARED_DATA.get();
    }

    public int getBaseIndex() {
        return this.baseIndex;
    }

    public void setBaseIndex(int b) {
        if (b < 0) {
            throw new IllegalArgumentException("base index >= 0 required.");
        }
        this.baseIndex = b;
    }

    public int getBaseLength() {
        return this.baseLength;
    }

    public void setBaseLength(int b) {
        if (b <= 0) {
            throw new IllegalArgumentException("base length > 0 required.");
        }
        this.baseLength = b;
    }

    public boolean getInterpolateFirst() {
        return this.interpolateFirst;
    }

    public boolean getInterpolateLast() {
        return this.interpolateLast;
    }

    public void setInterpolateFirst(boolean b) {
        this.interpolateFirst = b;
    }

    public void setInterpolateLast(boolean b) {
        this.interpolateLast = b;
    }

    public ValueVector getKnotVector() {
        return this.knotVector;
    }

    public void setKnotVector(ValueVector v) {
        if (v == null) {
            throw new IllegalArgumentException("Knot-vector cannot be null.");
        }
        this.knotVector = v;
    }

    @Override // com.graphbuilder.curve.ParametricCurve
    public int getSampleLimit() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        double t = p[p.length - 1];
        int n = this.knotVector.size();
        for (int i = 0; i < n; i++) {
            double[] q = this.sharedData.pt[i];
            double L = L(t, i);
            for (int j = 0; j < p.length - 1; j++) {
                p[j] = p[j] + (q[j] * L);
            }
        }
    }

    private double L(double t, int i) {
        double d = 1.0d;
        int n = this.knotVector.size();
        for (int j = 0; j < n; j++) {
            double e = this.knotVector.get(i) - this.knotVector.get(j);
            if (e != 0.0d) {
                d *= (t - this.knotVector.get(j)) / e;
            }
        }
        return d;
    }

    @Override // com.graphbuilder.curve.Curve
    public void appendTo(MultiPath mp) {
        int index_i;
        int count_j;
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        if (this.baseIndex + this.baseLength < this.knotVector.size()) {
            if (this.sharedData.pt.length < this.knotVector.size()) {
                this.sharedData.pt = new double[this.knotVector.size() * 2];
            }
            this.gi.set(0, 0);
            boolean b = false;
            if (this.baseIndex != 0 && this.interpolateFirst) {
                for (int i = 0; i < this.knotVector.size(); i++) {
                    if (this.gi.hasNext()) {
                        this.sharedData.pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
                    } else {
                        throw new IllegalArgumentException("Group iterator ended early");
                    }
                }
                b = doBCAA(mp, this.knotVector.get(0), this.knotVector.get(this.baseIndex), false);
            }
            this.gi.set(0, 0);
            int last_i = 0;
            int last_j = 0;
            while (true) {
                int temp_i = this.gi.index_i();
                int temp_j = this.gi.count_j();
                int index_i2 = 0;
                int count_j2 = 0;
                int i2 = 0;
                int j = 0;
                while (true) {
                    if (j >= this.knotVector.size()) {
                        index_i = index_i2;
                        count_j = count_j2;
                        break;
                    }
                    if (i2 == this.baseLength) {
                        index_i2 = this.gi.index_i();
                        count_j2 = this.gi.count_j();
                    }
                    if (!this.gi.hasNext()) {
                        index_i = index_i2;
                        count_j = count_j2;
                        break;
                    } else {
                        this.sharedData.pt[j] = this.cp.getPoint(this.gi.next()).getLocation();
                        i2++;
                        j++;
                    }
                }
                if (j < this.knotVector.size()) {
                    break;
                }
                this.gi.set(index_i, count_j);
                last_i = temp_i;
                last_j = temp_j;
                b = doBCAA(mp, this.knotVector.get(this.baseIndex), this.knotVector.get(this.baseIndex + this.baseLength), b);
            }
            if (this.baseIndex + this.baseLength < this.knotVector.size() - 1 && this.interpolateLast) {
                this.gi.set(last_i, last_j);
                for (int i3 = 0; i3 < this.knotVector.size(); i3++) {
                    if (this.gi.hasNext()) {
                        this.sharedData.pt[i3] = this.cp.getPoint(this.gi.next()).getLocation();
                    } else {
                        System.out.println("not enough points to interpolate last");
                        return;
                    }
                }
                doBCAA(mp, this.knotVector.get(this.baseIndex + this.baseLength), this.knotVector.get(this.knotVector.size() - 1), b);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("baseIndex + baseLength >= knotVector.size");
    }

    private boolean doBCAA(MultiPath mp, double t1, double t2, boolean b) {
        double t12;
        double t22;
        if (t2 >= t1) {
            t12 = t1;
            t22 = t2;
        } else {
            t12 = t2;
            t22 = t1;
        }
        if (!b) {
            b = true;
            double[] d = new double[mp.getDimension() + 1];
            d[mp.getDimension()] = t12;
            eval(d);
            if (this.connect) {
                mp.lineTo(d);
            } else {
                mp.moveTo(d);
            }
        }
        BinaryCurveApproximationAlgorithm.genPts(this, t12, t22, mp);
        return b;
    }

    @Override // com.graphbuilder.curve.Curve
    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            this.sharedData.pt = new double[0];
        }
    }
}
