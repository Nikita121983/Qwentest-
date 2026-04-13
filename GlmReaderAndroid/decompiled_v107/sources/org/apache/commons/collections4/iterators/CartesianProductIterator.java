package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class CartesianProductIterator<E> implements Iterator<List<E>> {
    private final List<Iterable<? extends E>> iterables;
    private final List<Iterator<? extends E>> iterators;
    private List<E> previousTuple;

    @SafeVarargs
    public CartesianProductIterator(Iterable<? extends E>... iterables) {
        Objects.requireNonNull(iterables, "iterables");
        this.iterables = new ArrayList(iterables.length);
        this.iterators = new ArrayList(iterables.length);
        for (Iterable<? extends E> iterable : iterables) {
            Objects.requireNonNull(iterable, "iterable");
            this.iterables.add(iterable);
            Iterator<? extends E> iterator = iterable.iterator();
            if (!iterator.hasNext()) {
                this.iterators.clear();
                return;
            }
            this.iterators.add(iterator);
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterators.stream().anyMatch(new Predicate() { // from class: org.apache.commons.collections4.iterators.CartesianProductIterator$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Iterator) obj).hasNext();
            }
        });
    }

    @Override // java.util.Iterator
    public List<E> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.previousTuple == null) {
            this.previousTuple = new ArrayList(this.iterables.size());
            Iterator<Iterator<? extends E>> it = this.iterators.iterator();
            while (it.hasNext()) {
                this.previousTuple.add(it.next().next());
            }
            return new ArrayList(this.previousTuple);
        }
        for (int i = this.iterators.size() - 1; i >= 0; i--) {
            Iterator<? extends E> iterator = this.iterators.get(i);
            if (iterator.hasNext()) {
                this.previousTuple.set(i, iterator.next());
                return new ArrayList(this.previousTuple);
            }
            Iterator<? extends E> iterator2 = this.iterables.get(i).iterator();
            this.iterators.set(i, iterator2);
            this.previousTuple.set(i, iterator2.next());
        }
        throw new IllegalStateException("reached unreachable code");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
