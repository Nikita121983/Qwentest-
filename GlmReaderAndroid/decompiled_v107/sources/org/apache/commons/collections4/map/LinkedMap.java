package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.map.AbstractLinkedMap;

/* loaded from: classes9.dex */
public class LinkedMap<K, V> extends AbstractLinkedMap<K, V> implements Serializable, Cloneable {
    private static final long serialVersionUID = 9077234323521161066L;

    /* loaded from: classes9.dex */
    static class LinkedMapList<K> extends AbstractList<K> {
        private final LinkedMap<K, ?> parent;

        LinkedMapList(LinkedMap<K, ?> parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean containsAll(Collection<?> coll) {
            return this.parent.keySet().containsAll(coll);
        }

        @Override // java.util.AbstractList, java.util.List
        public K get(int index) {
            return this.parent.get(index);
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<K> iterator() {
            return UnmodifiableIterator.unmodifiableIterator(this.parent.keySet().iterator());
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            return this.parent.indexOf(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<K> listIterator() {
            return UnmodifiableListIterator.unmodifiableListIterator(super.listIterator());
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<K> listIterator(int fromIndex) {
            return UnmodifiableListIterator.unmodifiableListIterator(super.listIterator(fromIndex));
        }

        @Override // java.util.AbstractList, java.util.List
        public K remove(int index) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<?> coll) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super K> filter) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<?> coll) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<K> subList(int fromIndexInclusive, int toIndexExclusive) {
            return UnmodifiableList.unmodifiableList(super.subList(fromIndexInclusive, toIndexExclusive));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            return this.parent.keySet().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            return (T[]) this.parent.keySet().toArray(tArr);
        }
    }

    public LinkedMap() {
        super(16, 0.75f, 12);
    }

    public LinkedMap(int initialCapacity) {
        super(initialCapacity);
    }

    public LinkedMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public LinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public List<K> asList() {
        return new LinkedMapList(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap
    public LinkedMap<K, V> clone() {
        return (LinkedMap) super.clone();
    }

    public K get(int index) {
        return getEntry(index).getKey();
    }

    public V getValue(int index) {
        return getEntry(index).getValue();
    }

    public int indexOf(Object key) {
        Object key2 = convertKey(key);
        int i = 0;
        AbstractLinkedMap.LinkEntry<K, V> entry = this.header.after;
        while (entry != this.header) {
            if (!isEqualKey(key2, entry.key)) {
                entry = entry.after;
                i++;
            } else {
                return i;
            }
        }
        return -1;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        doReadObject(in);
    }

    public V remove(int index) {
        return remove(get(index));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
