package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.FluentIterable;

/* loaded from: classes9.dex */
public class ZippingIterator<E> implements Iterator<E> {
    private final Iterator<Iterator<? extends E>> iterators;
    private Iterator<? extends E> lastReturned;
    private Iterator<? extends E> nextIterator;

    public ZippingIterator(Iterator<? extends E>... itArr) {
        ArrayList arrayList = new ArrayList();
        for (Iterator<? extends E> it : itArr) {
            Objects.requireNonNull(it, "iterator");
            arrayList.add(it);
        }
        this.iterators = FluentIterable.of((Iterable) arrayList).loop().iterator();
    }

    public ZippingIterator(Iterator<? extends E> a, Iterator<? extends E> b) {
        this(a, b);
    }

    public ZippingIterator(Iterator<? extends E> a, Iterator<? extends E> b, Iterator<? extends E> c) {
        this(a, b, c);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextIterator != null) {
            return true;
        }
        while (this.iterators.hasNext()) {
            Iterator<? extends E> childIterator = this.iterators.next();
            if (childIterator.hasNext()) {
                this.nextIterator = childIterator;
                return true;
            }
            this.iterators.remove();
        }
        return false;
    }

    @Override // java.util.Iterator
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        E val = this.nextIterator.next();
        this.lastReturned = this.nextIterator;
        this.nextIterator = null;
        return val;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException("No value can be removed at present");
        }
        this.lastReturned.remove();
        this.lastReturned = null;
    }
}
