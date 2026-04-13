package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes10.dex */
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
    private static final double TWO_EPS = Precision.EPSILON * 2.0d;
    private double[] beta;
    private final double costRelativeTolerance;
    private double[] diagR;
    private final double initialStepBoundFactor;
    private double[] jacNorm;
    private double[] lmDir;
    private double lmPar;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private int[] permutation;
    private final double qrRankingThreshold;
    private int rank;
    private int solvedCols;
    private double[][] weightedJacobian;
    private double[] weightedResidual;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(100.0d, checker, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, ConvergenceChecker<PointVectorValuePair> checker, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        super(checker);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    public LevenbergMarquardtOptimizer(double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance) {
        this(100.0d, costRelativeTolerance, parRelativeTolerance, orthoTolerance, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        super(null);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x035e, code lost:
    
        r3.setCost(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0361, code lost:
    
        return r20;
     */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.optim.PointVectorValuePair doOptimize() {
        /*
            Method dump skipped, instructions count: 1010
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.vector.jacobian.LevenbergMarquardtOptimizer.doOptimize():org.apache.commons.math3.optim.PointVectorValuePair");
    }

    private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3) {
        double dxNorm;
        double gNorm;
        int countdown;
        double correction;
        double sPar;
        double[] dArr = qy;
        int nC = this.weightedJacobian[0].length;
        for (int j = 0; j < this.rank; j++) {
            this.lmDir[this.permutation[j]] = dArr[j];
        }
        for (int j2 = this.rank; j2 < nC; j2++) {
            this.lmDir[this.permutation[j2]] = 0.0d;
        }
        int j3 = this.rank;
        for (int k = j3 - 1; k >= 0; k--) {
            int pk = this.permutation[k];
            double ypk = this.lmDir[pk] / this.diagR[pk];
            for (int i = 0; i < k; i++) {
                double[] dArr2 = this.lmDir;
                int i2 = this.permutation[i];
                dArr2[i2] = dArr2[i2] - (this.weightedJacobian[i][pk] * ypk);
            }
            this.lmDir[pk] = ypk;
        }
        double dxNorm2 = 0.0d;
        for (int j4 = 0; j4 < this.solvedCols; j4++) {
            int pj = this.permutation[j4];
            double s = diag[pj] * this.lmDir[pj];
            work1[pj] = s;
            dxNorm2 += s * s;
        }
        double dxNorm3 = FastMath.sqrt(dxNorm2);
        double fp = dxNorm3 - delta;
        if (fp <= delta * 0.1d) {
            this.lmPar = 0.0d;
            return;
        }
        double parl = 0.0d;
        if (this.rank == this.solvedCols) {
            for (int j5 = 0; j5 < this.solvedCols; j5++) {
                int pj2 = this.permutation[j5];
                work1[pj2] = work1[pj2] * (diag[pj2] / dxNorm3);
            }
            double sum2 = 0.0d;
            int j6 = 0;
            while (true) {
                dxNorm = dxNorm3;
                if (j6 >= this.solvedCols) {
                    break;
                }
                int pj3 = this.permutation[j6];
                double sum = 0.0d;
                int i3 = 0;
                while (i3 < j6) {
                    sum += this.weightedJacobian[i3][pj3] * work1[this.permutation[i3]];
                    i3++;
                    j6 = j6;
                }
                double s2 = (work1[pj3] - sum) / this.diagR[pj3];
                work1[pj3] = s2;
                sum2 += s2 * s2;
                j6++;
                dxNorm3 = dxNorm;
            }
            parl = fp / (delta * sum2);
        } else {
            dxNorm = dxNorm3;
        }
        double sum22 = 0.0d;
        for (int j7 = 0; j7 < this.solvedCols; j7++) {
            int pj4 = this.permutation[j7];
            double sum3 = 0.0d;
            int i4 = 0;
            while (i4 <= j7) {
                sum3 += this.weightedJacobian[i4][pj4] * dArr[i4];
                i4++;
                sum22 = sum22;
            }
            double sum23 = sum22;
            double sum24 = diag[pj4];
            double sum4 = sum3 / sum24;
            sum22 = sum23 + (sum4 * sum4);
        }
        double gNorm2 = FastMath.sqrt(sum22);
        double paru = gNorm2 / delta;
        if (paru != 0.0d) {
            gNorm = gNorm2;
        } else {
            gNorm = gNorm2;
            paru = Precision.SAFE_MIN / FastMath.min(delta, 0.1d);
        }
        this.lmPar = FastMath.min(paru, FastMath.max(this.lmPar, parl));
        if (this.lmPar == 0.0d) {
            this.lmPar = gNorm / dxNorm;
        }
        int countdown2 = 10;
        double dxNorm4 = dxNorm;
        while (countdown2 >= 0) {
            if (this.lmPar == 0.0d) {
                countdown = countdown2;
                this.lmPar = FastMath.max(Precision.SAFE_MIN, paru * 0.001d);
            } else {
                countdown = countdown2;
            }
            double sPar2 = FastMath.sqrt(this.lmPar);
            for (int j8 = 0; j8 < this.solvedCols; j8++) {
                int pj5 = this.permutation[j8];
                work1[pj5] = diag[pj5] * sPar2;
            }
            determineLMDirection(dArr, work1, work2, work3);
            double dxNorm5 = 0.0d;
            for (int j9 = 0; j9 < this.solvedCols; j9++) {
                int pj6 = this.permutation[j9];
                double s3 = diag[pj6] * this.lmDir[pj6];
                work3[pj6] = s3;
                dxNorm5 += s3 * s3;
            }
            dxNorm4 = FastMath.sqrt(dxNorm5);
            double previousFP = fp;
            fp = dxNorm4 - delta;
            if (FastMath.abs(fp) <= delta * 0.1d) {
                return;
            }
            if (parl == 0.0d && fp <= previousFP && previousFP < 0.0d) {
                return;
            }
            for (int j10 = 0; j10 < this.solvedCols; j10++) {
                int pj7 = this.permutation[j10];
                work1[pj7] = (work3[pj7] * diag[pj7]) / dxNorm4;
            }
            int i5 = 0;
            while (i5 < this.solvedCols) {
                int pj8 = this.permutation[i5];
                work1[pj8] = work1[pj8] / work2[i5];
                double tmp = work1[pj8];
                int j11 = i5;
                int j12 = i5 + 1;
                while (true) {
                    sPar = sPar2;
                    if (j12 < this.solvedCols) {
                        int i6 = this.permutation[j12];
                        work1[i6] = work1[i6] - (this.weightedJacobian[j12][pj8] * tmp);
                        j12++;
                        sPar2 = sPar;
                    }
                }
                i5 = j11 + 1;
                sPar2 = sPar;
            }
            double sum25 = 0.0d;
            for (int j13 = 0; j13 < this.solvedCols; j13++) {
                double s4 = work1[this.permutation[j13]];
                sum25 += s4 * s4;
            }
            double correction2 = fp / (delta * sum25);
            if (fp > 0.0d) {
                correction = correction2;
                parl = FastMath.max(parl, this.lmPar);
            } else {
                correction = correction2;
                if (fp < 0.0d) {
                    paru = FastMath.min(paru, this.lmPar);
                }
            }
            this.lmPar = FastMath.max(parl, this.lmPar + correction);
            countdown2 = countdown - 1;
            dArr = qy;
        }
    }

    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work) {
        int j;
        double cos;
        double sin;
        double sin2;
        double[] dArr = lmDiag;
        for (int j2 = 0; j2 < this.solvedCols; j2++) {
            int pj = this.permutation[j2];
            for (int i = j2 + 1; i < this.solvedCols; i++) {
                this.weightedJacobian[i][pj] = this.weightedJacobian[j2][this.permutation[i]];
            }
            this.lmDir[j2] = this.diagR[pj];
            work[j2] = qy[j2];
        }
        int j3 = 0;
        while (true) {
            double d = 0.0d;
            if (j3 >= this.solvedCols) {
                break;
            }
            int pj2 = this.permutation[j3];
            double dpj = diag[pj2];
            if (dpj != 0.0d) {
                Arrays.fill(dArr, j3 + 1, dArr.length, 0.0d);
            }
            dArr[j3] = dpj;
            double qtbpj = 0.0d;
            int k = j3;
            while (k < this.solvedCols) {
                int pk = this.permutation[k];
                if (dArr[k] == d) {
                    j = j3;
                    cos = d;
                } else {
                    double rkk = this.weightedJacobian[k][pk];
                    if (FastMath.abs(rkk) < FastMath.abs(dArr[k])) {
                        double cotan = rkk / dArr[k];
                        double sin3 = 1.0d / FastMath.sqrt((cotan * cotan) + 1.0d);
                        double cos2 = cotan * sin3;
                        cos = d;
                        sin2 = sin3;
                        sin = cos2;
                    } else {
                        double cos3 = dArr[k];
                        double tan = cos3 / rkk;
                        sin = 1.0d / FastMath.sqrt((tan * tan) + 1.0d);
                        double d2 = sin * tan;
                        cos = d;
                        sin2 = d2;
                    }
                    this.weightedJacobian[k][pk] = (sin * rkk) + (dArr[k] * sin2);
                    double temp = (work[k] * sin) + (sin2 * qtbpj);
                    j = j3;
                    double qtbpj2 = ((-sin2) * work[k]) + (sin * qtbpj);
                    work[k] = temp;
                    int i2 = k + 1;
                    while (i2 < this.solvedCols) {
                        double rik = this.weightedJacobian[i2][pk];
                        double temp2 = (sin * rik) + (lmDiag[i2] * sin2);
                        double qtbpj3 = qtbpj2;
                        double qtbpj4 = -sin2;
                        lmDiag[i2] = (qtbpj4 * rik) + (lmDiag[i2] * sin);
                        this.weightedJacobian[i2][pk] = temp2;
                        i2++;
                        qtbpj2 = qtbpj3;
                    }
                    qtbpj = qtbpj2;
                }
                k++;
                dArr = lmDiag;
                j3 = j;
                d = cos;
            }
            int j4 = j3;
            lmDiag[j4] = this.weightedJacobian[j4][this.permutation[j4]];
            this.weightedJacobian[j4][this.permutation[j4]] = this.lmDir[j4];
            j3 = j4 + 1;
            dArr = lmDiag;
        }
        int nSing = this.solvedCols;
        for (int j5 = 0; j5 < this.solvedCols; j5++) {
            if (lmDiag[j5] == 0.0d && nSing == this.solvedCols) {
                nSing = j5;
            }
            if (nSing < this.solvedCols) {
                work[j5] = 0.0d;
            }
        }
        if (nSing > 0) {
            for (int j6 = nSing - 1; j6 >= 0; j6--) {
                int pj3 = this.permutation[j6];
                double sum = 0.0d;
                for (int i3 = j6 + 1; i3 < nSing; i3++) {
                    sum += this.weightedJacobian[i3][pj3] * work[i3];
                }
                work[j6] = (work[j6] - sum) / lmDiag[j6];
            }
        }
        for (int j7 = 0; j7 < this.lmDir.length; j7++) {
            this.lmDir[this.permutation[j7]] = work[j7];
        }
    }

    private void qrDecomposition(RealMatrix jacobian) throws ConvergenceException {
        this.weightedJacobian = jacobian.scalarMultiply(-1.0d).getData();
        int nR = this.weightedJacobian.length;
        int nC = this.weightedJacobian[0].length;
        for (int k = 0; k < nC; k++) {
            this.permutation[k] = k;
            double norm2 = 0.0d;
            for (int i = 0; i < nR; i++) {
                double akk = this.weightedJacobian[i][k];
                norm2 += akk * akk;
            }
            this.jacNorm[k] = FastMath.sqrt(norm2);
        }
        for (int k2 = 0; k2 < nC; k2++) {
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i2 = k2; i2 < nC; i2++) {
                double norm22 = 0.0d;
                for (int j = k2; j < nR; j++) {
                    double aki = this.weightedJacobian[j][this.permutation[i2]];
                    norm22 += aki * aki;
                }
                if (!Double.isInfinite(norm22) && !Double.isNaN(norm22)) {
                    if (norm22 > ak2) {
                        nextColumn = i2;
                        ak2 = norm22;
                    }
                } else {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, Integer.valueOf(nR), Integer.valueOf(nC));
                }
            }
            if (ak2 <= this.qrRankingThreshold) {
                this.rank = k2;
                return;
            }
            int pk = this.permutation[nextColumn];
            this.permutation[nextColumn] = this.permutation[k2];
            this.permutation[k2] = pk;
            double akk2 = this.weightedJacobian[k2][pk];
            double alpha = FastMath.sqrt(ak2);
            if (akk2 > 0.0d) {
                alpha = -alpha;
            }
            double betak = 1.0d / (ak2 - (akk2 * alpha));
            this.beta[pk] = betak;
            this.diagR[pk] = alpha;
            double[] dArr = this.weightedJacobian[k2];
            dArr[pk] = dArr[pk] - alpha;
            int dk = (nC - 1) - k2;
            while (dk > 0) {
                double gamma = 0.0d;
                int nC2 = nC;
                int nC3 = k2;
                while (nC3 < nR) {
                    int j2 = nC3;
                    gamma += this.weightedJacobian[j2][pk] * this.weightedJacobian[j2][this.permutation[k2 + dk]];
                    nC3 = j2 + 1;
                }
                double gamma2 = gamma * betak;
                int j3 = k2;
                while (j3 < nR) {
                    int nR2 = nR;
                    double[] dArr2 = this.weightedJacobian[j3];
                    int i3 = this.permutation[k2 + dk];
                    dArr2[i3] = dArr2[i3] - (this.weightedJacobian[j3][pk] * gamma2);
                    j3++;
                    nR = nR2;
                }
                dk--;
                nC = nC2;
            }
        }
        this.rank = this.solvedCols;
    }

    private void qTy(double[] y) {
        int nR = this.weightedJacobian.length;
        int nC = this.weightedJacobian[0].length;
        for (int k = 0; k < nC; k++) {
            int pk = this.permutation[k];
            double gamma = 0.0d;
            for (int i = k; i < nR; i++) {
                gamma += this.weightedJacobian[i][pk] * y[i];
            }
            double gamma2 = gamma * this.beta[pk];
            for (int i2 = k; i2 < nR; i2++) {
                y[i2] = y[i2] - (this.weightedJacobian[i2][pk] * gamma2);
            }
        }
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
