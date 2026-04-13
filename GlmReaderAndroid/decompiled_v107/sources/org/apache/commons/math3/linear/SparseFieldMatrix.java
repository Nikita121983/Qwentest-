package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

/* loaded from: classes10.dex */
public class SparseFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> {
    private final int columns;
    private final OpenIntToFieldHashMap<T> entries;
    private final int rows;

    public SparseFieldMatrix(Field<T> field) {
        super(field);
        this.rows = 0;
        this.columns = 0;
        this.entries = new OpenIntToFieldHashMap<>(field);
    }

    public SparseFieldMatrix(Field<T> field, int rowDimension, int columnDimension) {
        super(field, rowDimension, columnDimension);
        this.rows = rowDimension;
        this.columns = columnDimension;
        this.entries = new OpenIntToFieldHashMap<>(field);
    }

    public SparseFieldMatrix(SparseFieldMatrix<T> other) {
        super(other.getField(), other.getRowDimension(), other.getColumnDimension());
        this.rows = other.getRowDimension();
        this.columns = other.getColumnDimension();
        this.entries = new OpenIntToFieldHashMap<>(other.entries);
    }

    public SparseFieldMatrix(FieldMatrix<T> other) {
        super(other.getField(), other.getRowDimension(), other.getColumnDimension());
        this.rows = other.getRowDimension();
        this.columns = other.getColumnDimension();
        this.entries = new OpenIntToFieldHashMap<>(getField());
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                setEntry(i, j, other.getEntry(i, j));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void addToEntry(int i, int i2, T t) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int computeKey = computeKey(i, i2);
        FieldElement fieldElement = (FieldElement) this.entries.get(computeKey).add(t);
        if (getField().getZero().equals(fieldElement)) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, fieldElement);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> copy() {
        return new SparseFieldMatrix((SparseFieldMatrix) this);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) {
        return new SparseFieldMatrix(getField(), rowDimension, columnDimension);
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getColumnDimension() {
        return this.columns;
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public T getEntry(int row, int column) {
        checkRowIndex(row);
        checkColumnIndex(column);
        return this.entries.get(computeKey(row, column));
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.AnyMatrix
    public int getRowDimension() {
        return this.rows;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void multiplyEntry(int i, int i2, T t) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int computeKey = computeKey(i, i2);
        FieldElement fieldElement = (FieldElement) this.entries.get(computeKey).multiply(t);
        if (getField().getZero().equals(fieldElement)) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, fieldElement);
        }
    }

    @Override // org.apache.commons.math3.linear.AbstractFieldMatrix, org.apache.commons.math3.linear.FieldMatrix
    public void setEntry(int row, int column, T value) {
        checkRowIndex(row);
        checkColumnIndex(column);
        if (getField().getZero().equals(value)) {
            this.entries.remove(computeKey(row, column));
        } else {
            this.entries.put(computeKey(row, column), value);
        }
    }

    private int computeKey(int row, int column) {
        return (this.columns * row) + column;
    }
}
