package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class SchurTransformer {
    private static final int MAX_ITERATIONS = 100;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private RealMatrix cachedT;
    private final double epsilon = Precision.EPSILON;
    private final double[][] matrixP;
    private final double[][] matrixT;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SchurTransformer(RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        HessenbergTransformer transformer = new HessenbergTransformer(matrix);
        this.matrixT = transformer.getH().getData();
        this.matrixP = transformer.getP().getData();
        this.cachedT = null;
        this.cachedP = null;
        this.cachedPt = null;
        transform();
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            this.cachedP = MatrixUtils.createRealMatrix(this.matrixP);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getT() {
        if (this.cachedT == null) {
            this.cachedT = MatrixUtils.createRealMatrix(this.matrixT);
        }
        return this.cachedT;
    }

    private void transform() {
        int n;
        double norm;
        double z;
        SchurTransformer schurTransformer = this;
        int n2 = schurTransformer.matrixT.length;
        double norm2 = schurTransformer.getNorm();
        ShiftInfo shift = new ShiftInfo();
        int iteration = 0;
        int iu = n2 - 1;
        while (iu >= 0) {
            int il = schurTransformer.findSmallSubDiagonalElement(iu, norm2);
            if (il == iu) {
                double[] dArr = schurTransformer.matrixT[iu];
                dArr[iu] = dArr[iu] + shift.exShift;
                iu--;
                iteration = 0;
                n = n2;
                norm = norm2;
            } else if (il == iu - 1) {
                double p = (schurTransformer.matrixT[iu - 1][iu - 1] - schurTransformer.matrixT[iu][iu]) / 2.0d;
                double q = (p * p) + (schurTransformer.matrixT[iu][iu - 1] * schurTransformer.matrixT[iu - 1][iu]);
                double[] dArr2 = schurTransformer.matrixT[iu];
                double d = dArr2[iu];
                norm = norm2;
                double norm3 = shift.exShift;
                dArr2[iu] = d + norm3;
                double[] dArr3 = schurTransformer.matrixT[iu - 1];
                int i = iu - 1;
                dArr3[i] = dArr3[i] + shift.exShift;
                if (q < 0.0d) {
                    n = n2;
                } else {
                    double z2 = FastMath.sqrt(FastMath.abs(q));
                    if (p >= 0.0d) {
                        z = z2 + p;
                    } else {
                        z = p - z2;
                    }
                    double x = schurTransformer.matrixT[iu][iu - 1];
                    double s = FastMath.abs(x) + FastMath.abs(z);
                    double p2 = x / s;
                    double q2 = z / s;
                    double r = FastMath.sqrt((p2 * p2) + (q2 * q2));
                    double p3 = p2 / r;
                    double q3 = q2 / r;
                    int j = iu - 1;
                    while (j < n2) {
                        int j2 = j;
                        double z3 = schurTransformer.matrixT[iu - 1][j2];
                        schurTransformer.matrixT[iu - 1][j2] = (q3 * z3) + (schurTransformer.matrixT[iu][j2] * p3);
                        schurTransformer.matrixT[iu][j2] = (schurTransformer.matrixT[iu][j2] * q3) - (p3 * z3);
                        j = j2 + 1;
                    }
                    int i2 = 0;
                    while (i2 <= iu) {
                        int i3 = i2;
                        double z4 = schurTransformer.matrixT[i3][iu - 1];
                        schurTransformer.matrixT[i3][iu - 1] = (q3 * z4) + (schurTransformer.matrixT[i3][iu] * p3);
                        schurTransformer.matrixT[i3][iu] = (schurTransformer.matrixT[i3][iu] * q3) - (p3 * z4);
                        i2 = i3 + 1;
                    }
                    int i4 = 0;
                    while (true) {
                        n = n2;
                        if (i4 > n - 1) {
                            break;
                        }
                        double z5 = schurTransformer.matrixP[i4][iu - 1];
                        int i5 = i4;
                        schurTransformer.matrixP[i4][iu - 1] = (q3 * z5) + (schurTransformer.matrixP[i5][iu] * p3);
                        schurTransformer.matrixP[i5][iu] = (schurTransformer.matrixP[i5][iu] * q3) - (p3 * z5);
                        i4 = i5 + 1;
                        n2 = n;
                    }
                }
                iu -= 2;
                iteration = 0;
            } else {
                n = n2;
                norm = norm2;
                schurTransformer.computeShift(il, iu, iteration, shift);
                int iteration2 = iteration + 1;
                if (iteration2 > 100) {
                    throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, 100, new Object[0]);
                }
                double[] hVec = new double[3];
                int im = schurTransformer.initQRStep(il, iu, shift, hVec);
                schurTransformer.performDoubleQRStep(il, im, iu, shift, hVec);
                iteration = iteration2;
            }
            schurTransformer = this;
            norm2 = norm;
            n2 = n;
        }
    }

    private double getNorm() {
        double norm = 0.0d;
        for (int i = 0; i < this.matrixT.length; i++) {
            for (int j = FastMath.max(i - 1, 0); j < this.matrixT.length; j++) {
                norm += FastMath.abs(this.matrixT[i][j]);
            }
        }
        return norm;
    }

    private int findSmallSubDiagonalElement(int startIdx, double norm) {
        int l = startIdx;
        while (l > 0) {
            double s = FastMath.abs(this.matrixT[l - 1][l - 1]) + FastMath.abs(this.matrixT[l][l]);
            if (s == 0.0d) {
                s = norm;
            }
            if (FastMath.abs(this.matrixT[l][l - 1]) < this.epsilon * s) {
                break;
            }
            l--;
        }
        return l;
    }

    private void computeShift(int l, int idx, int iteration, ShiftInfo shift) {
        shift.x = this.matrixT[idx][idx];
        shift.w = 0.0d;
        shift.y = 0.0d;
        if (l < idx) {
            shift.y = this.matrixT[idx - 1][idx - 1];
            shift.w = this.matrixT[idx][idx - 1] * this.matrixT[idx - 1][idx];
        }
        if (iteration == 10) {
            shift.exShift += shift.x;
            for (int i = 0; i <= idx; i++) {
                double[] dArr = this.matrixT[i];
                dArr[i] = dArr[i] - shift.x;
            }
            double s = FastMath.abs(this.matrixT[idx][idx - 1]) + FastMath.abs(this.matrixT[idx - 1][idx - 2]);
            shift.x = s * 0.75d;
            shift.y = 0.75d * s;
            shift.w = (-0.4375d) * s * s;
        }
        if (iteration == 30) {
            double s2 = (shift.y - shift.x) / 2.0d;
            double s3 = (s2 * s2) + shift.w;
            if (s3 > 0.0d) {
                double s4 = FastMath.sqrt(s3);
                if (shift.y < shift.x) {
                    s4 = -s4;
                }
                double s5 = shift.x - (shift.w / (((shift.y - shift.x) / 2.0d) + s4));
                for (int i2 = 0; i2 <= idx; i2++) {
                    double[] dArr2 = this.matrixT[i2];
                    dArr2[i2] = dArr2[i2] - s5;
                }
                shift.exShift += s5;
                shift.w = 0.964d;
                shift.y = 0.964d;
                shift.x = 0.964d;
            }
        }
    }

    private int initQRStep(int il, int iu, ShiftInfo shift, double[] hVec) {
        int i = il;
        ShiftInfo shiftInfo = shift;
        int im = iu - 2;
        while (im >= i) {
            double z = this.matrixT[im][im];
            double r = shiftInfo.x - z;
            double s = shiftInfo.y - z;
            hVec[0] = (((r * s) - shiftInfo.w) / this.matrixT[im + 1][im]) + this.matrixT[im][im + 1];
            hVec[1] = ((this.matrixT[im + 1][im + 1] - z) - r) - s;
            hVec[2] = this.matrixT[im + 2][im + 1];
            if (im == i) {
                break;
            }
            double lhs = FastMath.abs(this.matrixT[im][im - 1]) * (FastMath.abs(hVec[1]) + FastMath.abs(hVec[2]));
            double rhs = FastMath.abs(hVec[0]) * (FastMath.abs(this.matrixT[im - 1][im - 1]) + FastMath.abs(z) + FastMath.abs(this.matrixT[im + 1][im + 1]));
            if (lhs < this.epsilon * rhs) {
                break;
            }
            im--;
            i = il;
            shiftInfo = shift;
        }
        return im;
    }

    private void performDoubleQRStep(int il, int im, int iu, ShiftInfo shift, double[] hVec) {
        boolean z;
        int n;
        boolean z2;
        double s;
        double q;
        int i = im;
        int n2 = this.matrixT.length;
        boolean z3 = false;
        double p = hVec[0];
        boolean z4 = true;
        double q2 = hVec[1];
        double r = hVec[2];
        int k = im;
        while (k <= iu - 1) {
            boolean notlast = k != iu + (-1) ? z4 : z3;
            if (k == i) {
                z = z4;
            } else {
                p = this.matrixT[k][k - 1];
                double q3 = this.matrixT[k + 1][k - 1];
                double r2 = notlast ? this.matrixT[k + 2][k - 1] : 0.0d;
                double r3 = FastMath.abs(p);
                shift.x = r3 + FastMath.abs(q3) + FastMath.abs(r2);
                z = z4;
                if (Precision.equals(shift.x, 0.0d, this.epsilon)) {
                    n = n2;
                    z2 = z;
                    r = r2;
                    q2 = q3;
                    k++;
                    i = im;
                    z4 = z2;
                    n2 = n;
                    z3 = false;
                } else {
                    p /= shift.x;
                    q2 = q3 / shift.x;
                    r = r2 / shift.x;
                }
            }
            boolean z5 = z;
            double p2 = p;
            double s2 = FastMath.sqrt((p * p) + (q2 * q2) + (r * r));
            if (p2 < 0.0d) {
                s2 = -s2;
            }
            if (s2 == 0.0d) {
                n = n2;
                z2 = z5;
                p = p2;
            } else {
                if (k != i) {
                    z2 = z5;
                    q = q2;
                    s = s2;
                    this.matrixT[k][k - 1] = (-s2) * shift.x;
                } else {
                    s = s2;
                    z2 = z5;
                    q = q2;
                    if (il != i) {
                        this.matrixT[k][k - 1] = -this.matrixT[k][k - 1];
                    }
                }
                p = p2 + s;
                shift.x = p / s;
                shift.y = q / s;
                double z6 = r / s;
                double q4 = q / p;
                r /= p;
                int j = k;
                while (j < n2) {
                    double p3 = this.matrixT[k][j] + (this.matrixT[k + 1][j] * q4);
                    if (notlast) {
                        p3 += this.matrixT[k + 2][j] * r;
                        double[] dArr = this.matrixT[k + 2];
                        dArr[j] = dArr[j] - (p3 * z6);
                    }
                    p = p3;
                    double[] dArr2 = this.matrixT[k];
                    dArr2[j] = dArr2[j] - (shift.x * p);
                    double[] dArr3 = this.matrixT[k + 1];
                    dArr3[j] = dArr3[j] - (shift.y * p);
                    j++;
                    n2 = n2;
                }
                n = n2;
                for (int i2 = 0; i2 <= FastMath.min(iu, k + 3); i2++) {
                    double p4 = (shift.x * this.matrixT[i2][k]) + (shift.y * this.matrixT[i2][k + 1]);
                    if (notlast) {
                        p4 += this.matrixT[i2][k + 2] * z6;
                        double[] dArr4 = this.matrixT[i2];
                        int i3 = k + 2;
                        dArr4[i3] = dArr4[i3] - (p4 * r);
                    }
                    p = p4;
                    double[] dArr5 = this.matrixT[i2];
                    dArr5[k] = dArr5[k] - p;
                    double[] dArr6 = this.matrixT[i2];
                    int i4 = k + 1;
                    dArr6[i4] = dArr6[i4] - (p * q4);
                }
                int high = this.matrixT.length - 1;
                int i5 = 0;
                while (i5 <= high) {
                    int i6 = i5;
                    int high2 = high;
                    double p5 = (shift.x * this.matrixP[i6][k]) + (shift.y * this.matrixP[i6][k + 1]);
                    if (notlast) {
                        p5 += this.matrixP[i6][k + 2] * z6;
                        double[] dArr7 = this.matrixP[i6];
                        int i7 = k + 2;
                        dArr7[i7] = dArr7[i7] - (p5 * r);
                    }
                    p = p5;
                    double[] dArr8 = this.matrixP[i6];
                    dArr8[k] = dArr8[k] - p;
                    double[] dArr9 = this.matrixP[i6];
                    int i8 = k + 1;
                    dArr9[i8] = dArr9[i8] - (p * q4);
                    i5 = i6 + 1;
                    high = high2;
                }
                q2 = q4;
            }
            k++;
            i = im;
            z4 = z2;
            n2 = n;
            z3 = false;
        }
        for (int i9 = im + 2; i9 <= iu; i9++) {
            this.matrixT[i9][i9 - 2] = 0.0d;
            if (i9 > im + 2) {
                this.matrixT[i9][i9 - 3] = 0.0d;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ShiftInfo {
        double exShift;
        double w;
        double x;
        double y;

        private ShiftInfo() {
        }
    }
}
