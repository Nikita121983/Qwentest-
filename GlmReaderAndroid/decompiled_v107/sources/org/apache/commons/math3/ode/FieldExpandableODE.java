package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class FieldExpandableODE<T extends RealFieldElement<T>> {
    private List<FieldSecondaryEquations<T>> components = new ArrayList();
    private FieldEquationsMapper<T> mapper;
    private final FirstOrderFieldDifferentialEquations<T> primary;

    public FieldExpandableODE(FirstOrderFieldDifferentialEquations<T> primary) {
        this.primary = primary;
        this.mapper = new FieldEquationsMapper<>(null, primary.getDimension());
    }

    public FieldEquationsMapper<T> getMapper() {
        return this.mapper;
    }

    public int addSecondaryEquations(FieldSecondaryEquations<T> secondary) {
        this.components.add(secondary);
        this.mapper = new FieldEquationsMapper<>(this.mapper, secondary.getDimension());
        return this.components.size();
    }

    public void init(T t0, T[] y0, T finalTime) {
        int index = 0;
        T[] primary0 = this.mapper.extractEquationData(0, y0);
        this.primary.init(t0, primary0, finalTime);
        while (true) {
            index++;
            if (index < this.mapper.getNumberOfEquations()) {
                T[] secondary0 = this.mapper.extractEquationData(index, y0);
                this.components.get(index - 1).init(t0, primary0, secondary0, finalTime);
            } else {
                return;
            }
        }
    }

    public T[] computeDerivatives(T t, T[] tArr) throws MaxCountExceededException, DimensionMismatchException {
        T[] tArr2 = (T[]) ((RealFieldElement[]) MathArrays.buildArray(t.getField(), this.mapper.getTotalDimension()));
        int i = 0;
        T[] extractEquationData = this.mapper.extractEquationData(0, tArr);
        T[] computeDerivatives = this.primary.computeDerivatives(t, extractEquationData);
        this.mapper.insertEquationData(0, computeDerivatives, tArr2);
        while (true) {
            i++;
            if (i < this.mapper.getNumberOfEquations()) {
                this.mapper.insertEquationData(i, this.components.get(i - 1).computeDerivatives(t, extractEquationData, computeDerivatives, this.mapper.extractEquationData(i, tArr)), tArr2);
            } else {
                return tArr2;
            }
        }
    }
}
