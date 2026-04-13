package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;

/* loaded from: classes10.dex */
public class DefaultIterativeLinearSolverEvent extends IterativeLinearSolverEvent {
    private static final long serialVersionUID = 20120129;
    private final RealVector b;
    private final RealVector r;
    private final double rnorm;
    private final RealVector x;

    public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, RealVector r, double rnorm) {
        super(source, iterations);
        this.x = x;
        this.b = b;
        this.r = r;
        this.rnorm = rnorm;
    }

    public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, double rnorm) {
        super(source, iterations);
        this.x = x;
        this.b = b;
        this.r = null;
        this.rnorm = rnorm;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public double getNormOfResidual() {
        return this.rnorm;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getResidual() {
        if (this.r != null) {
            return this.r;
        }
        throw new MathUnsupportedOperationException();
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getRightHandSideVector() {
        return this.b;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public RealVector getSolution() {
        return this.x;
    }

    @Override // org.apache.commons.math3.linear.IterativeLinearSolverEvent
    public boolean providesResidual() {
        return this.r != null;
    }
}
