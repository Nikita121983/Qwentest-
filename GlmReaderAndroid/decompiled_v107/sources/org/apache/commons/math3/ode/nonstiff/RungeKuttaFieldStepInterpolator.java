package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
abstract class RungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends AbstractFieldStepInterpolator<T> {
    private final Field<T> field;
    private final T[][] yDotK;

    protected abstract RungeKuttaFieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper);

    /* JADX INFO: Access modifiers changed from: protected */
    public RungeKuttaFieldStepInterpolator(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(z, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
        this.field = field;
        this.yDotK = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(field, tArr.length, -1));
        for (int i = 0; i < tArr.length; i++) {
            ((T[][]) this.yDotK)[i] = (RealFieldElement[]) tArr[i].clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    public RungeKuttaFieldStepInterpolator<T> create(boolean newForward, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return create(this.field, newForward, this.yDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T[] previousStateLinearCombination(T... coefficients) {
        return combine(getPreviousState().getState(), coefficients);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T[] currentStateLinearCombination(T... coefficients) {
        return combine(getCurrentState().getState(), coefficients);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public T[] derivativeLinearCombination(T... tArr) {
        return (T[]) combine((RealFieldElement[]) MathArrays.buildArray(this.field, this.yDotK[0].length), tArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T[] combine(T[] tArr, T... coefficients) {
        for (int i = 0; i < tArr.length; i++) {
            for (int k = 0; k < coefficients.length; k++) {
                tArr[i] = (RealFieldElement) tArr[i].add((Decimal64) coefficients[k].multiply(this.yDotK[k][i]));
            }
        }
        return tArr;
    }
}
