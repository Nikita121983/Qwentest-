package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public class FilterListIterator<E> implements ListIterator<E> {
    private ListIterator<? extends E> iterator;
    private int nextIndex;
    private E nextObject;
    private boolean nextObjectSet;
    private Predicate<? super E> predicate;
    private E previousObject;
    private boolean previousObjectSet;

    public FilterListIterator() {
    }

    public FilterListIterator(ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public FilterListIterator(ListIterator<? extends E> iterator, Predicate<? super E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    public FilterListIterator(Predicate<? super E> predicate) {
        this.predicate = predicate;
    }

    @Override // java.util.ListIterator
    public void add(E o) {
        throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
    }

    private void clearNextObject() {
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private void clearPreviousObject() {
        this.previousObject = null;
        this.previousObjectSet = false;
    }

    public ListIterator<? extends E> getListIterator() {
        return this.iterator;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.nextObjectSet || setNextObject();
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return this.previousObjectSet || setPreviousObject();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        if (!this.nextObjectSet && !setNextObject()) {
            throw new NoSuchElementException();
        }
        this.nextIndex++;
        E temp = this.nextObject;
        clearNextObject();
        return temp;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.nextIndex;
    }

    @Override // java.util.ListIterator
    public E previous() {
        if (!this.previousObjectSet && !setPreviousObject()) {
            throw new NoSuchElementException();
        }
        this.nextIndex--;
        E temp = this.previousObject;
        clearPreviousObject();
        return temp;
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.nextIndex - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
    }

    @Override // java.util.ListIterator
    public void set(E ignored) {
        throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
    }

    public void setListIterator(ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    private boolean setNextObject() {
        if (this.previousObjectSet) {
            clearPreviousObject();
            if (!setNextObject()) {
                return false;
            }
            clearNextObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasNext()) {
            E object = this.iterator.next();
            if (this.predicate.test(object)) {
                this.nextObject = object;
                this.nextObjectSet = true;
                return true;
            }
        }
        return false;
    }

    public void setPredicate(Predicate<? super E> predicate) {
        this.predicate = predicate;
    }

    private boolean setPreviousObject() {
        if (this.nextObjectSet) {
            clearNextObject();
            if (!setPreviousObject()) {
                return false;
            }
            clearPreviousObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasPrevious()) {
            E object = this.iterator.previous();
            if (this.predicate.test(object)) {
                this.previousObject = object;
                this.previousObjectSet = true;
                return true;
            }
        }
        return false;
    }
}
