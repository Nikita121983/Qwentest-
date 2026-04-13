package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldIntegrator;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public abstract class MultistepFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> {
    private double exp;
    private double maxGrowth;
    private double minReduction;
    private final int nSteps;
    protected Array2DRowFieldMatrix<T> nordsieck;
    private double safety;
    protected T[] scaled;
    private FirstOrderFieldIntegrator<T> starter;

    protected abstract Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T t, T[] tArr, T[][] tArr2, T[][] tArr3);

    /* JADX INFO: Access modifiers changed from: protected */
    public MultistepFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        if (nSteps < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, Integer.valueOf(nSteps), 2, true);
        }
        this.starter = new DormandPrince853FieldIntegrator(field, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MultistepFieldIntegrator(Field<T> field, String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(field, name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.starter = new DormandPrince853FieldIntegrator(field, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    public FirstOrderFieldIntegrator<T> getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderFieldIntegrator<T> starterIntegrator) {
        this.starter = starterIntegrator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void start(FieldExpandableODE<T> equations, FieldODEState<T> initialState, T t) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new FieldNordsieckInitializer(equations.getMapper(), (this.nSteps + 3) / 2));
        try {
            this.starter.integrate(equations, initialState, t);
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY, new Object[0]);
        } catch (InitializationCompletedMarkerException e) {
            getEvaluationsCounter().increment(this.starter.getEvaluations());
            this.starter.clearStepHandlers();
        }
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double minReduction) {
        this.minReduction = minReduction;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    public int getNSteps() {
        return this.nSteps;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void rescale(T t) {
        RealFieldElement realFieldElement = (RealFieldElement) t.divide(getStepSize());
        for (int i = 0; i < this.scaled.length; i++) {
            ((T[]) this.scaled)[i] = (RealFieldElement) this.scaled[i].multiply(realFieldElement);
        }
        RealFieldElement realFieldElement2 = realFieldElement;
        for (FieldElement[] fieldElementArr : this.nordsieck.getDataRef()) {
            realFieldElement2 = (RealFieldElement) realFieldElement2.multiply(realFieldElement);
            for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                fieldElementArr[i2] = (RealFieldElement) fieldElementArr[i2].multiply(realFieldElement2);
            }
        }
        setStepSize(t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T computeStepGrowShrinkFactor(T t) {
        return (T) MathUtils.min((RealFieldElement) ((RealFieldElement) t.getField().getZero()).add(this.maxGrowth), MathUtils.max((RealFieldElement) ((RealFieldElement) t.getField().getZero()).add(this.minReduction), (RealFieldElement) ((RealFieldElement) t.pow(this.exp)).multiply(this.safety)));
    }

    /* loaded from: classes10.dex */
    private class FieldNordsieckInitializer implements FieldStepHandler<T> {
        private int count = 0;
        private final FieldEquationsMapper<T> mapper;
        private FieldODEStateAndDerivative<T> savedStart;
        private final T[] t;
        private final T[][] y;
        private final T[][] yDot;

        FieldNordsieckInitializer(FieldEquationsMapper<T> fieldEquationsMapper, int i) {
            this.mapper = fieldEquationsMapper;
            this.t = (T[]) ((RealFieldElement[]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i));
            this.y = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i, -1));
            this.yDot = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), i, -1));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
        public void handleStep(FieldStepInterpolator<T> fieldStepInterpolator, boolean z) throws MaxCountExceededException {
            if (this.count == 0) {
                FieldODEStateAndDerivative<T> previousState = fieldStepInterpolator.getPreviousState();
                this.savedStart = previousState;
                this.t[this.count] = previousState.getTime();
                this.y[this.count] = this.mapper.mapState(previousState);
                this.yDot[this.count] = this.mapper.mapDerivative(previousState);
            }
            this.count++;
            FieldODEStateAndDerivative<T> currentState = fieldStepInterpolator.getCurrentState();
            this.t[this.count] = currentState.getTime();
            this.y[this.count] = this.mapper.mapState(currentState);
            this.yDot[this.count] = this.mapper.mapDerivative(currentState);
            if (this.count == this.t.length - 1) {
                MultistepFieldIntegrator.this.setStepSize((RealFieldElement) ((RealFieldElement) this.t[this.t.length - 1].subtract(this.t[0])).divide(this.t.length - 1));
                MultistepFieldIntegrator.this.scaled = (T[]) ((RealFieldElement[]) MathArrays.buildArray(MultistepFieldIntegrator.this.getField(), this.yDot[0].length));
                for (int i = 0; i < MultistepFieldIntegrator.this.scaled.length; i++) {
                    ((T[]) MultistepFieldIntegrator.this.scaled)[i] = (RealFieldElement) this.yDot[0][i].multiply(MultistepFieldIntegrator.this.getStepSize());
                }
                MultistepFieldIntegrator.this.nordsieck = MultistepFieldIntegrator.this.initializeHighOrderDerivatives(MultistepFieldIntegrator.this.getStepSize(), this.t, this.y, this.yDot);
                MultistepFieldIntegrator.this.setStepStart(this.savedStart);
                throw new InitializationCompletedMarkerException();
            }
        }

        @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
        public void init(FieldODEStateAndDerivative<T> initialState, T finalTime) {
        }
    }

    /* loaded from: classes10.dex */
    private static class InitializationCompletedMarkerException extends RuntimeException {
        private static final long serialVersionUID = -1914085471038046418L;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        InitializationCompletedMarkerException() {
            super((Throwable) null);
        }
    }
}
