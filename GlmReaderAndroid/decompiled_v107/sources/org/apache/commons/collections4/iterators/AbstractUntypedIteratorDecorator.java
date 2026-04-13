package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes9.dex */
public abstract class AbstractUntypedIteratorDecorator<I, O> implements Iterator<O> {
    private final Iterator<I> iterator;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractUntypedIteratorDecorator(Iterator<I> iterator) {
        this.iterator = (Iterator) Objects.requireNonNull(iterator, "iterator");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterator<I> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }
}
