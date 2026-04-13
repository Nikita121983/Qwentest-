package com.graphbuilder.math;

/* loaded from: classes.dex */
public final class PascalsTriangle {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>() { // from class: com.graphbuilder.math.PascalsTriangle.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SharedData {
        private double[][] pt;

        private SharedData() {
            this.pt = new double[][]{new double[]{1.0d}};
        }
    }

    public double nCr(int n, int r) {
        double[][] pt2;
        double x;
        if (n >= 0 && r >= 0 && r <= n) {
            if (n >= this.sharedData.pt.length) {
                int d = this.sharedData.pt.length * 2;
                if (n > d) {
                    pt2 = new double[n + 1];
                } else {
                    pt2 = new double[d + 1];
                }
                for (int i = 0; i < this.sharedData.pt.length; i++) {
                    pt2[i] = this.sharedData.pt[i];
                }
                for (int i2 = this.sharedData.pt.length; i2 < pt2.length; i2++) {
                    pt2[i2] = new double[(i2 / 2) + 1];
                    pt2[i2][0] = 1.0d;
                    for (int j = 1; j < pt2[i2].length; j++) {
                        double x2 = pt2[i2 - 1][j - 1];
                        if (j < pt2[i2 - 1].length) {
                            x = x2 + pt2[i2 - 1][j];
                        } else {
                            x = x2 * 2.0d;
                        }
                        pt2[i2][j] = x;
                    }
                }
                this.sharedData.pt = pt2;
            }
            if (r * 2 > n) {
                r = n - r;
            }
            return this.sharedData.pt[n][r];
        }
        return 0.0d;
    }

    public void reset() {
        this.sharedData.pt = new double[][]{new double[]{1.0d}};
    }
}
