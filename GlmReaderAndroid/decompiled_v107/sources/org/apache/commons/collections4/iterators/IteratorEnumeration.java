package org.apache.commons.collections4.iterators;

import java.util.Enumeration;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class IteratorEnumeration<E> implements Enumeration<E> {
    private Iterator<? extends E> iterator;

    public IteratorEnumeration() {
    }

    public IteratorEnumeration(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public Iterator<? extends E> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Enumeration
    public E nextElement() {
        return this.iterator.next();
    }

    public void setIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }
}
