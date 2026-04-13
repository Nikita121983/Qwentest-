package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;

/* loaded from: classes9.dex */
public abstract class AbstractDualBidiMap<K, V> implements BidiMap<K, V> {
    transient Set<Map.Entry<K, V>> entrySet;
    transient BidiMap<V, K> inverseBidiMap;
    transient Set<K> keySet;
    transient Map<K, V> normalMap;
    transient Map<V, K> reverseMap;
    transient Set<V> values;

    protected abstract BidiMap<V, K> createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap);

    /* loaded from: classes9.dex */
    protected static class BidiMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        protected boolean canRemove;
        protected Iterator<Map.Entry<K, V>> iterator;
        protected Map.Entry<K, V> last;
        protected final AbstractDualBidiMap<K, V> parent;

        protected BidiMapIterator(AbstractDualBidiMap<K, V> parent) {
            this.parent = parent;
            this.iterator = parent.normalMap.entrySet().iterator();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K getKey() {
            if (this.last == null) {
                throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
            }
            return this.last.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V getValue() {
            if (this.last == null) {
                throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
            }
            return this.last.getValue();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public K next() {
            this.last = this.iterator.next();
            this.canRemove = true;
            return this.last.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            V value = this.last.getValue();
            this.iterator.remove();
            this.parent.reverseMap.remove(value);
            this.last = null;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.iterator = this.parent.normalMap.entrySet().iterator();
            this.last = null;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.last == null) {
                throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
            }
            if (this.parent.reverseMap.containsKey(v) && this.parent.reverseMap.get(v) != this.last.getKey()) {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
            return (V) this.parent.put(this.last.getKey(), v);
        }

        public String toString() {
            if (this.last != null) {
                return "MapIterator[" + getKey() + "=" + getValue() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
            }
            return "MapIterator[]";
        }
    }

    /* loaded from: classes9.dex */
    protected static class EntrySet<K, V> extends View<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4040410962603292348L;

        protected EntrySet(AbstractDualBidiMap<K, V> parent) {
            super(parent.normalMap.entrySet(), parent);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator(super.iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object key = entry.getKey();
            if (this.parent.containsKey(key)) {
                V value = this.parent.normalMap.get(key);
                if (Objects.equals(value, entry.getValue())) {
                    this.parent.normalMap.remove(key);
                    this.parent.reverseMap.remove(value);
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class EntrySetIterator<K, V> extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        protected boolean canRemove;
        protected Map.Entry<K, V> last;
        protected final AbstractDualBidiMap<K, V> parent;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractDualBidiMap<K, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        public Map.Entry<K, V> next() {
            this.last = new MapEntry((Map.Entry) super.next(), this.parent);
            this.canRemove = true;
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            Object value = this.last.getValue();
            super.remove();
            this.parent.reverseMap.remove(value);
            this.last = null;
            this.canRemove = false;
        }
    }

    /* loaded from: classes9.dex */
    protected static class KeySet<K> extends View<K, Object, K> implements Set<K> {
        private static final long serialVersionUID = -7107935777385040694L;

        protected KeySet(AbstractDualBidiMap<K, ?> parent) {
            super(parent.normalMap.keySet(), parent);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.util.Set
        public boolean contains(Object key) {
            return this.parent.normalMap.containsKey(key);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        public Iterator<K> iterator() {
            return this.parent.createKeySetIterator(super.iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object key) {
            if (this.parent.normalMap.containsKey(key)) {
                Object value = this.parent.normalMap.remove(key);
                this.parent.reverseMap.remove(value);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class KeySetIterator<K> extends AbstractIteratorDecorator<K> {
        protected boolean canRemove;
        protected K lastKey;
        protected final AbstractDualBidiMap<K, ?> parent;

        protected KeySetIterator(Iterator<K> iterator, AbstractDualBidiMap<K, ?> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        public K next() {
            this.lastKey = (K) super.next();
            this.canRemove = true;
            return this.lastKey;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            Object value = this.parent.normalMap.get(this.lastKey);
            super.remove();
            this.parent.reverseMap.remove(value);
            this.lastKey = null;
            this.canRemove = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class MapEntry<K, V> extends AbstractMapEntryDecorator<K, V> {
        protected final AbstractDualBidiMap<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractDualBidiMap<K, V> parent) {
            super(entry);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator, java.util.Map.Entry
        public V setValue(V v) {
            K key = getKey();
            if (this.parent.reverseMap.containsKey(v) && this.parent.reverseMap.get(v) != key) {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
            this.parent.put(key, v);
            return (V) super.setValue(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class Values<V> extends View<Object, V, V> implements Set<V> {
        private static final long serialVersionUID = 4023777119829639864L;

        protected Values(AbstractDualBidiMap<?, V> parent) {
            super(parent.normalMap.values(), parent);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.util.Set
        public boolean contains(Object value) {
            return this.parent.reverseMap.containsKey(value);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        public Iterator<V> iterator() {
            return this.parent.createValuesIterator(super.iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object value) {
            if (this.parent.reverseMap.containsKey(value)) {
                Object key = this.parent.reverseMap.remove(value);
                this.parent.normalMap.remove(key);
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class ValuesIterator<V> extends AbstractIteratorDecorator<V> {
        protected boolean canRemove;
        protected V lastValue;
        protected final AbstractDualBidiMap<Object, V> parent;

        protected ValuesIterator(Iterator<V> iterator, AbstractDualBidiMap<?, V> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        public V next() {
            this.lastValue = (V) super.next();
            this.canRemove = true;
            return this.lastValue;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("Iterator remove() can only be called once after next()");
            }
            super.remove();
            this.parent.reverseMap.remove(this.lastValue);
            this.lastValue = null;
            this.canRemove = false;
        }
    }

    /* loaded from: classes9.dex */
    protected static abstract class View<K, V, E> extends AbstractCollectionDecorator<E> {
        private static final long serialVersionUID = 4621510560119690639L;
        protected final AbstractDualBidiMap<K, V> parent;

        protected View(Collection<E> coll, AbstractDualBidiMap<K, V> parent) {
            super(coll);
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.Collection
        public boolean equals(Object object) {
            return object == this || decorated().equals(object);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return decorated().hashCode();
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean removeAll(Collection<?> coll) {
            if (this.parent.isEmpty() || coll.isEmpty()) {
                return false;
            }
            boolean modified = false;
            for (Object current : coll) {
                modified |= remove(current);
            }
            return modified;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public boolean removeIf(Predicate<? super E> filter) {
            if (this.parent.isEmpty()) {
                return false;
            }
            Objects.isNull(filter);
            if (filter == null) {
                return false;
            }
            boolean modified = false;
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                E e = it.next();
                if (filter.test(e)) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean retainAll(Collection<?> coll) {
            if (this.parent.isEmpty()) {
                return false;
            }
            if (coll.isEmpty()) {
                this.parent.clear();
                return true;
            }
            boolean modified = false;
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                if (!coll.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
            return modified;
        }
    }

    protected AbstractDualBidiMap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
        this.inverseBidiMap = inverseBidiMap;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        this.normalMap.clear();
        this.reverseMap.clear();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        return this.normalMap.containsKey(key);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        return this.reverseMap.containsKey(value);
    }

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator(Iterator<Map.Entry<K, V>> iterator) {
        return new EntrySetIterator(iterator, this);
    }

    protected Iterator<K> createKeySetIterator(Iterator<K> iterator) {
        return new KeySetIterator(iterator, this);
    }

    protected Iterator<V> createValuesIterator(Iterator<V> iterator) {
        return new ValuesIterator(iterator, this);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.normalMap.equals(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        return this.normalMap.get(key);
    }

    @Override // org.apache.commons.collections4.BidiMap
    public K getKey(Object value) {
        return this.reverseMap.get(value);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.normalMap.hashCode();
    }

    @Override // org.apache.commons.collections4.BidiMap
    public BidiMap<V, K> inverseBidiMap() {
        if (this.inverseBidiMap == null) {
            this.inverseBidiMap = createBidiMap(this.reverseMap, this.normalMap, this);
        }
        return this.inverseBidiMap;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return this.normalMap.isEmpty();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.IterableGet
    public MapIterator<K, V> mapIterator() {
        return new BidiMapIterator(this);
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K key, V value) {
        if (this.normalMap.containsKey(key)) {
            this.reverseMap.remove(this.normalMap.get(key));
        }
        if (this.reverseMap.containsKey(value)) {
            this.normalMap.remove(this.reverseMap.get(value));
        }
        V obj = this.normalMap.put(key, value);
        this.reverseMap.put(value, key);
        return obj;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V remove(Object key) {
        if (!this.normalMap.containsKey(key)) {
            return null;
        }
        V value = this.normalMap.remove(key);
        this.reverseMap.remove(value);
        return value;
    }

    @Override // org.apache.commons.collections4.BidiMap
    public K removeValue(Object value) {
        if (!this.reverseMap.containsKey(value)) {
            return null;
        }
        K key = this.reverseMap.remove(value);
        this.normalMap.remove(key);
        return key;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.normalMap.size();
    }

    public String toString() {
        return this.normalMap.toString();
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<V> values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }
}
