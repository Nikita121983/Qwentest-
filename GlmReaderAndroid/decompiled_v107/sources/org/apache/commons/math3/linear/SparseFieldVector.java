package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

/* loaded from: classes10.dex */
public class SparseFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
    private static final long serialVersionUID = 7841233292190413362L;
    private final OpenIntToFieldHashMap<T> entries;
    private final Field<T> field;
    private final int virtualSize;

    public SparseFieldVector(Field<T> field) {
        this(field, 0);
    }

    public SparseFieldVector(Field<T> field, int dimension) {
        this.field = field;
        this.virtualSize = dimension;
        this.entries = new OpenIntToFieldHashMap<>(field);
    }

    protected SparseFieldVector(SparseFieldVector<T> v, int resize) {
        this.field = v.field;
        this.virtualSize = v.getDimension() + resize;
        this.entries = new OpenIntToFieldHashMap<>(v.entries);
    }

    public SparseFieldVector(Field<T> field, int dimension, int expectedSize) {
        this.field = field;
        this.virtualSize = dimension;
        this.entries = new OpenIntToFieldHashMap<>(field, expectedSize);
    }

    public SparseFieldVector(Field<T> field, T[] values) throws NullArgumentException {
        MathUtils.checkNotNull(values);
        this.field = field;
        this.virtualSize = values.length;
        this.entries = new OpenIntToFieldHashMap<>(field);
        for (int key = 0; key < values.length; key++) {
            T value = values[key];
            this.entries.put(key, value);
        }
    }

    public SparseFieldVector(SparseFieldVector<T> v) {
        this.field = v.field;
        this.virtualSize = v.getDimension();
        this.entries = new OpenIntToFieldHashMap<>(v.getEntries());
    }

    private OpenIntToFieldHashMap<T> getEntries() {
        return this.entries;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldVector<T> add(SparseFieldVector<T> sparseFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(sparseFieldVector.getDimension());
        SparseFieldVector sparseFieldVector2 = (SparseFieldVector) copy();
        OpenIntToFieldHashMap<T>.Iterator it = sparseFieldVector.getEntries().iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            T value = it.value();
            if (this.entries.containsKey(key)) {
                sparseFieldVector2.setEntry(key, (FieldElement) this.entries.get(key).add(value));
            } else {
                sparseFieldVector2.setEntry(key, value);
            }
        }
        return sparseFieldVector2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FieldVector<T> append(SparseFieldVector<T> sparseFieldVector) {
        SparseFieldVector sparseFieldVector2 = new SparseFieldVector(this, sparseFieldVector.getDimension());
        OpenIntToFieldHashMap<T>.Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector2.setEntry(it.key() + this.virtualSize, it.value());
        }
        return sparseFieldVector2;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> append(FieldVector<T> v) {
        if (v instanceof SparseFieldVector) {
            return append((SparseFieldVector) v);
        }
        int n = v.getDimension();
        FieldVector<T> res = new SparseFieldVector<>(this, n);
        for (int i = 0; i < n; i++) {
            res.setEntry(this.virtualSize + i, v.getEntry(i));
        }
        return res;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> append(T d) throws NullArgumentException {
        MathUtils.checkNotNull(d);
        FieldVector<T> res = new SparseFieldVector<>(this, 1);
        res.setEntry(this.virtualSize, d);
        return res;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> copy() {
        return new SparseFieldVector(this);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T dotProduct(FieldVector<T> v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        T zero = this.field.getZero();
        OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            zero = (T) zero.add(v.getEntry(iter.key()).multiply(iter.value()));
        }
        return zero;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.commons.math3.FieldElement] */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> ebeDivide(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(fieldVector.getDimension());
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        OpenIntToFieldHashMap<T>.Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector.setEntry(it.key(), (FieldElement) it.value().divide(fieldVector.getEntry(it.key())));
        }
        return sparseFieldVector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.commons.math3.FieldElement] */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> ebeMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        checkVectorDimensions(fieldVector.getDimension());
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        OpenIntToFieldHashMap<T>.Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector.setEntry(it.key(), (FieldElement) it.value().multiply(fieldVector.getEntry(it.key())));
        }
        return sparseFieldVector;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    @Deprecated
    public T[] getData() {
        return toArray();
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public int getDimension() {
        return this.virtualSize;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public T getEntry(int index) throws OutOfRangeException {
        checkIndex(index);
        return this.entries.get(index);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public Field<T> getField() {
        return this.field;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        checkIndex(i);
        checkIndex((i + i2) - 1);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this.field, i2);
        int i3 = i + i2;
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (key >= i && key < i3) {
                sparseFieldVector.setEntry(key - i, it.value());
            }
        }
        return sparseFieldVector;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapAdd(T d) throws NullArgumentException {
        return copy().mapAddToSelf(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapAddToSelf(T d) throws NullArgumentException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, (FieldElement) getEntry(i).add(d));
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapDivide(T d) throws NullArgumentException, MathArithmeticException {
        return copy().mapDivideToSelf(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.commons.math3.FieldElement] */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapDivideToSelf(T t) throws NullArgumentException, MathArithmeticException {
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            this.entries.put(it.key(), (FieldElement) it.value().divide(t));
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapInv() throws MathArithmeticException {
        return copy().mapInvToSelf();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, (FieldElement) this.field.getOne().divide(getEntry(i)));
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapMultiply(T d) throws NullArgumentException {
        return copy().mapMultiplyToSelf(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.commons.math3.FieldElement] */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapMultiplyToSelf(T t) throws NullArgumentException {
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            this.entries.put(it.key(), (FieldElement) it.value().multiply(t));
        }
        return this;
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapSubtract(T d) throws NullArgumentException {
        return copy().mapSubtractToSelf(d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> mapSubtractToSelf(T d) throws NullArgumentException {
        return mapAddToSelf((FieldElement) this.field.getZero().subtract(d));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [org.apache.commons.math3.FieldElement] */
    public FieldMatrix<T> outerProduct(SparseFieldVector<T> sparseFieldVector) {
        SparseFieldMatrix sparseFieldMatrix = new SparseFieldMatrix(this.field, this.virtualSize, sparseFieldVector.getDimension());
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            OpenIntToFieldHashMap<T>.Iterator it2 = sparseFieldVector.entries.iterator();
            while (it2.hasNext()) {
                it2.advance();
                sparseFieldMatrix.setEntry(it.key(), it2.key(), (FieldElement) it.value().multiply(it2.value()));
            }
        }
        return sparseFieldMatrix;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [org.apache.commons.math3.FieldElement] */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) {
        if (fieldVector instanceof SparseFieldVector) {
            return outerProduct((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        SparseFieldMatrix sparseFieldMatrix = new SparseFieldMatrix(this.field, this.virtualSize, dimension);
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            ?? value = it.value();
            for (int i = 0; i < dimension; i++) {
                sparseFieldMatrix.setEntry(key, i, (FieldElement) value.multiply(fieldVector.getEntry(i)));
            }
        }
        return sparseFieldMatrix;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> projection(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(fieldVector.getDimension());
        return fieldVector.mapMultiply((FieldElement) dotProduct(fieldVector).divide(fieldVector.dotProduct(fieldVector)));
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void set(T value) {
        MathUtils.checkNotNull(value);
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, value);
        }
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void setEntry(int index, T value) throws NullArgumentException, OutOfRangeException {
        MathUtils.checkNotNull(value);
        checkIndex(index);
        this.entries.put(index, value);
    }

    @Override // org.apache.commons.math3.linear.FieldVector
    public void setSubVector(int index, FieldVector<T> v) throws OutOfRangeException {
        checkIndex(index);
        checkIndex((v.getDimension() + index) - 1);
        int n = v.getDimension();
        for (int i = 0; i < n; i++) {
            setEntry(i + index, v.getEntry(i));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SparseFieldVector<T> subtract(SparseFieldVector<T> sparseFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(sparseFieldVector.getDimension());
        SparseFieldVector<T> sparseFieldVector2 = (SparseFieldVector<T>) ((SparseFieldVector) copy());
        OpenIntToFieldHashMap<T>.Iterator it = sparseFieldVector.getEntries().iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (this.entries.containsKey(key)) {
                sparseFieldVector2.setEntry(key, (FieldElement) this.entries.get(key).subtract(it.value()));
            } else {
                sparseFieldVector2.setEntry(key, (FieldElement) this.field.getZero().subtract(it.value()));
            }
        }
        return sparseFieldVector2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> subtract(FieldVector<T> fieldVector) throws DimensionMismatchException {
        if (fieldVector instanceof SparseFieldVector) {
            return subtract((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        checkVectorDimensions(dimension);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        for (int i = 0; i < dimension; i++) {
            if (this.entries.containsKey(i)) {
                sparseFieldVector.setEntry(i, (FieldElement) this.entries.get(i).subtract(fieldVector.getEntry(i)));
            } else {
                sparseFieldVector.setEntry(i, (FieldElement) this.field.getZero().subtract(fieldVector.getEntry(i)));
            }
        }
        return sparseFieldVector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public T[] toArray() {
        T[] tArr = (T[]) ((FieldElement[]) MathArrays.buildArray(this.field, this.virtualSize));
        OpenIntToFieldHashMap<T>.Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            tArr[it.key()] = it.value();
        }
        return tArr;
    }

    private void checkIndex(int index) throws OutOfRangeException {
        if (index < 0 || index >= getDimension()) {
            throw new OutOfRangeException(Integer.valueOf(index), 0, Integer.valueOf(getDimension() - 1));
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

    protected void checkVectorDimensions(int n) throws DimensionMismatchException {
        if (getDimension() != n) {
            throw new DimensionMismatchException(getDimension(), n);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.FieldVector
    public FieldVector<T> add(FieldVector<T> fieldVector) throws DimensionMismatchException {
        if (fieldVector instanceof SparseFieldVector) {
            return add((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        checkVectorDimensions(dimension);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this.field, getDimension());
        for (int i = 0; i < dimension; i++) {
            sparseFieldVector.setEntry(i, (FieldElement) fieldVector.getEntry(i).add(getEntry(i)));
        }
        return sparseFieldVector;
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

    public int hashCode() {
        int result = (1 * 31) + (this.field == null ? 0 : this.field.hashCode());
        int result2 = (result * 31) + this.virtualSize;
        OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            int temp = iter.value().hashCode();
            result2 = (result2 * 31) + temp;
        }
        return result2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SparseFieldVector)) {
            return false;
        }
        SparseFieldVector<T> other = (SparseFieldVector) obj;
        if (this.field == null) {
            if (other.field != null) {
                return false;
            }
        } else if (!this.field.equals(other.field)) {
            return false;
        }
        if (this.virtualSize != other.virtualSize) {
            return false;
        }
        OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
        while (iter.hasNext()) {
            iter.advance();
            T test = other.getEntry(iter.key());
            if (!test.equals(iter.value())) {
                return false;
            }
        }
        OpenIntToFieldHashMap<T>.Iterator iter2 = other.getEntries().iterator();
        while (iter2.hasNext()) {
            iter2.advance();
            if (!iter2.value().equals(getEntry(iter2.key()))) {
                return false;
            }
        }
        return true;
    }
}
