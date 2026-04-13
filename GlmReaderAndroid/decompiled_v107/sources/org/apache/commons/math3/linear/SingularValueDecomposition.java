package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class SingularValueDecomposition {
    private static final double EPS = 2.220446049250313E-16d;
    private static final double TINY = 1.6033346880071782E-291d;
    private RealMatrix cachedS;
    private final RealMatrix cachedU;
    private RealMatrix cachedUt;
    private final RealMatrix cachedV;
    private RealMatrix cachedVt;
    private final int m;
    private final int n;
    private final double[] singularValues;
    private final double tol;
    private final boolean transposed;

    public SingularValueDecomposition(RealMatrix realMatrix) {
        double[][] data;
        int i;
        double[][] dArr;
        double[][] dArr2;
        double[][] dArr3;
        int i2;
        double[] dArr4;
        double[] dArr5;
        int i3;
        double d;
        double d2;
        boolean z;
        boolean z2;
        double d3;
        double d4;
        boolean z3 = false;
        boolean z4 = true;
        if (realMatrix.getRowDimension() < realMatrix.getColumnDimension()) {
            this.transposed = true;
            data = realMatrix.transpose().getData();
            this.m = realMatrix.getColumnDimension();
            this.n = realMatrix.getRowDimension();
        } else {
            this.transposed = false;
            data = realMatrix.getData();
            this.m = realMatrix.getRowDimension();
            this.n = realMatrix.getColumnDimension();
        }
        this.singularValues = new double[this.n];
        double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.m, this.n);
        double[][] dArr7 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, this.n, this.n);
        double[] dArr8 = new double[this.n];
        double[] dArr9 = new double[this.m];
        int min = FastMath.min(this.m - 1, this.n);
        int max = FastMath.max(0, this.n - 2);
        int i4 = 0;
        while (true) {
            double d5 = 0.0d;
            if (i4 >= FastMath.max(min, max)) {
                break;
            }
            if (i4 >= min) {
                z = z3;
                z2 = z4;
                d3 = 0.0d;
                d4 = 1.0d;
            } else {
                this.singularValues[i4] = 0.0d;
                int i5 = i4;
                while (true) {
                    z = z3;
                    if (i5 >= this.m) {
                        break;
                    }
                    this.singularValues[i4] = FastMath.hypot(this.singularValues[i4], data[i5][i4]);
                    i5++;
                    z3 = z;
                    z4 = z4;
                    d5 = d5;
                }
                z2 = z4;
                d3 = d5;
                d4 = 1.0d;
                if (this.singularValues[i4] != d3) {
                    if (data[i4][i4] < d3) {
                        this.singularValues[i4] = -this.singularValues[i4];
                    }
                    for (int i6 = i4; i6 < this.m; i6++) {
                        double[] dArr10 = data[i6];
                        dArr10[i4] = dArr10[i4] / this.singularValues[i4];
                    }
                    double[] dArr11 = data[i4];
                    dArr11[i4] = dArr11[i4] + 1.0d;
                }
                this.singularValues[i4] = -this.singularValues[i4];
            }
            for (int i7 = i4 + 1; i7 < this.n; i7++) {
                if (i4 < min && this.singularValues[i4] != d3) {
                    double d6 = 0.0d;
                    for (int i8 = i4; i8 < this.m; i8++) {
                        d6 += data[i8][i4] * data[i8][i7];
                    }
                    double d7 = (-d6) / data[i4][i4];
                    for (int i9 = i4; i9 < this.m; i9++) {
                        double[] dArr12 = data[i9];
                        dArr12[i7] = dArr12[i7] + (data[i9][i4] * d7);
                    }
                }
                dArr8[i7] = data[i4][i7];
            }
            if (i4 < min) {
                for (int i10 = i4; i10 < this.m; i10++) {
                    dArr6[i10][i4] = data[i10][i4];
                }
            }
            if (i4 < max) {
                dArr8[i4] = d3;
                for (int i11 = i4 + 1; i11 < this.n; i11++) {
                    dArr8[i4] = FastMath.hypot(dArr8[i4], dArr8[i11]);
                }
                if (dArr8[i4] != d3) {
                    if (dArr8[i4 + 1] < d3) {
                        dArr8[i4] = -dArr8[i4];
                    }
                    for (int i12 = i4 + 1; i12 < this.n; i12++) {
                        dArr8[i12] = dArr8[i12] / dArr8[i4];
                    }
                    int i13 = i4 + 1;
                    dArr8[i13] = dArr8[i13] + d4;
                }
                dArr8[i4] = -dArr8[i4];
                if (i4 + 1 < this.m && dArr8[i4] != d3) {
                    for (int i14 = i4 + 1; i14 < this.m; i14++) {
                        dArr9[i14] = d3;
                    }
                    for (int i15 = i4 + 1; i15 < this.n; i15++) {
                        for (int i16 = i4 + 1; i16 < this.m; i16++) {
                            dArr9[i16] = dArr9[i16] + (dArr8[i15] * data[i16][i15]);
                        }
                    }
                    for (int i17 = i4 + 1; i17 < this.n; i17++) {
                        double d8 = (-dArr8[i17]) / dArr8[i4 + 1];
                        for (int i18 = i4 + 1; i18 < this.m; i18++) {
                            double[] dArr13 = data[i18];
                            dArr13[i17] = dArr13[i17] + (dArr9[i18] * d8);
                        }
                    }
                }
                for (int i19 = i4 + 1; i19 < this.n; i19++) {
                    dArr7[i19][i4] = dArr8[i19];
                }
            }
            i4++;
            z3 = z;
            z4 = z2;
        }
        boolean z5 = z3;
        int i20 = this.n;
        if (min < this.n) {
            this.singularValues[min] = data[min][min];
        }
        if (this.m < i20) {
            this.singularValues[i20 - 1] = 0.0d;
        }
        if (max + 1 < i20) {
            dArr8[max] = data[max][i20 - 1];
        }
        dArr8[i20 - 1] = 0.0d;
        for (int i21 = min; i21 < this.n; i21++) {
            for (int i22 = 0; i22 < this.m; i22++) {
                dArr6[i22][i21] = 0.0d;
            }
            dArr6[i21][i21] = 1.0d;
        }
        for (int i23 = min - 1; i23 >= 0; i23--) {
            if (this.singularValues[i23] != 0.0d) {
                for (int i24 = i23 + 1; i24 < this.n; i24++) {
                    double d9 = 0.0d;
                    for (int i25 = i23; i25 < this.m; i25++) {
                        d9 += dArr6[i25][i23] * dArr6[i25][i24];
                    }
                    double d10 = (-d9) / dArr6[i23][i23];
                    for (int i26 = i23; i26 < this.m; i26++) {
                        double[] dArr14 = dArr6[i26];
                        dArr14[i24] = dArr14[i24] + (dArr6[i26][i23] * d10);
                    }
                }
                for (int i27 = i23; i27 < this.m; i27++) {
                    dArr6[i27][i23] = -dArr6[i27][i23];
                }
                dArr6[i23][i23] = dArr6[i23][i23] + 1.0d;
                for (int i28 = 0; i28 < i23 - 1; i28++) {
                    dArr6[i28][i23] = 0.0d;
                }
            } else {
                for (int i29 = 0; i29 < this.m; i29++) {
                    dArr6[i29][i23] = 0.0d;
                }
                dArr6[i23][i23] = 1.0d;
            }
        }
        for (int i30 = this.n - 1; i30 >= 0; i30--) {
            if (i30 < max && dArr8[i30] != 0.0d) {
                for (int i31 = i30 + 1; i31 < this.n; i31++) {
                    double d11 = 0.0d;
                    for (int i32 = i30 + 1; i32 < this.n; i32++) {
                        d11 += dArr7[i32][i30] * dArr7[i32][i31];
                    }
                    double d12 = (-d11) / dArr7[i30 + 1][i30];
                    for (int i33 = i30 + 1; i33 < this.n; i33++) {
                        double[] dArr15 = dArr7[i33];
                        dArr15[i31] = dArr15[i31] + (dArr7[i33][i30] * d12);
                    }
                }
            }
            for (int i34 = 0; i34 < this.n; i34++) {
                dArr7[i34][i30] = 0.0d;
            }
            dArr7[i30][i30] = 1.0d;
        }
        int i35 = i20 - 1;
        while (true) {
            double d13 = EPS;
            if (i20 <= 0) {
                double[][] dArr16 = dArr6;
                double[][] dArr17 = dArr7;
                this.tol = FastMath.max(this.m * this.singularValues[z5 ? 1 : 0] * EPS, FastMath.sqrt(Precision.SAFE_MIN));
                if (!this.transposed) {
                    this.cachedU = MatrixUtils.createRealMatrix(dArr16);
                    this.cachedV = MatrixUtils.createRealMatrix(dArr17);
                    return;
                } else {
                    this.cachedU = MatrixUtils.createRealMatrix(dArr17);
                    this.cachedV = MatrixUtils.createRealMatrix(dArr16);
                    return;
                }
            }
            int i36 = i20 - 2;
            while (true) {
                if (i36 >= 0) {
                    if (FastMath.abs(dArr8[i36]) > ((FastMath.abs(this.singularValues[i36]) + FastMath.abs(this.singularValues[i36 + 1])) * EPS) + TINY) {
                        i36--;
                    } else {
                        dArr8[i36] = 0.0d;
                    }
                }
            }
            if (i36 == i20 - 2) {
                i = 4;
            } else {
                int i37 = i20 - 1;
                while (true) {
                    if (i37 >= i36 && i37 != i36) {
                        double d14 = d13;
                        if (FastMath.abs(this.singularValues[i37]) > (((i37 != i20 ? FastMath.abs(dArr8[i37]) : 0.0d) + (i37 != i36 + 1 ? FastMath.abs(dArr8[i37 - 1]) : 0.0d)) * d14) + TINY) {
                            i37--;
                            d13 = d14;
                        } else {
                            this.singularValues[i37] = 0.0d;
                        }
                    }
                }
                if (i37 == i36) {
                    i = 3;
                } else if (i37 == i20 - 1) {
                    i = 1;
                } else {
                    i = 2;
                    i36 = i37;
                }
            }
            int i38 = i36 + 1;
            switch (i) {
                case 1:
                    dArr = data;
                    dArr2 = dArr6;
                    dArr3 = dArr7;
                    i2 = max;
                    dArr4 = dArr8;
                    dArr5 = dArr9;
                    i3 = min;
                    double d15 = dArr4[i20 - 2];
                    dArr4[i20 - 2] = 0.0d;
                    for (int i39 = i20 - 2; i39 >= i38; i39--) {
                        double hypot = FastMath.hypot(this.singularValues[i39], d15);
                        double d16 = this.singularValues[i39] / hypot;
                        double d17 = d15 / hypot;
                        this.singularValues[i39] = hypot;
                        if (i39 != i38) {
                            d15 = (-d17) * dArr4[i39 - 1];
                            dArr4[i39 - 1] = dArr4[i39 - 1] * d16;
                        }
                        int i40 = 0;
                        while (i40 < this.n) {
                            double d18 = (dArr3[i40][i39] * d16) + (dArr3[i40][i20 - 1] * d17);
                            dArr3[i40][i20 - 1] = ((-d17) * dArr3[i40][i39]) + (dArr3[i40][i20 - 1] * d16);
                            dArr3[i40][i39] = d18;
                            i40++;
                            d15 = d15;
                        }
                    }
                    break;
                case 2:
                    dArr = data;
                    dArr2 = dArr6;
                    dArr3 = dArr7;
                    i2 = max;
                    dArr4 = dArr8;
                    dArr5 = dArr9;
                    i3 = min;
                    double d19 = dArr4[i38 - 1];
                    dArr4[i38 - 1] = 0.0d;
                    for (int i41 = i38; i41 < i20; i41++) {
                        double hypot2 = FastMath.hypot(this.singularValues[i41], d19);
                        double d20 = this.singularValues[i41] / hypot2;
                        double d21 = d19 / hypot2;
                        this.singularValues[i41] = hypot2;
                        d19 = (-d21) * dArr4[i41];
                        dArr4[i41] = dArr4[i41] * d20;
                        int i42 = 0;
                        while (i42 < this.m) {
                            double d22 = (dArr2[i42][i41] * d20) + (dArr2[i42][i38 - 1] * d21);
                            dArr2[i42][i38 - 1] = ((-d21) * dArr2[i42][i41]) + (dArr2[i42][i38 - 1] * d20);
                            dArr2[i42][i41] = d22;
                            i42++;
                            d19 = d19;
                        }
                    }
                    break;
                case 3:
                    double[][] dArr18 = data;
                    dArr2 = dArr6;
                    double max2 = FastMath.max(FastMath.max(FastMath.max(FastMath.max(FastMath.abs(this.singularValues[i20 - 1]), FastMath.abs(this.singularValues[i20 - 2])), FastMath.abs(dArr8[i20 - 2])), FastMath.abs(this.singularValues[i38])), FastMath.abs(dArr8[i38]));
                    double d23 = this.singularValues[i20 - 1] / max2;
                    double d24 = this.singularValues[i20 - 2] / max2;
                    double d25 = dArr8[i20 - 2] / max2;
                    double d26 = this.singularValues[i38] / max2;
                    double d27 = dArr8[i38] / max2;
                    double d28 = (((d24 + d23) * (d24 - d23)) + (d25 * d25)) / 2.0d;
                    double d29 = d23 * d25 * d23 * d25;
                    double d30 = 0.0d;
                    if (d28 == 0.0d && d29 == 0.0d) {
                        d = d27;
                    } else {
                        d = d27;
                        double sqrt = FastMath.sqrt((d28 * d28) + d29);
                        d30 = d29 / (d28 + (d28 < 0.0d ? -sqrt : sqrt));
                    }
                    double d31 = ((d26 + d23) * (d26 - d23)) + d30;
                    dArr4 = dArr8;
                    int i43 = i38;
                    dArr3 = dArr7;
                    i2 = max;
                    double d32 = d26 * d;
                    while (true) {
                        dArr5 = dArr9;
                        if (i43 < i20 - 1) {
                            double hypot3 = FastMath.hypot(d31, d32);
                            double d33 = d31 / hypot3;
                            double d34 = d32 / hypot3;
                            if (i43 != i38) {
                                dArr4[i43 - 1] = hypot3;
                            }
                            double d35 = (this.singularValues[i43] * d33) + (dArr4[i43] * d34);
                            dArr4[i43] = (dArr4[i43] * d33) - (this.singularValues[i43] * d34);
                            int i44 = min;
                            double d36 = d34 * this.singularValues[i43 + 1];
                            int i45 = i;
                            this.singularValues[i43 + 1] = this.singularValues[i43 + 1] * d33;
                            int i46 = 0;
                            while (true) {
                                double[][] dArr19 = dArr18;
                                if (i46 < this.n) {
                                    double d37 = (dArr3[i46][i43] * d33) + (dArr3[i46][i43 + 1] * d34);
                                    int i47 = i46;
                                    dArr3[i46][i43 + 1] = ((-d34) * dArr3[i47][i43]) + (dArr3[i47][i43 + 1] * d33);
                                    dArr3[i47][i43] = d37;
                                    i46 = i47 + 1;
                                    dArr18 = dArr19;
                                } else {
                                    double hypot4 = FastMath.hypot(d35, d36);
                                    double d38 = d35 / hypot4;
                                    double d39 = d36 / hypot4;
                                    this.singularValues[i43] = hypot4;
                                    double d40 = (dArr4[i43] * d38) + (this.singularValues[i43 + 1] * d39);
                                    this.singularValues[i43 + 1] = ((-d39) * dArr4[i43]) + (this.singularValues[i43 + 1] * d38);
                                    double d41 = dArr4[i43 + 1] * d39;
                                    dArr4[i43 + 1] = dArr4[i43 + 1] * d38;
                                    if (i43 < this.m - 1) {
                                        int i48 = 0;
                                        while (i48 < this.m) {
                                            double d42 = (dArr2[i48][i43] * d38) + (dArr2[i48][i43 + 1] * d39);
                                            dArr2[i48][i43 + 1] = ((-d39) * dArr2[i48][i43]) + (dArr2[i48][i43 + 1] * d38);
                                            dArr2[i48][i43] = d42;
                                            i48++;
                                            d41 = d41;
                                        }
                                    }
                                    i43++;
                                    dArr9 = dArr5;
                                    d31 = d40;
                                    min = i44;
                                    i = i45;
                                    dArr18 = dArr19;
                                    d32 = d41;
                                }
                            }
                        } else {
                            i3 = min;
                            dArr = dArr18;
                            dArr4[i20 - 2] = d31;
                            break;
                        }
                    }
                    break;
                default:
                    dArr = data;
                    dArr2 = dArr6;
                    dArr3 = dArr7;
                    i2 = max;
                    dArr4 = dArr8;
                    dArr5 = dArr9;
                    i3 = min;
                    if (this.singularValues[i38] <= 0.0d) {
                        double[] dArr20 = this.singularValues;
                        if (this.singularValues[i38] < 0.0d) {
                            d2 = -this.singularValues[i38];
                        } else {
                            d2 = 0.0d;
                        }
                        dArr20[i38] = d2;
                        for (int i49 = 0; i49 <= i35; i49++) {
                            dArr3[i49][i38] = -dArr3[i49][i38];
                        }
                    }
                    while (i38 < i35 && this.singularValues[i38] < this.singularValues[i38 + 1]) {
                        double d43 = this.singularValues[i38];
                        this.singularValues[i38] = this.singularValues[i38 + 1];
                        this.singularValues[i38 + 1] = d43;
                        if (i38 < this.n - 1) {
                            for (int i50 = 0; i50 < this.n; i50++) {
                                double d44 = dArr3[i50][i38 + 1];
                                dArr3[i50][i38 + 1] = dArr3[i50][i38];
                                dArr3[i50][i38] = d44;
                            }
                        }
                        if (i38 < this.m - 1) {
                            for (int i51 = 0; i51 < this.m; i51++) {
                                double d45 = dArr2[i51][i38 + 1];
                                dArr2[i51][i38 + 1] = dArr2[i51][i38];
                                dArr2[i51][i38] = d45;
                            }
                        }
                        i38++;
                    }
                    i20--;
                    break;
            }
            dArr6 = dArr2;
            dArr7 = dArr3;
            max = i2;
            dArr8 = dArr4;
            dArr9 = dArr5;
            min = i3;
            data = dArr;
        }
    }

    public RealMatrix getU() {
        return this.cachedU;
    }

    public RealMatrix getUT() {
        if (this.cachedUt == null) {
            this.cachedUt = getU().transpose();
        }
        return this.cachedUt;
    }

    public RealMatrix getS() {
        if (this.cachedS == null) {
            this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues);
        }
        return this.cachedS;
    }

    public double[] getSingularValues() {
        return (double[]) this.singularValues.clone();
    }

    public RealMatrix getV() {
        return this.cachedV;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            this.cachedVt = getV().transpose();
        }
        return this.cachedVt;
    }

    public RealMatrix getCovariance(double minSingularValue) {
        int p = this.singularValues.length;
        int dimension = 0;
        while (dimension < p && this.singularValues[dimension] >= minSingularValue) {
            dimension++;
        }
        if (dimension == 0) {
            throw new NumberIsTooLargeException(LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, Double.valueOf(minSingularValue), Double.valueOf(this.singularValues[0]), true);
        }
        final double[][] data = (double[][]) Array.newInstance((Class<?>) Double.TYPE, dimension, p);
        getVT().walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor() { // from class: org.apache.commons.math3.linear.SingularValueDecomposition.1
            @Override // org.apache.commons.math3.linear.DefaultRealMatrixPreservingVisitor, org.apache.commons.math3.linear.RealMatrixPreservingVisitor
            public void visit(int row, int column, double value) {
                data[row][column] = value / SingularValueDecomposition.this.singularValues[row];
            }
        }, 0, dimension - 1, 0, p - 1);
        RealMatrix jv = new Array2DRowRealMatrix(data, false);
        return jv.transpose().multiply(jv);
    }

    public double getNorm() {
        return this.singularValues[0];
    }

    public double getConditionNumber() {
        return this.singularValues[0] / this.singularValues[this.n - 1];
    }

    public double getInverseConditionNumber() {
        return this.singularValues[this.n - 1] / this.singularValues[0];
    }

    public int getRank() {
        int r = 0;
        for (int i = 0; i < this.singularValues.length; i++) {
            if (this.singularValues[i] > this.tol) {
                r++;
            }
        }
        return r;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.singularValues, getUT(), getV(), getRank() == this.m, this.tol);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Solver implements DecompositionSolver {
        private boolean nonSingular;
        private final RealMatrix pseudoInverse;

        private Solver(double[] singularValues, RealMatrix uT, RealMatrix v, boolean nonSingular, double tol) {
            double a;
            double[][] suT = uT.getData();
            for (int i = 0; i < singularValues.length; i++) {
                if (singularValues[i] > tol) {
                    a = 1.0d / singularValues[i];
                } else {
                    a = 0.0d;
                }
                double[] suTi = suT[i];
                for (int j = 0; j < suTi.length; j++) {
                    suTi[j] = suTi[j] * a;
                }
            }
            this.pseudoInverse = v.multiply(new Array2DRowRealMatrix(suT, false));
            this.nonSingular = nonSingular;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector b) {
            return this.pseudoInverse.operate(b);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) {
            return this.pseudoInverse.multiply(b);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            return this.nonSingular;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return this.pseudoInverse;
        }
    }
}
