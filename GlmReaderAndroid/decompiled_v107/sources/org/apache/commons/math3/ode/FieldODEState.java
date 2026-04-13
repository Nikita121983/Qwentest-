package org.apache.commons.math3.ode;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class FieldODEState<T extends RealFieldElement<T>> {
    private final T[][] secondaryState;
    private final T[] state;
    private final T time;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FieldODEState(T time, T[] state) {
        this(time, state, null);
    }

    public FieldODEState(T t, T[] tArr, T[][] tArr2) {
        this.time = t;
        this.state = (T[]) ((RealFieldElement[]) tArr.clone());
        this.secondaryState = copy(t.getField(), tArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public T[][] copy(Field<T> field, T[][] tArr) {
        if (tArr == null) {
            return null;
        }
        T[][] tArr2 = (T[][]) ((RealFieldElement[][]) MathArrays.buildArray(field, tArr.length, -1));
        for (int i = 0; i < tArr.length; i++) {
            tArr2[i] = (RealFieldElement[]) tArr[i].clone();
        }
        return tArr2;
    }

    public T getTime() {
        return this.time;
    }

    public int getStateDimension() {
        return this.state.length;
    }

    public T[] getState() {
        return (T[]) ((RealFieldElement[]) this.state.clone());
    }

    public int getNumberOfSecondaryStates() {
        if (this.secondaryState == null) {
            return 0;
        }
        return this.secondaryState.length;
    }

    public int getSecondaryStateDimension(int index) {
        return index == 0 ? this.state.length : this.secondaryState[index - 1].length;
    }

    public T[] getSecondaryState(int i) {
        return (T[]) ((RealFieldElement[]) (i == 0 ? this.state.clone() : this.secondaryState[i - 1].clone()));
    }
}
