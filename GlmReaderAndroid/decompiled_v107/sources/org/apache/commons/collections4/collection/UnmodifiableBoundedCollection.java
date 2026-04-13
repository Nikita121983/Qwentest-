package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

/* loaded from: classes9.dex */
public final class UnmodifiableBoundedCollection<E> extends AbstractCollectionDecorator<E> implements BoundedCollection<E>, Unmodifiable {
    private static final long serialVersionUID = -7112672385450340330L;

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(BoundedCollection<? extends E> boundedCollection) {
        if (boundedCollection instanceof Unmodifiable) {
            return boundedCollection;
        }
        return new UnmodifiableBoundedCollection(boundedCollection);
    }

    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(Collection<? extends E> collection) {
        Objects.requireNonNull(collection, "collection");
        for (int i = 0; i < 1000 && !(collection instanceof BoundedCollection); i++) {
            if (collection instanceof AbstractCollectionDecorator) {
                collection = ((AbstractCollectionDecorator) collection).decorated();
            } else if (collection instanceof SynchronizedCollection) {
                collection = ((SynchronizedCollection) collection).decorated();
            }
        }
        if (!(collection instanceof BoundedCollection)) {
            throw new IllegalArgumentException("Collection is not a bounded collection.");
        }
        return new UnmodifiableBoundedCollection((BoundedCollection) collection);
    }

    private UnmodifiableBoundedCollection(BoundedCollection<? extends E> coll) {
        super(coll);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E object) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    public BoundedCollection<E> decorated() {
        return (BoundedCollection) super.decorated();
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public boolean isFull() {
        return decorated().isFull();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public int maxSize() {
        return decorated().maxSize();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }
}
