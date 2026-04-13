package org.apache.commons.math3.util;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

/* loaded from: classes10.dex */
public class MultidimensionalCounter implements Iterable<Integer> {
    private final int dimension;
    private final int last;
    private final int[] size;
    private final int totalSize;
    private final int[] uniCounterOffset;

    /* loaded from: classes10.dex */
    public class Iterator implements java.util.Iterator<Integer> {
        private int count = -1;
        private final int[] counter;
        private final int maxCount;

        Iterator() {
            this.counter = new int[MultidimensionalCounter.this.dimension];
            this.maxCount = MultidimensionalCounter.this.totalSize - 1;
            this.counter[MultidimensionalCounter.this.last] = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.count < this.maxCount;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            if (hasNext()) {
                int i = MultidimensionalCounter.this.last;
                while (true) {
                    if (i >= 0) {
                        if (this.counter[i] == MultidimensionalCounter.this.size[i] - 1) {
                            this.counter[i] = 0;
                            i--;
                        } else {
                            int[] iArr = this.counter;
                            iArr[i] = iArr[i] + 1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                int i2 = this.count;
                int i3 = i2 + 1;
                this.count = i3;
                return Integer.valueOf(i3);
            }
            throw new NoSuchElementException();
        }

        public int getCount() {
            return this.count;
        }

        public int[] getCounts() {
            return MathArrays.copyOf(this.counter);
        }

        public int getCount(int dim) {
            return this.counter[dim];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public MultidimensionalCounter(int... size) throws NotStrictlyPositiveException {
        this.dimension = size.length;
        this.size = MathArrays.copyOf(size);
        this.uniCounterOffset = new int[this.dimension];
        this.last = this.dimension - 1;
        int tS = size[this.last];
        for (int i = 0; i < this.last; i++) {
            int count = 1;
            for (int j = i + 1; j < this.dimension; j++) {
                count *= size[j];
            }
            this.uniCounterOffset[i] = count;
            tS *= size[i];
        }
        this.uniCounterOffset[this.last] = 0;
        if (tS <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(tS));
        }
        this.totalSize = tS;
    }

    @Override // java.lang.Iterable
    public java.util.Iterator<Integer> iterator() {
        return new Iterator();
    }

    public int getDimension() {
        return this.dimension;
    }

    public int[] getCounts(int index) throws OutOfRangeException {
        if (index < 0 || index >= this.totalSize) {
            throw new OutOfRangeException(Integer.valueOf(index), 0, Integer.valueOf(this.totalSize));
        }
        int[] indices = new int[this.dimension];
        int count = 0;
        for (int i = 0; i < this.last; i++) {
            int idx = 0;
            int offset = this.uniCounterOffset[i];
            while (count <= index) {
                count += offset;
                idx++;
            }
            count -= offset;
            indices[i] = idx - 1;
        }
        int i2 = this.last;
        indices[i2] = index - count;
        return indices;
    }

    public int getCount(int... c) throws OutOfRangeException, DimensionMismatchException {
        if (c.length != this.dimension) {
            throw new DimensionMismatchException(c.length, this.dimension);
        }
        int count = 0;
        for (int i = 0; i < this.dimension; i++) {
            int index = c[i];
            if (index < 0 || index >= this.size[i]) {
                throw new OutOfRangeException(Integer.valueOf(index), 0, Integer.valueOf(this.size[i] - 1));
            }
            count += this.uniCounterOffset[i] * c[i];
        }
        int i2 = this.last;
        return c[i2] + count;
    }

    public int getSize() {
        return this.totalSize;
    }

    public int[] getSizes() {
        return MathArrays.copyOf(this.size);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dimension; i++) {
            sb.append(CollectionUtils.DEFAULT_TOSTRING_PREFIX).append(getCount(i)).append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        return sb.toString();
    }
}
