package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class BSpline extends ParametricCurve {
    public static final int NON_UNIFORM = 2;
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.BSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    public static final int UNIFORM_CLAMPED = 0;
    public static final int UNIFORM_UNCLAMPED = 1;
    private int degree;
    private ValueVector knotVector;
    private int knotVectorType;
    private int sampleLimit;
    private final SharedData sharedData;
    private double t_max;
    private double t_min;
    private boolean useDefaultInterval;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private int[] a;
        private int[] c;
        private double[] knot;

        private SharedData() {
            this.a = new int[0];
            this.c = new int[0];
            this.knot = new double[0];
        }
    }

    public BSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.knotVector = new ValueVector(new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 1.0d}, 8);
        this.t_min = 0.0d;
        this.t_max = 1.0d;
        this.sampleLimit = 1;
        this.degree = 4;
        this.knotVectorType = 0;
        this.useDefaultInterval = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        int dim = p.length - 1;
        double t = p[dim];
        int numPts = this.gi.getGroupSize();
        this.gi.set(0, 0);
        for (int i = 0; i < numPts; i++) {
            double w = N(t, i);
            double[] loc = this.cp.getPoint(this.gi.next()).getLocation();
            for (int j = 0; j < dim; j++) {
                p[j] = p[j] + (loc[j] * w);
            }
        }
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

    public int getDegree() {
        return this.degree - 1;
    }

    public void setDegree(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("Degree > 0 required.");
        }
        this.degree = d + 1;
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

    public boolean getUseDefaultInterval() {
        return this.useDefaultInterval;
    }

    public void setUseDefaultInterval(boolean b) {
        this.useDefaultInterval = b;
    }

    public int getKnotVectorType() {
        return this.knotVectorType;
    }

    public void setKnotVectorType(int type) {
        if (type < 0 || type > 2) {
            throw new IllegalArgumentException("Unknown knot-vector type.");
        }
        this.knotVectorType = type;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x01ae  */
    @Override // com.graphbuilder.curve.Curve
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void appendTo(com.graphbuilder.curve.MultiPath r23) {
        /*
            Method dump skipped, instructions count: 453
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.curve.BSpline.appendTo(com.graphbuilder.curve.MultiPath):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double N(double t, int i) {
        int m;
        double d = 0.0d;
        int j = 0;
        while (true) {
            if (j >= this.degree) {
                break;
            }
            double t1 = this.sharedData.knot[i + j];
            int i2 = 1;
            double t2 = this.sharedData.knot[i + j + 1];
            if (t < t1 || t > t2 || t1 == t2) {
                j++;
            } else {
                int dm2 = this.degree - 2;
                for (int k = (this.degree - j) - 1; k >= 0; k--) {
                    this.sharedData.a[k] = 0;
                }
                if (j <= 0) {
                    this.sharedData.c[0] = dm2;
                    this.sharedData.c[1] = this.degree;
                } else {
                    for (int k2 = 0; k2 < j; k2++) {
                        this.sharedData.c[k2] = k2;
                    }
                    this.sharedData.c[j] = Integer.MAX_VALUE;
                }
                int z = 0;
                while (true) {
                    if (this.sharedData.c[z] < this.sharedData.c[z + 1] - i2) {
                        double e = 1.0d;
                        int bc = 0;
                        int y = dm2 - j;
                        int p = j - 1;
                        int i3 = i2;
                        int n = this.degree;
                        double d2 = d;
                        int w = dm2;
                        while (w >= 0) {
                            if (p < 0 || this.sharedData.c[p] != w) {
                                m = w;
                                int w2 = i + this.sharedData.a[y];
                                double kw = this.sharedData.knot[w2];
                                e *= (t - kw) / (this.sharedData.knot[(w2 + n) - 1] - kw);
                                y--;
                            } else {
                                int w3 = i + bc;
                                m = w;
                                double kd = this.sharedData.knot[w3 + n];
                                e *= (kd - t) / (kd - this.sharedData.knot[w3 + 1]);
                                bc++;
                                p--;
                            }
                            w = m - 1;
                            n--;
                        }
                        if (j > 0) {
                            int g = 0;
                            boolean reset = false;
                            while (true) {
                                int[] iArr = this.sharedData.a;
                                iArr[g] = iArr[g] + 1;
                                if (this.sharedData.a[g] <= j) {
                                    break;
                                }
                                g++;
                                reset = true;
                            }
                            if (reset) {
                                int h = g - 1;
                                while (h >= 0) {
                                    int g2 = g;
                                    this.sharedData.a[h] = this.sharedData.a[g2];
                                    h--;
                                    g = g2;
                                }
                            }
                        }
                        d = d2 + e;
                        int[] iArr2 = this.sharedData.c;
                        iArr2[z] = iArr2[z] + 1;
                        if (this.sharedData.c[z] > dm2) {
                            break;
                        }
                        int k3 = 0;
                        while (k3 < z) {
                            this.sharedData.c[k3] = k3;
                            k3++;
                            d = d;
                        }
                        z = 0;
                        i2 = i3;
                    } else {
                        z++;
                    }
                }
            }
        }
        return d;
    }

    @Override // com.graphbuilder.curve.Curve
    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            this.sharedData.a = new int[0];
            this.sharedData.c = new int[0];
        }
        if (this.sharedData.knot.length > 0) {
            this.sharedData.knot = new double[0];
        }
    }
}
