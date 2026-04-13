package org.apache.xmlbeans.impl.values;

import java.util.AbstractList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes11.dex */
public class JavaListObject<T> extends AbstractList<T> {
    private final BiConsumer<Integer, T> adder;
    private final Function<Integer, T> getter;
    private final Consumer<Integer> remover;
    private final BiConsumer<Integer, T> setter;
    private final Supplier<Integer> sizer;

    public JavaListObject(Function<Integer, T> getter, BiConsumer<Integer, T> setter, BiConsumer<Integer, T> adder, Consumer<Integer> remover, Supplier<Integer> sizer) {
        this.getter = getter;
        this.setter = setter;
        this.adder = adder;
        this.remover = remover;
        this.sizer = sizer;
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int index) {
        if (this.getter == null) {
            throw new IllegalStateException("XmlBean generated using partial methods - no getter method available");
        }
        return this.getter.apply(Integer.valueOf(index));
    }

    @Override // java.util.AbstractList, java.util.List
    public T set(int index, T element) {
        if (this.setter == null) {
            throw new IllegalStateException("XmlBean generated using partial methods - no setter method available");
        }
        T old = get(index);
        this.setter.accept(Integer.valueOf(index), element);
        return old;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int index, T t) {
        if (this.adder == null) {
            throw new IllegalStateException("XmlBean generated using partial methods - no add method available");
        }
        this.adder.accept(Integer.valueOf(index), t);
    }

    @Override // java.util.AbstractList, java.util.List
    public T remove(int index) {
        if (this.remover == null) {
            throw new IllegalStateException("XmlBean generated using partial methods - no remove method available");
        }
        T old = get(index);
        this.remover.accept(Integer.valueOf(index));
        return old;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        if (this.sizer == null) {
            throw new IllegalStateException("XmlBean generated using partial methods - no size-of method available");
        }
        return this.sizer.get().intValue();
    }
}
