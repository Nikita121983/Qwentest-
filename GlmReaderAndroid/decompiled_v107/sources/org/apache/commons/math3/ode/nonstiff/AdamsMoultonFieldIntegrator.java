package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class AdamsMoultonFieldIntegrator<T extends RealFieldElement<T>> extends AdamsFieldIntegrator<T> {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsMoultonFieldIntegrator(Field<T> field, int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v7, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r12v0, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v6, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r2v7, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r8v6, types: [org.apache.commons.math3.RealFieldElement[]] */
    /* JADX WARN: Type inference failed for: r9v1, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r9v22, types: [org.apache.commons.math3.RealFieldElement] */
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
            RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(getField(), mapState.length);
            Array2DRowFieldMatrix<T> array2DRowFieldMatrix = null;
            double d2 = d;
            T t4 = (T) ((RealFieldElement) getField().getZero()).add(10.0d);
            RealFieldElement[] realFieldElementArr2 = null;
            while (true) {
                fieldODEStateAndDerivative = stepStart;
                if (((RealFieldElement) t4.subtract(1.0d)).getReal() < d2) {
                    break;
                }
                ?? state = taylor.getState();
                RealFieldElement[] computeDerivatives = computeDerivatives(taylor.getTime(), state);
                for (int i = 0; i < realFieldElementArr.length; i++) {
                    realFieldElementArr[i] = (RealFieldElement) getStepSize().multiply(computeDerivatives[i]);
                }
                array2DRowFieldMatrix = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, realFieldElementArr, array2DRowFieldMatrix);
                t4 = array2DRowFieldMatrix.walkInOptimizedOrder(new Corrector(mapState, realFieldElementArr, state));
                if (((RealFieldElement) t4.subtract(1.0d)).getReal() >= d2) {
                    rescale(filterStep((RealFieldElement) getStepSize().multiply(computeStepGrowShrinkFactor(t4)), z2, false));
                    t2 = time;
                    z = z2;
                    taylor = AdamsFieldStepInterpolator.taylor(getStepStart(), (RealFieldElement) getStepStart().getTime().add(getStepSize()), getStepSize(), this.scaled, this.nordsieck);
                } else {
                    t2 = time;
                    z = z2;
                }
                stepStart = fieldODEStateAndDerivative;
                z2 = z;
                time = t2;
                realFieldElementArr2 = state;
            }
            T t5 = time;
            boolean z3 = z2;
            RealFieldElement[] computeDerivatives2 = computeDerivatives(taylor.getTime(), realFieldElementArr2);
            T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(getField(), mapState.length));
            for (int i2 = 0; i2 < tArr2.length; i2++) {
                tArr2[i2] = (RealFieldElement) getStepSize().multiply(computeDerivatives2[i2]);
            }
            updateHighOrderDerivativesPhase2(realFieldElementArr, tArr2, array2DRowFieldMatrix);
            FieldODEStateAndDerivative fieldODEStateAndDerivative3 = new FieldODEStateAndDerivative(taylor.getTime(), realFieldElementArr2, computeDerivatives2);
            setStepStart(acceptStep(new AdamsFieldStepInterpolator(getStepSize(), fieldODEStateAndDerivative3, tArr2, array2DRowFieldMatrix, z3, getStepStart(), fieldODEStateAndDerivative3, fieldExpandableODE2.getMapper()), t3));
            this.scaled = tArr2;
            this.nordsieck = array2DRowFieldMatrix;
            if (isLastStep()) {
                fieldODEStateAndDerivative2 = fieldODEStateAndDerivative;
                tArr = mapState;
                taylor = fieldODEStateAndDerivative3;
            } else {
                System.arraycopy(realFieldElementArr2, 0, mapState, 0, mapState.length);
                if (resetOccurred()) {
                    start(fieldExpandableODE2, getStepStart(), t3);
                }
                RealFieldElement realFieldElement = (RealFieldElement) getStepSize().multiply(computeStepGrowShrinkFactor(t4));
                double real = ((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(realFieldElement)).subtract(t3)).getReal();
                RealFieldElement filterStep = filterStep(realFieldElement, z3, !z3 ? real > d2 : real < d2);
                double real2 = ((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(filterStep)).subtract(t3)).getReal();
                if (!z3 ? real2 > d2 : real2 < d2) {
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
                z2 = z3;
                stepStart = fieldODEStateAndDerivative2;
                d = d2;
                time = t5;
                mapState = tArr;
            } else {
                FieldODEStateAndDerivative<T> stepStart2 = getStepStart();
                setStepStart(null);
                setStepSize(null);
                return stepStart2;
            }
        }
    }

    /* loaded from: classes10.dex */
    private class Corrector implements FieldMatrixPreservingVisitor<T> {
        private final T[] after;
        private final T[] before;
        private final T[] previous;
        private final T[] scaled;

        Corrector(T[] tArr, T[] tArr2, T[] tArr3) {
            this.previous = tArr;
            this.scaled = tArr2;
            this.after = tArr3;
            this.before = (T[]) ((RealFieldElement[]) tArr3.clone());
        }

        @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            Arrays.fill(this.after, AdamsMoultonFieldIntegrator.this.getField().getZero());
        }

        @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
        public void visit(int i, int i2, T t) {
            if ((i & 1) == 0) {
                ((T[]) this.after)[i2] = (RealFieldElement) this.after[i2].subtract(t);
            } else {
                ((T[]) this.after)[i2] = (RealFieldElement) this.after[i2].add(t);
            }
        }

        @Override // org.apache.commons.math3.linear.FieldMatrixPreservingVisitor
        public T end() {
            RealFieldElement realFieldElement = (RealFieldElement) AdamsMoultonFieldIntegrator.this.getField().getZero();
            for (int i = 0; i < this.after.length; i++) {
                ((T[]) this.after)[i] = (RealFieldElement) this.after[i].add(this.previous[i].add(this.scaled[i]));
                if (i < AdamsMoultonFieldIntegrator.this.mainSetDimension) {
                    RealFieldElement max = MathUtils.max((RealFieldElement) this.previous[i].abs(), (RealFieldElement) this.after[i].abs());
                    RealFieldElement realFieldElement2 = (RealFieldElement) ((RealFieldElement) this.after[i].subtract(this.before[i])).divide((RealFieldElement) (AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance == null ? ((RealFieldElement) max.multiply(AdamsMoultonFieldIntegrator.this.scalRelativeTolerance)).add(AdamsMoultonFieldIntegrator.this.scalAbsoluteTolerance) : ((RealFieldElement) max.multiply(AdamsMoultonFieldIntegrator.this.vecRelativeTolerance[i])).add(AdamsMoultonFieldIntegrator.this.vecAbsoluteTolerance[i])));
                    realFieldElement = (RealFieldElement) realFieldElement.add((RealFieldElement) realFieldElement2.multiply(realFieldElement2));
                }
            }
            return (T) ((RealFieldElement) realFieldElement.divide(AdamsMoultonFieldIntegrator.this.mainSetDimension)).sqrt();
        }
    }
}
