package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.events.FieldEventHandler;
import org.apache.commons.math3.ode.events.FieldEventState;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.IntegerSequence;

/* loaded from: classes10.dex */
public abstract class AbstractFieldIntegrator<T extends RealFieldElement<T>> implements FirstOrderFieldIntegrator<T> {
    private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15d;
    private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14d;
    private transient FieldExpandableODE<T> equations;
    private final Field<T> field;
    private boolean isLastStep;
    private final String name;
    private boolean resetOccurred;
    private Collection<FieldStepHandler<T>> stepHandlers = new ArrayList();
    private FieldODEStateAndDerivative<T> stepStart = null;
    private T stepSize = null;
    private Collection<FieldEventState<T>> eventsStates = new ArrayList();
    private boolean statesInitialized = false;
    private IntegerSequence.Incrementor evaluations = IntegerSequence.Incrementor.create().withMaximalCount(Integer.MAX_VALUE);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFieldIntegrator(Field<T> field, String name) {
        this.field = field;
        this.name = name;
    }

    public Field<T> getField() {
        return this.field;
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public String getName() {
        return this.name;
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void addStepHandler(FieldStepHandler<T> handler) {
        this.stepHandlers.add(handler);
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public Collection<FieldStepHandler<T>> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void addEventHandler(FieldEventHandler<T> handler, double maxCheckInterval, double convergence, int maxIterationCount) {
        addEventHandler(handler, maxCheckInterval, convergence, maxIterationCount, new FieldBracketingNthOrderBrentSolver((RealFieldElement) this.field.getZero().add(DEFAULT_RELATIVE_ACCURACY), (RealFieldElement) this.field.getZero().add(convergence), (RealFieldElement) this.field.getZero().add(1.0E-15d), 5));
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void addEventHandler(FieldEventHandler<T> handler, double maxCheckInterval, double convergence, int maxIterationCount, BracketedRealFieldUnivariateSolver<T> solver) {
        this.eventsStates.add(new FieldEventState<>(handler, maxCheckInterval, (RealFieldElement) this.field.getZero().add(convergence), maxIterationCount, solver));
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public Collection<FieldEventHandler<T>> getEventHandlers() {
        List<FieldEventHandler<T>> list = new ArrayList<>(this.eventsStates.size());
        for (FieldEventState<T> state : this.eventsStates) {
            list.add(state.getEventHandler());
        }
        return Collections.unmodifiableCollection(list);
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public FieldODEStateAndDerivative<T> getCurrentStepStart() {
        return this.stepStart;
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public T getCurrentSignedStepsize() {
        return this.stepSize;
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public void setMaxEvaluations(int maxEvaluations) {
        this.evaluations = this.evaluations.withMaximalCount(maxEvaluations < 0 ? Integer.MAX_VALUE : maxEvaluations);
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> initIntegration(FieldExpandableODE<T> eqn, T t0, T[] y0, T t) {
        this.equations = eqn;
        this.evaluations = this.evaluations.withStart(0);
        eqn.init(t0, y0, t);
        T[] y0Dot = computeDerivatives(t0, y0);
        FieldODEStateAndDerivative<T> state0 = new FieldODEStateAndDerivative<>(t0, y0, y0Dot);
        for (FieldEventState<T> state : this.eventsStates) {
            state.getEventHandler().init(state0, t);
        }
        for (FieldStepHandler<T> handler : this.stepHandlers) {
            handler.init(state0, t);
        }
        setStateInitialized(false);
        return state0;
    }

    protected FieldExpandableODE<T> getEquations() {
        return this.equations;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IntegerSequence.Incrementor getEvaluationsCounter() {
        return this.evaluations;
    }

    public T[] computeDerivatives(T t, T[] y) throws DimensionMismatchException, MaxCountExceededException, NullPointerException {
        this.evaluations.increment();
        return this.equations.computeDerivatives(t, y);
    }

    protected void setStateInitialized(boolean stateInitialized) {
        this.statesInitialized = stateInitialized;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v4, types: [org.apache.commons.math3.ode.sampling.FieldStepHandler] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v4, types: [org.apache.commons.math3.ode.sampling.FieldStepInterpolator, org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator] */
    /* JADX WARN: Type inference failed for: r7v9 */
    public FieldODEStateAndDerivative<T> acceptStep(AbstractFieldStepInterpolator<T> abstractFieldStepInterpolator, T t) throws MaxCountExceededException, DimensionMismatchException, NoBracketingException {
        FieldODEStateAndDerivative<T> globalPreviousState = abstractFieldStepInterpolator.getGlobalPreviousState();
        FieldODEStateAndDerivative<T> globalCurrentState = abstractFieldStepInterpolator.getGlobalCurrentState();
        boolean z = true;
        if (!this.statesInitialized) {
            Iterator<FieldEventState<T>> it = this.eventsStates.iterator();
            while (it.hasNext()) {
                it.next().reinitializeBegin(abstractFieldStepInterpolator);
            }
            this.statesInitialized = true;
        }
        final int i = abstractFieldStepInterpolator.isForward() ? 1 : -1;
        TreeSet treeSet = new TreeSet(new Comparator<FieldEventState<T>>() { // from class: org.apache.commons.math3.ode.AbstractFieldIntegrator.1
            @Override // java.util.Comparator
            public int compare(FieldEventState<T> es0, FieldEventState<T> es1) {
                return i * Double.compare(es0.getEventTime().getReal(), es1.getEventTime().getReal());
            }
        });
        for (FieldEventState<T> fieldEventState : this.eventsStates) {
            if (fieldEventState.evaluateStep(abstractFieldStepInterpolator)) {
                treeSet.add(fieldEventState);
            }
        }
        ?? r7 = abstractFieldStepInterpolator;
        while (!treeSet.isEmpty()) {
            Iterator it2 = treeSet.iterator();
            FieldEventState fieldEventState2 = (FieldEventState) it2.next();
            it2.remove();
            FieldODEStateAndDerivative<T> interpolatedState = r7.getInterpolatedState(fieldEventState2.getEventTime());
            AbstractFieldStepInterpolator restrictStep = r7.restrictStep(globalPreviousState, interpolatedState);
            for (FieldEventState<T> fieldEventState3 : this.eventsStates) {
                fieldEventState3.stepAccepted(interpolatedState);
                this.isLastStep = this.isLastStep || fieldEventState3.stop();
            }
            Iterator<FieldStepHandler<T>> it3 = this.stepHandlers.iterator();
            while (it3.hasNext()) {
                it3.next().handleStep(restrictStep, this.isLastStep);
            }
            if (this.isLastStep) {
                return interpolatedState;
            }
            this.resetOccurred = false;
            Iterator<FieldEventState<T>> it4 = this.eventsStates.iterator();
            while (it4.hasNext()) {
                FieldODEState<T> reset = it4.next().reset(interpolatedState);
                if (reset != null) {
                    T[] mapState = this.equations.getMapper().mapState(reset);
                    T[] computeDerivatives = computeDerivatives(reset.getTime(), mapState);
                    this.resetOccurred = true;
                    return this.equations.getMapper().mapStateAndDerivative(reset.getTime(), mapState, computeDerivatives);
                }
            }
            globalPreviousState = interpolatedState;
            AbstractFieldStepInterpolator<T> restrictStep2 = restrictStep.restrictStep(interpolatedState, globalCurrentState);
            if (fieldEventState2.evaluateStep(restrictStep2)) {
                treeSet.add(fieldEventState2);
            }
            r7 = restrictStep2;
        }
        for (FieldEventState<T> fieldEventState4 : this.eventsStates) {
            fieldEventState4.stepAccepted(globalCurrentState);
            this.isLastStep = this.isLastStep || fieldEventState4.stop();
        }
        if (!this.isLastStep && ((RealFieldElement) ((RealFieldElement) globalCurrentState.getTime().subtract(t)).abs()).getReal() > FastMath.ulp(t.getReal())) {
            z = false;
        }
        this.isLastStep = z;
        Iterator<FieldStepHandler<T>> it5 = this.stepHandlers.iterator();
        while (it5.hasNext()) {
            ((FieldStepHandler) it5.next()).handleStep(r7, this.isLastStep);
        }
        return globalCurrentState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sanityChecks(FieldODEState<T> eqn, T t) throws NumberIsTooSmallException, DimensionMismatchException {
        double threshold = FastMath.ulp(FastMath.max(FastMath.abs(eqn.getTime().getReal()), FastMath.abs(t.getReal()))) * 1000.0d;
        double dt = ((RealFieldElement) ((RealFieldElement) eqn.getTime().subtract(t)).abs()).getReal();
        if (dt <= threshold) {
            throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, Double.valueOf(dt), Double.valueOf(threshold), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean resetOccurred() {
        return this.resetOccurred;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStepSize(T stepSize) {
        this.stepSize = stepSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T getStepSize() {
        return this.stepSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStepStart(FieldODEStateAndDerivative<T> stepStart) {
        this.stepStart = stepStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> getStepStart() {
        return this.stepStart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIsLastStep(boolean isLastStep) {
        this.isLastStep = isLastStep;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLastStep() {
        return this.isLastStep;
    }
}
