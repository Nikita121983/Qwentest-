package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class SymmLQ extends PreconditionedIterativeLinearSolver {
    private static final String OPERATOR = "operator";
    private static final String THRESHOLD = "threshold";
    private static final String VECTOR = "vector";
    private static final String VECTOR1 = "vector1";
    private static final String VECTOR2 = "vector2";
    private final boolean check;
    private final double delta;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class State {
        private final RealLinearOperator a;
        private final RealVector b;
        private boolean bIsNull;
        private double beta;
        private double beta1;
        private double bstep;
        private double cgnorm;
        private final boolean check;
        private double dbar;
        private final double delta;
        private double gammaZeta;
        private double gbar;
        private double gmax;
        private double gmin;
        private final boolean goodb;
        private boolean hasConverged;
        private double lqnorm;
        private final RealLinearOperator m;
        private final RealVector mb;
        private double minusEpsZeta;
        private double oldb;
        private RealVector r1;
        private RealVector r2;
        private double rnorm;
        private final double shift;
        private double snprod;
        private double tnorm;
        private RealVector wbar;
        private final RealVector xL;
        private RealVector y;
        private double ynorm2;
        static final double MACH_PREC = FastMath.ulp(1.0d);
        static final double CBRT_MACH_PREC = FastMath.cbrt(MACH_PREC);

        State(RealLinearOperator a, RealLinearOperator m, RealVector b, boolean goodb, double shift, double delta, boolean check) {
            this.a = a;
            this.m = m;
            this.b = b;
            this.xL = new ArrayRealVector(b.getDimension());
            this.goodb = goodb;
            this.shift = shift;
            this.mb = m == null ? b : m.operate(b);
            this.hasConverged = false;
            this.check = check;
            this.delta = delta;
        }

        private static void checkSymmetry(RealLinearOperator l, RealVector x, RealVector y, RealVector z) throws NonSelfAdjointOperatorException {
            double s = y.dotProduct(y);
            double t = x.dotProduct(z);
            double epsa = (MACH_PREC + s) * CBRT_MACH_PREC;
            if (FastMath.abs(s - t) > epsa) {
                NonSelfAdjointOperatorException e = new NonSelfAdjointOperatorException();
                ExceptionContext context = e.getContext();
                context.setValue("operator", l);
                context.setValue(SymmLQ.VECTOR1, x);
                context.setValue(SymmLQ.VECTOR2, y);
                context.setValue(SymmLQ.THRESHOLD, Double.valueOf(epsa));
                throw e;
            }
        }

        private static void throwNPDLOException(RealLinearOperator l, RealVector v) throws NonPositiveDefiniteOperatorException {
            NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
            ExceptionContext context = e.getContext();
            context.setValue("operator", l);
            context.setValue("vector", v);
            throw e;
        }

        private static void daxpy(double a, RealVector x, RealVector y) {
            int n = x.getDimension();
            for (int i = 0; i < n; i++) {
                y.setEntry(i, (x.getEntry(i) * a) + y.getEntry(i));
            }
        }

        private static void daxpbypz(double a, RealVector x, double b, RealVector y, RealVector z) {
            int n = z.getDimension();
            for (int i = 0; i < n; i++) {
                double zi = (x.getEntry(i) * a) + (y.getEntry(i) * b) + z.getEntry(i);
                z.setEntry(i, zi);
            }
        }

        void refineSolution(RealVector x) {
            int n = this.xL.getDimension();
            if (this.lqnorm < this.cgnorm) {
                if (this.goodb) {
                    double step = this.bstep / this.beta1;
                    for (int i = 0; i < n; i++) {
                        double bi = this.mb.getEntry(i);
                        double xi = this.xL.getEntry(i);
                        x.setEntry(i, (step * bi) + xi);
                    }
                    return;
                }
                x.setSubVector(0, this.xL);
                return;
            }
            double anorm = FastMath.sqrt(this.tnorm);
            double diag = this.gbar == 0.0d ? MACH_PREC * anorm : this.gbar;
            double zbar = this.gammaZeta / diag;
            double step2 = (this.bstep + (this.snprod * zbar)) / this.beta1;
            if (!this.goodb) {
                int i2 = 0;
                while (i2 < n) {
                    double xi2 = this.xL.getEntry(i2);
                    double wi = this.wbar.getEntry(i2);
                    x.setEntry(i2, xi2 + (zbar * wi));
                    i2++;
                    anorm = anorm;
                }
                return;
            }
            int i3 = 0;
            while (i3 < n) {
                double xi3 = this.xL.getEntry(i3);
                double wi2 = this.wbar.getEntry(i3);
                double bi2 = this.mb.getEntry(i3);
                x.setEntry(i3, xi3 + (zbar * wi2) + (step2 * bi2));
                i3++;
                diag = diag;
            }
        }

        void init() {
            this.xL.set(0.0d);
            this.r1 = this.b.copy();
            this.y = this.m == null ? this.b.copy() : this.m.operate(this.r1);
            if (this.m != null && this.check) {
                checkSymmetry(this.m, this.r1, this.y, this.m.operate(this.y));
            }
            this.beta1 = this.r1.dotProduct(this.y);
            if (this.beta1 < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            if (this.beta1 == 0.0d) {
                this.bIsNull = true;
                return;
            }
            this.bIsNull = false;
            this.beta1 = FastMath.sqrt(this.beta1);
            RealVector v = this.y.mapMultiply(1.0d / this.beta1);
            this.y = this.a.operate(v);
            if (this.check) {
                checkSymmetry(this.a, v, this.y, this.a.operate(this.y));
            }
            daxpy(-this.shift, v, this.y);
            double alpha = v.dotProduct(this.y);
            daxpy((-alpha) / this.beta1, this.r1, this.y);
            double vty = v.dotProduct(this.y);
            double vtv = v.dotProduct(v);
            daxpy((-vty) / vtv, v, this.y);
            this.r2 = this.y.copy();
            if (this.m != null) {
                this.y = this.m.operate(this.r2);
            }
            this.oldb = this.beta1;
            this.beta = this.r2.dotProduct(this.y);
            if (this.beta < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            this.beta = FastMath.sqrt(this.beta);
            this.cgnorm = this.beta1;
            this.gbar = alpha;
            this.dbar = this.beta;
            this.gammaZeta = this.beta1;
            this.minusEpsZeta = 0.0d;
            this.bstep = 0.0d;
            this.snprod = 1.0d;
            this.tnorm = (alpha * alpha) + (this.beta * this.beta);
            this.ynorm2 = 0.0d;
            this.gmax = FastMath.abs(alpha) + MACH_PREC;
            this.gmin = this.gmax;
            if (this.goodb) {
                this.wbar = new ArrayRealVector(this.a.getRowDimension());
                this.wbar.set(0.0d);
            } else {
                this.wbar = v;
            }
            updateNorms();
        }

        void update() {
            RealVector v = this.y.mapMultiply(1.0d / this.beta);
            this.y = this.a.operate(v);
            daxpbypz(-this.shift, v, (-this.beta) / this.oldb, this.r1, this.y);
            double alpha = v.dotProduct(this.y);
            daxpy((-alpha) / this.beta, this.r2, this.y);
            this.r1 = this.r2;
            this.r2 = this.y;
            if (this.m != null) {
                this.y = this.m.operate(this.r2);
            }
            this.oldb = this.beta;
            this.beta = this.r2.dotProduct(this.y);
            if (this.beta < 0.0d) {
                throwNPDLOException(this.m, this.y);
            }
            this.beta = FastMath.sqrt(this.beta);
            this.tnorm += (alpha * alpha) + (this.oldb * this.oldb) + (this.beta * this.beta);
            double gamma = FastMath.sqrt((this.gbar * this.gbar) + (this.oldb * this.oldb));
            double c = this.gbar / gamma;
            double s = this.oldb / gamma;
            double deltak = (this.dbar * c) + (s * alpha);
            this.gbar = (this.dbar * s) - (c * alpha);
            double eps = this.beta * s;
            this.dbar = this.beta * (-c);
            double zeta = this.gammaZeta / gamma;
            double zetaC = zeta * c;
            double zetaS = zeta * s;
            int i = 0;
            for (int n = this.xL.getDimension(); i < n; n = n) {
                double xi = this.xL.getEntry(i);
                double vi = v.getEntry(i);
                double wi = this.wbar.getEntry(i);
                this.xL.setEntry(i, xi + (wi * zetaC) + (vi * zetaS));
                this.wbar.setEntry(i, (wi * s) - (vi * c));
                i++;
                v = v;
            }
            this.bstep += this.snprod * c * zeta;
            this.snprod *= s;
            this.gmax = FastMath.max(this.gmax, gamma);
            this.gmin = FastMath.min(this.gmin, gamma);
            this.ynorm2 += zeta * zeta;
            this.gammaZeta = this.minusEpsZeta - (deltak * zeta);
            this.minusEpsZeta = (-eps) * zeta;
            updateNorms();
        }

        private void updateNorms() {
            double acond;
            double anorm = FastMath.sqrt(this.tnorm);
            double ynorm = FastMath.sqrt(this.ynorm2);
            double epsa = MACH_PREC * anorm;
            double epsx = anorm * ynorm * MACH_PREC;
            double epsr = anorm * ynorm * this.delta;
            double diag = this.gbar == 0.0d ? epsa : this.gbar;
            this.lqnorm = FastMath.sqrt((this.gammaZeta * this.gammaZeta) + (this.minusEpsZeta * this.minusEpsZeta));
            double qrnorm = this.snprod * this.beta1;
            this.cgnorm = (this.beta * qrnorm) / FastMath.abs(diag);
            double d = this.lqnorm;
            double qrnorm2 = this.cgnorm;
            if (d <= qrnorm2) {
                acond = this.gmax / this.gmin;
            } else {
                double acond2 = this.gmax;
                acond = acond2 / FastMath.min(this.gmin, FastMath.abs(diag));
            }
            if (MACH_PREC * acond >= 0.1d) {
                throw new IllConditionedOperatorException(acond);
            }
            if (this.beta1 <= epsx) {
                throw new SingularOperatorException();
            }
            this.rnorm = FastMath.min(this.cgnorm, this.lqnorm);
            this.hasConverged = this.cgnorm <= epsx || this.cgnorm <= epsr;
        }

        boolean hasConverged() {
            return this.hasConverged;
        }

        boolean bEqualsNullVector() {
            return this.bIsNull;
        }

        boolean betaEqualsZero() {
            return this.beta < MACH_PREC;
        }

        double getNormOfResidual() {
            return this.rnorm;
        }
    }

    public SymmLQ(int maxIterations, double delta, boolean check) {
        super(maxIterations);
        this.delta = delta;
        this.check = check;
    }

    public SymmLQ(IterationManager manager, double delta, boolean check) {
        super(manager);
        this.delta = delta;
        this.check = check;
    }

    public final boolean getCheck() {
        return this.check;
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solve(RealLinearOperator a, RealLinearOperator m, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(a);
        RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, m, b, x, false, 0.0d);
    }

    public RealVector solve(RealLinearOperator a, RealLinearOperator m, RealVector b, boolean goodb, double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
        MathUtils.checkNotNull(a);
        RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, m, b, x, goodb, shift);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solve(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(x);
        return solveInPlace(a, m, b, x.copy(), false, 0.0d);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        RealVector x = new ArrayRealVector(a.getColumnDimension());
        x.set(0.0d);
        return solveInPlace(a, null, b, x, false, 0.0d);
    }

    public RealVector solve(RealLinearOperator a, RealVector b, boolean goodb, double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        RealVector x = new ArrayRealVector(a.getColumnDimension());
        return solveInPlace(a, null, b, x, goodb, shift);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solve(RealLinearOperator a, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        MathUtils.checkNotNull(x);
        return solveInPlace(a, null, b, x.copy(), false, 0.0d);
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(a, m, b, x, false, 0.0d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x005b, code lost:
    
        if (r9 == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x005d, code lost:
    
        r7.incrementIterationCount();
        r7.fireIterationStartedEvent(new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent(r18, r7.getIterations(), r22, r21, r8.getNormOfResidual()));
        r8.update();
        r8.refineSolution(r22);
        r7.fireIterationPerformedEvent(new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent(r18, r7.getIterations(), r22, r21, r8.getNormOfResidual()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0092, code lost:
    
        if (r8.hasConverged() == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0094, code lost:
    
        r7.fireTerminationEvent(new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent(r18, r7.getIterations(), r22, r21, r8.getNormOfResidual()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00aa, code lost:
    
        return r22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.linear.RealVector solveInPlace(org.apache.commons.math3.linear.RealLinearOperator r19, org.apache.commons.math3.linear.RealLinearOperator r20, org.apache.commons.math3.linear.RealVector r21, org.apache.commons.math3.linear.RealVector r22, boolean r23, double r24) throws org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.linear.NonSquareOperatorException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.linear.NonSelfAdjointOperatorException, org.apache.commons.math3.linear.NonPositiveDefiniteOperatorException, org.apache.commons.math3.linear.IllConditionedOperatorException, org.apache.commons.math3.exception.MaxCountExceededException {
        /*
            r18 = this;
            r1 = r18
            r3 = r22
            checkParameters(r19, r20, r21, r22)
            org.apache.commons.math3.util.IterationManager r7 = r1.getIterationManager()
            r7.resetIterationCount()
            r7.incrementIterationCount()
            org.apache.commons.math3.linear.SymmLQ$State r8 = new org.apache.commons.math3.linear.SymmLQ$State
            double r4 = r1.delta
            boolean r0 = r1.check
            r9 = r19
            r10 = r20
            r11 = r21
            r12 = r23
            r13 = r24
            r17 = r0
            r15 = r4
            r8.<init>(r9, r10, r11, r12, r13, r15, r17)
            r8.init()
            r8.refineSolution(r3)
            org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent r0 = new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent
            int r2 = r7.getIterations()
            double r5 = r8.getNormOfResidual()
            r4 = r21
            r0.<init>(r1, r2, r3, r4, r5)
            boolean r1 = r8.bEqualsNullVector()
            if (r1 == 0) goto L46
            r7.fireTerminationEvent(r0)
            return r22
        L46:
            boolean r1 = r8.betaEqualsZero()
            if (r1 != 0) goto L55
            boolean r1 = r8.hasConverged()
            if (r1 == 0) goto L53
            goto L55
        L53:
            r1 = 0
            goto L56
        L55:
            r1 = 1
        L56:
            r9 = r1
            r7.fireInitializationEvent(r0)
            r10 = r0
            if (r9 != 0) goto L94
        L5d:
            r7.incrementIterationCount()
            org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent r0 = new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent
            int r2 = r7.getIterations()
            double r5 = r8.getNormOfResidual()
            r1 = r18
            r4 = r21
            r3 = r22
            r0.<init>(r1, r2, r3, r4, r5)
            r10 = r0
            r7.fireIterationStartedEvent(r10)
            r8.update()
            r8.refineSolution(r3)
            org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent r0 = new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent
            int r2 = r7.getIterations()
            double r5 = r8.getNormOfResidual()
            r0.<init>(r1, r2, r3, r4, r5)
            r10 = r0
            r7.fireIterationPerformedEvent(r10)
            boolean r0 = r8.hasConverged()
            if (r0 == 0) goto L5d
        L94:
            org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent r0 = new org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent
            int r2 = r7.getIterations()
            double r5 = r8.getNormOfResidual()
            r1 = r18
            r4 = r21
            r3 = r22
            r0.<init>(r1, r2, r3, r4, r5)
            r7.fireTerminationEvent(r0)
            return r22
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.SymmLQ.solveInPlace(org.apache.commons.math3.linear.RealLinearOperator, org.apache.commons.math3.linear.RealLinearOperator, org.apache.commons.math3.linear.RealVector, org.apache.commons.math3.linear.RealVector, boolean, double):org.apache.commons.math3.linear.RealVector");
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver, org.apache.commons.math3.linear.IterativeLinearSolver
    public RealVector solveInPlace(RealLinearOperator a, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
        return solveInPlace(a, null, b, x, false, 0.0d);
    }
}
