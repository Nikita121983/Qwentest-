package org.apache.commons.math3.optimization.direct;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class CMAESOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public static final int DEFAULT_CHECKFEASABLECOUNT = 0;
    public static final int DEFAULT_DIAGONALONLY = 0;
    public static final boolean DEFAULT_ISACTIVECMA = true;
    public static final int DEFAULT_MAXITERATIONS = 30000;
    public static final RandomGenerator DEFAULT_RANDOMGENERATOR = new MersenneTwister();
    public static final double DEFAULT_STOPFITNESS = 0.0d;
    private RealMatrix B;
    private RealMatrix BD;
    private RealMatrix C;
    private RealMatrix D;
    private double cc;
    private double ccov1;
    private double ccov1Sep;
    private double ccovmu;
    private double ccovmuSep;
    private int checkFeasableCount;
    private double chiN;
    private double cs;
    private double damps;
    private RealMatrix diagC;
    private RealMatrix diagD;
    private int diagonalOnly;
    private int dimension;
    private double[] fitnessHistory;
    private boolean generateStatistics;
    private int historySize;
    private double[] inputSigma;
    private boolean isActiveCMA;
    private boolean isMinimize;
    private int iterations;
    private int lambda;
    private double logMu2;
    private int maxIterations;
    private int mu;
    private double mueff;
    private double normps;
    private RealMatrix pc;
    private RealMatrix ps;
    private RandomGenerator random;
    private double sigma;
    private List<RealMatrix> statisticsDHistory;
    private List<Double> statisticsFitnessHistory;
    private List<RealMatrix> statisticsMeanHistory;
    private List<Double> statisticsSigmaHistory;
    private double stopFitness;
    private double stopTolFun;
    private double stopTolHistFun;
    private double stopTolUpX;
    private double stopTolX;
    private RealMatrix weights;
    private RealMatrix xmean;

    @Deprecated
    public CMAESOptimizer() {
        this(0);
    }

    @Deprecated
    public CMAESOptimizer(int lambda) {
        this(lambda, null, DEFAULT_MAXITERATIONS, 0.0d, true, 0, 0, DEFAULT_RANDOMGENERATOR, false, null);
    }

    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma) {
        this(lambda, inputSigma, DEFAULT_MAXITERATIONS, 0.0d, true, 0, 0, DEFAULT_RANDOMGENERATOR, false);
    }

    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics) {
        this(lambda, inputSigma, maxIterations, stopFitness, isActiveCMA, diagonalOnly, checkFeasableCount, random, generateStatistics, new SimpleValueChecker());
    }

    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        this.diagonalOnly = 0;
        this.isMinimize = true;
        this.generateStatistics = false;
        this.statisticsSigmaHistory = new ArrayList();
        this.statisticsMeanHistory = new ArrayList();
        this.statisticsFitnessHistory = new ArrayList();
        this.statisticsDHistory = new ArrayList();
        this.lambda = lambda;
        this.inputSigma = inputSigma == null ? null : (double[]) inputSigma.clone();
        this.maxIterations = maxIterations;
        this.stopFitness = stopFitness;
        this.isActiveCMA = isActiveCMA;
        this.diagonalOnly = diagonalOnly;
        this.checkFeasableCount = checkFeasableCount;
        this.random = random;
        this.generateStatistics = generateStatistics;
    }

    public CMAESOptimizer(int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        this.diagonalOnly = 0;
        this.isMinimize = true;
        this.generateStatistics = false;
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    public PointValuePair optimizeInternal(int maxEval, MultivariateFunction f, GoalType goalType, OptimizationData... optData) {
        parseOptimizationData(optData);
        return super.optimizeInternal(maxEval, (int) f, goalType, optData);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0295, code lost:
    
        r1 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x027c, code lost:
    
        r0 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02ae, code lost:
    
        if (r20 != r30[r27[(int) ((r1.lambda / 4.0d) + 0.1d)]]) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x02b0, code lost:
    
        r36 = 0.2d;
        r1.sigma = org.apache.commons.math3.util.FastMath.exp((r1.cs / r1.damps) + 0.2d) * r1.sigma;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02d3, code lost:
    
        if (r1.iterations <= 2) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02e0, code lost:
    
        if ((org.apache.commons.math3.util.FastMath.max(r14, r8) - org.apache.commons.math3.util.FastMath.min(r4, r8)) != 0.0d) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x02e2, code lost:
    
        r1.sigma = org.apache.commons.math3.util.FastMath.exp((r1.cs / r1.damps) + r36) * r1.sigma;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x02f5, code lost:
    
        push(r1.fitnessHistory, r8);
        r0.setValueRange(r34 - r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0301, code lost:
    
        if (r1.generateStatistics == false) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0303, code lost:
    
        r1.statisticsSigmaHistory.add(java.lang.Double.valueOf(r1.sigma));
        r1.statisticsFitnessHistory.add(java.lang.Double.valueOf(r8));
        r1.statisticsMeanHistory.add(r1.xmean.transpose());
        r1.statisticsDHistory.add(r1.diagD.transpose().scalarMultiply(100000.0d));
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0336, code lost:
    
        r1.iterations++;
        r9 = r16;
        r8 = r18;
        r10 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02ca, code lost:
    
        r36 = 0.2d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x023d, code lost:
    
        r34 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0202, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0205, code lost:
    
        if (r2 >= r1.dimension) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0210, code lost:
    
        if ((r1.sigma * r0[r2]) <= r1.stopTolUpX) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0214, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0217, code lost:
    
        r4 = min(r1.fitnessHistory);
        r14 = max(r1.fitnessHistory);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0228, code lost:
    
        if (r1.iterations <= 2) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x022a, code lost:
    
        r34 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x023a, code lost:
    
        if ((org.apache.commons.math3.util.FastMath.max(r14, r10) - org.apache.commons.math3.util.FastMath.min(r4, r8)) >= r1.stopTolFun) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0244, code lost:
    
        if (r1.iterations <= r1.fitnessHistory.length) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x024e, code lost:
    
        if ((r14 - r4) >= r1.stopTolHistFun) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0266, code lost:
    
        if ((max(r1.diagD) / min(r1.diagD)) <= 1.0E7d) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x026d, code lost:
    
        if (r1.getConvergenceChecker() == null) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x026f, code lost:
    
        r11 = r3.getColumn(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0278, code lost:
    
        if (r1.isMinimize == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x027a, code lost:
    
        r0 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x027d, code lost:
    
        r2 = new org.apache.commons.math3.optimization.PointValuePair(r11, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0280, code lost:
    
        if (r13 == null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0282, code lost:
    
        r1 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x028e, code lost:
    
        if (getConvergenceChecker().converged(r1.iterations, r2, r13) == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0297, code lost:
    
        r13 = r2;
     */
    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected org.apache.commons.math3.optimization.PointValuePair doOptimize() {
        /*
            Method dump skipped, instructions count: 844
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize():org.apache.commons.math3.optimization.PointValuePair");
    }

    private void parseOptimizationData(OptimizationData... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof Sigma) {
                this.inputSigma = ((Sigma) data).getSigma();
            } else if (data instanceof PopulationSize) {
                this.lambda = ((PopulationSize) data).getPopulationSize();
            }
        }
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
                if (this.inputSigma[i] < 0.0d) {
                    throw new NotPositiveException(Double.valueOf(this.inputSigma[i]));
                }
                if (this.inputSigma[i] > uB[i] - lB[i]) {
                    throw new OutOfRangeException(Double.valueOf(this.inputSigma[i]), 0, Double.valueOf(uB[i] - lB[i]));
                }
            }
        }
    }

    private void initializeCMA(double[] guess) {
        double d;
        double d2 = 3.0d;
        if (this.lambda <= 0) {
            this.lambda = ((int) (FastMath.log(this.dimension) * 3.0d)) + 4;
        }
        double[][] sigmaArray = (double[][]) Array.newInstance((Class<?>) Double.TYPE, guess.length, 1);
        for (int i = 0; i < guess.length; i++) {
            sigmaArray[i][0] = this.inputSigma == null ? 0.3d : this.inputSigma[i];
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
        int i2 = 0;
        while (true) {
            d = d2;
            if (i2 >= this.mu) {
                break;
            }
            double w = this.weights.getEntry(i2, 0);
            sumw += w;
            sumwq += w * w;
            i2++;
            d2 = d;
        }
        this.weights = this.weights.scalarMultiply(1.0d / sumw);
        this.mueff = (sumw * sumw) / sumwq;
        this.cc = ((this.mueff / this.dimension) + 4.0d) / ((this.dimension + 4) + ((this.mueff * 2.0d) / this.dimension));
        this.cs = (this.mueff + 2.0d) / ((this.dimension + this.mueff) + d);
        this.damps = (((FastMath.max(0.0d, FastMath.sqrt((this.mueff - 1.0d) / (this.dimension + 1)) - 1.0d) * 2.0d) + 1.0d) * FastMath.max(0.3d, 1.0d - (this.dimension / (this.maxIterations + 1.0E-6d)))) + this.cs;
        this.ccov1 = 2.0d / (((this.dimension + 1.3d) * (this.dimension + 1.3d)) + this.mueff);
        this.ccovmu = FastMath.min(1.0d - this.ccov1, (((this.mueff - 2.0d) + (1.0d / this.mueff)) * 2.0d) / (((this.dimension + 2) * (this.dimension + 2)) + this.mueff));
        this.ccov1Sep = FastMath.min(1.0d, (this.ccov1 * (this.dimension + 1.5d)) / d);
        this.ccovmuSep = FastMath.min(1.0d - this.ccov1, (this.ccovmu * (this.dimension + 1.5d)) / d);
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

    /* loaded from: classes10.dex */
    private class FitnessFunction {
        private double valueRange = 1.0d;
        private final boolean isRepairMode = true;

        FitnessFunction() {
        }

        public double value(double[] point) {
            double value;
            if (this.isRepairMode) {
                double[] repaired = repair(point);
                value = CMAESOptimizer.this.computeObjectiveValue(repaired) + penalty(point, repaired);
            } else {
                value = CMAESOptimizer.this.computeObjectiveValue(point);
            }
            return CMAESOptimizer.this.isMinimize ? value : -value;
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

        public void setValueRange(double valueRange) {
            this.valueRange = valueRange;
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
                penalty += this.valueRange * diff;
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
