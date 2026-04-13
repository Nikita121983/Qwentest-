package org.apache.commons.collections4.iterators;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class EnumerationIterator<E> implements Iterator<E> {
    private final Collection<? super E> collection;
    private Enumeration<? extends E> enumeration;
    private E last;

    public EnumerationIterator() {
        this(null, null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration) {
        this(enumeration, null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration, Collection<? super E> collection) {
        this.enumeration = enumeration;
        this.collection = collection;
        this.last = null;
    }

    public Enumeration<? extends E> getEnumeration() {
        return this.enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    @Override // java.util.Iterator
    public E next() {
        this.last = this.enumeration.nextElement();
        return this.last;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.collection == null) {
            throw new UnsupportedOperationException("No Collection associated with this Iterator");
        }
        if (this.last == null) {
            throw new IllegalStateException("next() must have been called for remove() to function");
        }
        this.collection.remove(this.last);
    }

    public void setEnumeration(Enumeration<? extends E> enumeration) {
        this.enumeration = enumeration;
    }
}
