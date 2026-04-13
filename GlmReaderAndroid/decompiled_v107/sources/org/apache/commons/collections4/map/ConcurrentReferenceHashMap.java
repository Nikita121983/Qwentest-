package org.apache.commons.collections4.map;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: classes9.dex */
public class ConcurrentReferenceHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final int MAX_SEGMENTS = 65536;
    private static final int RETRIES_BEFORE_LOCK = 2;
    private transient Set<Map.Entry<K, V>> entrySet;
    private final boolean identityComparisons;
    private transient Set<K> keySet;
    private final int segmentMask;
    private final int segmentShift;
    private final Segment<K, V>[] segments;
    private transient Collection<V> values;
    static final ReferenceType DEFAULT_KEY_TYPE = ReferenceType.WEAK;
    static final ReferenceType DEFAULT_VALUE_TYPE = ReferenceType.STRONG;
    static final EnumSet<Option> DEFAULT_OPTIONS = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface KeyReference {
        int keyHash();

        Object keyRef();
    }

    /* loaded from: classes9.dex */
    public enum Option {
        IDENTITY_COMPARISONS
    }

    /* loaded from: classes9.dex */
    public enum ReferenceType {
        STRONG,
        WEAK,
        SOFT
    }

    /* loaded from: classes9.dex */
    public static class Builder<K, V> implements Supplier<ConcurrentReferenceHashMap<K, V>> {
        private static final Map<?, ?> DEFAULT_SOURCE_MAP = null;
        private int initialCapacity = 16;
        private float loadFactor = 0.75f;
        private int concurrencyLevel = 16;
        private ReferenceType keyReferenceType = ConcurrentReferenceHashMap.DEFAULT_KEY_TYPE;
        private ReferenceType valueReferenceType = ConcurrentReferenceHashMap.DEFAULT_VALUE_TYPE;
        private EnumSet<Option> options = ConcurrentReferenceHashMap.DEFAULT_OPTIONS;
        private Map<? extends K, ? extends V> sourceMap = (Map<? extends K, ? extends V>) DEFAULT_SOURCE_MAP;

        @Override // java.util.function.Supplier
        public ConcurrentReferenceHashMap<K, V> get() {
            ConcurrentReferenceHashMap<K, V> map = new ConcurrentReferenceHashMap<>(this.initialCapacity, this.loadFactor, this.concurrencyLevel, this.keyReferenceType, this.valueReferenceType, this.options);
            if (this.sourceMap != null) {
                map.putAll(this.sourceMap);
            }
            return map;
        }

        public Builder<K, V> setConcurrencyLevel(int concurrencyLevel) {
            this.concurrencyLevel = concurrencyLevel;
            return this;
        }

        public Builder<K, V> setInitialCapacity(int initialCapacity) {
            this.initialCapacity = initialCapacity;
            return this;
        }

        public Builder<K, V> setKeyReferenceType(ReferenceType keyReferenceType) {
            this.keyReferenceType = keyReferenceType;
            return this;
        }

        public Builder<K, V> setLoadFactor(float loadFactor) {
            this.loadFactor = loadFactor;
            return this;
        }

        public Builder<K, V> setOptions(EnumSet<Option> options) {
            this.options = options;
            return this;
        }

        public Builder<K, V> setSourceMap(Map<? extends K, ? extends V> sourceMap) {
            this.sourceMap = sourceMap;
            return this;
        }

        public Builder<K, V> setValueReferenceType(ReferenceType valueReferenceType) {
            this.valueReferenceType = valueReferenceType;
            return this;
        }

        public Builder<K, V> softKeys() {
            setKeyReferenceType(ReferenceType.SOFT);
            return this;
        }

        public Builder<K, V> softValues() {
            setValueReferenceType(ReferenceType.SOFT);
            return this;
        }

        public Builder<K, V> strongKeys() {
            setKeyReferenceType(ReferenceType.STRONG);
            return this;
        }

        public Builder<K, V> strongValues() {
            setValueReferenceType(ReferenceType.STRONG);
            return this;
        }

        public Builder<K, V> weakKeys() {
            setKeyReferenceType(ReferenceType.WEAK);
            return this;
        }

        public Builder<K, V> weakValues() {
            setValueReferenceType(ReferenceType.WEAK);
            return this;
        }
    }

    /* loaded from: classes9.dex */
    private final class CachedEntryIterator extends ConcurrentReferenceHashMap<K, V>.HashIterator implements Iterator<Map.Entry<K, V>> {
        private final InitializableEntry<K, V> entry;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private CachedEntryIterator() {
            /*
                r1 = this;
                org.apache.commons.collections4.map.ConcurrentReferenceHashMap.this = r2
                r0 = 0
                r1.<init>()
                org.apache.commons.collections4.map.ConcurrentReferenceHashMap$InitializableEntry r2 = new org.apache.commons.collections4.map.ConcurrentReferenceHashMap$InitializableEntry
                r2.<init>()
                r1.entry = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.ConcurrentReferenceHashMap.CachedEntryIterator.<init>(org.apache.commons.collections4.map.ConcurrentReferenceHashMap):void");
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            HashEntry<K, V> e = super.nextEntry();
            return this.entry.init(e.key(), e.value());
        }
    }

    /* loaded from: classes9.dex */
    private final class EntryIterator extends ConcurrentReferenceHashMap<K, V>.HashIterator implements Iterator<Map.Entry<K, V>> {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            HashEntry<K, V> e = super.nextEntry();
            return new WriteThroughEntry(e.key(), e.value());
        }
    }

    /* loaded from: classes9.dex */
    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private final boolean cached;

        private EntrySet(boolean cached) {
            this.cached = cached;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            ConcurrentReferenceHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            return Objects.equals(ConcurrentReferenceHashMap.this.get(((Map.Entry) o).getKey()), ((Map.Entry) o).getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return ConcurrentReferenceHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.cached ? new CachedEntryIterator() : new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            return ConcurrentReferenceHashMap.this.remove(e.getKey(), e.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ConcurrentReferenceHashMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class HashEntry<K, V> {
        private final int hash;
        private final Object keyRef;
        private final HashEntry<K, V> next;
        private volatile Object valueRef;

        static <K, V> HashEntry<K, V>[] newArray(int i) {
            return new HashEntry[i];
        }

        HashEntry(K key, int hash, HashEntry<K, V> next, V value, ReferenceType keyType, ReferenceType valueType, ReferenceQueue<Object> refQueue) {
            this.hash = hash;
            this.next = next;
            this.keyRef = newKeyReference(key, keyType, refQueue);
            this.valueRef = newValueReference(value, valueType, refQueue);
        }

        /* JADX WARN: Multi-variable type inference failed */
        V dereferenceValue(Object obj) {
            if (obj instanceof KeyReference) {
                return (V) ((Reference) obj).get();
            }
            return obj;
        }

        K key() {
            if (this.keyRef instanceof KeyReference) {
                return (K) ((Reference) this.keyRef).get();
            }
            return (K) this.keyRef;
        }

        Object newKeyReference(K key, ReferenceType keyType, ReferenceQueue<Object> refQueue) {
            if (keyType == ReferenceType.WEAK) {
                return new WeakKeyReference(key, this.hash, refQueue);
            }
            if (keyType == ReferenceType.SOFT) {
                return new SoftKeyReference(key, this.hash, refQueue);
            }
            return key;
        }

        Object newValueReference(V value, ReferenceType valueType, ReferenceQueue<Object> refQueue) {
            if (valueType == ReferenceType.WEAK) {
                return new WeakValueReference(value, this.keyRef, this.hash, refQueue);
            }
            if (valueType == ReferenceType.SOFT) {
                return new SoftValueReference(value, this.keyRef, this.hash, refQueue);
            }
            return value;
        }

        void setValue(V value, ReferenceType valueType, ReferenceQueue<Object> refQueue) {
            this.valueRef = newValueReference(value, valueType, refQueue);
        }

        V value() {
            return dereferenceValue(this.valueRef);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public abstract class HashIterator {
        private K currentKey;
        private HashEntry<K, V>[] currentTable;
        private HashEntry<K, V> lastReturned;
        private HashEntry<K, V> nextEntry;
        private int nextSegmentIndex;
        private int nextTableIndex;

        private HashIterator() {
            this.nextSegmentIndex = ConcurrentReferenceHashMap.this.segments.length - 1;
            this.nextTableIndex = -1;
            advance();
        }

        final void advance() {
            if (this.nextEntry != null) {
                HashEntry<K, V> hashEntry = ((HashEntry) this.nextEntry).next;
                this.nextEntry = hashEntry;
                if (hashEntry != null) {
                    return;
                }
            }
            while (this.nextTableIndex >= 0) {
                HashEntry<K, V>[] hashEntryArr = this.currentTable;
                int i = this.nextTableIndex;
                this.nextTableIndex = i - 1;
                HashEntry<K, V> hashEntry2 = hashEntryArr[i];
                this.nextEntry = hashEntry2;
                if (hashEntry2 != null) {
                    return;
                }
            }
            while (this.nextSegmentIndex >= 0) {
                Segment<K, V>[] segmentArr = ConcurrentReferenceHashMap.this.segments;
                int i2 = this.nextSegmentIndex;
                this.nextSegmentIndex = i2 - 1;
                Segment<K, V> seg = segmentArr[i2];
                if (((Segment) seg).count != 0) {
                    this.currentTable = ((Segment) seg).table;
                    for (int j = this.currentTable.length - 1; j >= 0; j--) {
                        HashEntry<K, V> hashEntry3 = this.currentTable[j];
                        this.nextEntry = hashEntry3;
                        if (hashEntry3 != null) {
                            this.nextTableIndex = j - 1;
                            return;
                        }
                    }
                }
            }
        }

        public boolean hasMoreElements() {
            return hasNext();
        }

        public boolean hasNext() {
            while (this.nextEntry != null) {
                if (this.nextEntry.key() != null) {
                    return true;
                }
                advance();
            }
            return false;
        }

        HashEntry<K, V> nextEntry() {
            while (this.nextEntry != null) {
                this.lastReturned = this.nextEntry;
                this.currentKey = this.lastReturned.key();
                advance();
                if (this.currentKey != null) {
                    return this.lastReturned;
                }
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            ConcurrentReferenceHashMap.this.remove(this.currentKey);
            this.lastReturned = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class InitializableEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        private InitializableEntry() {
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        public Map.Entry<K, V> init(K key, V value) {
            this.key = key;
            this.value = value;
            return this;
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes9.dex */
    private final class KeyIterator extends ConcurrentReferenceHashMap<K, V>.HashIterator implements Iterator<K>, Enumeration<K> {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return super.nextEntry().key();
        }

        @Override // java.util.Enumeration
        public K nextElement() {
            return super.nextEntry().key();
        }
    }

    /* loaded from: classes9.dex */
    private final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            ConcurrentReferenceHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return ConcurrentReferenceHashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return ConcurrentReferenceHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return ConcurrentReferenceHashMap.this.remove(o) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ConcurrentReferenceHashMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Segment<K, V> extends ReentrantLock {
        private static final long serialVersionUID = 1;
        private volatile transient int count;
        private final boolean identityComparisons;
        private final ReferenceType keyType;
        private final float loadFactor;
        private transient int modCount;
        private volatile transient ReferenceQueue<Object> refQueue;
        private volatile transient HashEntry<K, V>[] table;
        private transient int threshold;
        private final ReferenceType valueType;

        static <K, V> Segment<K, V>[] newArray(int i) {
            return new Segment[i];
        }

        Segment(int initialCapacity, float loadFactor, ReferenceType keyType, ReferenceType valueType, boolean identityComparisons) {
            this.loadFactor = loadFactor;
            this.keyType = keyType;
            this.valueType = valueType;
            this.identityComparisons = identityComparisons;
            setTable(HashEntry.newArray(initialCapacity));
        }

        V apply(K k, int i, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            lock();
            try {
                V v = get(k, i);
                V apply = biFunction.apply(k, v);
                if (apply == null) {
                    if (v != null) {
                        try {
                            removeInternal(k, i, v, false);
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                    unlock();
                    return null;
                }
                try {
                    putInternal(k, i, apply, null, false);
                    unlock();
                    return apply;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            unlock();
            throw th;
        }

        V applyIfPresent(K k, int i, BiFunction<? super K, ? super V, ? extends V> biFunction) {
            V v;
            lock();
            try {
                v = get(k, i);
            } catch (Throwable th) {
                th = th;
            }
            if (v == null) {
                unlock();
                return null;
            }
            V apply = biFunction.apply(k, v);
            if (apply == null) {
                try {
                    removeInternal(k, i, v, false);
                    unlock();
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                try {
                    putInternal(k, i, apply, null, false);
                    unlock();
                    return apply;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            unlock();
            throw th;
        }

        void clear() {
            if (this.count != 0) {
                lock();
                try {
                    HashEntry<K, V>[] tab = this.table;
                    Arrays.fill(tab, (Object) null);
                    this.modCount++;
                    this.refQueue = new ReferenceQueue<>();
                    this.count = 0;
                } finally {
                    unlock();
                }
            }
        }

        boolean containsKey(Object key, int hash) {
            if (this.count != 0) {
                for (HashEntry<K, V> e = getFirst(hash); e != null; e = ((HashEntry) e).next) {
                    if (((HashEntry) e).hash == hash && keyEq(key, e.key())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        boolean containsValue(Object value) {
            V v;
            if (this.count != 0) {
                HashEntry<K, V>[] tab = this.table;
                for (HashEntry<K, V> e : tab) {
                    for (; e != null; e = ((HashEntry) e).next) {
                        Object opaque = ((HashEntry) e).valueRef;
                        if (opaque == null) {
                            v = readValueUnderLock(e);
                        } else {
                            v = e.dereferenceValue(opaque);
                        }
                        if (Objects.equals(value, v)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }

        V get(Object key, int hash) {
            if (this.count != 0) {
                for (HashEntry<K, V> e = getFirst(hash); e != null; e = ((HashEntry) e).next) {
                    if (((HashEntry) e).hash == hash && keyEq(key, e.key())) {
                        Object opaque = ((HashEntry) e).valueRef;
                        if (opaque != null) {
                            return e.dereferenceValue(opaque);
                        }
                        return readValueUnderLock(e);
                    }
                }
                return null;
            }
            return null;
        }

        HashEntry<K, V> getFirst(int hash) {
            HashEntry<K, V>[] tab = this.table;
            return tab[(tab.length - 1) & hash];
        }

        V getValue(K key, V value, Function<? super K, ? extends V> function) {
            return value != null ? value : function.apply(key);
        }

        private boolean keyEq(Object src, Object dest) {
            return this.identityComparisons ? src == dest : Objects.equals(src, dest);
        }

        HashEntry<K, V> newHashEntry(K key, int hash, HashEntry<K, V> next, V value) {
            return new HashEntry<>(key, hash, next, value, this.keyType, this.valueType, this.refQueue);
        }

        V put(K key, int hash, V value, Function<? super K, ? extends V> function, boolean onlyIfAbsent) {
            lock();
            try {
                return putInternal(key, hash, value, function, onlyIfAbsent);
            } finally {
                unlock();
            }
        }

        private V putInternal(K key, int hash, V value, Function<? super K, ? extends V> function, boolean onlyIfAbsent) {
            int reduced;
            removeStale();
            int c = this.count;
            int c2 = c + 1;
            if (c > this.threshold && (reduced = rehash()) > 0) {
                int i = c2 - reduced;
                c2 = i;
                this.count = i - 1;
            }
            HashEntry<K, V>[] tab = this.table;
            int index = (tab.length - 1) & hash;
            HashEntry<K, V> first = tab[index];
            HashEntry<K, V> e = first;
            while (e != null && (((HashEntry) e).hash != hash || !keyEq(key, e.key()))) {
                e = ((HashEntry) e).next;
            }
            if (e != null) {
                V resultValue = e.value();
                if (onlyIfAbsent) {
                    return resultValue;
                }
                e.setValue(getValue(key, value, function), this.valueType, this.refQueue);
                return resultValue;
            }
            V v = getValue(key, value, function);
            V resultValue2 = function != null ? v : null;
            if (v != null) {
                this.modCount++;
                tab[index] = newHashEntry(key, hash, first, v);
                this.count = c2;
            }
            return resultValue2;
        }

        V readValueUnderLock(HashEntry<K, V> e) {
            lock();
            try {
                removeStale();
                return e.value();
            } finally {
                unlock();
            }
        }

        int rehash() {
            HashEntry<K, V>[] oldTable;
            int oldCapacity;
            HashEntry<K, V>[] oldTable2 = this.table;
            int oldCapacity2 = oldTable2.length;
            if (oldCapacity2 >= 1073741824) {
                return 0;
            }
            HashEntry<K, V>[] newTable = HashEntry.newArray(oldCapacity2 << 1);
            this.threshold = (int) (newTable.length * this.loadFactor);
            int sizeMask = newTable.length - 1;
            int reduce = 0;
            int i = 0;
            while (i < oldCapacity2) {
                HashEntry<K, V> e = oldTable2[i];
                if (e != null) {
                    HashEntry<K, V> next = ((HashEntry) e).next;
                    int idx = ((HashEntry) e).hash & sizeMask;
                    if (next == null) {
                        newTable[idx] = e;
                    } else {
                        HashEntry<K, V> lastRun = e;
                        int lastIdx = idx;
                        for (HashEntry<K, V> last = next; last != null; last = ((HashEntry) last).next) {
                            int k = ((HashEntry) last).hash & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }
                        newTable[lastIdx] = lastRun;
                        HashEntry<K, V> p = e;
                        while (p != lastRun) {
                            K key = p.key();
                            if (key != null) {
                                int k2 = ((HashEntry) p).hash & sizeMask;
                                HashEntry<K, V> n = newTable[k2];
                                oldTable = oldTable2;
                                oldCapacity = oldCapacity2;
                                newTable[k2] = newHashEntry(key, ((HashEntry) p).hash, n, p.value());
                            } else {
                                reduce++;
                                oldTable = oldTable2;
                                oldCapacity = oldCapacity2;
                            }
                            p = ((HashEntry) p).next;
                            oldTable2 = oldTable;
                            oldCapacity2 = oldCapacity;
                        }
                    }
                }
                i++;
                oldTable2 = oldTable2;
                oldCapacity2 = oldCapacity2;
            }
            this.table = newTable;
            return reduce;
        }

        V remove(Object key, int hash, Object value, boolean refRemove) {
            lock();
            try {
                return removeInternal(key, hash, value, refRemove);
            } finally {
                unlock();
            }
        }

        private V removeInternal(Object key, int hash, Object value, boolean refRemove) {
            if (!refRemove) {
                removeStale();
            }
            int c = this.count - 1;
            HashEntry<K, V>[] tab = this.table;
            int index = (tab.length - 1) & hash;
            HashEntry<K, V> first = tab[index];
            HashEntry<K, V> e = first;
            while (e != null && key != ((HashEntry) e).keyRef && (refRemove || hash != ((HashEntry) e).hash || !keyEq(key, e.key()))) {
                e = ((HashEntry) e).next;
            }
            V oldValue = null;
            if (e != null) {
                V v = e.value();
                if (value == null || value.equals(v)) {
                    oldValue = v;
                    this.modCount++;
                    HashEntry<K, V> newFirst = ((HashEntry) e).next;
                    for (HashEntry<K, V> p = first; p != e; p = ((HashEntry) p).next) {
                        K pKey = p.key();
                        if (pKey != null) {
                            newFirst = newHashEntry(pKey, ((HashEntry) p).hash, newFirst, p.value());
                        } else {
                            c--;
                        }
                    }
                    tab[index] = newFirst;
                    this.count = c;
                }
            }
            return oldValue;
        }

        void removeStale() {
            while (true) {
                KeyReference ref = (KeyReference) this.refQueue.poll();
                if (ref != null) {
                    remove(ref.keyRef(), ref.keyHash(), null, true);
                } else {
                    return;
                }
            }
        }

        V replace(K key, int hash, V newValue) {
            lock();
            try {
                return replaceInternal(key, hash, newValue);
            } finally {
                unlock();
            }
        }

        boolean replace(K key, int hash, V oldValue, V newValue) {
            lock();
            try {
                return replaceInternal2(key, hash, oldValue, newValue);
            } finally {
                unlock();
            }
        }

        private V replaceInternal(K key, int hash, V newValue) {
            removeStale();
            HashEntry<K, V> e = getFirst(hash);
            while (e != null && (((HashEntry) e).hash != hash || !keyEq(key, e.key()))) {
                e = ((HashEntry) e).next;
            }
            if (e == null) {
                return null;
            }
            V oldValue = e.value();
            e.setValue(newValue, this.valueType, this.refQueue);
            return oldValue;
        }

        private boolean replaceInternal2(K key, int hash, V oldValue, V newValue) {
            removeStale();
            HashEntry<K, V> e = getFirst(hash);
            while (e != null && (((HashEntry) e).hash != hash || !keyEq(key, e.key()))) {
                e = ((HashEntry) e).next;
            }
            if (e == null || !Objects.equals(oldValue, e.value())) {
                return false;
            }
            e.setValue(newValue, this.valueType, this.refQueue);
            return true;
        }

        void setTable(HashEntry<K, V>[] newTable) {
            this.threshold = (int) (newTable.length * this.loadFactor);
            this.table = newTable;
            this.refQueue = new ReferenceQueue<>();
        }
    }

    /* loaded from: classes9.dex */
    private static class SimpleEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private static boolean eq(Object o1, Object o2) {
            return Objects.equals(o1, o2);
        }

        SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            return eq(this.key, e.getKey()) && eq(this.value, e.getValue());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value != null ? this.value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class SoftKeyReference<K> extends SoftReference<K> implements KeyReference {
        private final int hash;

        SoftKeyReference(K key, int hash, ReferenceQueue<Object> refQueue) {
            super(key, refQueue);
            this.hash = hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public int keyHash() {
            return this.hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public Object keyRef() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class SoftValueReference<V> extends SoftReference<V> implements KeyReference {
        private final int hash;
        private final Object keyRef;

        SoftValueReference(V value, Object keyRef, int hash, ReferenceQueue<Object> refQueue) {
            super(value, refQueue);
            this.keyRef = keyRef;
            this.hash = hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public int keyHash() {
            return this.hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public Object keyRef() {
            return this.keyRef;
        }
    }

    /* loaded from: classes9.dex */
    private final class ValueIterator extends ConcurrentReferenceHashMap<K, V>.HashIterator implements Iterator<V>, Enumeration<V> {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return super.nextEntry().value();
        }

        @Override // java.util.Enumeration
        public V nextElement() {
            return super.nextEntry().value();
        }
    }

    /* loaded from: classes9.dex */
    private final class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            ConcurrentReferenceHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return ConcurrentReferenceHashMap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return ConcurrentReferenceHashMap.this.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return ConcurrentReferenceHashMap.this.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class WeakKeyReference<K> extends WeakReference<K> implements KeyReference {
        private final int hash;

        WeakKeyReference(K key, int hash, ReferenceQueue<Object> refQueue) {
            super(key, refQueue);
            this.hash = hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public int keyHash() {
            return this.hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public Object keyRef() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class WeakValueReference<V> extends WeakReference<V> implements KeyReference {
        private final int hash;
        private final Object keyRef;

        WeakValueReference(V value, Object keyRef, int hash, ReferenceQueue<Object> refQueue) {
            super(value, refQueue);
            this.keyRef = keyRef;
            this.hash = hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public int keyHash() {
            return this.hash;
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.KeyReference
        public Object keyRef() {
            return this.keyRef;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class WriteThroughEntry extends SimpleEntry<K, V> {
        private WriteThroughEntry(K k, V v) {
            super(k, v);
        }

        @Override // org.apache.commons.collections4.map.ConcurrentReferenceHashMap.SimpleEntry, java.util.Map.Entry
        public V setValue(V v) {
            Objects.requireNonNull(v, "value");
            V v2 = (V) super.setValue(v);
            ConcurrentReferenceHashMap.this.put(getKey(), v);
            return v2;
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    private static int hash(int h) {
        int h2 = h + ((h << 15) ^ (-12931));
        int h3 = h2 ^ (h2 >>> 10);
        int h4 = h3 + (h3 << 3);
        int h5 = h4 ^ (h4 >>> 6);
        int h6 = h5 + (h5 << 2) + (h5 << 14);
        return (h6 >>> 16) ^ h6;
    }

    private ConcurrentReferenceHashMap(int initialCapacity, float loadFactor, int concurrencyLevel, ReferenceType keyType, ReferenceType valueType, EnumSet<Option> options) {
        int concurrencyLevel2 = concurrencyLevel;
        if (loadFactor <= 0.0f || initialCapacity < 0 || concurrencyLevel2 <= 0) {
            throw new IllegalArgumentException();
        }
        int sshift = 0;
        int ssize = 1;
        while (ssize < (concurrencyLevel2 > 65536 ? 65536 : concurrencyLevel2)) {
            sshift++;
            ssize <<= 1;
        }
        this.segmentShift = 32 - sshift;
        this.segmentMask = ssize - 1;
        this.segments = Segment.newArray(ssize);
        initialCapacity = initialCapacity > 1073741824 ? 1073741824 : initialCapacity;
        int c = initialCapacity / ssize;
        int cap = 1;
        while (cap < (c * ssize < initialCapacity ? c + 1 : c)) {
            cap <<= 1;
        }
        this.identityComparisons = options != null && options.contains(Option.IDENTITY_COMPARISONS);
        for (int i = 0; i < this.segments.length; i++) {
            this.segments[i] = new Segment<>(cap, loadFactor, keyType, valueType, this.identityComparisons);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        for (Segment<K, V> segment : this.segments) {
            segment.clear();
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(remappingFunction);
        int hash = hashOf(key);
        Segment<K, V> segment = segmentFor(hash);
        return segment.apply(key, hash, remappingFunction);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(mappingFunction);
        int hash = hashOf(key);
        Segment<K, V> segment = segmentFor(hash);
        V v = segment.get(key, hash);
        return v == null ? segment.put(key, hash, null, mappingFunction, true) : v;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(remappingFunction);
        int hash = hashOf(key);
        Segment<K, V> segment = segmentFor(hash);
        V v = segment.get(key, hash);
        if (v == null) {
            return null;
        }
        return segmentFor(hash).applyIfPresent(key, hash, remappingFunction);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        int hash = hashOf(key);
        return segmentFor(hash).containsKey(key, hash);
    }

    /* JADX WARN: Finally extract failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        Objects.requireNonNull(value, "value");
        Segment<K, V>[] segments = this.segments;
        int[] mc = new int[segments.length];
        int k = 0;
        while (true) {
            int i = 0;
            if (k >= 2) {
                for (Segment<K, V> segment : segments) {
                    segment.lock();
                }
                boolean found = false;
                try {
                    int length = segments.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        Segment<K, V> segment2 = segments[i2];
                        if (segment2.containsValue(value)) {
                            found = true;
                            break;
                        }
                        i2++;
                    }
                    int length2 = segments.length;
                    while (i < length2) {
                        Segment<K, V> segment3 = segments[i];
                        segment3.unlock();
                        i++;
                    }
                    return found;
                } catch (Throwable th) {
                    int length3 = segments.length;
                    while (i < length3) {
                        Segment<K, V> segment4 = segments[i];
                        segment4.unlock();
                        i++;
                    }
                    throw th;
                }
            }
            int mcsum = 0;
            for (int i3 = 0; i3 < segments.length; i3++) {
                int i4 = ((Segment) segments[i3]).modCount;
                mc[i3] = i4;
                mcsum += i4;
                if (segments[i3].containsValue(value)) {
                    return true;
                }
            }
            int i5 = 1;
            if (mcsum != 0) {
                int i6 = 0;
                while (true) {
                    if (i6 >= segments.length) {
                        break;
                    }
                    if (mc[i6] != ((Segment) segments[i6]).modCount) {
                        i5 = 0;
                        break;
                    }
                    i6++;
                }
            }
            if (i5 != 0) {
                return false;
            }
            k++;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet = new EntrySet(false);
        this.entrySet = entrySet;
        return entrySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        int hash = hashOf(key);
        return segmentFor(hash).get(key, hash);
    }

    private int hashOf(Object key) {
        return hash(this.identityComparisons ? System.identityHashCode(key) : key.hashCode());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        Segment<K, V>[] segments = this.segments;
        int[] mc = new int[segments.length];
        int mcsum = 0;
        for (int i = 0; i < segments.length; i++) {
            if (((Segment) segments[i]).count != 0) {
                return false;
            }
            int i2 = ((Segment) segments[i]).modCount;
            mc[i] = i2;
            mcsum += i2;
        }
        if (mcsum != 0) {
            for (int i3 = 0; i3 < segments.length; i3++) {
                if (((Segment) segments[i3]).count != 0 || mc[i3] != ((Segment) segments[i3]).modCount) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    public void purgeStaleEntries() {
        for (Segment<K, V> segment : this.segments) {
            segment.removeStale();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        int hash = hashOf(key);
        return segmentFor(hash).put(key, hash, value, null, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K key, V value) {
        Objects.requireNonNull(value, "value");
        int hash = hashOf(key);
        return segmentFor(hash).put(key, hash, value, null, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        int hash = hashOf(key);
        return segmentFor(hash).remove(key, hash, null, false);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object key, Object value) {
        int hash = hashOf(key);
        return (value == null || segmentFor(hash).remove(key, hash, value, false) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K key, V value) {
        Objects.requireNonNull(value, "value");
        int hash = hashOf(key);
        return segmentFor(hash).replace(key, hash, value);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K key, V oldValue, V newValue) {
        Objects.requireNonNull(oldValue, "oldValue");
        Objects.requireNonNull(newValue, "newValue");
        int hash = hashOf(key);
        return segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    private Segment<K, V> segmentFor(int hash) {
        return this.segments[(hash >>> this.segmentShift) & this.segmentMask];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        Segment<K, V>[] segments = this.segments;
        long sum = 0;
        long check = 0;
        int[] mc = new int[segments.length];
        for (int k = 0; k < 2; k++) {
            check = 0;
            sum = 0;
            int mcsum = 0;
            for (int i = 0; i < segments.length; i++) {
                sum += ((Segment) segments[i]).count;
                int i2 = ((Segment) segments[i]).modCount;
                mc[i] = i2;
                mcsum += i2;
            }
            if (mcsum != 0) {
                int i3 = 0;
                while (true) {
                    if (i3 >= segments.length) {
                        break;
                    }
                    check += ((Segment) segments[i3]).count;
                    if (mc[i3] == ((Segment) segments[i3]).modCount) {
                        i3++;
                    } else {
                        check = -1;
                        break;
                    }
                }
            }
            if (check == sum) {
                break;
            }
        }
        if (check != sum) {
            sum = 0;
            for (Segment<K, V> segment : segments) {
                segment.lock();
            }
            for (Segment<K, V> segment2 : segments) {
                sum += ((Segment) segment2).count;
            }
            for (Segment<K, V> segment3 : segments) {
                segment3.unlock();
            }
        }
        if (sum > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sum;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }
}
