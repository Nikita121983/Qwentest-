package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
class BiDiagonalTransformer {
    private RealMatrix cachedB;
    private RealMatrix cachedU;
    private RealMatrix cachedV;
    private final double[][] householderVectors;
    private final double[] main;
    private final double[] secondary;

    BiDiagonalTransformer(RealMatrix matrix) {
        int m = matrix.getRowDimension();
        int n = matrix.getColumnDimension();
        int p = FastMath.min(m, n);
        this.householderVectors = matrix.getData();
        this.main = new double[p];
        this.secondary = new double[p - 1];
        this.cachedU = null;
        this.cachedB = null;
        this.cachedV = null;
        if (m >= n) {
            transformToUpperBiDiagonal();
        } else {
            transformToLowerBiDiagonal();
        }
    }

    public RealMatrix getU() {
        double d;
        if (this.cachedU == null) {
            int length = this.householderVectors.length;
            boolean z = false;
            int length2 = this.householderVectors[0].length;
            int length3 = this.main.length;
            int i = length >= length2 ? 0 : 1;
            double[] dArr = length >= length2 ? this.main : this.secondary;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
            int i2 = length - 1;
            while (true) {
                d = 1.0d;
                if (i2 < length3) {
                    break;
                }
                dArr2[i2][i2] = 1.0d;
                i2--;
            }
            int i3 = length3 - 1;
            while (i3 >= i) {
                double[] dArr3 = this.householderVectors[i3];
                dArr2[i3][i3] = d;
                if (dArr3[i3 - i] != 0.0d) {
                    int i4 = i3;
                    while (i4 < length) {
                        double d2 = 0.0d;
                        int i5 = i3;
                        while (i5 < length) {
                            d2 -= dArr2[i5][i4] * this.householderVectors[i5][i3 - i];
                            i5++;
                            z = z;
                        }
                        boolean z2 = z;
                        double d3 = d2 / (dArr[i3 - i] * dArr3[i3 - i]);
                        int i6 = i3;
                        while (i6 < length) {
                            double[] dArr4 = dArr2[i6];
                            dArr4[i4] = dArr4[i4] + ((-d3) * this.householderVectors[i6][i3 - i]);
                            i6++;
                            d = d;
                            length = length;
                        }
                        i4++;
                        z = z2;
                    }
                }
                i3--;
                z = z;
                d = d;
                length = length;
            }
            boolean z3 = z;
            double d4 = d;
            if (i > 0) {
                dArr2[z3 ? 1 : 0][z3 ? 1 : 0] = d4;
            }
            this.cachedU = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedU;
    }

    public RealMatrix getB() {
        if (this.cachedB == null) {
            int m = this.householderVectors.length;
            int n = this.householderVectors[0].length;
            double[][] ba = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, n);
            for (int i = 0; i < this.main.length; i++) {
                ba[i][i] = this.main[i];
                if (m < n) {
                    if (i > 0) {
                        ba[i][i - 1] = this.secondary[i - 1];
                    }
                } else if (i < this.main.length - 1) {
                    ba[i][i + 1] = this.secondary[i];
                }
            }
            this.cachedB = MatrixUtils.createRealMatrix(ba);
        }
        return this.cachedB;
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int length = this.householderVectors.length;
            boolean z = false;
            int length2 = this.householderVectors[0].length;
            int length3 = this.main.length;
            int i = length >= length2 ? 1 : 0;
            double[] dArr = length >= length2 ? this.secondary : this.main;
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length2, length2);
            for (int i2 = length2 - 1; i2 >= length3; i2--) {
                dArr2[i2][i2] = 1.0d;
            }
            int i3 = length3 - 1;
            while (i3 >= i) {
                double[] dArr3 = this.householderVectors[i3 - i];
                dArr2[i3][i3] = 1.0d;
                if (dArr3[i3] != 0.0d) {
                    for (int i4 = i3; i4 < length2; i4++) {
                        double d = 0.0d;
                        for (int i5 = i3; i5 < length2; i5++) {
                            d -= dArr2[i5][i4] * dArr3[i5];
                        }
                        double d2 = d / (dArr[i3 - i] * dArr3[i3]);
                        int i6 = i3;
                        while (i6 < length2) {
                            double[] dArr4 = dArr2[i6];
                            dArr4[i4] = dArr4[i4] + ((-d2) * dArr3[i6]);
                            i6++;
                            z = z;
                            length3 = length3;
                        }
                    }
                }
                i3--;
                z = z;
                length3 = length3;
            }
            boolean z2 = z;
            if (i > 0) {
                dArr2[z2 ? 1 : 0][z2 ? 1 : 0] = 1.0d;
            }
            this.cachedV = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedV;
    }

    double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    double[] getMainDiagonalRef() {
        return this.main;
    }

    double[] getSecondaryDiagonalRef() {
        return this.secondary;
    }

    boolean isUpperBiDiagonal() {
        return this.householderVectors.length >= this.householderVectors[0].length;
    }

    private void transformToUpperBiDiagonal() {
        int m = this.householderVectors.length;
        int n = this.householderVectors[0].length;
        for (int k = 0; k < n; k++) {
            double xNormSqr = 0.0d;
            for (int i = k; i < m; i++) {
                double c = this.householderVectors[i][k];
                xNormSqr += c * c;
            }
            double[] hK = this.householderVectors[k];
            double a = hK[k] > 0.0d ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.main[k] = a;
            if (a != 0.0d) {
                hK[k] = hK[k] - a;
                for (int j = k + 1; j < n; j++) {
                    double alpha = 0.0d;
                    for (int i2 = k; i2 < m; i2++) {
                        double[] hI = this.householderVectors[i2];
                        alpha -= hI[j] * hI[k];
                    }
                    double alpha2 = alpha / (this.householderVectors[k][k] * a);
                    for (int i3 = k; i3 < m; i3++) {
                        double[] hI2 = this.householderVectors[i3];
                        hI2[j] = hI2[j] - (hI2[k] * alpha2);
                    }
                }
            }
            int j2 = n - 1;
            if (k < j2) {
                double xNormSqr2 = 0.0d;
                for (int j3 = k + 1; j3 < n; j3++) {
                    double c2 = hK[j3];
                    xNormSqr2 += c2 * c2;
                }
                int j4 = k + 1;
                double b = hK[j4] > 0.0d ? -FastMath.sqrt(xNormSqr2) : FastMath.sqrt(xNormSqr2);
                this.secondary[k] = b;
                if (b != 0.0d) {
                    int i4 = k + 1;
                    hK[i4] = hK[i4] - b;
                    for (int i5 = k + 1; i5 < m; i5++) {
                        double[] hI3 = this.householderVectors[i5];
                        double beta = 0.0d;
                        for (int j5 = k + 1; j5 < n; j5++) {
                            beta -= hI3[j5] * hK[j5];
                        }
                        int j6 = k + 1;
                        double beta2 = beta / (hK[j6] * b);
                        for (int j7 = k + 1; j7 < n; j7++) {
                            hI3[j7] = hI3[j7] - (hK[j7] * beta2);
                        }
                    }
                }
            }
        }
    }

    private void transformToLowerBiDiagonal() {
        int m = this.householderVectors.length;
        int n = this.householderVectors[0].length;
        int k = 0;
        while (k < m) {
            double[] hK = this.householderVectors[k];
            double xNormSqr = 0.0d;
            for (int j = k; j < n; j++) {
                double c = hK[j];
                xNormSqr += c * c;
            }
            double a = hK[k] > 0.0d ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            this.main[k] = a;
            if (a != 0.0d) {
                hK[k] = hK[k] - a;
                for (int i = k + 1; i < m; i++) {
                    double[] hI = this.householderVectors[i];
                    double alpha = 0.0d;
                    for (int j2 = k; j2 < n; j2++) {
                        alpha -= hI[j2] * hK[j2];
                    }
                    double alpha2 = alpha / (this.householderVectors[k][k] * a);
                    for (int j3 = k; j3 < n; j3++) {
                        hI[j3] = hI[j3] - (hK[j3] * alpha2);
                    }
                }
            }
            int i2 = m - 1;
            if (k < i2) {
                double[] hKp1 = this.householderVectors[k + 1];
                double xNormSqr2 = 0.0d;
                for (int i3 = k + 1; i3 < m; i3++) {
                    double c2 = this.householderVectors[i3][k];
                    xNormSqr2 += c2 * c2;
                }
                double b = hKp1[k] > 0.0d ? -FastMath.sqrt(xNormSqr2) : FastMath.sqrt(xNormSqr2);
                this.secondary[k] = b;
                if (b != 0.0d) {
                    hKp1[k] = hKp1[k] - b;
                    int j4 = k + 1;
                    while (j4 < n) {
                        double beta = 0.0d;
                        int i4 = k + 1;
                        while (i4 < m) {
                            int n2 = n;
                            double[] hI2 = this.householderVectors[i4];
                            beta -= hI2[j4] * hI2[k];
                            i4++;
                            n = n2;
                        }
                        int n3 = n;
                        double beta2 = beta / (hKp1[k] * b);
                        for (int i5 = k + 1; i5 < m; i5++) {
                            double[] hI3 = this.householderVectors[i5];
                            hI3[j4] = hI3[j4] - (hI3[k] * beta2);
                        }
                        j4++;
                        n = n3;
                    }
                }
            }
            k++;
            n = n;
        }
    }
}
