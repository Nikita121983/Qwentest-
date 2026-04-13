package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import org.apache.commons.collections4.ResettableListIterator;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes9.dex */
public class ReverseListIterator<E> implements ResettableListIterator<E> {
    private ListIterator<E> iterator;
    private final List<E> list;
    private boolean validForUpdate = true;

    public ReverseListIterator(List<E> list) {
        this.list = (List) Objects.requireNonNull(list, XmlErrorCodes.LIST);
        this.iterator = list.listIterator(list.size());
    }

    @Override // java.util.ListIterator
    public void add(E obj) {
        if (!this.validForUpdate) {
            throw new IllegalStateException("Cannot add to list until next() or previous() called");
        }
        this.validForUpdate = false;
        this.iterator.add(obj);
        this.iterator.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasPrevious();
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        return this.iterator.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        E obj = this.iterator.previous();
        this.validForUpdate = true;
        return obj;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.iterator.previousIndex();
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public E previous() {
        E obj = this.iterator.next();
        this.validForUpdate = true;
        return obj;
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.iterator.nextIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        if (!this.validForUpdate) {
            throw new IllegalStateException("Cannot remove from list until next() or previous() called");
        }
        this.iterator.remove();
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        this.iterator = this.list.listIterator(this.list.size());
    }

    @Override // java.util.ListIterator
    public void set(E obj) {
        if (!this.validForUpdate) {
            throw new IllegalStateException("Cannot set to list until next() or previous() called");
        }
        this.iterator.set(obj);
    }
}
