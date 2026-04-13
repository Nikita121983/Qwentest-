package org.apache.commons.collections4.iterators;

import java.util.Iterator;

/* loaded from: classes9.dex */
public abstract class AbstractIteratorDecorator<E> extends AbstractUntypedIteratorDecorator<E, E> {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIteratorDecorator(Iterator<E> iterator) {
        super(iterator);
    }

    @Override // java.util.Iterator
    public E next() {
        return getIterator().next();
    }
}
