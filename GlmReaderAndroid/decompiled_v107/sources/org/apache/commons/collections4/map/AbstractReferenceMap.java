package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.collections4.map.AbstractHashedMap;

/* loaded from: classes9.dex */
public abstract class AbstractReferenceMap<K, V> extends AbstractHashedMap<K, V> {
    private ReferenceStrength keyType;
    private boolean purgeValues;
    private transient ReferenceQueue<Object> queue;
    private ReferenceStrength valueType;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected /* bridge */ /* synthetic */ AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry hashEntry, int i, Object obj, Object obj2) {
        return createEntry((AbstractHashedMap.HashEntry<int, Object>) hashEntry, i, (int) obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class ReferenceBaseIterator<K, V> {
        ReferenceEntry<K, V> current;
        K currentKey;
        V currentValue;
        int expectedModCount;
        int index;
        ReferenceEntry<K, V> next;
        K nextKey;
        V nextValue;
        final AbstractReferenceMap<K, V> parent;

        ReferenceBaseIterator(AbstractReferenceMap<K, V> parent) {
            this.parent = parent;
            this.index = !parent.isEmpty() ? parent.data.length : 0;
            this.expectedModCount = parent.modCount;
        }

        private void checkMod() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        protected ReferenceEntry<K, V> currentEntry() {
            checkMod();
            return this.current;
        }

        public boolean hasNext() {
            checkMod();
            while (nextNull()) {
                ReferenceEntry<K, V> e = this.next;
                int i = this.index;
                while (e == null && i > 0) {
                    i--;
                    e = (ReferenceEntry) this.parent.data[i];
                }
                this.next = e;
                this.index = i;
                if (e == null) {
                    return false;
                }
                this.nextKey = e.getKey();
                this.nextValue = e.getValue();
                if (nextNull()) {
                    this.next = this.next.next();
                }
            }
            return true;
        }

        protected ReferenceEntry<K, V> nextEntry() {
            checkMod();
            if (nextNull() && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.current = this.next;
            this.next = this.next.next();
            this.currentKey = this.nextKey;
            this.currentValue = this.nextValue;
            this.nextKey = null;
            this.nextValue = null;
            return this.current;
        }

        private boolean nextNull() {
            return this.nextKey == null || this.nextValue == null;
        }

        public void remove() {
            checkMod();
            if (this.current == null) {
                throw new IllegalStateException();
            }
            this.parent.remove(this.currentKey);
            this.current = null;
            this.currentKey = null;
            this.currentValue = null;
            this.expectedModCount = this.parent.modCount;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class ReferenceEntry<K, V> extends AbstractHashedMap.HashEntry<K, V> {
        private final AbstractReferenceMap<K, V> parent;

        public ReferenceEntry(AbstractReferenceMap<K, V> parent, AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value) {
            super(next, hashCode, null, null);
            this.parent = parent;
            this.key = toReference(((AbstractReferenceMap) parent).keyType, key, hashCode);
            this.value = toReference(((AbstractReferenceMap) parent).valueType, value, hashCode);
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object entryKey = entry.getKey();
            Object entryValue = entry.getValue();
            if (entryKey == null || entryValue == null) {
                return false;
            }
            return this.parent.isEqualKey(entryKey, this.key) && this.parent.isEqualValue(entryValue, getValue());
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        public K getKey() {
            return ((AbstractReferenceMap) this.parent).keyType == ReferenceStrength.HARD ? (K) this.key : (K) ((Reference) this.key).get();
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        public V getValue() {
            return ((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD ? (V) this.value : (V) ((Reference) this.value).get();
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public int hashCode() {
            return this.parent.hashEntry(getKey(), getValue());
        }

        protected ReferenceEntry<K, V> next() {
            return (ReferenceEntry) this.next;
        }

        protected void nullValue() {
            this.value = null;
        }

        protected void onPurge() {
        }

        protected boolean purge(Reference<?> ref) {
            boolean r = true;
            if (!(((AbstractReferenceMap) this.parent).keyType != ReferenceStrength.HARD && this.key == ref) && (((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD || this.value != ref)) {
                r = false;
            }
            if (r) {
                if (((AbstractReferenceMap) this.parent).keyType != ReferenceStrength.HARD) {
                    ((Reference) this.key).clear();
                }
                if (((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD) {
                    if (((AbstractReferenceMap) this.parent).purgeValues) {
                        nullValue();
                    }
                } else {
                    ((Reference) this.value).clear();
                }
            }
            return r;
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public V setValue(V value) {
            V old = getValue();
            if (((AbstractReferenceMap) this.parent).valueType != ReferenceStrength.HARD) {
                ((Reference) this.value).clear();
            }
            this.value = toReference(((AbstractReferenceMap) this.parent).valueType, value, this.hashCode);
            return old;
        }

        protected <T> Object toReference(ReferenceStrength type, T referent, int hash) {
            switch (type) {
                case HARD:
                    return referent;
                case SOFT:
                    return new SoftRef(hash, referent, ((AbstractReferenceMap) this.parent).queue);
                case WEAK:
                    return new WeakRef(hash, referent, ((AbstractReferenceMap) this.parent).queue);
                default:
                    throw new Error();
            }
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceEntrySet<K, V> extends AbstractHashedMap.EntrySet<K, V> {
        protected ReferenceEntrySet(AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator<Map.Entry<K, V>> it = iterator();
            while (it.hasNext()) {
                arrayList.add(new DefaultMapEntry(it.next()));
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceEntrySetIterator<K, V> extends ReferenceBaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        ReferenceEntrySetIterator(AbstractReferenceMap<K, V> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceKeySet<K> extends AbstractHashedMap.KeySet<K> {
        protected ReferenceKeySet(AbstractHashedMap<K, ?> parent) {
            super(parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Objects.requireNonNull(arrayList);
            forEach(new AbstractReferenceMap$ReferenceKeySet$$ExternalSyntheticLambda0(arrayList));
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceKeySetIterator<K> extends ReferenceBaseIterator<K, Object> implements Iterator<K> {
        ReferenceKeySetIterator(AbstractReferenceMap<K, ?> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class ReferenceMapIterator<K, V> extends ReferenceBaseIterator<K, V> implements MapIterator<K, V> {
        protected ReferenceMapIterator(AbstractReferenceMap<K, V> parent) {
            super(parent);
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K getKey() {
            AbstractHashedMap.HashEntry<K, V> current = currentEntry();
            if (current == null) {
                throw new IllegalStateException("getKey() can only be called after next() and before remove()");
            }
            return current.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V getValue() {
            AbstractHashedMap.HashEntry<K, V> current = currentEntry();
            if (current == null) {
                throw new IllegalStateException("getValue() can only be called after next() and before remove()");
            }
            return current.getValue();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V value) {
            AbstractHashedMap.HashEntry<K, V> current = currentEntry();
            if (current == null) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            return current.setValue(value);
        }
    }

    /* loaded from: classes9.dex */
    public enum ReferenceStrength {
        HARD(0),
        SOFT(1),
        WEAK(2);

        public final int value;

        public static ReferenceStrength resolve(int value) {
            switch (value) {
                case 0:
                    return HARD;
                case 1:
                    return SOFT;
                case 2:
                    return WEAK;
                default:
                    throw new IllegalArgumentException();
            }
        }

        ReferenceStrength(int value) {
            this.value = value;
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceValues<V> extends AbstractHashedMap.Values<V> {
        protected ReferenceValues(AbstractHashedMap<?, V> parent) {
            super(parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Objects.requireNonNull(arrayList);
            forEach(new AbstractReferenceMap$ReferenceKeySet$$ExternalSyntheticLambda0(arrayList));
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes9.dex */
    static class ReferenceValuesIterator<V> extends ReferenceBaseIterator<Object, V> implements Iterator<V> {
        ReferenceValuesIterator(AbstractReferenceMap<?, V> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class SoftRef<T> extends SoftReference<T> {
        private final int hash;

        SoftRef(int hash, T r, ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SoftRef<?> other = (SoftRef) obj;
            if (this.hash == other.hash) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class WeakRef<T> extends WeakReference<T> {
        private final int hash;

        WeakRef(int hash, T r, ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WeakRef<?> other = (WeakRef) obj;
            if (this.hash == other.hash) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    protected AbstractReferenceMap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractReferenceMap(ReferenceStrength keyType, ReferenceStrength valueType, int capacity, float loadFactor, boolean purgeValues) {
        super(capacity, loadFactor);
        this.keyType = keyType;
        this.valueType = valueType;
        this.purgeValues = purgeValues;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        super.clear();
        do {
        } while (this.queue.poll() != null);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        purgeBeforeRead();
        Map.Entry<K, V> entry = getEntry(key);
        return (entry == null || entry.getValue() == null) ? false : true;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        purgeBeforeRead();
        if (value == null) {
            return false;
        }
        return super.containsValue(value);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected ReferenceEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value) {
        return new ReferenceEntry<>(this, next, hashCode, key, value);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        return new ReferenceEntrySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<K> createKeySetIterator() {
        return new ReferenceKeySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<V> createValuesIterator() {
        return new ReferenceValuesIterator(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doReadObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.keyType = ReferenceStrength.resolve(in.readInt());
        this.valueType = ReferenceStrength.resolve(in.readInt());
        this.purgeValues = in.readBoolean();
        this.loadFactor = in.readFloat();
        int capacity = in.readInt();
        init();
        this.data = new AbstractHashedMap.HashEntry[capacity];
        this.threshold = calculateThreshold(this.data.length, this.loadFactor);
        while (true) {
            Object readObject = in.readObject();
            if (readObject != null) {
                put(readObject, in.readObject());
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doWriteObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.keyType.value);
        out.writeInt(this.valueType.value);
        out.writeBoolean(this.purgeValues);
        out.writeFloat(this.loadFactor);
        out.writeInt(this.data.length);
        MapIterator<K, V> it = mapIterator();
        while (it.hasNext()) {
            out.writeObject(it.next());
            out.writeObject(it.getValue());
        }
        out.writeObject(null);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new ReferenceEntrySet(this);
        }
        return this.entrySet;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        purgeBeforeRead();
        Map.Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public AbstractHashedMap.HashEntry<K, V> getEntry(Object key) {
        if (key == null) {
            return null;
        }
        return super.getEntry(key);
    }

    protected int hashEntry(Object key, Object value) {
        return (value != null ? value.hashCode() : 0) ^ (key == null ? 0 : key.hashCode());
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected void init() {
        this.queue = new ReferenceQueue<>();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        purgeBeforeRead();
        return super.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public boolean isEqualKey(Object key1, Object key2) {
        return Objects.equals(key1, this.keyType == ReferenceStrength.HARD ? key2 : ((Reference) key2).get());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isKeyType(ReferenceStrength type) {
        return this.keyType == type;
    }

    protected boolean isValueType(ReferenceStrength type) {
        return this.valueType == type;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new ReferenceKeySet(this);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, org.apache.commons.collections4.IterableGet
    public MapIterator<K, V> mapIterator() {
        return new ReferenceMapIterator(this);
    }

    protected void purge() {
        Reference<?> ref = this.queue.poll();
        while (ref != null) {
            purge(ref);
            ref = this.queue.poll();
        }
    }

    protected void purge(Reference<?> ref) {
        int hash = ref.hashCode();
        int index = hashIndex(hash, this.data.length);
        AbstractHashedMap.HashEntry<K, V> previous = null;
        for (AbstractHashedMap.HashEntry<K, V> entry = this.data[index]; entry != null; entry = entry.next) {
            ReferenceEntry<K, V> refEntry = (ReferenceEntry) entry;
            if (refEntry.purge(ref)) {
                if (previous == null) {
                    this.data[index] = entry.next;
                } else {
                    previous.next = entry.next;
                }
                this.size--;
                refEntry.onPurge();
                return;
            }
            previous = entry;
        }
    }

    protected void purgeBeforeRead() {
        purge();
    }

    protected void purgeBeforeWrite() {
        purge();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key");
        Objects.requireNonNull(v, "value");
        purgeBeforeWrite();
        return (V) super.put(k, v);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        purgeBeforeWrite();
        return (V) super.remove(obj);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        purgeBeforeRead();
        return super.size();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Collection<V> values() {
        if (this.values == null) {
            this.values = new ReferenceValues(this);
        }
        return this.values;
    }
}
