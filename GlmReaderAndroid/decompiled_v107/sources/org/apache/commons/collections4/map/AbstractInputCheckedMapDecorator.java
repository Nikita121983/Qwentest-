package org.apache.commons.collections4.map;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class AbstractInputCheckedMapDecorator<K, V> extends AbstractMapDecorator<K, V> {
    protected abstract V checkSetValue(V v);

    /* loaded from: classes9.dex */
    private final class EntrySet extends AbstractSetDecorator<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4354731610923110264L;
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySet(Set<Map.Entry<K, V>> set, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(set);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntrySetIterator(decorated().iterator(), this.parent);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public Object[] toArray() {
            Object[] array = decorated().toArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = new MapEntry((Map.Entry) array[i], this.parent);
            }
            return array;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            Object[] objArr = tArr;
            if (tArr.length > 0) {
                objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), 0);
            }
            T[] tArr2 = (T[]) decorated().toArray(objArr);
            for (int i = 0; i < tArr2.length; i++) {
                tArr2[i] = new MapEntry((Map.Entry) tArr2[i], this.parent);
            }
            if (tArr2.length > tArr.length) {
                return tArr2;
            }
            System.arraycopy(tArr2, 0, tArr, 0, tArr2.length);
            if (tArr.length > tArr2.length) {
                tArr[tArr2.length] = null;
            }
            return tArr;
        }
    }

    /* loaded from: classes9.dex */
    private final class EntrySetIterator extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        public Map.Entry<K, V> next() {
            Map.Entry<K, V> entry = getIterator().next();
            return new MapEntry(entry, this.parent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class MapEntry extends AbstractMapEntryDecorator<K, V> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractInputCheckedMapDecorator<K, V> parent) {
            super(entry);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator, java.util.Map.Entry
        public V setValue(V value) {
            return getMapEntry().setValue(this.parent.checkSetValue(value));
        }
    }

    protected AbstractInputCheckedMapDecorator() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractInputCheckedMapDecorator(Map<K, V> map) {
        super(map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (isSetValueChecking()) {
            return new EntrySet(this.map.entrySet(), this);
        }
        return this.map.entrySet();
    }

    protected boolean isSetValueChecking() {
        return true;
    }
}
