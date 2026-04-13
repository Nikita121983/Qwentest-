package org.apache.commons.collections4.list;

import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class LazyList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -3677737457567429713L;
    private final Factory<? extends E> factory;
    private final Transformer<Integer, ? extends E> transformer;

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return new LazyList<>(list, factory);
    }

    public static <E> LazyList<E> lazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        return new LazyList<>(list, transformer);
    }

    protected LazyList(List<E> list, Factory<? extends E> factory) {
        super(list);
        this.factory = (Factory) Objects.requireNonNull(factory);
        this.transformer = null;
    }

    protected LazyList(List<E> list, Transformer<Integer, ? extends E> transformer) {
        super(list);
        this.factory = null;
        this.transformer = (Transformer) Objects.requireNonNull(transformer);
    }

    private E element(int index) {
        if (this.factory != null) {
            return this.factory.get();
        }
        if (this.transformer != null) {
            return this.transformer.apply(Integer.valueOf(index));
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E get(int i) {
        int size = decorated().size();
        if (i < size) {
            E e = (E) decorated().get(i);
            if (e == null) {
                E element = element(i);
                decorated().set(i, element);
                return element;
            }
            return e;
        }
        for (int i2 = size; i2 < i; i2++) {
            decorated().add(null);
        }
        E element2 = element(i);
        decorated().add(element2);
        return element2;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sub = decorated().subList(fromIndex, toIndex);
        if (this.factory != null) {
            return new LazyList(sub, this.factory);
        }
        if (this.transformer != null) {
            return new LazyList(sub, this.transformer);
        }
        throw new IllegalStateException("Factory and Transformer are both null!");
    }
}
