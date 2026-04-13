package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.util.IterationManager;

/* loaded from: classes10.dex */
public class ConjugateGradient extends PreconditionedIterativeLinearSolver {
    public static final String OPERATOR = "operator";
    public static final String VECTOR = "vector";
    private boolean check;
    private final double delta;

    public ConjugateGradient(int maxIterations, double delta, boolean check) {
        super(maxIterations);
        this.delta = delta;
        this.check = check;
    }

    public ConjugateGradient(IterationManager manager, double delta, boolean check) throws NullArgumentException {
        super(manager);
        this.delta = delta;
        this.check = check;
    }

    public final boolean getCheck() {
        return this.check;
    }

    @Override // org.apache.commons.math3.linear.PreconditionedIterativeLinearSolver
    public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x0) throws NullArgumentException, NonPositiveDefiniteOperatorException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        RealVector z;
        RealVector xro;
        RealVector z2;
        RealLinearOperator realLinearOperator = a;
        RealLinearOperator realLinearOperator2 = m;
        checkParameters(a, m, b, x0);
        IterationManager manager = getIterationManager();
        manager.resetIterationCount();
        double rmax = this.delta * b.getNorm();
        RealVector bro = RealVector.unmodifiableRealVector(b);
        manager.incrementIterationCount();
        RealVector xro2 = RealVector.unmodifiableRealVector(x0);
        RealVector p = x0.copy();
        RealVector r = b.combine(1.0d, -1.0d, realLinearOperator.operate(p));
        RealVector rro = RealVector.unmodifiableRealVector(r);
        double rnorm = r.getNorm();
        if (realLinearOperator2 == null) {
            z = r;
        } else {
            z = null;
        }
        IterativeLinearSolverEvent evt = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro2, bro, rro, rnorm);
        manager.fireInitializationEvent(evt);
        if (rnorm <= rmax) {
            manager.fireTerminationEvent(evt);
            return x0;
        }
        double rhoPrev = 0.0d;
        while (true) {
            manager.incrementIterationCount();
            manager.fireIterationStartedEvent(new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro2, bro, rro, rnorm));
            if (realLinearOperator2 != null) {
                z = realLinearOperator2.operate(r);
            }
            double rhoNext = r.dotProduct(z);
            if (!this.check) {
                xro = xro2;
            } else {
                if (rhoNext <= 0.0d) {
                    NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
                    ExceptionContext context = e.getContext();
                    context.setValue(OPERATOR, realLinearOperator2);
                    context.setValue(VECTOR, r);
                    throw e;
                }
                xro = xro2;
            }
            RealVector bro2 = bro;
            if (manager.getIterations() == 2) {
                p.setSubVector(0, z);
                z2 = z;
            } else {
                z2 = z;
                p.combineToSelf(rhoNext / rhoPrev, 1.0d, z2);
            }
            RealVector q = realLinearOperator.operate(p);
            double pq = p.dotProduct(q);
            if (this.check && pq <= 0.0d) {
                NonPositiveDefiniteOperatorException e2 = new NonPositiveDefiniteOperatorException();
                ExceptionContext context2 = e2.getContext();
                context2.setValue(OPERATOR, realLinearOperator);
                context2.setValue(VECTOR, p);
                throw e2;
            }
            double alpha = rhoNext / pq;
            RealVector p2 = p;
            x0.combineToSelf(1.0d, alpha, p2);
            p = p2;
            r.combineToSelf(1.0d, -alpha, q);
            rhoPrev = rhoNext;
            rnorm = r.getNorm();
            xro2 = xro;
            bro = bro2;
            IterativeLinearSolverEvent evt2 = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro2, bro, rro, rnorm);
            manager.fireIterationPerformedEvent(evt2);
            if (rnorm <= rmax) {
                manager.fireTerminationEvent(evt2);
                return x0;
            }
            realLinearOperator = a;
            realLinearOperator2 = m;
            z = z2;
        }
    }
}
