package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class TransformedSortedBag<E> extends TransformedBag<E> implements SortedBag<E> {
    private static final long serialVersionUID = -251737742649401930L;

    public static <E> TransformedSortedBag<E> transformedSortedBag(SortedBag<E> bag, Transformer<? super E, ? extends E> transformer) {
        TransformedSortedBag<E> decorated = new TransformedSortedBag<>(bag, transformer);
        if (!bag.isEmpty()) {
            Object[] array = bag.toArray();
            bag.clear();
            for (Object obj : array) {
                decorated.decorated().add(transformer.apply(obj));
            }
        }
        return decorated;
    }

    public static <E> TransformedSortedBag<E> transformingSortedBag(SortedBag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSortedBag<>(bag, transformer);
    }

    protected TransformedSortedBag(SortedBag<E> bag, Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    @Override // org.apache.commons.collections4.SortedBag
    public Comparator<? super E> comparator() {
        return getSortedBag().comparator();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E first() {
        return getSortedBag().first();
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag) decorated();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E last() {
        return getSortedBag().last();
    }
}
