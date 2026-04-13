package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class AdamsNordsieckFieldTransformer<T extends RealFieldElement<T>> {
    private static final Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> CACHE = new HashMap();
    private final T[] c1;
    private final Field<T> field;
    private final Array2DRowFieldMatrix<T> update;

    private AdamsNordsieckFieldTransformer(Field<T> field, int i) {
        this.field = field;
        int i2 = i - 1;
        FieldMatrix<T> buildP = buildP(i2);
        FieldDecompositionSolver solver = new FieldLUDecomposition(buildP).getSolver();
        RealFieldElement[] realFieldElementArr = (RealFieldElement[]) MathArrays.buildArray(field, i2);
        Arrays.fill(realFieldElementArr, field.getOne());
        this.c1 = (T[]) ((RealFieldElement[]) solver.solve(new ArrayFieldVector((FieldElement[]) realFieldElementArr, false)).toArray());
        T[][] data = buildP.getData();
        for (int length = data.length - 1; length > 0; length--) {
            data[length] = data[length - 1];
        }
        data[0] = (RealFieldElement[]) MathArrays.buildArray(field, i2);
        Arrays.fill(data[0], field.getZero());
        this.update = new Array2DRowFieldMatrix<>(solver.solve(new Array2DRowFieldMatrix((FieldElement[][]) data, false)).getData());
    }

    public static <T extends RealFieldElement<T>> AdamsNordsieckFieldTransformer<T> getInstance(Field<T> field, int nSteps) {
        AdamsNordsieckFieldTransformer t;
        synchronized (CACHE) {
            Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>> map = CACHE.get(Integer.valueOf(nSteps));
            if (map == null) {
                map = new HashMap();
                CACHE.put(Integer.valueOf(nSteps), map);
            }
            t = map.get(field);
            if (t == null) {
                t = new AdamsNordsieckFieldTransformer(field, nSteps);
                map.put(field, t);
            }
        }
        return t;
    }

    private FieldMatrix<T> buildP(int rows) {
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(this.field, rows, rows);
        for (int i = 1; i <= realFieldElementArr.length; i++) {
            RealFieldElement[] realFieldElementArr2 = realFieldElementArr[i - 1];
            int factor = -i;
            RealFieldElement realFieldElement = (RealFieldElement) this.field.getZero().add(factor);
            for (int j = 1; j <= realFieldElementArr2.length; j++) {
                realFieldElementArr2[j - 1] = (RealFieldElement) realFieldElement.multiply(j + 1);
                realFieldElement = (RealFieldElement) realFieldElement.multiply(factor);
            }
        }
        return new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr, false);
    }

    public Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T h, T[] t, T[][] y, T[][] yDot) {
        T[][] tArr = y;
        RealFieldElement[][] realFieldElementArr = (RealFieldElement[][]) MathArrays.buildArray(this.field, this.c1.length + 1, this.c1.length + 1);
        char c = 0;
        RealFieldElement[][] realFieldElementArr2 = (RealFieldElement[][]) MathArrays.buildArray(this.field, this.c1.length + 1, tArr[0].length);
        T[] y0 = tArr[0];
        T[] yDot0 = yDot[0];
        int i = 1;
        while (i < tArr.length) {
            RealFieldElement realFieldElement = (RealFieldElement) t[i].subtract(t[c]);
            RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.divide(h);
            RealFieldElement realFieldElement3 = (RealFieldElement) h.reciprocal();
            RealFieldElement[] realFieldElementArr3 = realFieldElementArr[(i * 2) - 2];
            RealFieldElement[] realFieldElementArr4 = (i * 2) + (-1) < realFieldElementArr.length ? realFieldElementArr[(i * 2) - 1] : null;
            for (int j = 0; j < realFieldElementArr3.length; j++) {
                realFieldElement3 = (RealFieldElement) realFieldElement3.multiply(realFieldElement2);
                realFieldElementArr3[j] = (RealFieldElement) realFieldElement.multiply(realFieldElement3);
                if (realFieldElementArr4 != null) {
                    realFieldElementArr4[j] = (RealFieldElement) realFieldElement3.multiply(j + 2);
                }
            }
            T[] yI = tArr[i];
            T[] yDotI = yDot[i];
            RealFieldElement[] realFieldElementArr5 = realFieldElementArr2[(i * 2) - 2];
            RealFieldElement[] realFieldElementArr6 = (i * 2) + (-1) < realFieldElementArr2.length ? realFieldElementArr2[(i * 2) - 1] : null;
            int j2 = 0;
            while (j2 < yI.length) {
                int j3 = j2;
                realFieldElementArr5[j3] = (RealFieldElement) ((RealFieldElement) yI[j2].subtract(y0[j3])).subtract((RealFieldElement) realFieldElement.multiply(yDot0[j3]));
                if (realFieldElementArr6 != null) {
                    realFieldElementArr6[j3] = (RealFieldElement) yDotI[j3].subtract(yDot0[j3]);
                }
                j2 = j3 + 1;
            }
            i++;
            tArr = y;
            c = 0;
        }
        FieldLUDecomposition<T> decomposition = new FieldLUDecomposition<>(new Array2DRowFieldMatrix((FieldElement[][]) realFieldElementArr, false));
        FieldMatrix<T> x = decomposition.getSolver().solve(new Array2DRowFieldMatrix<>((FieldElement[][]) realFieldElementArr2, false));
        Array2DRowFieldMatrix<T> truncatedX = new Array2DRowFieldMatrix<>(this.field, x.getRowDimension() - 1, x.getColumnDimension());
        for (int i2 = 0; i2 < truncatedX.getRowDimension(); i2++) {
            for (int j4 = 0; j4 < truncatedX.getColumnDimension(); j4++) {
                truncatedX.setEntry(i2, j4, x.getEntry(i2, j4));
            }
        }
        return truncatedX;
    }

    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix<T> highOrder) {
        return this.update.multiply(highOrder);
    }

    public void updateHighOrderDerivativesPhase2(T[] start, T[] end, Array2DRowFieldMatrix<T> highOrder) {
        T[][] dataRef = highOrder.getDataRef();
        for (int i = 0; i < dataRef.length; i++) {
            FieldElement[] fieldElementArr = dataRef[i];
            T c1I = this.c1[i];
            for (int j = 0; j < fieldElementArr.length; j++) {
                fieldElementArr[j] = (RealFieldElement) fieldElementArr[j].add(c1I.multiply(start[j].subtract(end[j])));
            }
        }
    }
}
