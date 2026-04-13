package com.graphbuilder.curve;

/* loaded from: classes.dex */
public class NaturalCubicSpline extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.curve.NaturalCubicSpline.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private boolean closed;
    private final SharedData sharedData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private int ci;
        private double[][] data;
        private double[][] pt;

        private SharedData() {
            this.pt = new double[0];
            this.data = new double[0];
            this.ci = 0;
        }
    }

    public NaturalCubicSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
        this.sharedData = SHARED_DATA.get();
        this.closed = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.graphbuilder.curve.ParametricCurve
    public void eval(double[] p) {
        int n = p.length - 1;
        double t = p[n];
        double t2 = t * t;
        double t3 = t2 * t;
        int j = 0;
        int i = 0;
        while (i < n) {
            int j2 = j + 1;
            int j3 = j2 + 1;
            double d = this.sharedData.data[j][this.sharedData.ci] + (this.sharedData.data[j2][this.sharedData.ci] * t);
            int j4 = j3 + 1;
            p[i] = d + (this.sharedData.data[j3][this.sharedData.ci] * t2) + (this.sharedData.data[j4][this.sharedData.ci] * t3);
            i++;
            j = j4 + 1;
        }
    }

    private void precalc(int n, int dim, boolean closed) {
        int n2 = n - 1;
        double[] a = this.sharedData.data[dim * 4];
        double[] b = this.sharedData.data[(dim * 4) + 1];
        double[] c = this.sharedData.data[(dim * 4) + 2];
        int k = 0;
        double e = 1.0d;
        double d = 3.0d;
        if (closed) {
            double[] d2 = this.sharedData.data[(dim * 4) + 3];
            int j = 0;
            while (j < dim) {
                a[1] = 0.25d;
                d2[1] = 0.25d;
                b[0] = 0.25d * d * (this.sharedData.pt[1][j] - this.sharedData.pt[n2][j]);
                double h = 4.0d;
                double f = (this.sharedData.pt[0][j] - this.sharedData.pt[n2 - 1][j]) * d;
                double g = 1.0d;
                int i = 1;
                while (i < n2) {
                    double e2 = e / (4.0d - a[i]);
                    a[i + 1] = e2;
                    d2[i + 1] = (-e2) * d2[i];
                    b[i] = (((this.sharedData.pt[i + 1][j] - this.sharedData.pt[i - 1][j]) * d) - b[i - 1]) * e2;
                    h -= d2[i] * g;
                    f -= b[i - 1] * g;
                    g *= -a[i];
                    i++;
                    e = e;
                }
                double d3 = e;
                double h2 = h - ((g + d3) * (a[n2] + d2[n2]));
                b[n2] = f - ((g + d3) * b[n2 - 1]);
                c[n2] = b[n2] / h2;
                c[n2 - 1] = b[n2 - 1] - ((a[n2] + d2[n2]) * c[n2]);
                for (int i2 = n2 - 2; i2 >= 0; i2--) {
                    c[i2] = (b[i2] - (a[i2 + 1] * c[i2 + 1])) - (d2[i2 + 1] * c[n2]);
                }
                int k2 = k + 1;
                double[] w = this.sharedData.data[k];
                int k3 = k2 + 1;
                double[] x = this.sharedData.data[k2];
                int k4 = k3 + 1;
                double[] y = this.sharedData.data[k3];
                int k5 = k4 + 1;
                double[] z = this.sharedData.data[k4];
                int i3 = 0;
                while (i3 < n2) {
                    double d4 = d;
                    w[i3] = this.sharedData.pt[i3][j];
                    x[i3] = c[i3];
                    y[i3] = (((this.sharedData.pt[i3 + 1][j] - this.sharedData.pt[i3][j]) * d4) - (c[i3] * 2.0d)) - c[i3 + 1];
                    z[i3] = ((this.sharedData.pt[i3][j] - this.sharedData.pt[i3 + 1][j]) * 2.0d) + c[i3] + c[i3 + 1];
                    i3++;
                    d = d4;
                }
                double d5 = d;
                w[n2] = this.sharedData.pt[n2][j];
                x[n2] = c[n2];
                y[n2] = (((this.sharedData.pt[0][j] - this.sharedData.pt[n2][j]) * d5) - (c[n2] * 2.0d)) - c[0];
                z[n2] = ((this.sharedData.pt[n2][j] - this.sharedData.pt[0][j]) * 2.0d) + c[n2] + c[0];
                j++;
                k = k5;
                e = d3;
                d = d5;
            }
            return;
        }
        int j2 = 0;
        while (j2 < dim) {
            a[0] = 0.5d;
            for (int i4 = 1; i4 < n2; i4++) {
                a[i4] = 1.0d / (4.0d - a[i4 - 1]);
            }
            int i5 = n2 - 1;
            a[n2] = 1.0d / (2.0d - a[i5]);
            b[0] = a[0] * (this.sharedData.pt[1][j2] - this.sharedData.pt[0][j2]) * 3.0d;
            for (int i6 = 1; i6 < n2; i6++) {
                b[i6] = a[i6] * (((this.sharedData.pt[i6 + 1][j2] - this.sharedData.pt[i6 - 1][j2]) * 3.0d) - b[i6 - 1]);
            }
            b[n2] = a[n2] * (((this.sharedData.pt[n2][j2] - this.sharedData.pt[n2 - 1][j2]) * 3.0d) - b[n2 - 1]);
            c[n2] = b[n2];
            for (int i7 = n2 - 1; i7 >= 0; i7--) {
                c[i7] = b[i7] - (a[i7] * c[i7 + 1]);
            }
            int k6 = k + 1;
            double[] w2 = this.sharedData.data[k];
            int k7 = k6 + 1;
            double[] x2 = this.sharedData.data[k6];
            int k8 = k7 + 1;
            double[] y2 = this.sharedData.data[k7];
            int k9 = k8 + 1;
            double[] z2 = this.sharedData.data[k8];
            for (int i8 = 0; i8 < n2; i8++) {
                w2[i8] = this.sharedData.pt[i8][j2];
                x2[i8] = c[i8];
                y2[i8] = (((this.sharedData.pt[i8 + 1][j2] - this.sharedData.pt[i8][j2]) * 3.0d) - (c[i8] * 2.0d)) - c[i8 + 1];
                z2[i8] = ((this.sharedData.pt[i8][j2] - this.sharedData.pt[i8 + 1][j2]) * 2.0d) + c[i8] + c[i8 + 1];
            }
            w2[n2] = this.sharedData.pt[n2][j2];
            x2[n2] = 0.0d;
            y2[n2] = 0.0d;
            z2[n2] = 0.0d;
            j2++;
            k = k9;
        }
    }

    public void setClosed(boolean b) {
        this.closed = b;
    }

    public boolean getClosed() {
        return this.closed;
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
        int n = this.gi.getGroupSize();
        if (n < 2) {
            throw new IllegalArgumentException("Group iterator size < 2");
        }
        int dim = mp.getDimension();
        int x = (dim * 4) + 3 + 1;
        if (this.sharedData.data.length < x) {
            double[][] temp = new double[x];
            for (int i = 0; i < this.sharedData.data.length; i++) {
                temp[i] = this.sharedData.data[i];
            }
            this.sharedData.data = temp;
        }
        if (this.sharedData.pt.length < n) {
            int m = n * 2;
            this.sharedData.pt = new double[m];
            for (int i2 = 0; i2 < this.sharedData.data.length; i2++) {
                this.sharedData.data[i2] = new double[m];
            }
        }
        this.gi.set(0, 0);
        for (int i3 = 0; i3 < n; i3++) {
            this.sharedData.pt[i3] = this.cp.getPoint(this.gi.next()).getLocation();
        }
        precalc(n, dim, this.closed);
        this.sharedData.ci = 0;
        double[] p = new double[dim + 1];
        eval(p);
        if (this.connect) {
            mp.lineTo(p);
        } else {
            mp.moveTo(p);
        }
        for (int i4 = 0; i4 < n; i4++) {
            this.sharedData.ci = i4;
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0d, 1.0d, mp);
        }
    }

    @Override // com.graphbuilder.curve.Curve
    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            this.sharedData.pt = new double[0];
        }
        if (this.sharedData.data.length > 0) {
            this.sharedData.data = new double[0];
        }
    }
}
