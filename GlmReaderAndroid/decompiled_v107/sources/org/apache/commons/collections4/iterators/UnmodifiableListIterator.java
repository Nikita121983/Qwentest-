package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import java.util.Objects;
import org.apache.commons.collections4.Unmodifiable;

/* loaded from: classes9.dex */
public final class UnmodifiableListIterator<E> implements ListIterator<E>, Unmodifiable {
    private final ListIterator<? extends E> iterator;

    @Deprecated
    public static <E> ListIterator<E> umodifiableListIterator(ListIterator<? extends E> iterator) {
        return unmodifiableListIterator(iterator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ListIterator<E> unmodifiableListIterator(ListIterator<? extends E> listIterator) {
        Objects.requireNonNull(listIterator, "iterator");
        if (listIterator instanceof Unmodifiable) {
            return listIterator;
        }
        return new UnmodifiableListIterator(listIterator);
    }

    private UnmodifiableListIterator(ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    @Override // java.util.ListIterator
    public void add(E obj) {
        throw new UnsupportedOperationException("add() is not supported");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        return this.iterator.next();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.iterator.nextIndex();
    }

    @Override // java.util.ListIterator
    public E previous() {
        return this.iterator.previous();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.iterator.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override // java.util.ListIterator
    public void set(E ignored) {
        throw new UnsupportedOperationException("set() is not supported");
    }
}
