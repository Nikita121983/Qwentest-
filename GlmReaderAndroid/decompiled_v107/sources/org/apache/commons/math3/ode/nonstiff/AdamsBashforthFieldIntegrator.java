package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class AdamsBashforthFieldIntegrator<T extends RealFieldElement<T>> extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsBashforthFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, METHOD_NAME, nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    private T errorEstimation(T[] previousState, T[] predictedState, T[] predictedScaled, FieldMatrix<T> predictedNordsieck) {
        T zero = getField().getZero();
        for (int i = 0; i < this.mainSetDimension; i++) {
            RealFieldElement realFieldElement = (RealFieldElement) predictedState[i].abs();
            RealFieldElement realFieldElement2 = (RealFieldElement) (this.vecAbsoluteTolerance == null ? ((RealFieldElement) realFieldElement.multiply(this.scalRelativeTolerance)).add(this.scalAbsoluteTolerance) : ((RealFieldElement) realFieldElement.multiply(this.vecRelativeTolerance[i])).add(this.vecAbsoluteTolerance[i]));
            T zero2 = getField().getZero();
            int sign = predictedNordsieck.getRowDimension() % 2 == 0 ? -1 : 1;
            for (int k = predictedNordsieck.getRowDimension() - 1; k >= 0; k--) {
                zero2 = (T) zero2.add(predictedNordsieck.getEntry(k, i).multiply(sign));
                sign = -sign;
            }
            RealFieldElement realFieldElement3 = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) predictedState[i].subtract(previousState[i])).add((RealFieldElement) zero2.subtract(predictedScaled[i]))).divide(realFieldElement2);
            zero = (T) zero.add(realFieldElement3.multiply(realFieldElement3));
        }
        int i2 = this.mainSetDimension;
        return (T) ((RealFieldElement) zero.divide(i2)).sqrt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v7, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r12v0, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v5, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v4, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r9v19, types: [org.apache.commons.math3.RealFieldElement] */
    @Override // org.apache.commons.math3.ode.nonstiff.AdamsFieldIntegrator, org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> fieldExpandableODE, FieldODEState<T> fieldODEState, T t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        FieldODEStateAndDerivative fieldODEStateAndDerivative;
        FieldODEStateAndDerivative fieldODEStateAndDerivative2;
        T[] tArr;
        T t2;
        boolean z;
        FieldExpandableODE<T> fieldExpandableODE2 = fieldExpandableODE;
        T t3 = t;
        sanityChecks(fieldODEState, t3);
        T time = fieldODEState.getTime();
        T[] mapState = fieldExpandableODE2.getMapper().mapState(fieldODEState);
        setStepStart(initIntegration(fieldExpandableODE2, time, mapState, t3));
        double d = 0.0d;
        boolean z2 = ((RealFieldElement) t3.subtract(fieldODEState.getTime())).getReal() > 0.0d;
        start(fieldExpandableODE2, getStepStart(), t3);
        FieldODEStateAndDerivative stepStart = getStepStart();
        FieldODEStateAndDerivative taylor = AdamsFieldStepInterpolator.taylor(stepStart, (RealFieldElement) stepStart.getTime().add(getStepSize()), getStepSize(), this.scaled, this.nordsieck);
        setIsLastStep(false);
        while (true) {
            T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), mapState.length));
            Array2DRowFieldMatrix<T> array2DRowFieldMatrix = null;
            double d2 = d;
            RealFieldElement realFieldElement = (RealFieldElement) ((RealFieldElement) getField().getZero()).add(10.0d);
            Object obj = null;
            FieldODEStateAndDerivative fieldODEStateAndDerivative3 = taylor;
            while (true) {
                fieldODEStateAndDerivative = stepStart;
                if (((RealFieldElement) realFieldElement.subtract(1.0d)).getReal() < d2) {
                    break;
                }
                obj = fieldODEStateAndDerivative3.getState();
                RealFieldElement[] computeDerivatives = computeDerivatives(fieldODEStateAndDerivative3.getTime(), obj);
                for (int i = 0; i < tArr2.length; i++) {
                    tArr2[i] = (RealFieldElement) getStepSize().multiply(computeDerivatives[i]);
                }
                array2DRowFieldMatrix = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, tArr2, array2DRowFieldMatrix);
                realFieldElement = errorEstimation(mapState, obj, tArr2, array2DRowFieldMatrix);
                if (((RealFieldElement) realFieldElement.subtract(1.0d)).getReal() >= d2) {
                    rescale(filterStep((RealFieldElement) getStepSize().multiply(computeStepGrowShrinkFactor(realFieldElement)), z2, false));
                    t2 = time;
                    z = z2;
                    fieldODEStateAndDerivative3 = AdamsFieldStepInterpolator.taylor(getStepStart(), (RealFieldElement) getStepStart().getTime().add(getStepSize()), getStepSize(), this.scaled, this.nordsieck);
                } else {
                    t2 = time;
                    z = z2;
                }
                stepStart = fieldODEStateAndDerivative;
                z2 = z;
                time = t2;
            }
            T t4 = time;
            setStepStart(acceptStep(new AdamsFieldStepInterpolator(getStepSize(), fieldODEStateAndDerivative3, tArr2, array2DRowFieldMatrix, z2, getStepStart(), fieldODEStateAndDerivative3, fieldExpandableODE2.getMapper()), t3));
            this.scaled = tArr2;
            this.nordsieck = array2DRowFieldMatrix;
            if (isLastStep()) {
                fieldODEStateAndDerivative2 = fieldODEStateAndDerivative;
                tArr = mapState;
                taylor = fieldODEStateAndDerivative3;
            } else {
                System.arraycopy(obj, 0, mapState, 0, mapState.length);
                if (resetOccurred()) {
                    start(fieldExpandableODE2, getStepStart(), t3);
                }
                RealFieldElement realFieldElement2 = (RealFieldElement) getStepSize().multiply(computeStepGrowShrinkFactor(realFieldElement));
                double real = ((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(realFieldElement2)).subtract(t3)).getReal();
                RealFieldElement filterStep = filterStep(realFieldElement2, z2, !z2 ? real > d2 : real < d2);
                double real2 = ((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(filterStep)).subtract(t3)).getReal();
                if (!z2 ? real2 > d2 : real2 < d2) {
                    filterStep = (RealFieldElement) t3.subtract(getStepStart().getTime());
                }
                rescale(filterStep);
                fieldODEStateAndDerivative2 = fieldODEStateAndDerivative;
                tArr = mapState;
                taylor = AdamsFieldStepInterpolator.taylor(getStepStart(), (RealFieldElement) getStepStart().getTime().add(getStepSize()), getStepSize(), this.scaled, this.nordsieck);
            }
            if (!isLastStep()) {
                fieldExpandableODE2 = fieldExpandableODE;
                t3 = t;
                d = d2;
                stepStart = fieldODEStateAndDerivative2;
                time = t4;
                mapState = tArr;
            } else {
                FieldODEStateAndDerivative<T> stepStart2 = getStepStart();
                setStepStart(null);
                setStepSize(null);
                return stepStart2;
            }
        }
    }
}
