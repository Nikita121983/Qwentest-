package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class FieldHermiteInterpolator<T extends FieldElement<T>> {
    private final List<T> abscissae = new ArrayList();
    private final List<T[]> topDiagonal = new ArrayList();
    private final List<T[]> bottomDiagonal = new ArrayList();

    public void addSamplePoint(T t, T[]... tArr) throws ZeroException, MathArithmeticException, DimensionMismatchException, NullArgumentException {
        MathUtils.checkNotNull(t);
        FieldElement fieldElement = (FieldElement) t.getField().getOne();
        for (int i = 0; i < tArr.length; i++) {
            FieldElement[] fieldElementArr = (FieldElement[]) tArr[i].clone();
            if (i > 1) {
                fieldElement = (FieldElement) fieldElement.multiply(i);
                FieldElement fieldElement2 = (FieldElement) fieldElement.reciprocal();
                for (int i2 = 0; i2 < fieldElementArr.length; i2++) {
                    fieldElementArr[i2] = (FieldElement) fieldElementArr[i2].multiply(fieldElement2);
                }
            }
            int size = this.abscissae.size();
            this.bottomDiagonal.add(size - i, fieldElementArr);
            FieldElement[] fieldElementArr2 = fieldElementArr;
            for (int i3 = i; i3 < size; i3++) {
                T[] tArr2 = this.bottomDiagonal.get(size - (i3 + 1));
                if (t.equals(this.abscissae.get(size - (i3 + 1)))) {
                    throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, t);
                }
                FieldElement fieldElement3 = (FieldElement) ((FieldElement) t.subtract(this.abscissae.get(size - (i3 + 1)))).reciprocal();
                for (int i4 = 0; i4 < fieldElementArr.length; i4++) {
                    tArr2[i4] = (FieldElement) fieldElement3.multiply((FieldElement) fieldElementArr2[i4].subtract(tArr2[i4]));
                }
                fieldElementArr2 = tArr2;
            }
            this.topDiagonal.add(fieldElementArr2.clone());
            this.abscissae.add(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[] value(T t) throws NoDataException, NullArgumentException {
        MathUtils.checkNotNull(t);
        if (this.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(t.getField(), this.topDiagonal.get(0).length));
        FieldElement fieldElement = (FieldElement) t.getField().getOne();
        for (int i = 0; i < this.topDiagonal.size(); i++) {
            T[] tArr2 = this.topDiagonal.get(i);
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr[i2].add((Decimal64) tArr2[i2].multiply(fieldElement));
            }
            fieldElement = (FieldElement) fieldElement.multiply((FieldElement) t.subtract(this.abscissae.get(i)));
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T[][] derivatives(T t, int i) throws NoDataException, NullArgumentException {
        FieldHermiteInterpolator<T> fieldHermiteInterpolator = this;
        MathUtils.checkNotNull(t);
        if (fieldHermiteInterpolator.abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        FieldElement fieldElement = (FieldElement) t.getField().getZero();
        FieldElement fieldElement2 = (FieldElement) t.getField().getOne();
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(t.getField(), i + 1);
        fieldElementArr[0] = fieldElement;
        for (int i2 = 0; i2 < i; i2++) {
            fieldElementArr[i2 + 1] = (FieldElement) fieldElementArr[i2].add(fieldElement2);
        }
        T[][] tArr = (T[][]) ((FieldElement[][]) MathArrays.buildArray(t.getField(), i + 1, fieldHermiteInterpolator.topDiagonal.get(0).length));
        FieldElement[] fieldElementArr2 = (FieldElement[]) MathArrays.buildArray(t.getField(), i + 1);
        fieldElementArr2[0] = (FieldElement) t.getField().getOne();
        int i3 = 0;
        while (i3 < fieldHermiteInterpolator.topDiagonal.size()) {
            T[] tArr2 = fieldHermiteInterpolator.topDiagonal.get(i3);
            FieldElement fieldElement3 = (FieldElement) t.subtract(fieldHermiteInterpolator.abscissae.get(i3));
            for (int i4 = i; i4 >= 0; i4--) {
                for (int i5 = 0; i5 < tArr[i4].length; i5++) {
                    tArr[i4][i5] = (FieldElement) tArr[i4][i5].add((Decimal64) tArr2[i5].multiply(fieldElementArr2[i4]));
                }
                fieldElementArr2[i4] = (FieldElement) fieldElementArr2[i4].multiply(fieldElement3);
                if (i4 > 0) {
                    fieldElementArr2[i4] = (FieldElement) fieldElementArr2[i4].add(fieldElementArr[i4].multiply(fieldElementArr2[i4 - 1]));
                }
            }
            i3++;
            fieldHermiteInterpolator = this;
        }
        return tArr;
    }
}
