package org.apache.commons.math3.ode;

import java.io.Serializable;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class FieldEquationsMapper<T extends RealFieldElement<T>> implements Serializable {
    private static final long serialVersionUID = 20151114;
    private final int[] start;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldEquationsMapper(FieldEquationsMapper<T> mapper, int dimension) {
        int index = mapper == null ? 0 : mapper.getNumberOfEquations();
        this.start = new int[index + 2];
        if (mapper == null) {
            this.start[0] = 0;
        } else {
            System.arraycopy(mapper.start, 0, this.start, 0, index + 1);
        }
        this.start[index + 1] = this.start[index] + dimension;
    }

    public int getNumberOfEquations() {
        return this.start.length - 1;
    }

    public int getTotalDimension() {
        return this.start[this.start.length - 1];
    }

    public T[] mapState(FieldODEState<T> fieldODEState) {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(fieldODEState.getTime().getField(), getTotalDimension()));
        int i = 0;
        insertEquationData(0, fieldODEState.getState(), tArr);
        while (true) {
            i++;
            if (i < getNumberOfEquations()) {
                insertEquationData(i, fieldODEState.getSecondaryState(i), tArr);
            } else {
                return tArr;
            }
        }
    }

    public T[] mapDerivative(FieldODEStateAndDerivative<T> fieldODEStateAndDerivative) {
        T[] tArr = (T[]) ((RealFieldElement[]) MathArrays.buildArray(fieldODEStateAndDerivative.getTime().getField(), getTotalDimension()));
        int i = 0;
        insertEquationData(0, fieldODEStateAndDerivative.getDerivative(), tArr);
        while (true) {
            i++;
            if (i < getNumberOfEquations()) {
                insertEquationData(i, fieldODEStateAndDerivative.getSecondaryDerivative(i), tArr);
            } else {
                return tArr;
            }
        }
    }

    public FieldODEStateAndDerivative<T> mapStateAndDerivative(T t, T[] y, T[] yDot) throws DimensionMismatchException {
        if (y.length != getTotalDimension()) {
            throw new DimensionMismatchException(y.length, getTotalDimension());
        }
        if (yDot.length != getTotalDimension()) {
            throw new DimensionMismatchException(yDot.length, getTotalDimension());
        }
        int n = getNumberOfEquations();
        int index = 0;
        T[] state = extractEquationData(0, y);
        T[] derivative = extractEquationData(0, yDot);
        if (n < 2) {
            return new FieldODEStateAndDerivative<>(t, state, derivative);
        }
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(t.getField(), n - 1, -1);
        RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) MathArrays.buildArray(t.getField(), n - 1, -1);
        while (true) {
            index++;
            if (index < getNumberOfEquations()) {
                realFieldElementArr[index - 1] = extractEquationData(index, y);
                realFieldElementArr2[index - 1] = extractEquationData(index, yDot);
            } else {
                return new FieldODEStateAndDerivative<>(t, state, derivative, realFieldElementArr, realFieldElementArr2);
            }
        }
    }

    public T[] extractEquationData(int i, T[] tArr) throws MathIllegalArgumentException, DimensionMismatchException {
        checkIndex(i);
        int i2 = this.start[i];
        int i3 = this.start[i + 1];
        if (tArr.length < i3) {
            throw new DimensionMismatchException(tArr.length, i3);
        }
        int i4 = i3 - i2;
        T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(tArr[0].getField(), i4));
        System.arraycopy(tArr, i2, tArr2, 0, i4);
        return tArr2;
    }

    public void insertEquationData(int index, T[] equationData, T[] complete) throws DimensionMismatchException {
        checkIndex(index);
        int begin = this.start[index];
        int end = this.start[index + 1];
        int dimension = end - begin;
        if (complete.length < end) {
            throw new DimensionMismatchException(complete.length, end);
        }
        if (equationData.length != dimension) {
            throw new DimensionMismatchException(equationData.length, dimension);
        }
        System.arraycopy(equationData, 0, complete, begin, dimension);
    }

    private void checkIndex(int index) throws MathIllegalArgumentException {
        if (index < 0 || index > this.start.length - 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(index), 0, Integer.valueOf(this.start.length - 2));
        }
    }
}
