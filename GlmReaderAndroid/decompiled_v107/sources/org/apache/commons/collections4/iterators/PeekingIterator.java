package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/* loaded from: classes9.dex */
public class PeekingIterator<E> implements Iterator<E> {
    private boolean exhausted;
    private final Iterator<? extends E> iterator;
    private E slot;
    private boolean slotFilled;

    public static <E> PeekingIterator<E> peekingIterator(Iterator<? extends E> iterator) {
        Objects.requireNonNull(iterator, "iterator");
        if (iterator instanceof PeekingIterator) {
            PeekingIterator<E> it = (PeekingIterator) iterator;
            return it;
        }
        return new PeekingIterator<>(iterator);
    }

    public PeekingIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public E element() {
        fill();
        if (this.exhausted) {
            throw new NoSuchElementException();
        }
        return this.slot;
    }

    private void fill() {
        if (this.exhausted || this.slotFilled) {
            return;
        }
        if (this.iterator.hasNext()) {
            this.slot = this.iterator.next();
            this.slotFilled = true;
        } else {
            this.exhausted = true;
            this.slot = null;
            this.slotFilled = false;
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.exhausted) {
            return false;
        }
        return this.slotFilled || this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E x = this.slotFilled ? this.slot : this.iterator.next();
        this.slot = null;
        this.slotFilled = false;
        return x;
    }

    public E peek() {
        fill();
        if (this.exhausted) {
            return null;
        }
        return this.slot;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.slotFilled) {
            throw new IllegalStateException("peek() or element() called before remove()");
        }
        this.iterator.remove();
    }
}
