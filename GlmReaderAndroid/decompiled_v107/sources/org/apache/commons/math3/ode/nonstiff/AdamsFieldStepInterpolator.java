package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
class AdamsFieldStepInterpolator<T extends RealFieldElement<T>> extends AbstractFieldStepInterpolator<T> {
    private final Array2DRowFieldMatrix<T> nordsieck;
    private final FieldODEStateAndDerivative<T> reference;
    private final T[] scaled;
    private T scalingH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdamsFieldStepInterpolator(T stepSize, FieldODEStateAndDerivative<T> reference, T[] scaled, Array2DRowFieldMatrix<T> nordsieck, boolean isForward, FieldODEStateAndDerivative<T> globalPreviousState, FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> equationsMapper) {
        this(stepSize, reference, scaled, nordsieck, isForward, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, equationsMapper);
    }

    private AdamsFieldStepInterpolator(T t, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, T[] tArr, Array2DRowFieldMatrix<T> array2DRowFieldMatrix, boolean z, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative5, FieldEquationsMapper<T> fieldEquationsMapper) {
        super(z, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldODEStateAndDerivative5, fieldEquationsMapper);
        this.scalingH = t;
        this.reference = fieldODEStateAndDerivative;
        this.scaled = (T[]) ((RealFieldElement[]) tArr.clone());
        this.nordsieck = new Array2DRowFieldMatrix<>((FieldElement[][]) array2DRowFieldMatrix.getData(), false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    public AdamsFieldStepInterpolator<T> create(boolean newForward, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return new AdamsFieldStepInterpolator<>(this.scalingH, this.reference, this.scaled, this.nordsieck, newForward, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> equationsMapper, T time, T theta, T thetaH, T oneMinusThetaH) {
        return taylor(this.reference, time, this.scalingH, this.scaled, this.nordsieck);
    }

    public static <S extends RealFieldElement<S>> FieldODEStateAndDerivative<S> taylor(FieldODEStateAndDerivative<S> fieldODEStateAndDerivative, S s, S s2, S[] sArr, Array2DRowFieldMatrix<S> array2DRowFieldMatrix) {
        RealFieldElement realFieldElement = (RealFieldElement) s.subtract(fieldODEStateAndDerivative.getTime());
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide(s2);
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(s.getField(), sArr.length);
        Arrays.fill(realFieldElementArr, s.getField().getZero());
        RealFieldElement[] realFieldElementArr2 = (RealFieldElement[]) MathArrays.buildArray(s.getField(), sArr.length);
        Arrays.fill(realFieldElementArr2, s.getField().getZero());
        S[][] dataRef = array2DRowFieldMatrix.getDataRef();
        for (int length = dataRef.length - 1; length >= 0; length--) {
            int i = length + 2;
            S[] sArr2 = dataRef[length];
            RealFieldElement realFieldElement3 = (RealFieldElement) realFieldElement2.pow(i);
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                RealFieldElement realFieldElement4 = (RealFieldElement) sArr2[i2].multiply(realFieldElement3);
                realFieldElementArr[i2] = (RealFieldElement) realFieldElementArr[i2].add(realFieldElement4);
                realFieldElementArr2[i2] = (RealFieldElement) realFieldElementArr2[i2].add((RealFieldElement) realFieldElement4.multiply(i));
            }
        }
        S[] state = fieldODEStateAndDerivative.getState();
        for (int i3 = 0; i3 < realFieldElementArr.length; i3++) {
            realFieldElementArr[i3] = (RealFieldElement) realFieldElementArr[i3].add((RealFieldElement) sArr[i3].multiply(realFieldElement2));
            state[i3] = (RealFieldElement) state[i3].add(realFieldElementArr[i3]);
            realFieldElementArr2[i3] = (RealFieldElement) ((RealFieldElement) realFieldElementArr2[i3].add((RealFieldElement) sArr[i3].multiply(realFieldElement2))).divide(realFieldElement);
        }
        return new FieldODEStateAndDerivative<>(s, state, realFieldElementArr2);
    }
}
