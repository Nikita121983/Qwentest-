package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import org.apache.poi.ss.formula.ptg.IntPtg;

/* loaded from: classes10.dex */
public class EigenDecomposition {
    private static final double EPSILON = 1.0E-12d;
    private RealMatrix cachedD;
    private RealMatrix cachedV;
    private RealMatrix cachedVt;
    private ArrayRealVector[] eigenvectors;
    private double[] imagEigenvalues;
    private final boolean isSymmetric;
    private double[] main;
    private byte maxIter;
    private double[] realEigenvalues;
    private double[] secondary;
    private TriDiagonalTransformer transformer;

    public EigenDecomposition(RealMatrix matrix) throws MathArithmeticException {
        this.maxIter = IntPtg.sid;
        double symTol = matrix.getRowDimension() * 10 * matrix.getColumnDimension() * Precision.EPSILON;
        this.isSymmetric = MatrixUtils.isSymmetric(matrix, symTol);
        if (this.isSymmetric) {
            transformToTridiagonal(matrix);
            findEigenVectors(this.transformer.getQ().getData());
        } else {
            SchurTransformer t = transformToSchur(matrix);
            findEigenVectorsFromSchur(t);
        }
    }

    @Deprecated
    public EigenDecomposition(RealMatrix matrix, double splitTolerance) throws MathArithmeticException {
        this(matrix);
    }

    public EigenDecomposition(double[] main, double[] secondary) {
        this.maxIter = IntPtg.sid;
        this.isSymmetric = true;
        this.main = (double[]) main.clone();
        this.secondary = (double[]) secondary.clone();
        this.transformer = null;
        int size = main.length;
        double[][] z = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, size);
        for (int i = 0; i < size; i++) {
            z[i][i] = 1.0d;
        }
        findEigenVectors(z);
    }

    @Deprecated
    public EigenDecomposition(double[] main, double[] secondary, double splitTolerance) {
        this(main, secondary);
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int m = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; k++) {
                this.cachedV.setColumnVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedV;
    }

    public RealMatrix getD() {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
            for (int i = 0; i < this.imagEigenvalues.length; i++) {
                if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) > 0) {
                    this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
                } else if (Precision.compareTo(this.imagEigenvalues[i], 0.0d, 1.0E-12d) < 0) {
                    this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
                }
            }
        }
        return this.cachedD;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            int m = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; k++) {
                this.cachedVt.setRowVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedVt;
    }

    public boolean hasComplexEigenvalues() {
        for (int i = 0; i < this.imagEigenvalues.length; i++) {
            if (!Precision.equals(this.imagEigenvalues[i], 0.0d, 1.0E-12d)) {
                return true;
            }
        }
        return false;
    }

    public double[] getRealEigenvalues() {
        return (double[]) this.realEigenvalues.clone();
    }

    public double getRealEigenvalue(int i) {
        return this.realEigenvalues[i];
    }

    public double[] getImagEigenvalues() {
        return (double[]) this.imagEigenvalues.clone();
    }

    public double getImagEigenvalue(int i) {
        return this.imagEigenvalues[i];
    }

    public RealVector getEigenvector(int i) {
        return this.eigenvectors[i].copy();
    }

    public double getDeterminant() {
        double determinant = 1.0d;
        double[] arr$ = this.realEigenvalues;
        for (double lambda : arr$) {
            determinant *= lambda;
        }
        return determinant;
    }

    public RealMatrix getSquareRoot() {
        if (!this.isSymmetric) {
            throw new MathUnsupportedOperationException();
        }
        double[] sqrtEigenValues = new double[this.realEigenvalues.length];
        for (int i = 0; i < this.realEigenvalues.length; i++) {
            double eigen = this.realEigenvalues[i];
            if (eigen <= 0.0d) {
                throw new MathUnsupportedOperationException();
            }
            sqrtEigenValues[i] = FastMath.sqrt(eigen);
        }
        RealMatrix sqrtEigen = MatrixUtils.createRealDiagonalMatrix(sqrtEigenValues);
        RealMatrix v = getV();
        RealMatrix vT = getVT();
        return v.multiply(sqrtEigen).multiply(vT);
    }

    public DecompositionSolver getSolver() {
        if (hasComplexEigenvalues()) {
            throw new MathUnsupportedOperationException();
        }
        return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
    }

    /* loaded from: classes10.dex */
    private static class Solver implements DecompositionSolver {
        private final ArrayRealVector[] eigenvectors;
        private double[] imagEigenvalues;
        private double[] realEigenvalues;

        private Solver(double[] realEigenvalues, double[] imagEigenvalues, ArrayRealVector[] eigenvectors) {
            this.realEigenvalues = realEigenvalues;
            this.imagEigenvalues = imagEigenvalues;
            this.eigenvectors = eigenvectors;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector b) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getDimension() != m) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            double[] bp = new double[m];
            for (int i = 0; i < m; i++) {
                ArrayRealVector v = this.eigenvectors[i];
                double[] vData = v.getDataRef();
                double s = v.dotProduct(b) / this.realEigenvalues[i];
                for (int j = 0; j < m; j++) {
                    bp[j] = bp[j] + (vData[j] * s);
                }
            }
            return new ArrayRealVector(bp, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getRowDimension() != m) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            int nColB = b.getColumnDimension();
            double[][] bp = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, nColB);
            double[] tmpCol = new double[m];
            for (int k = 0; k < nColB; k++) {
                for (int i = 0; i < m; i++) {
                    tmpCol[i] = b.getEntry(i, k);
                    bp[i][k] = 0.0d;
                }
                for (int i2 = 0; i2 < m; i2++) {
                    ArrayRealVector v = this.eigenvectors[i2];
                    double[] vData = v.getDataRef();
                    double s = 0.0d;
                    for (int j = 0; j < m; j++) {
                        s += v.getEntry(j) * tmpCol[j];
                    }
                    double s2 = s / this.realEigenvalues[i2];
                    for (int j2 = 0; j2 < m; j2++) {
                        double[] dArr = bp[j2];
                        dArr[k] = dArr[k] + (vData[j2] * s2);
                    }
                }
            }
            return new Array2DRowRealMatrix(bp, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            double largestEigenvalueNorm = 0.0d;
            for (int i = 0; i < this.realEigenvalues.length; i++) {
                largestEigenvalueNorm = FastMath.max(largestEigenvalueNorm, eigenvalueNorm(i));
            }
            if (largestEigenvalueNorm == 0.0d) {
                return false;
            }
            for (int i2 = 0; i2 < this.realEigenvalues.length; i2++) {
                if (Precision.equals(eigenvalueNorm(i2) / largestEigenvalueNorm, 0.0d, 1.0E-12d)) {
                    return false;
                }
            }
            return true;
        }

        private double eigenvalueNorm(int i) {
            double re = this.realEigenvalues[i];
            double im = this.imagEigenvalues[i];
            return FastMath.sqrt((re * re) + (im * im));
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            double[][] invData = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, m);
            for (int i = 0; i < m; i++) {
                double[] invI = invData[i];
                for (int j = 0; j < m; j++) {
                    double invIJ = 0.0d;
                    for (int k = 0; k < m; k++) {
                        double[] vK = this.eigenvectors[k].getDataRef();
                        invIJ += (vK[i] * vK[j]) / this.realEigenvalues[k];
                    }
                    invI[j] = invIJ;
                }
            }
            return MatrixUtils.createRealMatrix(invData);
        }
    }

    private void transformToTridiagonal(RealMatrix matrix) {
        this.transformer = new TriDiagonalTransformer(matrix);
        this.main = this.transformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x01ec, code lost:
    
        r4 = r4 + 1;
        r5 = r30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void findEigenVectors(double[][] r39) {
        /*
            Method dump skipped, instructions count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.EigenDecomposition.findEigenVectors(double[][]):void");
    }

    private SchurTransformer transformToSchur(RealMatrix matrix) {
        SchurTransformer schurTransform = new SchurTransformer(matrix);
        double[][] matT = schurTransform.getT().getData();
        this.realEigenvalues = new double[matT.length];
        this.imagEigenvalues = new double[matT.length];
        int i = 0;
        while (i < this.realEigenvalues.length) {
            if (i == this.realEigenvalues.length - 1 || Precision.equals(matT[i + 1][i], 0.0d, 1.0E-12d)) {
                this.realEigenvalues[i] = matT[i][i];
            } else {
                double x = matT[i + 1][i + 1];
                double p = (matT[i][i] - x) * 0.5d;
                double z = FastMath.sqrt(FastMath.abs((p * p) + (matT[i + 1][i] * matT[i][i + 1])));
                this.realEigenvalues[i] = x + p;
                this.imagEigenvalues[i] = z;
                this.realEigenvalues[i + 1] = x + p;
                this.imagEigenvalues[i + 1] = -z;
                i++;
            }
            i++;
        }
        return schurTransform;
    }

    private Complex cdiv(double xr, double xi, double yr, double yi) {
        return new Complex(xr, xi).divide(new Complex(yr, yi));
    }

    private void findEigenVectorsFromSchur(SchurTransformer schur) throws MathArithmeticException {
        double[][] matrixT;
        double[][] matrixP;
        int n;
        double norm;
        int idx;
        double norm2;
        double s;
        double vr;
        int i;
        int l;
        double r;
        double[][] matrixP2;
        int n2;
        double z;
        int i2;
        double d;
        double s2;
        EigenDecomposition eigenDecomposition = this;
        double[][] matrixT2 = schur.getT().getData();
        double[][] matrixP3 = schur.getP().getData();
        int n3 = matrixT2.length;
        double norm3 = 0.0d;
        for (int i3 = 0; i3 < n3; i3++) {
            for (int j = FastMath.max(i3 - 1, 0); j < n3; j++) {
                norm3 += FastMath.abs(matrixT2[i3][j]);
            }
        }
        if (Precision.equals(norm3, 0.0d, 1.0E-12d)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double r2 = 0.0d;
        double s3 = 0.0d;
        double z2 = 0.0d;
        int idx2 = n3 - 1;
        while (idx2 >= 0) {
            double p = eigenDecomposition.realEigenvalues[idx2];
            double q = eigenDecomposition.imagEigenvalues[idx2];
            double d2 = 0.0d;
            if (Precision.equals(q, 0.0d)) {
                int l2 = idx2;
                matrixT2[idx2][idx2] = 1.0d;
                int i4 = idx2 - 1;
                double s4 = s3;
                while (i4 >= 0) {
                    double w = matrixT2[i4][i4] - p;
                    r2 = 0.0d;
                    for (int j2 = l2; j2 <= idx2; j2++) {
                        r2 += matrixT2[i4][j2] * matrixT2[j2][idx2];
                    }
                    if (Precision.compareTo(eigenDecomposition.imagEigenvalues[i4], 0.0d, 1.0E-12d) < 0) {
                        z2 = w;
                        s4 = r2;
                        d = d2;
                        i2 = i4;
                    } else {
                        int l3 = i4;
                        i2 = i4;
                        if (!Precision.equals(eigenDecomposition.imagEigenvalues[i2], d2)) {
                            double x = matrixT2[i2][i2 + 1];
                            double y = matrixT2[i2 + 1][i2];
                            d = d2;
                            s2 = s4;
                            double t = ((x * s2) - (z2 * r2)) / (((eigenDecomposition.realEigenvalues[i2] - p) * (eigenDecomposition.realEigenvalues[i2] - p)) + (eigenDecomposition.imagEigenvalues[i2] * eigenDecomposition.imagEigenvalues[i2]));
                            matrixT2[i2][idx2] = t;
                            if (FastMath.abs(x) > FastMath.abs(z2)) {
                                matrixT2[i2 + 1][idx2] = ((-r2) - (w * t)) / x;
                            } else {
                                matrixT2[i2 + 1][idx2] = ((-s2) - (y * t)) / z2;
                            }
                        } else {
                            if (w != d2) {
                                matrixT2[i2][idx2] = (-r2) / w;
                            } else {
                                matrixT2[i2][idx2] = (-r2) / (Precision.EPSILON * norm3);
                            }
                            d = d2;
                            s2 = s4;
                        }
                        double t2 = FastMath.abs(matrixT2[i2][idx2]);
                        if (Precision.EPSILON * t2 * t2 > 1.0d) {
                            for (int j3 = i2; j3 <= idx2; j3++) {
                                double[] dArr = matrixT2[j3];
                                dArr[idx2] = dArr[idx2] / t2;
                            }
                        }
                        s4 = s2;
                        l2 = l3;
                    }
                    i4 = i2 - 1;
                    d2 = d;
                }
                s3 = s4;
                matrixT = matrixT2;
                matrixP = matrixP3;
                n = n3;
                norm = norm3;
                idx = idx2;
            } else if (q >= 0.0d) {
                matrixT = matrixT2;
                matrixP = matrixP3;
                n = n3;
                norm = norm3;
                idx = idx2;
            } else {
                int l4 = idx2 - 1;
                if (FastMath.abs(matrixT2[idx2][idx2 - 1]) > FastMath.abs(matrixT2[idx2 - 1][idx2])) {
                    matrixT2[idx2 - 1][idx2 - 1] = q / matrixT2[idx2][idx2 - 1];
                    matrixT2[idx2 - 1][idx2] = (-(matrixT2[idx2][idx2] - p)) / matrixT2[idx2][idx2 - 1];
                    matrixT = matrixT2;
                    norm = norm3;
                    norm2 = 0.0d;
                    idx = idx2;
                } else {
                    double d3 = -matrixT2[idx2 - 1][idx2];
                    double d4 = matrixT2[idx2 - 1][idx2 - 1] - p;
                    matrixT = matrixT2;
                    norm = norm3;
                    idx = idx2;
                    norm2 = 0.0d;
                    Complex result = eigenDecomposition.cdiv(0.0d, d3, d4, q);
                    matrixT[idx - 1][idx - 1] = result.getReal();
                    matrixT[idx - 1][idx] = result.getImaginary();
                }
                matrixT[idx][idx - 1] = norm2;
                matrixT[idx][idx] = 1.0d;
                double d5 = r2;
                int i5 = idx - 2;
                double r3 = d5;
                while (i5 >= 0) {
                    double ra = 0.0d;
                    double sa = 0.0d;
                    for (int j4 = l4; j4 <= idx; j4++) {
                        ra += matrixT[i5][j4] * matrixT[j4][idx - 1];
                        sa += matrixT[i5][j4] * matrixT[j4][idx];
                    }
                    double w2 = matrixT[i5][i5] - p;
                    if (Precision.compareTo(eigenDecomposition.imagEigenvalues[i5], 0.0d, 1.0E-12d) < 0) {
                        z2 = w2;
                        r3 = ra;
                        matrixP2 = matrixP3;
                        n2 = n3;
                        i = i5;
                        s3 = sa;
                    } else {
                        int l5 = i5;
                        double r4 = r3;
                        if (Precision.equals(eigenDecomposition.imagEigenvalues[i5], norm2)) {
                            double d6 = -ra;
                            double ra2 = -sa;
                            eigenDecomposition = this;
                            s = s3;
                            Complex c = eigenDecomposition.cdiv(d6, ra2, w2, q);
                            matrixT[i5][idx - 1] = c.getReal();
                            matrixT[i5][idx] = c.getImaginary();
                            i = i5;
                            l = l5;
                            z = z2;
                            r = r4;
                            matrixP2 = matrixP3;
                            n2 = n3;
                        } else {
                            double ra3 = ra;
                            double sa2 = sa;
                            s = s3;
                            double s5 = q;
                            double x2 = matrixT[i5][i5 + 1];
                            double y2 = matrixT[i5 + 1][i5];
                            double vr2 = (((this.realEigenvalues[i5] - p) * (this.realEigenvalues[i5] - p)) + (this.imagEigenvalues[i5] * this.imagEigenvalues[i5])) - (s5 * s5);
                            double vi = (this.realEigenvalues[i5] - p) * 2.0d * s5;
                            if (Precision.equals(vr2, norm2) && Precision.equals(vi, norm2)) {
                                vr = Precision.EPSILON * norm * (FastMath.abs(w2) + FastMath.abs(s5) + FastMath.abs(x2) + FastMath.abs(y2) + FastMath.abs(z2));
                            } else {
                                vr = vr2;
                            }
                            i = i5;
                            l = l5;
                            r = r4;
                            matrixP2 = matrixP3;
                            n2 = n3;
                            Complex c2 = cdiv(((x2 * r4) - (z2 * ra3)) + (s5 * sa2), ((x2 * s) - (z2 * sa2)) - (s5 * ra3), vr, vi);
                            matrixT[i][idx - 1] = c2.getReal();
                            matrixT[i][idx] = c2.getImaginary();
                            if (FastMath.abs(x2) > FastMath.abs(z2) + FastMath.abs(s5)) {
                                matrixT[i + 1][idx - 1] = (((-ra3) - (w2 * matrixT[i][idx - 1])) + (s5 * matrixT[i][idx])) / x2;
                                matrixT[i + 1][idx] = (((-sa2) - (w2 * matrixT[i][idx])) - (s5 * matrixT[i][idx - 1])) / x2;
                                eigenDecomposition = this;
                                q = s5;
                                z = z2;
                            } else {
                                eigenDecomposition = this;
                                q = s5;
                                z = z2;
                                Complex c22 = eigenDecomposition.cdiv((-r) - (matrixT[i][idx - 1] * y2), (-s) - (matrixT[i][idx] * y2), z, q);
                                matrixT[i + 1][idx - 1] = c22.getReal();
                                matrixT[i + 1][idx] = c22.getImaginary();
                            }
                        }
                        double t3 = FastMath.max(FastMath.abs(matrixT[i][idx - 1]), FastMath.abs(matrixT[i][idx]));
                        if (Precision.EPSILON * t3 * t3 > 1.0d) {
                            for (int j5 = i; j5 <= idx; j5++) {
                                double[] dArr2 = matrixT[j5];
                                int i6 = idx - 1;
                                dArr2[i6] = dArr2[i6] / t3;
                                double[] dArr3 = matrixT[j5];
                                dArr3[idx] = dArr3[idx] / t3;
                            }
                        }
                        z2 = z;
                        r3 = r;
                        l4 = l;
                        s3 = s;
                    }
                    i5 = i - 1;
                    matrixP3 = matrixP2;
                    n3 = n2;
                    norm2 = 0.0d;
                }
                matrixP = matrixP3;
                n = n3;
                r2 = r3;
            }
            idx2 = idx - 1;
            matrixT2 = matrixT;
            norm3 = norm;
            matrixP3 = matrixP;
            n3 = n;
        }
        double[][] matrixT3 = matrixT2;
        double[][] matrixP4 = matrixP3;
        int n4 = n3;
        for (int j6 = n4 - 1; j6 >= 0; j6--) {
            for (int i7 = 0; i7 <= n4 - 1; i7++) {
                double z3 = 0.0d;
                for (int k = 0; k <= FastMath.min(j6, n4 - 1); k++) {
                    z3 += matrixP4[i7][k] * matrixT3[k][j6];
                }
                matrixP4[i7][j6] = z3;
            }
        }
        eigenDecomposition.eigenvectors = new ArrayRealVector[n4];
        double[] tmp = new double[n4];
        for (int i8 = 0; i8 < n4; i8++) {
            for (int j7 = 0; j7 < n4; j7++) {
                tmp[j7] = matrixP4[j7][i8];
            }
            eigenDecomposition.eigenvectors[i8] = new ArrayRealVector(tmp);
        }
    }
}
