package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes10.dex */
public class BOBYQAOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public static final double DEFAULT_INITIAL_RADIUS = 10.0d;
    public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8d;
    private static final double HALF = 0.5d;
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;
    private static final double MINUS_ONE = -1.0d;
    private static final double ONE = 1.0d;
    private static final double ONE_OVER_A_THOUSAND = 0.001d;
    private static final double ONE_OVER_EIGHT = 0.125d;
    private static final double ONE_OVER_FOUR = 0.25d;
    private static final double ONE_OVER_TEN = 0.1d;
    private static final double SIXTEEN = 16.0d;
    private static final double TEN = 10.0d;
    private static final double TWO = 2.0d;
    private static final double TWO_HUNDRED_FIFTY = 250.0d;
    private static final double ZERO = 0.0d;
    private ArrayRealVector alternativeNewPoint;
    private Array2DRowRealMatrix bMatrix;
    private double[] boundDifference;
    private ArrayRealVector currentBest;
    private ArrayRealVector fAtInterpolationPoints;
    private ArrayRealVector gradientAtTrustRegionCenter;
    private double initialTrustRegionRadius;
    private Array2DRowRealMatrix interpolationPoints;
    private boolean isMinimize;
    private ArrayRealVector lagrangeValuesAtNewPoint;
    private ArrayRealVector lowerDifference;
    private ArrayRealVector modelSecondDerivativesParameters;
    private ArrayRealVector modelSecondDerivativesValues;
    private ArrayRealVector newPoint;
    private final int numberOfInterpolationPoints;
    private ArrayRealVector originShift;
    private final double stoppingTrustRegionRadius;
    private ArrayRealVector trialStepPoint;
    private int trustRegionCenterInterpolationPointIndex;
    private ArrayRealVector trustRegionCenterOffset;
    private ArrayRealVector upperDifference;
    private Array2DRowRealMatrix zMatrix;

    public BOBYQAOptimizer(int numberOfInterpolationPoints) {
        this(numberOfInterpolationPoints, 10.0d, 1.0E-8d);
    }

    public BOBYQAOptimizer(int numberOfInterpolationPoints, double initialTrustRegionRadius, double stoppingTrustRegionRadius) {
        super(null);
        this.numberOfInterpolationPoints = numberOfInterpolationPoints;
        this.initialTrustRegionRadius = initialTrustRegionRadius;
        this.stoppingTrustRegionRadius = stoppingTrustRegionRadius;
    }

    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    protected PointValuePair doOptimize() {
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        setup(lowerBound, upperBound);
        this.isMinimize = getGoalType() == GoalType.MINIMIZE;
        this.currentBest = new ArrayRealVector(getStartPoint());
        double value = bobyqa(lowerBound, upperBound);
        return new PointValuePair(this.currentBest.getDataRef(), this.isMinimize ? value : -value);
    }

    private double bobyqa(double[] lowerBound, double[] upperBound) {
        printMethod();
        int n = this.currentBest.getDimension();
        for (int j = 0; j < n; j++) {
            double boundDiff = this.boundDifference[j];
            this.lowerDifference.setEntry(j, lowerBound[j] - this.currentBest.getEntry(j));
            this.upperDifference.setEntry(j, upperBound[j] - this.currentBest.getEntry(j));
            if (this.lowerDifference.getEntry(j) >= (-this.initialTrustRegionRadius)) {
                if (this.lowerDifference.getEntry(j) >= 0.0d) {
                    this.currentBest.setEntry(j, lowerBound[j]);
                    this.lowerDifference.setEntry(j, 0.0d);
                    this.upperDifference.setEntry(j, boundDiff);
                } else {
                    this.currentBest.setEntry(j, lowerBound[j] + this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(j, -this.initialTrustRegionRadius);
                    double deltaOne = upperBound[j] - this.currentBest.getEntry(j);
                    this.upperDifference.setEntry(j, FastMath.max(deltaOne, this.initialTrustRegionRadius));
                }
            } else if (this.upperDifference.getEntry(j) <= this.initialTrustRegionRadius) {
                if (this.upperDifference.getEntry(j) <= 0.0d) {
                    this.currentBest.setEntry(j, upperBound[j]);
                    this.lowerDifference.setEntry(j, -boundDiff);
                    this.upperDifference.setEntry(j, 0.0d);
                } else {
                    this.currentBest.setEntry(j, upperBound[j] - this.initialTrustRegionRadius);
                    double deltaOne2 = lowerBound[j] - this.currentBest.getEntry(j);
                    double deltaTwo = -this.initialTrustRegionRadius;
                    this.lowerDifference.setEntry(j, FastMath.min(deltaOne2, deltaTwo));
                    this.upperDifference.setEntry(j, this.initialTrustRegionRadius);
                }
            }
        }
        return bobyqb(lowerBound, upperBound);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x00c9. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x1011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0ff9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0ff3  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0c8b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0d8b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0ad2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0a8e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x09c3  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x09e9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0a30 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x041c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x042c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0da7  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x030b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double bobyqb(double[] r106, double[] r107) {
        /*
            Method dump skipped, instructions count: 4176
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb(double[], double[]):double");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0595 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r39v0 */
    /* JADX WARN: Type inference failed for: r39v1 */
    /* JADX WARN: Type inference failed for: r39v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double[] altmov(int r65, double r66) {
        /*
            Method dump skipped, instructions count: 1460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov(int, double):double[]");
    }

    private void prelim(double[] lowerBound, double[] upperBound) {
        double d;
        int ipt;
        int np;
        int nfx;
        double fbeg;
        double stepa;
        double stepb;
        int jpt;
        char c;
        int ipt2;
        double stepa2;
        int jpt2;
        printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        int ndim = this.bMatrix.getRowDimension();
        double rhosq = this.initialTrustRegionRadius * this.initialTrustRegionRadius;
        double d2 = 1.0d;
        double recip = 1.0d / rhosq;
        int jpt3 = n + 1;
        int j = 0;
        while (true) {
            d = 0.0d;
            if (j >= n) {
                break;
            }
            double d3 = d2;
            this.originShift.setEntry(j, this.currentBest.getEntry(j));
            for (int k = 0; k < npt; k++) {
                this.interpolationPoints.setEntry(k, j, 0.0d);
            }
            for (int i = 0; i < ndim; i++) {
                this.bMatrix.setEntry(i, j, 0.0d);
            }
            j++;
            d2 = d3;
        }
        double d4 = d2;
        int max = (n * jpt3) / 2;
        for (int i2 = 0; i2 < max; i2++) {
            this.modelSecondDerivativesValues.setEntry(i2, 0.0d);
        }
        for (int k2 = 0; k2 < npt; k2++) {
            this.modelSecondDerivativesParameters.setEntry(k2, 0.0d);
            int max2 = npt - jpt3;
            for (int j2 = 0; j2 < max2; j2++) {
                this.zMatrix.setEntry(k2, j2, 0.0d);
            }
        }
        int ipt3 = 0;
        int iptMinus1 = 0;
        double fbeg2 = Double.NaN;
        while (true) {
            double d5 = d;
            int nfm = getEvaluations();
            int nfx2 = nfm - n;
            int nfmm = nfm - 1;
            int ndim2 = ndim;
            int ndim3 = nfx2 - 1;
            double rhosq2 = rhosq;
            if (nfm <= n * 2) {
                if (nfm < 1 || nfm > n) {
                    int ipt4 = ipt3;
                    if (nfm <= n) {
                        nfx = nfx2;
                        fbeg = fbeg2;
                        stepa = 0.0d;
                        stepb = 0.0d;
                        ipt = ipt4;
                        np = jpt3;
                    } else {
                        double stepa3 = this.interpolationPoints.getEntry(nfx2, ndim3);
                        double stepb2 = -this.initialTrustRegionRadius;
                        if (this.lowerDifference.getEntry(ndim3) == d5) {
                            double stepb3 = this.initialTrustRegionRadius;
                            jpt2 = iptMinus1;
                            stepb2 = FastMath.min(stepb3 * TWO, this.upperDifference.getEntry(ndim3));
                        } else {
                            jpt2 = iptMinus1;
                        }
                        if (this.upperDifference.getEntry(ndim3) == d5) {
                            stepb2 = FastMath.max(this.initialTrustRegionRadius * (-2.0d), this.lowerDifference.getEntry(ndim3));
                        }
                        this.interpolationPoints.setEntry(nfm, ndim3, stepb2);
                        nfx = nfx2;
                        fbeg = fbeg2;
                        ipt = ipt4;
                        iptMinus1 = jpt2;
                        stepb = stepb2;
                        np = jpt3;
                        stepa = stepa3;
                    }
                } else {
                    int ipt5 = ipt3;
                    double stepa4 = this.initialTrustRegionRadius;
                    if (this.upperDifference.getEntry(nfmm) != d5) {
                        stepa = stepa4;
                    } else {
                        stepa = -stepa4;
                    }
                    this.interpolationPoints.setEntry(nfm, nfmm, stepa);
                    np = jpt3;
                    nfx = nfx2;
                    fbeg = fbeg2;
                    stepb = 0.0d;
                    ipt = ipt5;
                }
            } else {
                int tmp1 = (nfm - jpt3) / n;
                int jpt4 = (nfm - (tmp1 * n)) - n;
                ipt = jpt4 + tmp1;
                if (ipt > n) {
                    jpt4 = ipt - n;
                    ipt = jpt4;
                }
                int tmp2 = ipt - 1;
                int tmp12 = jpt4 - 1;
                np = jpt3;
                nfx = nfx2;
                fbeg = fbeg2;
                this.interpolationPoints.setEntry(nfm, tmp2, this.interpolationPoints.getEntry(ipt, tmp2));
                this.interpolationPoints.setEntry(nfm, tmp12, this.interpolationPoints.getEntry(jpt4, tmp12));
                iptMinus1 = jpt4;
                stepa = 0.0d;
                stepb = 0.0d;
            }
            int j3 = 0;
            while (j3 < n) {
                ArrayRealVector arrayRealVector = this.currentBest;
                int ipt6 = ipt;
                int jpt5 = iptMinus1;
                double d6 = lowerBound[j3];
                int n2 = n;
                double recip2 = recip;
                double recip3 = this.originShift.getEntry(j3) + this.interpolationPoints.getEntry(nfm, j3);
                arrayRealVector.setEntry(j3, FastMath.min(FastMath.max(d6, recip3), upperBound[j3]));
                if (this.interpolationPoints.getEntry(nfm, j3) == this.lowerDifference.getEntry(j3)) {
                    this.currentBest.setEntry(j3, lowerBound[j3]);
                }
                if (this.interpolationPoints.getEntry(nfm, j3) == this.upperDifference.getEntry(j3)) {
                    this.currentBest.setEntry(j3, upperBound[j3]);
                }
                j3++;
                ipt = ipt6;
                iptMinus1 = jpt5;
                n = n2;
                recip = recip2;
            }
            int n3 = n;
            int ipt7 = ipt;
            int jpt6 = iptMinus1;
            double recip4 = recip;
            double objectiveValue = computeObjectiveValue(this.currentBest.toArray());
            double f = this.isMinimize ? objectiveValue : -objectiveValue;
            int numEval = getEvaluations();
            this.fAtInterpolationPoints.setEntry(nfm, f);
            if (numEval == 1) {
                fbeg = f;
                this.trustRegionCenterInterpolationPointIndex = 0;
            } else if (f < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
                this.trustRegionCenterInterpolationPointIndex = nfm;
            }
            if (numEval > (n3 * 2) + 1) {
                this.zMatrix.setEntry(0, ndim3, recip4);
                this.zMatrix.setEntry(nfm, ndim3, recip4);
                this.zMatrix.setEntry(ipt7, ndim3, -recip4);
                jpt = jpt6;
                this.zMatrix.setEntry(jpt, ndim3, -recip4);
                c = 2;
                int ih = ((((ipt7 - 1) * ipt7) / 2) + jpt) - 1;
                ipt2 = ipt7;
                this.modelSecondDerivativesValues.setEntry(ih, (((fbeg - this.fAtInterpolationPoints.getEntry(ipt7)) - this.fAtInterpolationPoints.getEntry(jpt)) + f) / (this.interpolationPoints.getEntry(nfm, ipt7 - 1) * this.interpolationPoints.getEntry(nfm, jpt - 1)));
            } else if (numEval < 2 || numEval > n3 + 1) {
                double stepa5 = stepa;
                if (numEval >= n3 + 2) {
                    int ih2 = (((nfx + 1) * nfx) / 2) - 1;
                    double tmp = (f - fbeg) / stepb;
                    double diff = stepb - stepa5;
                    this.modelSecondDerivativesValues.setEntry(ih2, ((tmp - this.gradientAtTrustRegionCenter.getEntry(ndim3)) * TWO) / diff);
                    this.gradientAtTrustRegionCenter.setEntry(ndim3, ((this.gradientAtTrustRegionCenter.getEntry(ndim3) * stepb) - (tmp * stepa5)) / diff);
                    if (stepa5 * stepb < d5 && f < this.fAtInterpolationPoints.getEntry(nfm - n3)) {
                        this.fAtInterpolationPoints.setEntry(nfm, this.fAtInterpolationPoints.getEntry(nfm - n3));
                        this.fAtInterpolationPoints.setEntry(nfm - n3, f);
                        if (this.trustRegionCenterInterpolationPointIndex == nfm) {
                            this.trustRegionCenterInterpolationPointIndex = nfm - n3;
                        }
                        this.interpolationPoints.setEntry(nfm - n3, ndim3, stepb);
                        stepa2 = stepa5;
                        this.interpolationPoints.setEntry(nfm, ndim3, stepa2);
                    } else {
                        stepa2 = stepa5;
                    }
                    double stepa6 = stepa2;
                    this.bMatrix.setEntry(0, ndim3, (-(stepa6 + stepb)) / (stepa6 * stepb));
                    this.bMatrix.setEntry(nfm, ndim3, (-0.5d) / this.interpolationPoints.getEntry(nfm - n3, ndim3));
                    this.bMatrix.setEntry(nfm - n3, ndim3, (-this.bMatrix.getEntry(0, ndim3)) - this.bMatrix.getEntry(nfm, ndim3));
                    this.zMatrix.setEntry(0, ndim3, FastMath.sqrt(TWO) / (stepa6 * stepb));
                    this.zMatrix.setEntry(nfm, ndim3, FastMath.sqrt(HALF) / rhosq2);
                    this.zMatrix.setEntry(nfm - n3, ndim3, (-this.zMatrix.getEntry(0, ndim3)) - this.zMatrix.getEntry(nfm, ndim3));
                    ipt2 = ipt7;
                    jpt = jpt6;
                    c = 2;
                } else {
                    ipt2 = ipt7;
                    jpt = jpt6;
                    c = 2;
                }
            } else {
                this.gradientAtTrustRegionCenter.setEntry(nfmm, (f - fbeg) / stepa);
                if (npt < numEval + n3) {
                    double oneOverStepA = d4 / stepa;
                    double stepa7 = -oneOverStepA;
                    this.bMatrix.setEntry(0, nfmm, stepa7);
                    this.bMatrix.setEntry(nfm, nfmm, oneOverStepA);
                    this.bMatrix.setEntry(npt + nfmm, nfmm, rhosq2 * (-0.5d));
                    ipt2 = ipt7;
                    jpt = jpt6;
                    c = 2;
                } else {
                    ipt2 = ipt7;
                    jpt = jpt6;
                    c = 2;
                }
            }
            if (getEvaluations() < npt) {
                ipt3 = ipt2;
                iptMinus1 = jpt;
                d = d5;
                ndim = ndim2;
                n = n3;
                rhosq = rhosq2;
                jpt3 = np;
                fbeg2 = fbeg;
                recip = recip4;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x00fc. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:164:0x07e5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x07d5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x086c  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x08c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:309:0x08ae A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double[] trsbox(double r97, org.apache.commons.math3.linear.ArrayRealVector r99, org.apache.commons.math3.linear.ArrayRealVector r100, org.apache.commons.math3.linear.ArrayRealVector r101, org.apache.commons.math3.linear.ArrayRealVector r102, org.apache.commons.math3.linear.ArrayRealVector r103) {
        /*
            Method dump skipped, instructions count: 2338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox(double, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector):double[]");
    }

    private void update(double beta, double denom, int knew) {
        int nptm;
        double ztest;
        int i = knew;
        printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        int nptm2 = (npt - n) - 1;
        ArrayRealVector work = new ArrayRealVector(npt + n);
        double ztest2 = 0.0d;
        for (int k = 0; k < npt; k++) {
            for (int j = 0; j < nptm2; j++) {
                ztest2 = FastMath.max(ztest2, FastMath.abs(this.zMatrix.getEntry(k, j)));
            }
        }
        double ztest3 = ztest2 * 1.0E-20d;
        int j2 = 1;
        while (true) {
            int i2 = 0;
            if (j2 >= nptm2) {
                break;
            }
            double d1 = this.zMatrix.getEntry(i, j2);
            if (FastMath.abs(d1) <= ztest3) {
                nptm = nptm2;
                ztest = ztest3;
            } else {
                double d2 = this.zMatrix.getEntry(i, 0);
                double d3 = this.zMatrix.getEntry(i, j2);
                double d4 = FastMath.sqrt((d2 * d2) + (d3 * d3));
                nptm = nptm2;
                double d5 = this.zMatrix.getEntry(i, 0) / d4;
                double d6 = this.zMatrix.getEntry(i, j2) / d4;
                int i3 = 0;
                while (i3 < npt) {
                    double ztest4 = ztest3;
                    double d7 = (this.zMatrix.getEntry(i3, i2) * d5) + (this.zMatrix.getEntry(i3, j2) * d6);
                    this.zMatrix.setEntry(i3, j2, (this.zMatrix.getEntry(i3, j2) * d5) - (this.zMatrix.getEntry(i3, 0) * d6));
                    this.zMatrix.setEntry(i3, 0, d7);
                    i3++;
                    ztest3 = ztest4;
                    d1 = d1;
                    d2 = d2;
                    i2 = 0;
                }
                ztest = ztest3;
            }
            this.zMatrix.setEntry(i, j2, 0.0d);
            j2++;
            nptm2 = nptm;
            ztest3 = ztest;
        }
        for (int i4 = 0; i4 < npt; i4++) {
            work.setEntry(i4, this.zMatrix.getEntry(i, 0) * this.zMatrix.getEntry(i4, 0));
        }
        double alpha = work.getEntry(i);
        double tau = this.lagrangeValuesAtNewPoint.getEntry(i);
        this.lagrangeValuesAtNewPoint.setEntry(i, this.lagrangeValuesAtNewPoint.getEntry(i) - 1.0d);
        double sqrtDenom = FastMath.sqrt(denom);
        double d12 = tau / sqrtDenom;
        double d22 = this.zMatrix.getEntry(i, 0) / sqrtDenom;
        int i5 = 0;
        while (i5 < npt) {
            this.zMatrix.setEntry(i5, 0, (this.zMatrix.getEntry(i5, 0) * d12) - (this.lagrangeValuesAtNewPoint.getEntry(i5) * d22));
            i5++;
            alpha = alpha;
            tau = tau;
        }
        double alpha2 = alpha;
        double tau2 = tau;
        int j3 = 0;
        while (j3 < n) {
            int jp = npt + j3;
            work.setEntry(jp, this.bMatrix.getEntry(i, j3));
            double d32 = ((this.lagrangeValuesAtNewPoint.getEntry(jp) * alpha2) - (work.getEntry(jp) * tau2)) / denom;
            int n2 = n;
            double d33 = -beta;
            double d42 = ((d33 * work.getEntry(jp)) - (this.lagrangeValuesAtNewPoint.getEntry(jp) * tau2)) / denom;
            int i6 = 0;
            while (i6 <= jp) {
                double d43 = d42;
                this.bMatrix.setEntry(i6, j3, this.bMatrix.getEntry(i6, j3) + (this.lagrangeValuesAtNewPoint.getEntry(i6) * d32) + (work.getEntry(i6) * d42));
                if (i6 >= npt) {
                    this.bMatrix.setEntry(jp, i6 - npt, this.bMatrix.getEntry(i6, j3));
                }
                i6++;
                d42 = d43;
            }
            j3++;
            i = knew;
            n = n2;
        }
    }

    private void setup(double[] lowerBound, double[] upperBound) {
        printMethod();
        double[] init = getStartPoint();
        int dimension = init.length;
        if (dimension < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(dimension), 2, true);
        }
        int[] nPointsInterval = {dimension + 2, ((dimension + 2) * (dimension + 1)) / 2};
        if (this.numberOfInterpolationPoints >= nPointsInterval[0] && this.numberOfInterpolationPoints <= nPointsInterval[1]) {
            this.boundDifference = new double[dimension];
            double requiredMinDiff = this.initialTrustRegionRadius * TWO;
            double minDiff = Double.POSITIVE_INFINITY;
            for (int i = 0; i < dimension; i++) {
                this.boundDifference[i] = upperBound[i] - lowerBound[i];
                minDiff = FastMath.min(minDiff, this.boundDifference[i]);
            }
            if (minDiff < requiredMinDiff) {
                this.initialTrustRegionRadius = minDiff / 3.0d;
            }
            this.bMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints + dimension, dimension);
            this.zMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, (this.numberOfInterpolationPoints - dimension) - 1);
            this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, dimension);
            this.originShift = new ArrayRealVector(dimension);
            this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.trustRegionCenterOffset = new ArrayRealVector(dimension);
            this.gradientAtTrustRegionCenter = new ArrayRealVector(dimension);
            this.lowerDifference = new ArrayRealVector(dimension);
            this.upperDifference = new ArrayRealVector(dimension);
            this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.newPoint = new ArrayRealVector(dimension);
            this.alternativeNewPoint = new ArrayRealVector(dimension);
            this.trialStepPoint = new ArrayRealVector(dimension);
            this.lagrangeValuesAtNewPoint = new ArrayRealVector(this.numberOfInterpolationPoints + dimension);
            this.modelSecondDerivativesValues = new ArrayRealVector(((dimension + 1) * dimension) / 2);
            return;
        }
        throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, Integer.valueOf(this.numberOfInterpolationPoints), Integer.valueOf(nPointsInterval[0]), Integer.valueOf(nPointsInterval[1]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String caller(int n) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        StackTraceElement e = elements[n];
        return e.getMethodName() + " (at line " + e.getLineNumber() + ")";
    }

    private static void printState(int s) {
    }

    private static void printMethod() {
    }

    /* loaded from: classes10.dex */
    private static class PathIsExploredException extends RuntimeException {
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";
        private static final long serialVersionUID = 745350979634801853L;

        PathIsExploredException() {
            super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
        }
    }
}
