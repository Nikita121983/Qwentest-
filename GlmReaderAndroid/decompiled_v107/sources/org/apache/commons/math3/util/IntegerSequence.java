package org.apache.commons.math3.util;

import java.util.Iterator;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;

/* loaded from: classes10.dex */
public class IntegerSequence {
    private IntegerSequence() {
    }

    public static Range range(int start, int end) {
        return range(start, end, 1);
    }

    public static Range range(int start, int max, int step) {
        return new Range(start, max, step);
    }

    /* loaded from: classes10.dex */
    public static class Range implements Iterable<Integer> {
        private final int max;
        private final int size;
        private final int start;
        private final int step;

        public Range(int start, int max, int step) {
            this.start = start;
            this.max = max;
            this.step = step;
            int s = ((max - start) / step) + 1;
            this.size = s < 0 ? 0 : s;
        }

        public int size() {
            return this.size;
        }

        @Override // java.lang.Iterable
        public Iterator<Integer> iterator() {
            return Incrementor.create().withStart(this.start).withMaximalCount(this.max + (this.step > 0 ? 1 : -1)).withIncrement(this.step);
        }
    }

    /* loaded from: classes10.dex */
    public static class Incrementor implements Iterator<Integer> {
        private static final MaxCountExceededCallback CALLBACK = new MaxCountExceededCallback() { // from class: org.apache.commons.math3.util.IntegerSequence.Incrementor.1
            @Override // org.apache.commons.math3.util.IntegerSequence.Incrementor.MaxCountExceededCallback
            public void trigger(int max) throws MaxCountExceededException {
                throw new MaxCountExceededException(Integer.valueOf(max));
            }
        };
        private int count;
        private final int increment;
        private final int init;
        private final MaxCountExceededCallback maxCountCallback;
        private final int maximalCount;

        /* loaded from: classes10.dex */
        public interface MaxCountExceededCallback {
            void trigger(int i) throws MaxCountExceededException;
        }

        private Incrementor(int start, int max, int step, MaxCountExceededCallback cb) throws NullArgumentException {
            this.count = 0;
            if (cb == null) {
                throw new NullArgumentException();
            }
            this.init = start;
            this.maximalCount = max;
            this.increment = step;
            this.maxCountCallback = cb;
            this.count = start;
        }

        public static Incrementor create() {
            return new Incrementor(0, 0, 1, CALLBACK);
        }

        public Incrementor withStart(int start) {
            return new Incrementor(start, this.maximalCount, this.increment, this.maxCountCallback);
        }

        public Incrementor withMaximalCount(int max) {
            return new Incrementor(this.init, max, this.increment, this.maxCountCallback);
        }

        public Incrementor withIncrement(int step) {
            if (step == 0) {
                throw new ZeroException();
            }
            return new Incrementor(this.init, this.maximalCount, step, this.maxCountCallback);
        }

        public Incrementor withCallback(MaxCountExceededCallback cb) {
            return new Incrementor(this.init, this.maximalCount, this.increment, cb);
        }

        public int getMaximalCount() {
            return this.maximalCount;
        }

        public int getCount() {
            return this.count;
        }

        public boolean canIncrement() {
            return canIncrement(1);
        }

        public boolean canIncrement(int nTimes) {
            int finalCount = this.count + (this.increment * nTimes);
            if (this.increment < 0) {
                if (finalCount > this.maximalCount) {
                    return true;
                }
            } else if (finalCount < this.maximalCount) {
                return true;
            }
            return false;
        }

        public void increment(int nTimes) throws MaxCountExceededException {
            if (nTimes <= 0) {
                throw new NotStrictlyPositiveException(Integer.valueOf(nTimes));
            }
            if (!canIncrement(0)) {
                this.maxCountCallback.trigger(this.maximalCount);
            }
            this.count += this.increment * nTimes;
        }

        public void increment() throws MaxCountExceededException {
            increment(1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return canIncrement(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            int value = this.count;
            increment();
            return Integer.valueOf(value);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new MathUnsupportedOperationException();
        }
    }
}
