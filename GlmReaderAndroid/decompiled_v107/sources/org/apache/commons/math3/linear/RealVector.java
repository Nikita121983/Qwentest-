package org.apache.commons.math3.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.function.Multiply;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class RealVector {
    public abstract RealVector append(double d);

    public abstract RealVector append(RealVector realVector);

    public abstract RealVector copy();

    public abstract RealVector ebeDivide(RealVector realVector) throws DimensionMismatchException;

    public abstract RealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException;

    public abstract int getDimension();

    public abstract double getEntry(int i) throws OutOfRangeException;

    public abstract RealVector getSubVector(int i, int i2) throws NotPositiveException, OutOfRangeException;

    public abstract boolean isInfinite();

    public abstract boolean isNaN();

    public abstract void setEntry(int i, double d) throws OutOfRangeException;

    public abstract void setSubVector(int i, RealVector realVector) throws OutOfRangeException;

    public void addToEntry(int index, double increment) throws OutOfRangeException {
        setEntry(index, getEntry(index) + increment);
    }

    protected void checkVectorDimensions(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkVectorDimensions(int n) throws DimensionMismatchException {
        int d = getDimension();
        if (d != n) {
            throw new DimensionMismatchException(d, n);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkIndex(int index) throws OutOfRangeException {
        if (index < 0 || index >= getDimension()) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(index), 0, Integer.valueOf(getDimension() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkIndices(int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
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

    public RealVector add(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        RealVector result = v.copy();
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            int index = e.getIndex();
            result.setEntry(index, e.getValue() + result.getEntry(index));
        }
        return result;
    }

    public RealVector subtract(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        RealVector result = v.mapMultiply(-1.0d);
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            int index = e.getIndex();
            result.setEntry(index, e.getValue() + result.getEntry(index));
        }
        return result;
    }

    public RealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    public RealVector mapAddToSelf(double d) {
        if (d != 0.0d) {
            return mapToSelf(FunctionUtils.fix2ndArgument(new Add(), d));
        }
        return this;
    }

    public double dotProduct(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0.0d;
        int n = getDimension();
        for (int i = 0; i < n; i++) {
            d += getEntry(i) * v.getEntry(i);
        }
        return d;
    }

    public double cosine(RealVector v) throws DimensionMismatchException, MathArithmeticException {
        double norm = getNorm();
        double vNorm = v.getNorm();
        if (norm == 0.0d || vNorm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return dotProduct(v) / (norm * vNorm);
    }

    public double getDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            double diff = e.getValue() - v.getEntry(e.getIndex());
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }

    public double getNorm() {
        double sum = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            double value = e.getValue();
            sum += value * value;
        }
        return FastMath.sqrt(sum);
    }

    public double getL1Norm() {
        double norm = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            norm += FastMath.abs(e.getValue());
        }
        return norm;
    }

    public double getLInfNorm() {
        double norm = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            norm = FastMath.max(norm, FastMath.abs(e.getValue()));
        }
        return norm;
    }

    public double getL1Distance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            d += FastMath.abs(e.getValue() - v.getEntry(e.getIndex()));
        }
        return d;
    }

    public double getLInfDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0.0d;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            d = FastMath.max(FastMath.abs(e.getValue() - v.getEntry(e.getIndex())), d);
        }
        return d;
    }

    public int getMinIndex() {
        int minIndex = -1;
        double minValue = Double.POSITIVE_INFINITY;
        Iterator<Entry> iterator = iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.getValue() <= minValue) {
                minIndex = entry.getIndex();
                minValue = entry.getValue();
            }
        }
        return minIndex;
    }

    public double getMinValue() {
        int minIndex = getMinIndex();
        if (minIndex < 0) {
            return Double.NaN;
        }
        return getEntry(minIndex);
    }

    public int getMaxIndex() {
        int maxIndex = -1;
        double maxValue = Double.NEGATIVE_INFINITY;
        Iterator<Entry> iterator = iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.getValue() >= maxValue) {
                maxIndex = entry.getIndex();
                maxValue = entry.getValue();
            }
        }
        return maxIndex;
    }

    public double getMaxValue() {
        int maxIndex = getMaxIndex();
        if (maxIndex < 0) {
            return Double.NaN;
        }
        return getEntry(maxIndex);
    }

    public RealVector mapMultiply(double d) {
        return copy().mapMultiplyToSelf(d);
    }

    public RealVector mapMultiplyToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Multiply(), d));
    }

    public RealVector mapSubtract(double d) {
        return copy().mapSubtractToSelf(d);
    }

    public RealVector mapSubtractToSelf(double d) {
        return mapAddToSelf(-d);
    }

    public RealVector mapDivide(double d) {
        return copy().mapDivideToSelf(d);
    }

    public RealVector mapDivideToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Divide(), d));
    }

    public RealMatrix outerProduct(RealVector v) {
        RealMatrix product;
        int m = getDimension();
        int n = v.getDimension();
        if ((v instanceof SparseRealVector) || (this instanceof SparseRealVector)) {
            product = new OpenMapRealMatrix(m, n);
        } else {
            product = new Array2DRowRealMatrix(m, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                product.setEntry(i, j, getEntry(i) * v.getEntry(j));
            }
        }
        return product;
    }

    public RealVector projection(RealVector v) throws DimensionMismatchException, MathArithmeticException {
        double norm2 = v.dotProduct(v);
        if (norm2 == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return v.mapMultiply(dotProduct(v) / v.dotProduct(v));
    }

    public void set(double value) {
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            e.setValue(value);
        }
    }

    public double[] toArray() {
        int dim = getDimension();
        double[] values = new double[dim];
        for (int i = 0; i < dim; i++) {
            values[i] = getEntry(i);
        }
        return values;
    }

    public RealVector unitVector() throws MathArithmeticException {
        double norm = getNorm();
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        return mapDivide(norm);
    }

    public void unitize() throws MathArithmeticException {
        double norm = getNorm();
        if (norm == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        mapDivideToSelf(getNorm());
    }

    public Iterator<Entry> sparseIterator() {
        return new SparseEntryIterator();
    }

    public Iterator<Entry> iterator() {
        final int dim = getDimension();
        return new Iterator<Entry>() { // from class: org.apache.commons.math3.linear.RealVector.1
            private Entry e;
            private int i = 0;

            {
                this.e = new Entry();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < dim;
            }

            @Override // java.util.Iterator
            public Entry next() {
                if (this.i < dim) {
                    Entry entry = this.e;
                    int i = this.i;
                    this.i = i + 1;
                    entry.setIndex(i);
                    return this.e;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }
        };
    }

    public RealVector map(UnivariateFunction function) {
        return copy().mapToSelf(function);
    }

    public RealVector mapToSelf(UnivariateFunction function) {
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Entry e = it.next();
            e.setValue(function.value(e.getValue()));
        }
        return this;
    }

    public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
        return copy().combineToSelf(a, b, y);
    }

    public RealVector combineToSelf(double a, double b, RealVector y) throws DimensionMismatchException {
        checkVectorDimensions(y);
        for (int i = 0; i < getDimension(); i++) {
            double xi = getEntry(i);
            double yi = y.getEntry(i);
            setEntry(i, (a * xi) + (b * yi));
        }
        return this;
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor visitor) {
        int dim = getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; i++) {
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; i <= end; i++) {
            visitor.visit(i, getEntry(i));
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor visitor) {
        return walkInDefaultOrder(visitor);
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor visitor) {
        int dim = getDimension();
        visitor.start(dim, 0, dim - 1);
        for (int i = 0; i < dim; i++) {
            setEntry(i, visitor.visit(i, getEntry(i)));
        }
        return visitor.end();
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(start, end);
        visitor.start(getDimension(), start, end);
        for (int i = start; i <= end; i++) {
            setEntry(i, visitor.visit(i, getEntry(i)));
        }
        return visitor.end();
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor visitor) {
        return walkInDefaultOrder(visitor);
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor visitor, int start, int end) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(visitor, start, end);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public class Entry {
        private int index;

        public Entry() {
            setIndex(0);
        }

        public double getValue() {
            return RealVector.this.getEntry(getIndex());
        }

        public void setValue(double value) {
            RealVector.this.setEntry(getIndex(), value);
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public boolean equals(Object other) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    public int hashCode() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public class SparseEntryIterator implements Iterator<Entry> {
        private Entry current;
        private final int dim;
        private Entry next;

        protected SparseEntryIterator() {
            this.dim = RealVector.this.getDimension();
            this.current = new Entry();
            this.next = new Entry();
            if (this.next.getValue() == 0.0d) {
                advance(this.next);
            }
        }

        protected void advance(Entry e) {
            if (e == null) {
                return;
            }
            do {
                e.setIndex(e.getIndex() + 1);
                if (e.getIndex() >= this.dim) {
                    break;
                }
            } while (e.getValue() == 0.0d);
            if (e.getIndex() >= this.dim) {
                e.setIndex(-1);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next.getIndex() >= 0;
        }

        @Override // java.util.Iterator
        public Entry next() {
            int index = this.next.getIndex();
            if (index < 0) {
                throw new NoSuchElementException();
            }
            this.current.setIndex(index);
            advance(this.next);
            return this.current;
        }

        @Override // java.util.Iterator
        public void remove() throws MathUnsupportedOperationException {
            throw new MathUnsupportedOperationException();
        }
    }

    public static RealVector unmodifiableRealVector(RealVector v) {
        return new RealVector() { // from class: org.apache.commons.math3.linear.RealVector.2
            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapToSelf(UnivariateFunction function) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector map(UnivariateFunction function) {
                return RealVector.this.map(function);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public Iterator<Entry> iterator() {
                final Iterator<Entry> i = RealVector.this.iterator();
                return new Iterator<Entry>() { // from class: org.apache.commons.math3.linear.RealVector.2.1
                    private final UnmodifiableEntry e;

                    {
                        this.e = new UnmodifiableEntry();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Entry next() {
                        this.e.setIndex(((Entry) i.next()).getIndex());
                        return this.e;
                    }

                    @Override // java.util.Iterator
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public Iterator<Entry> sparseIterator() {
                final Iterator<Entry> i = RealVector.this.sparseIterator();
                return new Iterator<Entry>() { // from class: org.apache.commons.math3.linear.RealVector.2.2
                    private final UnmodifiableEntry e;

                    {
                        this.e = new UnmodifiableEntry();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Entry next() {
                        this.e.setIndex(((Entry) i.next()).getIndex());
                        return this.e;
                    }

                    @Override // java.util.Iterator
                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector copy() {
                return RealVector.this.copy();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector add(RealVector w) throws DimensionMismatchException {
                return RealVector.this.add(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector subtract(RealVector w) throws DimensionMismatchException {
                return RealVector.this.subtract(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapAdd(double d) {
                return RealVector.this.mapAdd(d);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapAddToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapSubtract(double d) {
                return RealVector.this.mapSubtract(d);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapSubtractToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapMultiply(double d) {
                return RealVector.this.mapMultiply(d);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapMultiplyToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapDivide(double d) {
                return RealVector.this.mapDivide(d);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector mapDivideToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector ebeMultiply(RealVector w) throws DimensionMismatchException {
                return RealVector.this.ebeMultiply(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector ebeDivide(RealVector w) throws DimensionMismatchException {
                return RealVector.this.ebeDivide(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double dotProduct(RealVector w) throws DimensionMismatchException {
                return RealVector.this.dotProduct(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double cosine(RealVector w) throws DimensionMismatchException, MathArithmeticException {
                return RealVector.this.cosine(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getNorm() {
                return RealVector.this.getNorm();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getL1Norm() {
                return RealVector.this.getL1Norm();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getLInfNorm() {
                return RealVector.this.getLInfNorm();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getDistance(RealVector w) throws DimensionMismatchException {
                return RealVector.this.getDistance(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getL1Distance(RealVector w) throws DimensionMismatchException {
                return RealVector.this.getL1Distance(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getLInfDistance(RealVector w) throws DimensionMismatchException {
                return RealVector.this.getLInfDistance(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector unitVector() throws MathArithmeticException {
                return RealVector.this.unitVector();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public void unitize() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealMatrix outerProduct(RealVector w) {
                return RealVector.this.outerProduct(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double getEntry(int index) throws OutOfRangeException {
                return RealVector.this.getEntry(index);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public void setEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public void addToEntry(int index, double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public int getDimension() {
                return RealVector.this.getDimension();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector append(RealVector w) {
                return RealVector.this.append(w);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector append(double d) {
                return RealVector.this.append(d);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector getSubVector(int index, int n) throws OutOfRangeException, NotPositiveException {
                return RealVector.this.getSubVector(index, n);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public void setSubVector(int index, RealVector w) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public void set(double value) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public double[] toArray() {
                return RealVector.this.toArray();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public boolean isNaN() {
                return RealVector.this.isNaN();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public boolean isInfinite() {
                return RealVector.this.isInfinite();
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector combine(double a, double b, RealVector y) throws DimensionMismatchException {
                return RealVector.this.combine(a, b, y);
            }

            @Override // org.apache.commons.math3.linear.RealVector
            public RealVector combineToSelf(double a, double b, RealVector y) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: org.apache.commons.math3.linear.RealVector$2$UnmodifiableEntry */
            /* loaded from: classes10.dex */
            public class UnmodifiableEntry extends Entry {
                UnmodifiableEntry() {
                    super();
                }

                @Override // org.apache.commons.math3.linear.RealVector.Entry
                public double getValue() {
                    return RealVector.this.getEntry(getIndex());
                }

                @Override // org.apache.commons.math3.linear.RealVector.Entry
                public void setValue(double value) throws MathUnsupportedOperationException {
                    throw new MathUnsupportedOperationException();
                }
            }
        };
    }
}
