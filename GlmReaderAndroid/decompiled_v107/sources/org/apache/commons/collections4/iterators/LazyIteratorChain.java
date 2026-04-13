package org.apache.commons.collections4.iterators;

import java.util.Iterator;

/* loaded from: classes9.dex */
public abstract class LazyIteratorChain<E> implements Iterator<E> {
    private int callCounter;
    private boolean chainExhausted;
    private Iterator<? extends E> currentIterator;
    private Iterator<? extends E> lastUsedIterator;

    protected abstract Iterator<? extends E> nextIterator(int i);

    @Override // java.util.Iterator
    public boolean hasNext() {
        updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.currentIterator == null) {
            updateCurrentIterator();
        }
        this.lastUsedIterator.remove();
    }

    private void updateCurrentIterator() {
        if (this.callCounter == 0) {
            int i = this.callCounter + 1;
            this.callCounter = i;
            this.currentIterator = nextIterator(i);
            if (this.currentIterator == null) {
                this.currentIterator = EmptyIterator.emptyIterator();
                this.chainExhausted = true;
            }
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && !this.chainExhausted) {
            int i2 = this.callCounter + 1;
            this.callCounter = i2;
            Iterator<? extends E> nextIterator = nextIterator(i2);
            if (nextIterator != null) {
                this.currentIterator = nextIterator;
            } else {
                this.chainExhausted = true;
            }
        }
    }
}
