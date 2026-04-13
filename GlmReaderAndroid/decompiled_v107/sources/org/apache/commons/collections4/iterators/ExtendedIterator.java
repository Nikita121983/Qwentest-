package org.apache.commons.collections4.iterators;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public final class ExtendedIterator<T> implements IteratorOperations<T> {
    private final Iterator<? extends T> base;
    private final boolean throwOnRemove;

    public static <T> ExtendedIterator<T> create(Iterator<T> it) {
        return it instanceof ExtendedIterator ? (ExtendedIterator) it : new ExtendedIterator<>(it, false);
    }

    public static <T> ExtendedIterator<T> create(Stream<T> stream) {
        return new ExtendedIterator<>(stream.iterator(), true);
    }

    public static <T> ExtendedIterator<T> createNoRemove(Iterator<T> it) {
        return new ExtendedIterator<>(it, true);
    }

    public static ExtendedIterator<?> emptyIterator() {
        return new ExtendedIterator<>(Collections.emptyIterator(), false);
    }

    public static <T> ExtendedIterator<T> flatten(Iterator<Iterator<T>> iterators) {
        return create(IteratorUtils.chainedIterator(iterators));
    }

    private ExtendedIterator(Iterator<? extends T> base, boolean throwOnRemove) {
        this.base = base;
        this.throwOnRemove = throwOnRemove;
    }

    public <X extends T> ExtendedIterator<T> andThen(Iterator<X> other) {
        if (this.base instanceof IteratorChain) {
            ((IteratorChain) this.base).addIterator(other);
            return this;
        }
        return new ExtendedIterator<>(new IteratorChain(this.base, other), this.throwOnRemove);
    }

    public ExtendedIterator<T> filter(final Predicate<T> predicate) {
        Objects.requireNonNull(predicate);
        return new ExtendedIterator<>(new FilterIterator(this, new org.apache.commons.collections4.Predicate() { // from class: org.apache.commons.collections4.iterators.ExtendedIterator$$ExternalSyntheticLambda1
            @Override // org.apache.commons.collections4.Predicate
            public final boolean evaluate(Object obj) {
                return predicate.test(obj);
            }
        }), this.throwOnRemove);
    }

    @Override // java.util.Iterator
    public void forEachRemaining(Consumer<? super T> action) {
        this.base.forEachRemaining(action);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.base.hasNext();
    }

    public <U> ExtendedIterator<U> map(final Function<T, U> function) {
        Objects.requireNonNull(function);
        return new ExtendedIterator<>(new TransformIterator(this, new Transformer() { // from class: org.apache.commons.collections4.iterators.ExtendedIterator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.collections4.Transformer
            public final Object transform(Object obj) {
                return function.apply(obj);
            }
        }), false);
    }

    @Override // java.util.Iterator
    public T next() {
        return this.base.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.throwOnRemove) {
            throw new UnsupportedOperationException();
        }
        this.base.remove();
    }
}
