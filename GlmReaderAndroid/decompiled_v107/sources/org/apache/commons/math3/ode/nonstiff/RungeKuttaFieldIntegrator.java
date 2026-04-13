package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public abstract class RungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> implements FieldButcherArrayProvider<T> {
    private final T[][] a;
    private final T[] b;
    private final T[] c;
    private final T step;

    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldEquationsMapper<T> fieldEquationsMapper);

    /* JADX INFO: Access modifiers changed from: protected */
    public RungeKuttaFieldIntegrator(Field<T> field, String name, T step) {
        super(field, name);
        this.c = getC();
        this.a = getA();
        this.b = getB();
        this.step = (T) step.abs();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T fraction(int p, int q) {
        return (T) ((RealFieldElement) getField().getZero().add(p)).divide(q);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r1v21, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r3v12, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r5v2, types: [org.apache.commons.math3.RealFieldElement] */
    /* JADX WARN: Type inference failed for: r5v40, types: [org.apache.commons.math3.RealFieldElement] */
    @Override // org.apache.commons.math3.ode.FirstOrderFieldIntegrator
    public FieldODEStateAndDerivative<T> integrate(FieldExpandableODE<T> equations, FieldODEState<T> initialState, T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double d;
        sanityChecks(initialState, finalTime);
        T t0 = initialState.getTime();
        T[] y0 = equations.getMapper().mapState(initialState);
        setStepStart(initIntegration(equations, t0, y0, finalTime));
        int i = 0;
        boolean forward = ((RealFieldElement) finalTime.subtract(initialState.getTime())).getReal() > 0.0d;
        int stages = this.c.length + 1;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), stages, -1);
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) MathArrays.buildArray(getField(), y0.length);
        if (forward) {
            d = 0.0d;
            if (((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(this.step)).subtract(finalTime)).getReal() >= 0.0d) {
                setStepSize((RealFieldElement) finalTime.subtract(getStepStart().getTime()));
            } else {
                setStepSize(this.step);
            }
        } else {
            d = 0.0d;
            if (((RealFieldElement) ((RealFieldElement) getStepStart().getTime().subtract(this.step)).subtract(finalTime)).getReal() <= 0.0d) {
                setStepSize((RealFieldElement) finalTime.subtract(getStepStart().getTime()));
            } else {
                setStepSize((RealFieldElement) this.step.negate());
            }
        }
        setIsLastStep(false);
        while (true) {
            T[] y = equations.getMapper().mapState(getStepStart());
            realFieldElementArr[i] = equations.getMapper().mapDerivative(getStepStart());
            int k = 1;
            while (k < stages) {
                int j = 0;
                while (j < y0.length) {
                    RealFieldElement realFieldElement = (RealFieldElement) realFieldElementArr[i][j].multiply(this.a[k - 1][i]);
                    int l = 1;
                    while (l < k) {
                        realFieldElement = (RealFieldElement) realFieldElement.add((RealFieldElement) realFieldElementArr[l][j].multiply(this.a[k - 1][l]));
                        l++;
                        forward = forward;
                        i = i;
                    }
                    realFieldElementArr2[j] = (RealFieldElement) y[j].add(getStepSize().multiply(realFieldElement));
                    j++;
                    forward = forward;
                    i = i;
                }
                realFieldElementArr[k] = computeDerivatives((RealFieldElement) getStepStart().getTime().add(getStepSize().multiply(this.c[k - 1])), realFieldElementArr2);
                k++;
                forward = forward;
                i = i;
            }
            boolean forward2 = forward;
            int i2 = i;
            for (int j2 = 0; j2 < y0.length; j2++) {
                RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElementArr[i2][j2].multiply(this.b[i2]);
                for (int l2 = 1; l2 < stages; l2++) {
                    realFieldElement2 = (RealFieldElement) realFieldElement2.add((RealFieldElement) realFieldElementArr[l2][j2].multiply(this.b[l2]));
                }
                realFieldElementArr2[j2] = (RealFieldElement) y[j2].add(getStepSize().multiply(realFieldElement2));
            }
            RealFieldElement realFieldElement3 = (RealFieldElement) getStepStart().getTime().add(getStepSize());
            FieldODEStateAndDerivative<T> stateTmp = new FieldODEStateAndDerivative<>(realFieldElement3, realFieldElementArr2, computeDerivatives(realFieldElement3, realFieldElementArr2));
            System.arraycopy(realFieldElementArr2, i2, y, i2, y0.length);
            RealFieldElement[][] realFieldElementArr3 = realFieldElementArr;
            RealFieldElement[] realFieldElementArr4 = realFieldElementArr2;
            forward = forward2;
            setStepStart(acceptStep(createInterpolator(forward, realFieldElementArr3, getStepStart(), stateTmp, equations.getMapper()), finalTime));
            if (!isLastStep()) {
                double real = ((RealFieldElement) ((RealFieldElement) getStepStart().getTime().add(getStepSize())).subtract(finalTime)).getReal();
                if (((!forward ? real <= d : real >= d) ? i2 : 1) != 0) {
                    setStepSize((RealFieldElement) finalTime.subtract(getStepStart().getTime()));
                }
            }
            if (!isLastStep()) {
                realFieldElementArr = realFieldElementArr3;
                realFieldElementArr2 = realFieldElementArr4;
                i = 0;
            } else {
                FieldODEStateAndDerivative<T> finalState = getStepStart();
                setStepStart(null);
                setStepSize(null);
                return finalState;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] singleStep(FirstOrderFieldDifferentialEquations<T> firstOrderFieldDifferentialEquations, T t, T[] tArr, T t2) {
        T[] tArr2 = (T[]) ((RealFieldElement[]) tArr.clone());
        int length = this.c.length + 1;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(getField(), length, -1);
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) tArr.clone();
        RealFieldElement realFieldElement = (RealFieldElement) t2.subtract(t);
        char c = 0;
        realFieldElementArr[0] = firstOrderFieldDifferentialEquations.computeDerivatives(t, tArr2);
        int i = 1;
        while (i < length) {
            int i2 = 0;
            while (i2 < tArr.length) {
                RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElementArr[c][i2].multiply(this.a[i - 1][c]);
                int i3 = 1;
                while (i3 < i) {
                    realFieldElement2 = (RealFieldElement) realFieldElement2.add((RealFieldElement) realFieldElementArr[i3][i2].multiply(this.a[i - 1][i3]));
                    i3++;
                    c = c;
                }
                realFieldElementArr2[i2] = (RealFieldElement) tArr2[i2].add((Decimal64) realFieldElement.multiply(realFieldElement2));
                i2++;
                c = c;
            }
            realFieldElementArr[i] = firstOrderFieldDifferentialEquations.computeDerivatives((RealFieldElement) t.add(realFieldElement.multiply(this.c[i - 1])), realFieldElementArr2);
            i++;
            c = c;
        }
        char c2 = c;
        for (int i4 = 0; i4 < tArr.length; i4++) {
            RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElementArr[c2][i4].multiply(this.b[c2]);
            for (int i5 = 1; i5 < length; i5++) {
                realFieldElement3 = (RealFieldElement) realFieldElement3.add((RealFieldElement) realFieldElementArr[i5][i4].multiply(this.b[i5]));
            }
            tArr2[i4] = (RealFieldElement) tArr2[i4].add((Decimal64) realFieldElement.multiply(realFieldElement3));
        }
        return tArr2;
    }
}
