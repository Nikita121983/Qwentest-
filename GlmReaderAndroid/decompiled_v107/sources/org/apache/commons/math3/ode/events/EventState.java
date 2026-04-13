package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class EventState {
    private final double convergence;
    private boolean forward;
    private final EventHandler handler;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private final UnivariateSolver solver;
    private ExpandableStatefulODE expandable = null;
    private double t0 = Double.NaN;
    private double g0 = Double.NaN;
    private boolean g0Positive = true;
    private boolean pendingEvent = false;
    private double pendingEventTime = Double.NaN;
    private double previousEventTime = Double.NaN;
    private boolean increasing = true;
    private EventHandler.Action nextAction = EventHandler.Action.CONTINUE;

    public EventState(EventHandler handler, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = FastMath.abs(convergence);
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public void setExpandable(ExpandableStatefulODE expandable) {
        this.expandable = expandable;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(StepInterpolator interpolator) throws MaxCountExceededException {
        this.t0 = interpolator.getPreviousTime();
        interpolator.setInterpolatedTime(this.t0);
        this.g0 = this.handler.g(this.t0, getCompleteState(interpolator));
        if (this.g0 == 0.0d) {
            double epsilon = FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0));
            double tStart = this.t0 + (0.5d * epsilon);
            interpolator.setInterpolatedTime(tStart);
            this.g0 = this.handler.g(tStart, getCompleteState(interpolator));
        }
        double epsilon2 = this.g0;
        this.g0Positive = epsilon2 >= 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double[] getCompleteState(StepInterpolator interpolator) {
        double[] complete = new double[this.expandable.getTotalDimension()];
        this.expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), complete);
        int index = 0;
        EquationsMapper[] arr$ = this.expandable.getSecondaryMappers();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            EquationsMapper secondary = arr$[i$];
            secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), complete);
            i$++;
            index++;
        }
        return complete;
    }

    public boolean evaluateStep(StepInterpolator interpolator) throws MaxCountExceededException, NoBracketingException {
        double t1;
        double d;
        int n;
        double dt;
        double baseRoot;
        double forceSide;
        double ta;
        double tb;
        double ta2;
        final StepInterpolator stepInterpolator = interpolator;
        try {
            this.forward = stepInterpolator.isForward();
            double t12 = stepInterpolator.getCurrentTime();
            double dt2 = t12 - this.t0;
            if (FastMath.abs(dt2) < this.convergence) {
                return false;
            }
            int n2 = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(dt2) / this.maxCheckInterval));
            double h = dt2 / n2;
            UnivariateFunction f = new UnivariateFunction() { // from class: org.apache.commons.math3.ode.events.EventState.1
                @Override // org.apache.commons.math3.analysis.UnivariateFunction
                public double value(double t) throws LocalMaxCountExceededException {
                    try {
                        stepInterpolator.setInterpolatedTime(t);
                        return EventState.this.handler.g(t, EventState.this.getCompleteState(stepInterpolator));
                    } catch (MaxCountExceededException mcee) {
                        throw new LocalMaxCountExceededException(mcee);
                    }
                }
            };
            double ta3 = this.t0;
            double ga = this.g0;
            double tb2 = ta3;
            int i = 0;
            while (i < n2) {
                if (i == n2 - 1) {
                    d = t12;
                    t1 = d;
                } else {
                    t1 = t12;
                    d = this.t0 + ((i + 1) * h);
                }
                double tb3 = d;
                stepInterpolator.setInterpolatedTime(tb3);
                double gb = this.handler.g(tb3, getCompleteState(interpolator));
                if (this.g0Positive ^ (gb >= 0.0d)) {
                    this.increasing = gb >= ga;
                    if (this.solver instanceof BracketedUnivariateSolver) {
                        BracketedUnivariateSolver<UnivariateFunction> bracketing = (BracketedUnivariateSolver) this.solver;
                        if (this.forward) {
                            double ta4 = tb2;
                            tb = bracketing.solve(this.maxIterationCount, (int) f, ta4, tb3, AllowedSolution.RIGHT_SIDE);
                            tb2 = ta4;
                            ta = tb3;
                        } else {
                            ta = tb3;
                            tb = bracketing.solve(this.maxIterationCount, (int) f, ta, tb2, AllowedSolution.LEFT_SIDE);
                        }
                        n = n2;
                        dt = dt2;
                    } else {
                        double ta5 = tb3;
                        if (this.forward) {
                            double ta6 = tb2;
                            baseRoot = this.solver.solve(this.maxIterationCount, f, ta6, ta5);
                            tb2 = ta6;
                            ta5 = ta5;
                        } else {
                            baseRoot = this.solver.solve(this.maxIterationCount, f, ta5, tb2);
                        }
                        int remainingEval = this.maxIterationCount - this.solver.getEvaluations();
                        n = n2;
                        double baseRoot2 = baseRoot;
                        dt = dt2;
                        BracketedUnivariateSolver<UnivariateFunction> bracketing2 = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
                        if (this.forward) {
                            UnivariateFunction f2 = f;
                            double baseRoot3 = ta5;
                            forceSide = UnivariateSolverUtils.forceSide(remainingEval, f2, bracketing2, baseRoot2, tb2, baseRoot3, AllowedSolution.RIGHT_SIDE);
                            f = f2;
                            ta = baseRoot3;
                        } else {
                            UnivariateFunction f3 = f;
                            double ta7 = tb2;
                            double ta8 = ta5;
                            forceSide = UnivariateSolverUtils.forceSide(remainingEval, f3, bracketing2, baseRoot2, ta8, ta7, AllowedSolution.LEFT_SIDE);
                            f = f3;
                            ta = ta8;
                            tb2 = ta7;
                        }
                        tb = forceSide;
                    }
                    if (Double.isNaN(this.previousEventTime) || FastMath.abs(tb - tb2) > this.convergence || FastMath.abs(tb - this.previousEventTime) > this.convergence) {
                        double ta9 = this.previousEventTime;
                        if (!Double.isNaN(ta9) && FastMath.abs(this.previousEventTime - tb) <= this.convergence) {
                            ta2 = ta;
                            ga = gb;
                        }
                        this.pendingEventTime = tb;
                        this.pendingEvent = true;
                        return true;
                    }
                    while (true) {
                        ta2 = this.forward ? tb2 + this.convergence : tb2 - this.convergence;
                        ga = f.value(ta2);
                        if (!(this.g0Positive ^ (ga >= 0.0d))) {
                            break;
                        }
                        if (!(this.forward ^ (ta2 >= ta))) {
                            break;
                        }
                        tb2 = ta2;
                    }
                    if (this.forward ^ (ta2 >= ta)) {
                        i--;
                    } else {
                        this.pendingEventTime = tb;
                        this.pendingEvent = true;
                        return true;
                    }
                    tb2 = ta2;
                } else {
                    n = n2;
                    dt = dt2;
                    tb2 = tb3;
                    ga = gb;
                }
                i++;
                stepInterpolator = interpolator;
                t12 = t1;
                n2 = n;
                dt2 = dt;
            }
            this.pendingEvent = false;
            this.pendingEventTime = Double.NaN;
            return false;
        } catch (LocalMaxCountExceededException lmcee) {
            throw lmcee.getException();
        }
    }

    public double getEventTime() {
        return this.pendingEvent ? this.pendingEventTime : this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public void stepAccepted(double t, double[] y) {
        this.t0 = t;
        this.g0 = this.handler.g(t, y);
        if (this.pendingEvent && FastMath.abs(this.pendingEventTime - t) <= this.convergence) {
            this.previousEventTime = t;
            this.g0Positive = this.increasing;
            this.nextAction = this.handler.eventOccurred(t, y, true ^ (this.increasing ^ this.forward));
        } else {
            this.g0Positive = this.g0 >= 0.0d;
            this.nextAction = EventHandler.Action.CONTINUE;
        }
    }

    public boolean stop() {
        return this.nextAction == EventHandler.Action.STOP;
    }

    public boolean reset(double t, double[] y) {
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - t) > this.convergence) {
            return false;
        }
        if (this.nextAction == EventHandler.Action.RESET_STATE) {
            this.handler.resetState(t, y);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        return this.nextAction == EventHandler.Action.RESET_STATE || this.nextAction == EventHandler.Action.RESET_DERIVATIVES;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class LocalMaxCountExceededException extends RuntimeException {
        private static final long serialVersionUID = 20120901;
        private final MaxCountExceededException wrapped;

        LocalMaxCountExceededException(MaxCountExceededException exception) {
            this.wrapped = exception;
        }

        public MaxCountExceededException getException() {
            return this.wrapped;
        }
    }
}
