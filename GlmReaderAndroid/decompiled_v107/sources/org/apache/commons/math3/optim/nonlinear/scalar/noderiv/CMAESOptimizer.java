package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class CMAESOptimizer extends MultivariateOptimizer {
    private RealMatrix B;
    private RealMatrix BD;
    private RealMatrix C;
    private RealMatrix D;
    private double cc;
    private double ccov1;
    private double ccov1Sep;
    private double ccovmu;
    private double ccovmuSep;
    private final int checkFeasableCount;
    private double chiN;
    private double cs;
    private double damps;
    private RealMatrix diagC;
    private RealMatrix diagD;
    private int diagonalOnly;
    private int dimension;
    private double[] fitnessHistory;
    private final boolean generateStatistics;
    private int historySize;
    private double[] inputSigma;
    private final boolean isActiveCMA;
    private boolean isMinimize;
    private int iterations;
    private int lambda;
    private double logMu2;
    private final int maxIterations;
    private int mu;
    private double mueff;
    private double normps;
    private RealMatrix pc;
    private RealMatrix ps;
    private final RandomGenerator random;
    private double sigma;
    private final List<RealMatrix> statisticsDHistory;
    private final List<Double> statisticsFitnessHistory;
    private final List<RealMatrix> statisticsMeanHistory;
    private final List<Double> statisticsSigmaHistory;
    private final double stopFitness;
    private double stopTolFun;
    private double stopTolHistFun;
    private double stopTolUpX;
    private double stopTolX;
    private RealMatrix weights;
    private RealMatrix xmean;

    public CMAESOptimizer(int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        this.isMinimize = true;
        this.statisticsSigmaHistory = new ArrayList();
        this.statisticsMeanHistory = new ArrayList();
        this.statisticsFitnessHistory = new ArrayList();
        this.statisticsDHistory = new ArrayList();
        this.maxIterations = maxIterations;
        this.stopFitness = stopFitness;
        this.isActiveCMA = isActiveCMA;
        this.diagonalOnly = diagonalOnly;
        this.checkFeasableCount = checkFeasableCount;
        this.random = random;
        this.generateStatistics = generateStatistics;
    }

    public List<Double> getStatisticsSigmaHistory() {
        return this.statisticsSigmaHistory;
    }

    public List<RealMatrix> getStatisticsMeanHistory() {
        return this.statisticsMeanHistory;
    }

    public List<Double> getStatisticsFitnessHistory() {
        return this.statisticsFitnessHistory;
    }

    public List<RealMatrix> getStatisticsDHistory() {
        return this.statisticsDHistory;
    }

    /* loaded from: classes10.dex */
    public static class Sigma implements OptimizationData {
        private final double[] sigma;

        public Sigma(double[] s) throws NotPositiveException {
            for (int i = 0; i < s.length; i++) {
                if (s[i] < 0.0d) {
                    throw new NotPositiveException(Double.valueOf(s[i]));
                }
            }
            this.sigma = (double[]) s.clone();
        }

        public double[] getSigma() {
            return (double[]) this.sigma.clone();
        }
    }

    /* loaded from: classes10.dex */
    public static class PopulationSize implements OptimizationData {
        private final int lambda;

        public PopulationSize(int size) throws NotStrictlyPositiveException {
            if (size <= 0) {
                throw new NotStrictlyPositiveException(Integer.valueOf(size));
            }
            this.lambda = size;
        }

        public int getPopulationSize() {
            return this.lambda;
        }
    }

    @Override // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        return super.optimize(optData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x02ca, code lost:
    
        if (getConvergenceChecker() == null) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02cc, code lost:
    
        r7 = r3.getColumn(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02d7, code lost:
    
        if (r46.isMinimize == false) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02d9, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02de, code lost:
    
        r0 = new org.apache.commons.math3.optim.PointValuePair(r7, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x02e1, code lost:
    
        if (r14 == null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02ed, code lost:
    
        if (getConvergenceChecker().converged(r46.iterations, r0, r14) == false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02f6, code lost:
    
        r14 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0311, code lost:
    
        if (r25 != r2[r31[(int) ((r46.lambda / 4.0d) + 0.1d)]]) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0313, code lost:
    
        r39 = 0.2d;
        r46.sigma = org.apache.commons.math3.util.FastMath.exp((r46.cs / r46.damps) + 0.2d) * r46.sigma;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0337, code lost:
    
        if (r46.iterations <= 2) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0345, code lost:
    
        if ((org.apache.commons.math3.util.FastMath.max(r12, r8) - org.apache.commons.math3.util.FastMath.min(r4, r8)) != 0.0d) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0347, code lost:
    
        r46.sigma = org.apache.commons.math3.util.FastMath.exp((r46.cs / r46.damps) + r39) * r46.sigma;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x035d, code lost:
    
        push(r46.fitnessHistory, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0364, code lost:
    
        if (r46.generateStatistics == false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0366, code lost:
    
        r46.statisticsSigmaHistory.add(java.lang.Double.valueOf(r46.sigma));
        r46.statisticsFitnessHistory.add(java.lang.Double.valueOf(r8));
        r46.statisticsMeanHistory.add(r46.xmean.transpose());
        r46.statisticsDHistory.add(r46.diagD.transpose().scalarMultiply(100000.0d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x039e, code lost:
    
        r46.iterations++;
        r10 = r16;
        r8 = r19;
        r9 = r20;
        r11 = r25;
        r13 = r33;
        r7 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x032f, code lost:
    
        r39 = 0.2d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02dc, code lost:
    
        r2 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x029a, code lost:
    
        r38 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x025d, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0260, code lost:
    
        if (r4 >= r46.dimension) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x026b, code lost:
    
        if ((r46.sigma * r0[r4]) <= r46.stopTolUpX) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0271, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0274, code lost:
    
        r4 = min(r46.fitnessHistory);
        r12 = max(r46.fitnessHistory);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0285, code lost:
    
        if (r46.iterations <= 2) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0287, code lost:
    
        r38 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0297, code lost:
    
        if ((org.apache.commons.math3.util.FastMath.max(r12, r10) - org.apache.commons.math3.util.FastMath.min(r4, r8)) >= r46.stopTolFun) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02a1, code lost:
    
        if (r46.iterations <= r46.fitnessHistory.length) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x02ab, code lost:
    
        if ((r12 - r4) >= r46.stopTolHistFun) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02c3, code lost:
    
        if ((max(r46.diagD) / min(r46.diagD)) <= 1.0E7d) goto L92;
     */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.optim.PointValuePair doOptimize() {
        /*
            Method dump skipped, instructions count: 958
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize():org.apache.commons.math3.optim.PointValuePair");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public void parseOptimizationData(OptimizationData... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof Sigma) {
                this.inputSigma = ((Sigma) data).getSigma();
            } else if (data instanceof PopulationSize) {
                this.lambda = ((PopulationSize) data).getPopulationSize();
            }
        }
        checkParameters();
    }

    private void checkParameters() {
        double[] init = getStartPoint();
        double[] lB = getLowerBound();
        double[] uB = getUpperBound();
        if (this.inputSigma != null) {
            if (this.inputSigma.length != init.length) {
                throw new DimensionMismatchException(this.inputSigma.length, init.length);
            }
            for (int i = 0; i < init.length; i++) {
                if (this.inputSigma[i] > uB[i] - lB[i]) {
                    throw new OutOfRangeException(Double.valueOf(this.inputSigma[i]), 0, Double.valueOf(uB[i] - lB[i]));
                }
            }
        }
    }

    private void initializeCMA(double[] guess) {
        if (this.lambda <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(this.lambda));
        }
        double[][] sigmaArray = (double[][]) Array.newInstance((Class<?>) Double.TYPE, guess.length, 1);
        for (int i = 0; i < guess.length; i++) {
            sigmaArray[i][0] = this.inputSigma[i];
        }
        RealMatrix insigma = new Array2DRowRealMatrix(sigmaArray, false);
        this.sigma = max(insigma);
        this.stopTolUpX = max(insigma) * 1000.0d;
        this.stopTolX = max(insigma) * 1.0E-11d;
        this.stopTolFun = 1.0E-12d;
        this.stopTolHistFun = 1.0E-13d;
        this.mu = this.lambda / 2;
        this.logMu2 = FastMath.log(this.mu + 0.5d);
        this.weights = log(sequence(1.0d, this.mu, 1.0d)).scalarMultiply(-1.0d).scalarAdd(this.logMu2);
        double sumw = 0.0d;
        double sumwq = 0.0d;
        for (int i2 = 0; i2 < this.mu; i2++) {
            double w = this.weights.getEntry(i2, 0);
            sumw += w;
            sumwq += w * w;
        }
        this.weights = this.weights.scalarMultiply(1.0d / sumw);
        this.mueff = (sumw * sumw) / sumwq;
        this.cc = ((this.mueff / this.dimension) + 4.0d) / ((this.dimension + 4) + ((this.mueff * 2.0d) / this.dimension));
        this.cs = (this.mueff + 2.0d) / ((this.dimension + this.mueff) + 3.0d);
        this.damps = (((FastMath.max(0.0d, FastMath.sqrt((this.mueff - 1.0d) / (this.dimension + 1)) - 1.0d) * 2.0d) + 1.0d) * FastMath.max(0.3d, 1.0d - (this.dimension / (this.maxIterations + 1.0E-6d)))) + this.cs;
        this.ccov1 = 2.0d / (((this.dimension + 1.3d) * (this.dimension + 1.3d)) + this.mueff);
        this.ccovmu = FastMath.min(1.0d - this.ccov1, (((this.mueff - 2.0d) + (1.0d / this.mueff)) * 2.0d) / (((this.dimension + 2) * (this.dimension + 2)) + this.mueff));
        this.ccov1Sep = FastMath.min(1.0d, (this.ccov1 * (this.dimension + 1.5d)) / 3.0d);
        this.ccovmuSep = FastMath.min(1.0d - this.ccov1, (this.ccovmu * (this.dimension + 1.5d)) / 3.0d);
        this.chiN = FastMath.sqrt(this.dimension) * ((1.0d - (1.0d / (this.dimension * 4.0d))) + (1.0d / ((this.dimension * 21.0d) * this.dimension)));
        this.xmean = MatrixUtils.createColumnRealMatrix(guess);
        this.diagD = insigma.scalarMultiply(1.0d / this.sigma);
        this.diagC = square(this.diagD);
        this.pc = zeros(this.dimension, 1);
        this.ps = zeros(this.dimension, 1);
        this.normps = this.ps.getFrobeniusNorm();
        this.B = eye(this.dimension, this.dimension);
        this.D = ones(this.dimension, 1);
        this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
        this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
        this.historySize = ((int) ((this.dimension * 30) / this.lambda)) + 10;
        this.fitnessHistory = new double[this.historySize];
        for (int i3 = 0; i3 < this.historySize; i3++) {
            this.fitnessHistory[i3] = Double.MAX_VALUE;
        }
    }

    private boolean updateEvolutionPaths(RealMatrix zmean, RealMatrix xold) {
        this.ps = this.ps.scalarMultiply(1.0d - this.cs).add(this.B.multiply(zmean).scalarMultiply(FastMath.sqrt(this.cs * (2.0d - this.cs) * this.mueff)));
        this.normps = this.ps.getFrobeniusNorm();
        boolean hsig = (this.normps / FastMath.sqrt(1.0d - FastMath.pow(1.0d - this.cs, this.iterations * 2))) / this.chiN < (2.0d / (((double) this.dimension) + 1.0d)) + 1.4d;
        this.pc = this.pc.scalarMultiply(1.0d - this.cc);
        if (hsig) {
            this.pc = this.pc.add(this.xmean.subtract(xold).scalarMultiply(FastMath.sqrt((this.cc * (2.0d - this.cc)) * this.mueff) / this.sigma));
        }
        return hsig;
    }

    private void updateCovarianceDiagonalOnly(boolean hsig, RealMatrix bestArz) {
        double oldFac = hsig ? 0.0d : this.ccov1Sep * this.cc * (2.0d - this.cc);
        this.diagC = this.diagC.scalarMultiply(oldFac + ((1.0d - this.ccov1Sep) - this.ccovmuSep)).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(bestArz).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
        this.diagD = sqrt(this.diagC);
        if (this.diagonalOnly > 1 && this.iterations > this.diagonalOnly) {
            this.diagonalOnly = 0;
            this.B = eye(this.dimension, this.dimension);
            this.BD = diag(this.diagD);
            this.C = diag(this.diagC);
        }
    }

    private void updateCovariance(boolean hsig, RealMatrix bestArx, RealMatrix arz, int[] arindex, RealMatrix xold) {
        double d;
        double negccov = 0.0d;
        double oldFac = 0.0d;
        if (this.ccov1 + this.ccovmu > 0.0d) {
            RealMatrix arpos = bestArx.subtract(repmat(xold, 1, this.mu)).scalarMultiply(1.0d / this.sigma);
            RealMatrix roneu = this.pc.multiply(this.pc.transpose()).scalarMultiply(this.ccov1);
            if (hsig) {
                d = 1.0d;
            } else {
                d = 1.0d;
                oldFac = this.ccov1 * this.cc * (2.0d - this.cc);
            }
            double oldFac2 = oldFac + ((d - this.ccov1) - this.ccovmu);
            if (!this.isActiveCMA) {
                this.C = this.C.scalarMultiply(oldFac2).add(roneu).add(arpos.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose())));
            } else {
                double negccov2 = (((d - this.ccovmu) * 0.25d) * this.mueff) / (FastMath.pow(this.dimension + 2, 1.5d) + (this.mueff * 2.0d));
                int[] arReverseIndex = reverse(arindex);
                RealMatrix arzneg = selectColumns(arz, MathArrays.copyOf(arReverseIndex, this.mu));
                RealMatrix arnorms = sqrt(sumRows(square(arzneg)));
                int[] idxnorms = sortedIndices(arnorms.getRow(0));
                RealMatrix arnormsSorted = selectColumns(arnorms, idxnorms);
                int[] idxReverse = reverse(idxnorms);
                RealMatrix arnormsReverse = selectColumns(arnorms, idxReverse);
                RealMatrix arnorms2 = divide(arnormsReverse, arnormsSorted);
                int[] idxInv = inverse(idxnorms);
                RealMatrix arnormsInv = selectColumns(arnorms2, idxInv);
                RealMatrix square = square(arnormsInv);
                RealMatrix arnormsReverse2 = this.weights;
                double negcovMax = 0.33999999999999997d / square.multiply(arnormsReverse2).getEntry(0, 0);
                if (negccov2 > negcovMax) {
                    negccov2 = negcovMax;
                }
                RealMatrix artmp = this.BD.multiply(times(arzneg, repmat(arnormsInv, this.dimension, 1)));
                RealMatrix Cneg = artmp.multiply(diag(this.weights)).multiply(artmp.transpose());
                RealMatrix add = this.C.scalarMultiply(oldFac2 + (negccov2 * 0.5d)).add(roneu);
                double oldFac3 = this.ccovmu;
                this.C = add.add(arpos.scalarMultiply(oldFac3 + (0.5d * negccov2)).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose()))).subtract(Cneg.scalarMultiply(negccov2));
                negccov = negccov2;
            }
        }
        updateBD(negccov);
    }

    private void updateBD(double negccov) {
        if (this.ccov1 + this.ccovmu + negccov > 0.0d && (((this.iterations % 1.0d) / ((this.ccov1 + this.ccovmu) + negccov)) / this.dimension) / 10.0d < 1.0d) {
            this.C = triu(this.C, 0).add(triu(this.C, 1).transpose());
            EigenDecomposition eig = new EigenDecomposition(this.C);
            this.B = eig.getV();
            this.D = eig.getD();
            this.diagD = diag(this.D);
            if (min(this.diagD) <= 0.0d) {
                for (int i = 0; i < this.dimension; i++) {
                    if (this.diagD.getEntry(i, 0) < 0.0d) {
                        this.diagD.setEntry(i, 0, 0.0d);
                    }
                }
                double tfac = max(this.diagD) / 1.0E14d;
                this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac));
            }
            if (max(this.diagD) > min(this.diagD) * 1.0E14d) {
                double tfac2 = (max(this.diagD) / 1.0E14d) - min(this.diagD);
                this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac2));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac2));
            }
            this.diagC = diag(this.C);
            this.diagD = sqrt(this.diagD);
            this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
        }
    }

    private static void push(double[] vals, double val) {
        for (int i = vals.length - 1; i > 0; i--) {
            vals[i] = vals[i - 1];
        }
        vals[0] = val;
    }

    private int[] sortedIndices(double[] doubles) {
        DoubleIndex[] dis = new DoubleIndex[doubles.length];
        for (int i = 0; i < doubles.length; i++) {
            dis[i] = new DoubleIndex(doubles[i], i);
        }
        Arrays.sort(dis);
        int[] indices = new int[doubles.length];
        for (int i2 = 0; i2 < doubles.length; i2++) {
            indices[i2] = dis[i2].index;
        }
        return indices;
    }

    private double valueRange(ValuePenaltyPair[] vpPairs) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.MAX_VALUE;
        for (ValuePenaltyPair vpPair : vpPairs) {
            if (vpPair.value > max) {
                max = vpPair.value;
            }
            if (vpPair.value < min) {
                min = vpPair.value;
            }
        }
        return max - min;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class DoubleIndex implements Comparable<DoubleIndex> {
        private final int index;
        private final double value;

        DoubleIndex(double value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override // java.lang.Comparable
        public int compareTo(DoubleIndex o) {
            return Double.compare(this.value, o.value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DoubleIndex) && Double.compare(this.value, ((DoubleIndex) other).value) == 0;
        }

        public int hashCode() {
            long bits = Double.doubleToLongBits(this.value);
            return (int) ((((bits >>> 32) ^ 1438542) ^ bits) & (-1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ValuePenaltyPair {
        private double penalty;
        private double value;

        ValuePenaltyPair(double value, double penalty) {
            this.value = value;
            this.penalty = penalty;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class FitnessFunction {
        private final boolean isRepairMode = true;

        FitnessFunction() {
        }

        public ValuePenaltyPair value(double[] point) {
            double value;
            double penalty = 0.0d;
            if (this.isRepairMode) {
                double[] repaired = repair(point);
                value = CMAESOptimizer.this.computeObjectiveValue(repaired);
                penalty = penalty(point, repaired);
            } else {
                value = CMAESOptimizer.this.computeObjectiveValue(point);
            }
            return new ValuePenaltyPair(CMAESOptimizer.this.isMinimize ? value : -value, CMAESOptimizer.this.isMinimize ? penalty : -penalty);
        }

        public boolean isFeasible(double[] x) {
            double[] lB = CMAESOptimizer.this.getLowerBound();
            double[] uB = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; i < x.length; i++) {
                if (x[i] < lB[i] || x[i] > uB[i]) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double[] repair(double[] x) {
            double[] lB = CMAESOptimizer.this.getLowerBound();
            double[] uB = CMAESOptimizer.this.getUpperBound();
            double[] repaired = new double[x.length];
            for (int i = 0; i < x.length; i++) {
                if (x[i] < lB[i]) {
                    repaired[i] = lB[i];
                } else if (x[i] > uB[i]) {
                    repaired[i] = uB[i];
                } else {
                    repaired[i] = x[i];
                }
            }
            return repaired;
        }

        private double penalty(double[] x, double[] repaired) {
            double penalty = 0.0d;
            for (int i = 0; i < x.length; i++) {
                double diff = FastMath.abs(x[i] - repaired[i]);
                penalty += diff;
            }
            return CMAESOptimizer.this.isMinimize ? penalty : -penalty;
        }
    }

    private static RealMatrix log(RealMatrix m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                d[r][c] = FastMath.log(m.getEntry(r, c));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix sqrt(RealMatrix m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                d[r][c] = FastMath.sqrt(m.getEntry(r, c));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix square(RealMatrix m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                double e = m.getEntry(r, c);
                d[r][c] = e * e;
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix times(RealMatrix m, RealMatrix n) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                d[r][c] = m.getEntry(r, c) * n.getEntry(r, c);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix divide(RealMatrix m, RealMatrix n) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                d[r][c] = m.getEntry(r, c) / n.getEntry(r, c);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix selectColumns(RealMatrix m, int[] cols) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), cols.length);
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < cols.length; c++) {
                d[r][c] = m.getEntry(r, cols[c]);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix triu(RealMatrix m, int k) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getColumnDimension());
        int r = 0;
        while (r < m.getRowDimension()) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                d[r][c] = r <= c - k ? m.getEntry(r, c) : 0.0d;
            }
            r++;
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix sumRows(RealMatrix m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 1, m.getColumnDimension());
        for (int c = 0; c < m.getColumnDimension(); c++) {
            double sum = 0.0d;
            for (int r = 0; r < m.getRowDimension(); r++) {
                sum += m.getEntry(r, c);
            }
            d[0][c] = sum;
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix diag(RealMatrix m) {
        if (m.getColumnDimension() == 1) {
            double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), m.getRowDimension());
            for (int i = 0; i < m.getRowDimension(); i++) {
                d[i][i] = m.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(d, false);
        }
        double[][] d2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, m.getRowDimension(), 1);
        for (int i2 = 0; i2 < m.getColumnDimension(); i2++) {
            d2[i2][0] = m.getEntry(i2, i2);
        }
        return new Array2DRowRealMatrix(d2, false);
    }

    private static void copyColumn(RealMatrix m1, int col1, RealMatrix m2, int col2) {
        for (int i = 0; i < m1.getRowDimension(); i++) {
            m2.setEntry(i, col2, m1.getEntry(i, col1));
        }
    }

    private static RealMatrix ones(int n, int m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, n, m);
        for (int r = 0; r < n; r++) {
            Arrays.fill(d[r], 1.0d);
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix eye(int n, int m) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, n, m);
        for (int r = 0; r < n; r++) {
            if (r < m) {
                d[r][r] = 1.0d;
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix zeros(int n, int m) {
        return new Array2DRowRealMatrix(n, m);
    }

    private static RealMatrix repmat(RealMatrix mat, int n, int m) {
        int rd = mat.getRowDimension();
        int cd = mat.getColumnDimension();
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, n * rd, m * cd);
        for (int r = 0; r < n * rd; r++) {
            for (int c = 0; c < m * cd; c++) {
                d[r][c] = mat.getEntry(r % rd, c % cd);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static RealMatrix sequence(double start, double end, double step) {
        int size = (int) (((end - start) / step) + 1.0d);
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 1);
        double value = start;
        for (int r = 0; r < size; r++) {
            d[r][0] = value;
            value += step;
        }
        return new Array2DRowRealMatrix(d, false);
    }

    private static double max(RealMatrix m) {
        double max = -1.7976931348623157E308d;
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                double e = m.getEntry(r, c);
                if (max < e) {
                    max = e;
                }
            }
        }
        return max;
    }

    private static double min(RealMatrix m) {
        double min = Double.MAX_VALUE;
        for (int r = 0; r < m.getRowDimension(); r++) {
            for (int c = 0; c < m.getColumnDimension(); c++) {
                double e = m.getEntry(r, c);
                if (min > e) {
                    min = e;
                }
            }
        }
        return min;
    }

    private static double max(double[] m) {
        double max = -1.7976931348623157E308d;
        for (int r = 0; r < m.length; r++) {
            if (max < m[r]) {
                max = m[r];
            }
        }
        return max;
    }

    private static double min(double[] m) {
        double min = Double.MAX_VALUE;
        for (int r = 0; r < m.length; r++) {
            if (min > m[r]) {
                min = m[r];
            }
        }
        return min;
    }

    private static int[] inverse(int[] indices) {
        int[] inverse = new int[indices.length];
        for (int i = 0; i < indices.length; i++) {
            inverse[indices[i]] = i;
        }
        return inverse;
    }

    private static int[] reverse(int[] indices) {
        int[] reverse = new int[indices.length];
        for (int i = 0; i < indices.length; i++) {
            reverse[i] = indices[(indices.length - i) - 1];
        }
        return reverse;
    }

    private double[] randn(int size) {
        double[] randn = new double[size];
        for (int i = 0; i < size; i++) {
            randn[i] = this.random.nextGaussian();
        }
        return randn;
    }

    private RealMatrix randn1(int size, int popSize) {
        double[][] d = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, popSize);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < popSize; c++) {
                d[r][c] = this.random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }
}
