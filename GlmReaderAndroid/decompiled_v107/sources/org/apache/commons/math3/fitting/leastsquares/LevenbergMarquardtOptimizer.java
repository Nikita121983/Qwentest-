package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class LevenbergMarquardtOptimizer implements LeastSquaresOptimizer {
    private static final double TWO_EPS = Precision.EPSILON * 2.0d;
    private final double costRelativeTolerance;
    private final double initialStepBoundFactor;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private final double qrRankingThreshold;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double qrRankingThreshold) {
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = qrRankingThreshold;
    }

    public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double newInitialStepBoundFactor) {
        return new LevenbergMarquardtOptimizer(newInitialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withCostRelativeTolerance(double newCostRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, newCostRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double newParRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, newParRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withOrthoTolerance(double newOrthoTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, newOrthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withRankingThreshold(double newQRRankingThreshold) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, newQRRankingThreshold);
    }

    public double getInitialStepBoundFactor() {
        return this.initialStepBoundFactor;
    }

    public double getCostRelativeTolerance() {
        return this.costRelativeTolerance;
    }

    public double getParameterRelativeTolerance() {
        return this.parRelativeTolerance;
    }

    public double getOrthoTolerance() {
        return this.orthoTolerance;
    }

    public double getRankingThreshold() {
        return this.qrRankingThreshold;
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x0333, code lost:
    
        return new org.apache.commons.math3.fitting.leastsquares.OptimumImpl(r2, r17.getCount(), r16.getCount());
     */
    @Override // org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem r74) {
        /*
            Method dump skipped, instructions count: 973
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem):org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer$Optimum");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class InternalData {
        private final double[] beta;
        private final double[] diagR;
        private final double[] jacNorm;
        private final int[] permutation;
        private final int rank;
        private final double[][] weightedJacobian;

        InternalData(double[][] weightedJacobian, int[] permutation, int rank, double[] diagR, double[] jacNorm, double[] beta) {
            this.weightedJacobian = weightedJacobian;
            this.permutation = permutation;
            this.rank = rank;
            this.diagR = diagR;
            this.jacNorm = jacNorm;
            this.beta = beta;
        }
    }

    private double determineLMParameter(double[] qy, double delta, double[] diag, InternalData internalData, int solvedCols, double[] work1, double[] work2, double[] work3, double[] lmDir, double lmPar) {
        double d;
        double d2;
        double parl;
        double dxNorm;
        double[][] weightedJacobian = internalData.weightedJacobian;
        int[] permutation = internalData.permutation;
        int rank = internalData.rank;
        double[] diagR = internalData.diagR;
        int nC = weightedJacobian[0].length;
        for (int j = 0; j < rank; j++) {
            lmDir[permutation[j]] = qy[j];
        }
        int j2 = rank;
        while (true) {
            d = 0.0d;
            if (j2 >= nC) {
                break;
            }
            lmDir[permutation[j2]] = 0.0d;
            j2++;
        }
        for (int k = rank - 1; k >= 0; k--) {
            int pk = permutation[k];
            double ypk = lmDir[pk] / diagR[pk];
            for (int i = 0; i < k; i++) {
                int i2 = permutation[i];
                lmDir[i2] = lmDir[i2] - (weightedJacobian[i][pk] * ypk);
            }
            lmDir[pk] = ypk;
        }
        double dxNorm2 = 0.0d;
        for (int j3 = 0; j3 < solvedCols; j3++) {
            int pj = permutation[j3];
            double s = diag[pj] * lmDir[pj];
            work1[pj] = s;
            dxNorm2 += s * s;
        }
        double dxNorm3 = FastMath.sqrt(dxNorm2);
        double fp = dxNorm3 - delta;
        if (fp <= delta * 0.1d) {
            return 0.0d;
        }
        if (rank != solvedCols) {
            d2 = 0.0d;
            parl = 0.0d;
        } else {
            for (int j4 = 0; j4 < solvedCols; j4++) {
                int pj2 = permutation[j4];
                work1[pj2] = work1[pj2] * (diag[pj2] / dxNorm3);
            }
            double sum2 = 0.0d;
            int j5 = 0;
            while (j5 < solvedCols) {
                int pj3 = permutation[j5];
                double sum = 0.0d;
                double d3 = d;
                for (int i3 = 0; i3 < j5; i3++) {
                    sum += weightedJacobian[i3][pj3] * work1[permutation[i3]];
                }
                double s2 = (work1[pj3] - sum) / diagR[pj3];
                work1[pj3] = s2;
                sum2 += s2 * s2;
                j5++;
                d = d3;
            }
            d2 = d;
            double parl2 = fp / (delta * sum2);
            parl = parl2;
        }
        double sum22 = 0.0d;
        for (int j6 = 0; j6 < solvedCols; j6++) {
            int pj4 = permutation[j6];
            double sum3 = 0.0d;
            for (int i4 = 0; i4 <= j6; i4++) {
                sum3 += weightedJacobian[i4][pj4] * qy[i4];
            }
            double sum4 = sum3 / diag[pj4];
            sum22 += sum4 * sum4;
        }
        double gNorm = FastMath.sqrt(sum22);
        double paru = gNorm / delta;
        if (paru == d2) {
            dxNorm = dxNorm3;
            paru = Precision.SAFE_MIN / FastMath.min(delta, 0.1d);
        } else {
            dxNorm = dxNorm3;
        }
        double fp2 = fp;
        double parl3 = parl;
        double lmPar2 = FastMath.min(paru, FastMath.max(lmPar, parl3));
        if (lmPar2 == d2) {
            lmPar2 = gNorm / dxNorm;
        }
        double parl4 = parl3;
        int countdown = 10;
        while (countdown >= 0) {
            if (lmPar2 == d2) {
                lmPar2 = FastMath.max(Precision.SAFE_MIN, 0.001d * paru);
            }
            double sPar = FastMath.sqrt(lmPar2);
            for (int j7 = 0; j7 < solvedCols; j7++) {
                int pj5 = permutation[j7];
                work1[pj5] = diag[pj5] * sPar;
            }
            double[][] weightedJacobian2 = weightedJacobian;
            int[] permutation2 = permutation;
            int rank2 = rank;
            double[] diagR2 = diagR;
            double parl5 = parl4;
            double paru2 = paru;
            determineLMDirection(qy, work1, work2, internalData, solvedCols, work3, lmDir);
            double dxNorm4 = 0.0d;
            for (int j8 = 0; j8 < solvedCols; j8++) {
                int pj6 = permutation2[j8];
                double s3 = diag[pj6] * lmDir[pj6];
                work3[pj6] = s3;
                dxNorm4 += s3 * s3;
            }
            double dxNorm5 = FastMath.sqrt(dxNorm4);
            double dxNorm6 = fp2;
            fp2 = dxNorm5 - delta;
            if (FastMath.abs(fp2) <= delta * 0.1d || (parl5 == d2 && fp2 <= dxNorm6 && dxNorm6 < d2)) {
                return lmPar2;
            }
            for (int j9 = 0; j9 < solvedCols; j9++) {
                int pj7 = permutation2[j9];
                work1[pj7] = (work3[pj7] * diag[pj7]) / dxNorm5;
            }
            for (int j10 = 0; j10 < solvedCols; j10++) {
                int pj8 = permutation2[j10];
                work1[pj8] = work1[pj8] / work2[j10];
                double tmp = work1[pj8];
                for (int i5 = j10 + 1; i5 < solvedCols; i5++) {
                    int i6 = permutation2[i5];
                    work1[i6] = work1[i6] - (weightedJacobian2[i5][pj8] * tmp);
                }
            }
            double sum23 = 0.0d;
            for (int j11 = 0; j11 < solvedCols; j11++) {
                double s4 = work1[permutation2[j11]];
                sum23 += s4 * s4;
            }
            double correction = fp2 / (delta * sum23);
            if (fp2 > d2) {
                parl5 = FastMath.max(parl5, lmPar2);
                paru = paru2;
            } else if (fp2 >= d2) {
                paru = paru2;
            } else {
                paru = FastMath.min(paru2, lmPar2);
            }
            lmPar2 = FastMath.max(parl5, lmPar2 + correction);
            countdown--;
            parl4 = parl5;
            weightedJacobian = weightedJacobian2;
            permutation = permutation2;
            rank = rank2;
            diagR = diagR2;
        }
        return lmPar2;
    }

    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, InternalData internalData, int solvedCols, double[] work, double[] lmDir) {
        int[] permutation;
        double[][] weightedJacobian;
        double cos;
        double sin;
        double sin2;
        int[] permutation2 = internalData.permutation;
        double[][] weightedJacobian2 = internalData.weightedJacobian;
        double[] diagR = internalData.diagR;
        for (int j = 0; j < solvedCols; j++) {
            int pj = permutation2[j];
            for (int i = j + 1; i < solvedCols; i++) {
                weightedJacobian2[i][pj] = weightedJacobian2[j][permutation2[i]];
            }
            lmDir[j] = diagR[pj];
            work[j] = qy[j];
        }
        int j2 = 0;
        while (true) {
            double d = 0.0d;
            if (j2 >= solvedCols) {
                break;
            }
            int pj2 = permutation2[j2];
            double dpj = diag[pj2];
            if (dpj != 0.0d) {
                Arrays.fill(lmDiag, j2 + 1, lmDiag.length, 0.0d);
            }
            lmDiag[j2] = dpj;
            double qtbpj = 0.0d;
            int k = j2;
            while (k < solvedCols) {
                int pk = permutation2[k];
                if (lmDiag[k] == d) {
                    permutation = permutation2;
                    weightedJacobian = weightedJacobian2;
                    cos = d;
                } else {
                    double rkk = weightedJacobian2[k][pk];
                    if (FastMath.abs(rkk) < FastMath.abs(lmDiag[k])) {
                        double cotan = rkk / lmDiag[k];
                        double sin3 = 1.0d / FastMath.sqrt((cotan * cotan) + 1.0d);
                        double cos2 = cotan * sin3;
                        cos = d;
                        sin2 = sin3;
                        sin = cos2;
                    } else {
                        double cos3 = lmDiag[k];
                        double tan = cos3 / rkk;
                        sin = 1.0d / FastMath.sqrt((tan * tan) + 1.0d);
                        double d2 = sin * tan;
                        cos = d;
                        sin2 = d2;
                    }
                    weightedJacobian2[k][pk] = (sin * rkk) + (lmDiag[k] * sin2);
                    double temp = (work[k] * sin) + (sin2 * qtbpj);
                    permutation = permutation2;
                    weightedJacobian = weightedJacobian2;
                    double qtbpj2 = ((-sin2) * work[k]) + (sin * qtbpj);
                    work[k] = temp;
                    int i2 = k + 1;
                    while (i2 < solvedCols) {
                        double rik = weightedJacobian[i2][pk];
                        double temp2 = (sin * rik) + (lmDiag[i2] * sin2);
                        double qtbpj3 = qtbpj2;
                        double qtbpj4 = -sin2;
                        lmDiag[i2] = (qtbpj4 * rik) + (lmDiag[i2] * sin);
                        weightedJacobian[i2][pk] = temp2;
                        i2++;
                        qtbpj2 = qtbpj3;
                    }
                    qtbpj = qtbpj2;
                }
                k++;
                permutation2 = permutation;
                d = cos;
                weightedJacobian2 = weightedJacobian;
            }
            int[] permutation3 = permutation2;
            double[][] weightedJacobian3 = weightedJacobian2;
            lmDiag[j2] = weightedJacobian3[j2][permutation3[j2]];
            weightedJacobian3[j2][permutation3[j2]] = lmDir[j2];
            j2++;
            permutation2 = permutation3;
            weightedJacobian2 = weightedJacobian3;
        }
        int[] permutation4 = permutation2;
        double[][] weightedJacobian4 = weightedJacobian2;
        int nSing = solvedCols;
        for (int j3 = 0; j3 < solvedCols; j3++) {
            if (lmDiag[j3] == 0.0d && nSing == solvedCols) {
                nSing = j3;
            }
            if (nSing < solvedCols) {
                work[j3] = 0.0d;
            }
        }
        if (nSing > 0) {
            for (int j4 = nSing - 1; j4 >= 0; j4--) {
                int pj3 = permutation4[j4];
                double sum = 0.0d;
                for (int i3 = j4 + 1; i3 < nSing; i3++) {
                    sum += weightedJacobian4[i3][pj3] * work[i3];
                }
                work[j4] = (work[j4] - sum) / lmDiag[j4];
            }
        }
        for (int j5 = 0; j5 < lmDir.length; j5++) {
            lmDir[permutation4[j5]] = work[j5];
        }
    }

    private InternalData qrDecomposition(RealMatrix jacobian, int solvedCols) throws ConvergenceException {
        int pk;
        double alpha;
        double[][] weightedJacobian = jacobian.scalarMultiply(-1.0d).getData();
        int nR = weightedJacobian.length;
        int nC = weightedJacobian[0].length;
        int[] permutation = new int[nC];
        double[] diagR = new double[nC];
        double[] jacNorm = new double[nC];
        double[] beta = new double[nC];
        for (int k = 0; k < nC; k++) {
            permutation[k] = k;
            double norm2 = 0.0d;
            for (double[] dArr : weightedJacobian) {
                double akk = dArr[k];
                norm2 += akk * akk;
            }
            jacNorm[k] = FastMath.sqrt(norm2);
        }
        int k2 = 0;
        while (k2 < nC) {
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int nextColumn2 = k2; nextColumn2 < nC; nextColumn2++) {
                double norm22 = 0.0d;
                for (int j = k2; j < nR; j++) {
                    double aki = weightedJacobian[j][permutation[nextColumn2]];
                    norm22 += aki * aki;
                }
                if (!Double.isInfinite(norm22) && !Double.isNaN(norm22)) {
                    if (norm22 > ak2) {
                        nextColumn = nextColumn2;
                        ak2 = norm22;
                    }
                } else {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, Integer.valueOf(nR), Integer.valueOf(nC));
                }
            }
            int nC2 = nC;
            if (ak2 <= this.qrRankingThreshold) {
                return new InternalData(weightedJacobian, permutation, k2, diagR, jacNorm, beta);
            }
            int pk2 = permutation[nextColumn];
            permutation[nextColumn] = permutation[k2];
            permutation[k2] = pk2;
            double akk2 = weightedJacobian[k2][pk2];
            if (akk2 > 0.0d) {
                pk = pk2;
                alpha = -FastMath.sqrt(ak2);
            } else {
                pk = pk2;
                alpha = FastMath.sqrt(ak2);
            }
            double betak = 1.0d / (ak2 - (akk2 * alpha));
            beta[pk] = betak;
            diagR[pk] = alpha;
            double[] dArr2 = weightedJacobian[k2];
            dArr2[pk] = dArr2[pk] - alpha;
            int dk = (nC2 - 1) - k2;
            while (dk > 0) {
                double gamma = 0.0d;
                double alpha2 = alpha;
                for (int j2 = k2; j2 < nR; j2++) {
                    gamma += weightedJacobian[j2][pk] * weightedJacobian[j2][permutation[k2 + dk]];
                }
                double gamma2 = gamma * betak;
                for (int j3 = k2; j3 < nR; j3++) {
                    double[] dArr3 = weightedJacobian[j3];
                    int i = permutation[k2 + dk];
                    dArr3[i] = dArr3[i] - (weightedJacobian[j3][pk] * gamma2);
                }
                dk--;
                alpha = alpha2;
            }
            k2++;
            nC = nC2;
        }
        return new InternalData(weightedJacobian, permutation, solvedCols, diagR, jacNorm, beta);
    }

    private void qTy(double[] y, InternalData internalData) {
        double[][] weightedJacobian = internalData.weightedJacobian;
        int[] permutation = internalData.permutation;
        double[] beta = internalData.beta;
        int nR = weightedJacobian.length;
        int nC = weightedJacobian[0].length;
        for (int k = 0; k < nC; k++) {
            int pk = permutation[k];
            double gamma = 0.0d;
            for (int i = k; i < nR; i++) {
                gamma += weightedJacobian[i][pk] * y[i];
            }
            double gamma2 = gamma * beta[pk];
            for (int i2 = k; i2 < nR; i2++) {
                y[i2] = y[i2] - (weightedJacobian[i2][pk] * gamma2);
            }
        }
    }
}
