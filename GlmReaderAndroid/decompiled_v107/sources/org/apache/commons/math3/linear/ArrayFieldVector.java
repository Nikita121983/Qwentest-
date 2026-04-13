package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class ArrayFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
    private static final long serialVersionUID = 7648186910365927050L;
    private T[] data;
    private final Field<T> field;

    public ArrayFieldVector(Field<T> field) {
        this(field, 0);
    }

    public ArrayFieldVector(Field<T> field, int i) {
        this.field = field;
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(field, i));
    }

    public ArrayFieldVector(int size, T preset) {
        this(preset.getField(), size);
        Arrays.fill(this.data, preset);
    }

    public ArrayFieldVector(T[] tArr) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        try {
            this.field = tArr[0].getField();
            this.data = (T[]) ((FieldElement[]) tArr.clone());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public ArrayFieldVector(Field<T> field, T[] tArr) throws NullArgumentException {
        MathUtils.checkNotNull(tArr);
        this.field = field;
        this.data = (T[]) ((FieldElement[]) tArr.clone());
    }

    public ArrayFieldVector(T[] tArr, boolean z) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        if (tArr.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        this.field = tArr[0].getField();
        this.data = z ? (T[]) ((FieldElement[]) tArr.clone()) : tArr;
    }

    public ArrayFieldVector(Field<T> field, T[] tArr, boolean z) throws NullArgumentException {
        MathUtils.checkNotNull(tArr);
        this.field = field;
        this.data = z ? (T[]) ((FieldElement[]) tArr.clone()) : tArr;
    }

    public ArrayFieldVector(T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        MathUtils.checkNotNull(tArr);
        if (tArr.length < i + i2) {
            throw new NumberIsTooLargeException(Integer.valueOf(i + i2), Integer.valueOf(tArr.length), true);
        }
        this.field = tArr[0].getField();
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, i2));
        System.arraycopy(tArr, i, this.data, 0, i2);
    }

    public ArrayFieldVector(Field<T> field, T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        MathUtils.checkNotNull(tArr);
        if (tArr.length < i + i2) {
            throw new NumberIsTooLargeException(Integer.valueOf(i + i2), Integer.valueOf(tArr.length), true);
        }
        this.field = field;
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(field, i2));
        System.arraycopy(tArr, i, this.data, 0, i2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        this.field = fieldVector.getField();
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, fieldVector.getDimension()));
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = fieldVector.getEntry(i);
        }
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(arrayFieldVector);
        this.field = arrayFieldVector.getField();
        this.data = (T[]) ((FieldElement[]) arrayFieldVector.data.clone());
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, boolean z) throws NullArgumentException {
        MathUtils.checkNotNull(arrayFieldVector);
        this.field = arrayFieldVector.getField();
        T[] tArr = arrayFieldVector.data;
        this.data = z ? (T[]) ((FieldElement[]) tArr.clone()) : tArr;
    }

    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> v1, ArrayFieldVector<T> v2) throws NullArgumentException {
        this((FieldVector) v1, (FieldVector) v2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector, FieldVector<T> fieldVector2) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        MathUtils.checkNotNull(fieldVector2);
        this.field = fieldVector.getField();
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        T[] array2 = fieldVector2 instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector2).data : fieldVector2.toArray();
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, array.length + array2.length));
        System.arraycopy(array, 0, this.data, 0, array.length);
        System.arraycopy(array2, 0, this.data, array.length, array2.length);
    }

    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> v1, T[] v2) throws NullArgumentException {
        this((FieldVector) v1, (FieldElement[]) v2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector, T[] tArr) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        MathUtils.checkNotNull(tArr);
        this.field = fieldVector.getField();
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, array.length + tArr.length));
        System.arraycopy(array, 0, this.data, 0, array.length);
        System.arraycopy(tArr, 0, this.data, array.length, tArr.length);
    }

    @Deprecated
    public ArrayFieldVector(T[] v1, ArrayFieldVector<T> v2) throws NullArgumentException {
        this((FieldElement[]) v1, (FieldVector) v2);
    }

    public ArrayFieldVector(T[] tArr, FieldVector<T> fieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(fieldVector);
        this.field = fieldVector.getField();
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, tArr.length + array.length));
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(array, 0, this.data, tArr.length, array.length);
    }

    public ArrayFieldVector(T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(tArr2);
        if (tArr.length + tArr2.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(tArr[0].getField(), tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
        this.field = this.data[0].getField();
    }

    public ArrayFieldVector(Field<T> field, T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(tArr2);
        if (tArr.length + tArr2.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
        this.data = (T[]) ((FieldElement[]) MathArrays.buildArray(field, tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
        this.field = field;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public Field<T> getField() {
        return this.field;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> copy() {
        return new ArrayFieldVector((ArrayFieldVector) this, true);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> add(FieldVector<T> v) throws DimensionMismatchException {
        try {
            return add((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArr[i] = (FieldElement) this.data[i].add(v.getEntry(i));
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    public ArrayFieldVector<T> add(ArrayFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].add(v.data[i]);
        }
        return new ArrayFieldVector<>((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> subtract(FieldVector<T> v) throws DimensionMismatchException {
        try {
            return subtract((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArr[i] = (FieldElement) this.data[i].subtract(v.getEntry(i));
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    public ArrayFieldVector<T> subtract(ArrayFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].subtract(v.data[i]);
        }
        return new ArrayFieldVector<>((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapAdd(T d) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].add(d);
        }
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapAddToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].add(t);
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapSubtract(T d) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].subtract(d);
        }
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapSubtractToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].subtract(t);
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapMultiply(T d) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].multiply(d);
        }
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapMultiplyToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].multiply(t);
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapDivide(T d) throws NullArgumentException, MathArithmeticException {
        MathUtils.checkNotNull(d);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].divide(d);
        }
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapDivideToSelf(T t) throws NullArgumentException, MathArithmeticException {
        MathUtils.checkNotNull(t);
        for (int i = 0; i < this.data.length; i++) {
            ((T[]) this.data)[i] = (FieldElement) this.data[i].divide(t);
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapInv() throws MathArithmeticException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        T one = this.field.getOne();
        for (int i = 0; i < this.data.length; i++) {
            try {
                fieldElementArr[i] = (FieldElement) one.divide(this.data[i]);
            } catch (MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        T one = this.field.getOne();
        for (int i = 0; i < this.data.length; i++) {
            try {
                ((T[]) this.data)[i] = (FieldElement) one.divide(this.data[i]);
            } catch (MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> ebeMultiply(FieldVector<T> v) throws DimensionMismatchException {
        try {
            return ebeMultiply((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                fieldElementArr[i] = (FieldElement) this.data[i].multiply(v.getEntry(i));
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            fieldElementArr[i] = (FieldElement) this.data[i].multiply(v.data[i]);
        }
        return new ArrayFieldVector<>((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> ebeDivide(FieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        try {
            return ebeDivide((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                try {
                    fieldElementArr[i] = (FieldElement) this.data[i].divide(v.getEntry(i));
                } catch (MathArithmeticException e2) {
                    throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
                }
            }
            return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
        }
    }

    public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> v) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(v.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            try {
                fieldElementArr[i] = (FieldElement) this.data[i].divide(v.data[i]);
            } catch (MathArithmeticException e) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return new ArrayFieldVector<>((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T[] getData() {
        return (T[]) ((FieldElement[]) this.data.clone());
    }

    public T[] getDataRef() {
        return this.data;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T dotProduct(FieldVector<T> v) throws DimensionMismatchException {
        try {
            return dotProduct((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            checkVectorDimensions(v);
            T zero = this.field.getZero();
            for (int i = 0; i < this.data.length; i++) {
                zero = (T) zero.add(this.data[i].multiply(v.getEntry(i)));
            }
            return zero;
        }
    }

    public T dotProduct(ArrayFieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.data.length);
        T zero = this.field.getZero();
        for (int i = 0; i < this.data.length; i++) {
            zero = (T) zero.add(this.data[i].multiply(v.data[i]));
        }
        return zero;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> projection(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        return fieldVector.mapMultiply((FieldElement) dotProduct(fieldVector).divide(fieldVector.dotProduct(fieldVector)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArrayFieldVector<T> projection(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException, MathArithmeticException {
        return (ArrayFieldVector) arrayFieldVector.mapMultiply((FieldElement) dotProduct((ArrayFieldVector) arrayFieldVector).divide(arrayFieldVector.dotProduct((ArrayFieldVector) arrayFieldVector)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) {
        try {
            return outerProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException e) {
            int length = this.data.length;
            int dimension = fieldVector.getDimension();
            Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, dimension);
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < dimension; i2++) {
                    array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(fieldVector.getEntry(i2)));
                }
            }
            return array2DRowFieldMatrix;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldMatrix<T> outerProduct(ArrayFieldVector<T> arrayFieldVector) {
        int length = this.data.length;
        int length2 = arrayFieldVector.data.length;
        Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, length2);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(arrayFieldVector.data[i2]));
            }
        }
        return array2DRowFieldMatrix;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T getEntry(int index) {
        return this.data[index];
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public int getDimension() {
        return this.data.length;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> append(FieldVector<T> v) {
        try {
            return append((ArrayFieldVector) v);
        } catch (ClassCastException e) {
            return new ArrayFieldVector((ArrayFieldVector) this, new ArrayFieldVector(v));
        }
    }

    public ArrayFieldVector<T> append(ArrayFieldVector<T> v) {
        return new ArrayFieldVector<>((ArrayFieldVector) this, (ArrayFieldVector) v);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> append(T in) {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length + 1);
        System.arraycopy(this.data, 0, fieldElementArr, 0, this.data.length);
        fieldElementArr[this.data.length] = in;
        return new ArrayFieldVector((Field) this.field, fieldElementArr, false);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
        if (n < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(n));
        }
        ArrayFieldVector<T> out = new ArrayFieldVector<>(this.field, n);
        try {
            System.arraycopy(this.data, index, out.data, 0, n);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex((index + n) - 1);
        }
        return out;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void setEntry(int index, T value) {
        try {
            this.data[index] = value;
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void setSubVector(int index, FieldVector<T> v) throws OutOfRangeException {
        try {
            try {
                set(index, (ArrayFieldVector) v);
            } catch (ClassCastException e) {
                for (int i = index; i < v.getDimension() + index; i++) {
                    this.data[i] = v.getEntry(i - index);
                }
            }
        } catch (IndexOutOfBoundsException e2) {
            checkIndex(index);
            checkIndex((v.getDimension() + index) - 1);
        }
    }

    public void set(int index, ArrayFieldVector<T> v) throws OutOfRangeException {
        try {
            System.arraycopy(v.data, 0, this.data, index, v.data.length);
        } catch (IndexOutOfBoundsException e) {
            checkIndex(index);
            checkIndex((v.data.length + index) - 1);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void set(T value) {
        Arrays.fill(this.data, value);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T[] toArray() {
        return (T[]) ((FieldElement[]) this.data.clone());
    }

    protected void checkVectorDimensions(FieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        if (this.data.length != n) {
            throw new DimensionMismatchException(this.data.length, n);
        }
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> visitor) {
        int dim = getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; i++) {
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; i <= end; i++) {
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> visitor) {
        return walkInDefaultOrder(visitor);
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor) {
        int dimension = getDimension();
        fieldVectorChangingVisitor.start(dimension, 0, dimension - 1);
        for (int i = 0; i < dimension; i++) {
            setEntry(i, fieldVectorChangingVisitor.visit(i, getEntry(i)));
        }
        return fieldVectorChangingVisitor.end();
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        fieldVectorChangingVisitor.start(getDimension(), i, i2);
        for (int i3 = i; i3 <= i2; i3++) {
            setEntry(i3, fieldVectorChangingVisitor.visit(i3, getEntry(i3)));
        }
        return fieldVectorChangingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> visitor) {
        return walkInDefaultOrder(visitor);
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        try {
            FieldVector<T> rhs = (FieldVector) other;
            if (this.data.length != rhs.getDimension()) {
                return false;
            }
            for (int i = 0; i < this.data.length; i++) {
                if (!this.data[i].equals(rhs.getEntry(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        int h = 3542;
        T[] arr$ = this.data;
        for (T a : arr$) {
            h ^= a.hashCode();
        }
        return h;
    }

    private void checkIndex(int index) throws OutOfRangeException {
        if (index < 0 || index >= getDimension()) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(index), 0, Integer.valueOf(getDimension() - 1));
        }
    }

    private void checkIndices(int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        int dim = getDimension();
        if (start < 0 || start >= dim) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(start), 0, Integer.valueOf(dim - 1));
        }
        if (end < 0 || end >= dim) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(end), 0, Integer.valueOf(dim - 1));
        }
        if (end < start) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(end), Integer.valueOf(start), false);
        }
    }
}
