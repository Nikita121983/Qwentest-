package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class FieldEventState<T extends RealFieldElement<T>> {
    private final T convergence;
    private boolean forward;
    private final FieldEventHandler<T> handler;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private final BracketedRealFieldUnivariateSolver<T> solver;
    private T t0 = null;
    private T g0 = null;
    private boolean g0Positive = true;
    private boolean pendingEvent = false;
    private T pendingEventTime = null;
    private T previousEventTime = null;
    private boolean increasing = true;
    private Action nextAction = Action.CONTINUE;

    public FieldEventState(FieldEventHandler<T> handler, double maxCheckInterval, T convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver<T> solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = (T) convergence.abs();
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
    }

    public FieldEventHandler<T> getEventHandler() {
        return this.handler;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public T getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void reinitializeBegin(FieldStepInterpolator<T> fieldStepInterpolator) throws MaxCountExceededException {
        FieldODEStateAndDerivative<T> s0 = fieldStepInterpolator.getPreviousState();
        this.t0 = s0.getTime();
        this.g0 = this.handler.g(s0);
        if (this.g0.getReal() == 0.0d) {
            double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy().getReal(), FastMath.abs(((RealFieldElement) this.solver.getRelativeAccuracy().multiply(this.t0)).getReal()));
            this.g0 = this.handler.g(fieldStepInterpolator.getInterpolatedState((RealFieldElement) this.t0.add(0.5d * epsilon)));
        }
        this.g0Positive = this.g0.getReal() >= 0.0d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0119, code lost:
    
        if (((org.apache.commons.math3.RealFieldElement) ((org.apache.commons.math3.RealFieldElement) ((org.apache.commons.math3.RealFieldElement) r12.subtract(r23.previousEventTime)).abs()).subtract(r23.convergence)).getReal() <= r18) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x011d, code lost:
    
        if (r23.forward == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x011f, code lost:
    
        r6 = r15.add(r23.convergence);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x012c, code lost:
    
        r15 = (org.apache.commons.math3.RealFieldElement) r6;
        r11 = r11.value(r15);
        r6 = r23.g0Positive;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x013b, code lost:
    
        if (r11.getReal() < r18) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x013d, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0141, code lost:
    
        if ((r6 ^ r9) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0143, code lost:
    
        r6 = r23.forward;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0151, code lost:
    
        if (((org.apache.commons.math3.RealFieldElement) r15.subtract(r13)).getReal() < r18) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0153, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0157, code lost:
    
        if ((r6 ^ r9) != false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0159, code lost:
    
        r6 = r23.forward;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0167, code lost:
    
        if (((org.apache.commons.math3.RealFieldElement) r15.subtract(r13)).getReal() < r18) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0169, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x016d, code lost:
    
        if ((r6 ^ r9) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x016f, code lost:
    
        r12 = r7 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0173, code lost:
    
        r23.pendingEventTime = r12;
        r23.pendingEvent = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0178, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x016b, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0155, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x013f, code lost:
    
        r9 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0126, code lost:
    
        r6 = r15.subtract(r23.convergence);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v11, types: [org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver<T extends org.apache.commons.math3.RealFieldElement<T>>, org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver] */
    /* JADX WARN: Type inference failed for: r12v16, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v5, types: [org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver<T extends org.apache.commons.math3.RealFieldElement<T>>, org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r6v38, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r7v6, types: [org.apache.commons.math3.RealFieldElement] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean evaluateStep(final org.apache.commons.math3.ode.sampling.FieldStepInterpolator<T> r24) throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            Method dump skipped, instructions count: 443
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.events.FieldEventState.evaluateStep(org.apache.commons.math3.ode.sampling.FieldStepInterpolator):boolean");
    }

    public T getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return (T) ((RealFieldElement) this.t0.getField().getZero()).add(this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    public void stepAccepted(FieldODEStateAndDerivative<T> state) {
        this.t0 = state.getTime();
        this.g0 = this.handler.g(state);
        if (this.pendingEvent && ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() <= 0.0d) {
            this.previousEventTime = state.getTime();
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(state, true ^ (this.increasing ^ this.forward));
        } else {
            this.g0Positive = this.g0.getReal() >= 0.0d;
            this.nextAction = Action.CONTINUE;
        }
    }

    public boolean stop() {
        return this.nextAction == Action.STOP;
    }

    public FieldODEState<T> reset(FieldODEStateAndDerivative<T> state) {
        FieldODEState<T> newState;
        if (!this.pendingEvent || ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) this.pendingEventTime.subtract(state.getTime())).abs()).subtract(this.convergence)).getReal() > 0.0d) {
            return null;
        }
        if (this.nextAction == Action.RESET_STATE) {
            newState = this.handler.resetState(state);
        } else if (this.nextAction == Action.RESET_DERIVATIVES) {
            newState = state;
        } else {
            newState = null;
        }
        this.pendingEvent = false;
        this.pendingEventTime = null;
        return newState;
    }
}
