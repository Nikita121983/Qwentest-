package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

/* loaded from: classes9.dex */
abstract class AbstractEmptyIterator<E> implements ResettableIterator<E> {
    @Deprecated
    public void add(E ignored) {
        throw new UnsupportedOperationException("add() not supported for empty Iterator");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    public boolean hasPrevious() {
        return false;
    }

    @Override // java.util.Iterator
    public E next() {
        throw new NoSuchElementException("Iterator contains no elements");
    }

    public int nextIndex() {
        return 0;
    }

    public E previous() {
        throw new NoSuchElementException("Iterator contains no elements");
    }

    public int previousIndex() {
        return -1;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new IllegalStateException("Iterator contains no elements");
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
    }

    public void set(E ignored) {
        throw new IllegalStateException("Iterator contains no elements");
    }
}
