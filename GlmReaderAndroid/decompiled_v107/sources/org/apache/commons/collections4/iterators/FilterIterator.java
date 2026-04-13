package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;

/* loaded from: classes9.dex */
public class FilterIterator<E> implements IteratorOperations<E> {
    private Iterator<? extends E> iterator;
    private E nextObject;
    private boolean nextObjectSet;
    private Predicate<? super E> predicate;

    public FilterIterator() {
        this.predicate = TruePredicate.truePredicate();
    }

    public FilterIterator(Iterator<? extends E> iterator) {
        this.predicate = TruePredicate.truePredicate();
        this.iterator = iterator;
    }

    public FilterIterator(Iterator<? extends E> iterator, Predicate<? super E> predicate) {
        this.predicate = TruePredicate.truePredicate();
        this.iterator = iterator;
        this.predicate = safePredicate(predicate);
    }

    public Iterator<? extends E> getIterator() {
        return this.iterator;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.nextObjectSet || setNextObject();
    }

    @Override // java.util.Iterator
    public E next() {
        if (!this.nextObjectSet && !setNextObject()) {
            throw new NoSuchElementException();
        }
        this.nextObjectSet = false;
        return this.nextObject;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.nextObjectSet) {
            throw new IllegalStateException("remove() cannot be called");
        }
        this.iterator.remove();
    }

    private Predicate<? super E> safePredicate(Predicate<? super E> predicate) {
        return predicate != null ? predicate : TruePredicate.truePredicate();
    }

    public void setIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private boolean setNextObject() {
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
        this.predicate = safePredicate(predicate);
        this.nextObject = null;
        this.nextObjectSet = false;
    }
}
