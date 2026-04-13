package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.IntegerSequence;

@Deprecated
/* loaded from: classes10.dex */
public class Incrementor {
    private int count;
    private final MaxCountExceededCallback maxCountCallback;
    private int maximalCount;

    /* loaded from: classes10.dex */
    public interface MaxCountExceededCallback {
        void trigger(int i) throws MaxCountExceededException;
    }

    public Incrementor() {
        this(0);
    }

    public Incrementor(int max) {
        this(max, new MaxCountExceededCallback() { // from class: org.apache.commons.math3.util.Incrementor.1
            @Override // org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback
            public void trigger(int max2) throws MaxCountExceededException {
                throw new MaxCountExceededException(Integer.valueOf(max2));
            }
        });
    }

    public Incrementor(int max, MaxCountExceededCallback cb) throws NullArgumentException {
        this.count = 0;
        if (cb == null) {
            throw new NullArgumentException();
        }
        this.maximalCount = max;
        this.maxCountCallback = cb;
    }

    public void setMaximalCount(int max) {
        this.maximalCount = max;
    }

    public int getMaximalCount() {
        return this.maximalCount;
    }

    public int getCount() {
        return this.count;
    }

    public boolean canIncrement() {
        return this.count < this.maximalCount;
    }

    public void incrementCount(int value) throws MaxCountExceededException {
        for (int i = 0; i < value; i++) {
            incrementCount();
        }
    }

    public void incrementCount() throws MaxCountExceededException {
        int i = this.count + 1;
        this.count = i;
        if (i > this.maximalCount) {
            this.maxCountCallback.trigger(this.maximalCount);
        }
    }

    public void resetCount() {
        this.count = 0;
    }

    public static Incrementor wrap(final IntegerSequence.Incrementor incrementor) {
        return new Incrementor() { // from class: org.apache.commons.math3.util.Incrementor.2
            private IntegerSequence.Incrementor delegate;

            {
                this.delegate = IntegerSequence.Incrementor.this;
                super.setMaximalCount(this.delegate.getMaximalCount());
                super.incrementCount(this.delegate.getCount());
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void setMaximalCount(int max) {
                super.setMaximalCount(max);
                this.delegate = this.delegate.withMaximalCount(max);
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void resetCount() {
                super.resetCount();
                this.delegate = this.delegate.withStart(0);
            }

            @Override // org.apache.commons.math3.util.Incrementor
            public void incrementCount() {
                super.incrementCount();
                this.delegate.increment();
            }
        };
    }
}
