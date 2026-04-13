package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.set.TransformedSet;

/* loaded from: classes9.dex */
public class TransformedBag<E> extends TransformedCollection<E> implements Bag<E> {
    private static final long serialVersionUID = 5421170911299074185L;

    public static <E> Bag<E> transformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        TransformedBag<E> decorated = new TransformedBag<>(bag, transformer);
        if (!bag.isEmpty()) {
            Object[] array = bag.toArray();
            bag.clear();
            for (Object obj : array) {
                decorated.decorated().add(transformer.apply(obj));
            }
        }
        return decorated;
    }

    public static <E> Bag<E> transformingBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedBag(bag, transformer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E object, int nCopies) {
        return getBag().add(transform((TransformedBag<E>) object), nCopies);
    }

    @Override // java.util.Collection
    public boolean equals(Object object) {
        return object == this || decorated().equals(object);
    }

    protected Bag<E> getBag() {
        return (Bag) decorated();
    }

    @Override // org.apache.commons.collections4.Bag
    public int getCount(Object object) {
        return getBag().getCount(object);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean remove(Object object, int nCopies) {
        return getBag().remove(object, nCopies);
    }

    @Override // org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        Set<E> set = getBag().uniqueSet();
        return TransformedSet.transformingSet(set, this.transformer);
    }
}
