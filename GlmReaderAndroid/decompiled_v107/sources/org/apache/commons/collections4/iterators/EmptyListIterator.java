package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import org.apache.commons.collections4.ResettableListIterator;

/* loaded from: classes9.dex */
public class EmptyListIterator<E> extends AbstractEmptyIterator<E> implements ResettableListIterator<E> {
    public static final ResettableListIterator RESETTABLE_INSTANCE = new EmptyListIterator();
    public static final ListIterator INSTANCE = RESETTABLE_INSTANCE;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    @Deprecated
    public /* bridge */ /* synthetic */ void add(Object obj) {
        super.add(obj);
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator, java.util.Iterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ boolean hasPrevious() {
        return super.hasPrevious();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator, java.util.Iterator
    public /* bridge */ /* synthetic */ Object next() {
        return super.next();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ int nextIndex() {
        return super.nextIndex();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ Object previous() {
        return super.previous();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ int previousIndex() {
        return super.previousIndex();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator, java.util.Iterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }

    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator, org.apache.commons.collections4.ResettableIterator
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.iterators.AbstractEmptyIterator
    public /* bridge */ /* synthetic */ void set(Object obj) {
        super.set(obj);
    }

    public static <E> ListIterator<E> emptyListIterator() {
        return INSTANCE;
    }

    public static <E> ResettableListIterator<E> resettableEmptyListIterator() {
        return RESETTABLE_INSTANCE;
    }

    protected EmptyListIterator() {
    }
}
