package org.apache.poi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import org.apache.poi.common.Duplicatable;

/* loaded from: classes10.dex */
public class IntMapper<T> implements Duplicatable, Iterable<T> {
    private static final int _default_size = 10;
    private final List<T> elements;
    private final Map<T, Integer> valueKeyMap;

    public IntMapper() {
        this(10);
    }

    public IntMapper(int initialCapacity) {
        this.elements = new ArrayList(initialCapacity);
        this.valueKeyMap = new HashMap(initialCapacity);
    }

    public IntMapper(IntMapper<T> other) {
        this.elements = new ArrayList(other.elements);
        this.valueKeyMap = new HashMap(other.valueKeyMap);
    }

    public boolean add(T value) {
        int index = this.elements.size();
        this.elements.add(value);
        this.valueKeyMap.put(value, Integer.valueOf(index));
        return true;
    }

    public int size() {
        return this.elements.size();
    }

    public T get(int index) {
        return this.elements.get(index);
    }

    public int getIndex(T o) {
        return this.valueKeyMap.getOrDefault(o, -1).intValue();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<T> spliterator() {
        return this.elements.spliterator();
    }

    @Override // org.apache.poi.common.Duplicatable
    public IntMapper<T> copy() {
        return new IntMapper<>(this);
    }

    public List<T> getElements() {
        return this.elements;
    }
}
