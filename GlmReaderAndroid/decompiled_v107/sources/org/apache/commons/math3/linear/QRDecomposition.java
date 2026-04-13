package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class QRDecomposition {
    private RealMatrix cachedH;
    private RealMatrix cachedQ;
    private RealMatrix cachedQT;
    private RealMatrix cachedR;
    private double[][] qrt;
    private double[] rDiag;
    private final double threshold;

    public QRDecomposition(RealMatrix matrix) {
        this(matrix, 0.0d);
    }

    public QRDecomposition(RealMatrix matrix, double threshold) {
        this.threshold = threshold;
        int m = matrix.getRowDimension();
        int n = matrix.getColumnDimension();
        this.qrt = matrix.transpose().getData();
        this.rDiag = new double[FastMath.min(m, n)];
        this.cachedQ = null;
        this.cachedQT = null;
        this.cachedR = null;
        this.cachedH = null;
        decompose(this.qrt);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void decompose(double[][] matrix) {
        for (int minor = 0; minor < FastMath.min(matrix.length, matrix[0].length); minor++) {
            performHouseholderReflection(minor, matrix);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performHouseholderReflection(int minor, double[][] matrix) {
        double[] qrtMinor = matrix[minor];
        double xNormSqr = 0.0d;
        for (int row = minor; row < qrtMinor.length; row++) {
            double c = qrtMinor[row];
            xNormSqr += c * c;
        }
        double a = qrtMinor[minor] > 0.0d ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
        this.rDiag[minor] = a;
        if (a != 0.0d) {
            qrtMinor[minor] = qrtMinor[minor] - a;
            for (int col = minor + 1; col < matrix.length; col++) {
                double[] qrtCol = matrix[col];
                double alpha = 0.0d;
                for (int row2 = minor; row2 < qrtCol.length; row2++) {
                    alpha -= qrtCol[row2] * qrtMinor[row2];
                }
                double alpha2 = alpha / (qrtMinor[minor] * a);
                for (int row3 = minor; row3 < qrtCol.length; row3++) {
                    qrtCol[row3] = qrtCol[row3] - (qrtMinor[row3] * alpha2);
                }
            }
        }
    }

    public RealMatrix getR() {
        if (this.cachedR == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            double[][] ra = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, n);
            for (int row = FastMath.min(m, n) - 1; row >= 0; row--) {
                ra[row][row] = this.rDiag[row];
                for (int col = row + 1; col < n; col++) {
                    ra[row][col] = this.qrt[col][row];
                }
            }
            this.cachedR = MatrixUtils.createRealMatrix(ra);
        }
        return this.cachedR;
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() {
        double d;
        if (this.cachedQT == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            double[][] qta = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, m);
            int minor = m - 1;
            while (true) {
                d = 1.0d;
                if (minor < FastMath.min(m, n)) {
                    break;
                }
                qta[minor][minor] = 1.0d;
                minor--;
            }
            int minor2 = FastMath.min(m, n);
            int minor3 = minor2 - 1;
            while (minor3 >= 0) {
                double[] qrtMinor = this.qrt[minor3];
                qta[minor3][minor3] = d;
                if (qrtMinor[minor3] != 0.0d) {
                    for (int col = minor3; col < m; col++) {
                        double alpha = 0.0d;
                        for (int row = minor3; row < m; row++) {
                            alpha -= qta[col][row] * qrtMinor[row];
                        }
                        double alpha2 = alpha / (this.rDiag[minor3] * qrtMinor[minor3]);
                        for (int row2 = minor3; row2 < m; row2++) {
                            double[] dArr = qta[col];
                            dArr[row2] = dArr[row2] + ((-alpha2) * qrtMinor[row2]);
                        }
                    }
                }
                minor3--;
                d = 1.0d;
            }
            this.cachedQT = MatrixUtils.createRealMatrix(qta);
        }
        return this.cachedQT;
    }

    public RealMatrix getH() {
        if (this.cachedH == null) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            double[][] ha = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m, n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < FastMath.min(i + 1, n); j++) {
                    ha[i][j] = this.qrt[j][i] / (-this.rDiag[j]);
                }
            }
            this.cachedH = MatrixUtils.createRealMatrix(ha);
        }
        return this.cachedH;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.qrt, this.rDiag, this.threshold);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Solver implements DecompositionSolver {
        private final double[][] qrt;
        private final double[] rDiag;
        private final double threshold;

        private Solver(double[][] qrt, double[] rDiag, double threshold) {
            this.qrt = qrt;
            this.rDiag = rDiag;
            this.threshold = threshold;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public boolean isNonSingular() {
            double[] arr$ = this.rDiag;
            for (double diag : arr$) {
                if (FastMath.abs(diag) <= this.threshold) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealVector solve(RealVector b) {
            int n = this.qrt.length;
            int m = this.qrt[0].length;
            if (b.getDimension() != m) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            if (!isNonSingular()) {
                throw new SingularMatrixException();
            }
            double[] x = new double[n];
            double[] y = b.toArray();
            for (int minor = 0; minor < FastMath.min(m, n); minor++) {
                double[] qrtMinor = this.qrt[minor];
                double dotProduct = 0.0d;
                for (int row = minor; row < m; row++) {
                    dotProduct += y[row] * qrtMinor[row];
                }
                double dotProduct2 = dotProduct / (this.rDiag[minor] * qrtMinor[minor]);
                for (int row2 = minor; row2 < m; row2++) {
                    y[row2] = y[row2] + (qrtMinor[row2] * dotProduct2);
                }
            }
            for (int row3 = this.rDiag.length - 1; row3 >= 0; row3--) {
                y[row3] = y[row3] / this.rDiag[row3];
                double yRow = y[row3];
                double[] qrtRow = this.qrt[row3];
                x[row3] = yRow;
                for (int i = 0; i < row3; i++) {
                    y[i] = y[i] - (qrtRow[i] * yRow);
                }
            }
            return new ArrayRealVector(x, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix solve(RealMatrix b) {
            Solver solver = this;
            int n = solver.qrt.length;
            int i = 0;
            int m = solver.qrt[0].length;
            if (b.getRowDimension() != m) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            if (!solver.isNonSingular()) {
                throw new SingularMatrixException();
            }
            int columns = b.getColumnDimension();
            int blockSize = 52;
            boolean z = true;
            int cBlocks = ((columns + 52) - 1) / 52;
            double[][] xBlocks = BlockRealMatrix.createBlocksLayout(n, columns);
            double[][] y = (double[][]) Array.newInstance((Class<?>) Double.TYPE, b.getRowDimension(), 52);
            double[] alpha = new double[52];
            int kBlock = 0;
            while (kBlock < cBlocks) {
                int kStart = kBlock * 52;
                int kEnd = FastMath.min(kStart + 52, columns);
                int kWidth = kEnd - kStart;
                boolean z2 = z;
                b.copySubMatrix(0, m - 1, kStart, kEnd - 1, y);
                int minor = 0;
                while (minor < FastMath.min(m, n)) {
                    double[] qrtMinor = solver.qrt[minor];
                    double factor = 1.0d / (solver.rDiag[minor] * qrtMinor[minor]);
                    int blockSize2 = blockSize;
                    int cBlocks2 = cBlocks;
                    Arrays.fill(alpha, i, kWidth, 0.0d);
                    for (int row = minor; row < m; row++) {
                        double d = qrtMinor[row];
                        double[] yRow = y[row];
                        for (int k = 0; k < kWidth; k++) {
                            alpha[k] = alpha[k] + (yRow[k] * d);
                        }
                    }
                    for (int k2 = 0; k2 < kWidth; k2++) {
                        alpha[k2] = alpha[k2] * factor;
                    }
                    int k3 = minor;
                    while (k3 < m) {
                        double d2 = qrtMinor[k3];
                        double[] yRow2 = y[k3];
                        int row2 = k3;
                        for (int row3 = 0; row3 < kWidth; row3++) {
                            yRow2[row3] = yRow2[row3] + (alpha[row3] * d2);
                        }
                        k3 = row2 + 1;
                    }
                    minor++;
                    blockSize = blockSize2;
                    cBlocks = cBlocks2;
                    i = 0;
                }
                int blockSize3 = blockSize;
                int cBlocks3 = cBlocks;
                int j = solver.rDiag.length - 1;
                while (j >= 0) {
                    int jBlock = j / 52;
                    int jStart = jBlock * 52;
                    double factor2 = 1.0d / solver.rDiag[j];
                    double[] yJ = y[j];
                    double[] xBlock = xBlocks[(jBlock * cBlocks3) + kBlock];
                    int index = (j - jStart) * kWidth;
                    int jBlock2 = 0;
                    while (jBlock2 < kWidth) {
                        yJ[jBlock2] = yJ[jBlock2] * factor2;
                        xBlock[index] = yJ[jBlock2];
                        jBlock2++;
                        index++;
                    }
                    double[] qrtJ = solver.qrt[j];
                    int k4 = 0;
                    while (k4 < j) {
                        double rIJ = qrtJ[k4];
                        double[] yI = y[k4];
                        int i2 = k4;
                        for (int i3 = 0; i3 < kWidth; i3++) {
                            yI[i3] = yI[i3] - (yJ[i3] * rIJ);
                        }
                        k4 = i2 + 1;
                    }
                    j--;
                    solver = this;
                }
                kBlock++;
                solver = this;
                blockSize = blockSize3;
                cBlocks = cBlocks3;
                z = z2;
                i = 0;
            }
            return new BlockRealMatrix(n, columns, xBlocks, false);
        }

        @Override // org.apache.commons.math3.linear.DecompositionSolver
        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.qrt[0].length));
        }
    }
}
