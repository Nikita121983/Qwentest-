package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap;

/* loaded from: classes9.dex */
public class MultiKeyMap<K, V> extends AbstractMapDecorator<MultiKey<? extends K>, V> implements Serializable, Cloneable {
    private static final long serialVersionUID = -1788199231038721040L;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((MultiKey) obj, (MultiKey<? extends K>) obj2);
    }

    public static <K, V> MultiKeyMap<K, V> multiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> map) {
        Objects.requireNonNull(map, "map");
        if (map.isEmpty()) {
            return new MultiKeyMap<>(map);
        }
        throw new IllegalArgumentException("Map must be empty");
    }

    public MultiKeyMap() {
        this(new HashedMap());
    }

    protected MultiKeyMap(AbstractHashedMap<MultiKey<? extends K>, V> map) {
        super(map);
        this.map = map;
    }

    protected void checkKey(MultiKey<?> key) {
        Objects.requireNonNull(key, "key");
    }

    public MultiKeyMap<K, V> clone() {
        try {
            return (MultiKeyMap) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public boolean containsKey(Object key1, Object key2) {
        int hashCode = hash(key1, key2);
        for (AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode); decoratedHashEntry != null; decoratedHashEntry = decoratedHashEntry.next) {
            if (decoratedHashEntry.hashCode == hashCode && isEqualKey(decoratedHashEntry, key1, key2)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(Object key1, Object key2, Object key3) {
        int hashCode = hash(key1, key2, key3);
        for (AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode); decoratedHashEntry != null; decoratedHashEntry = decoratedHashEntry.next) {
            if (decoratedHashEntry.hashCode == hashCode && isEqualKey(decoratedHashEntry, key1, key2, key3)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(Object key1, Object key2, Object key3, Object key4) {
        Object key12;
        Object key22;
        Object key32;
        Object key42;
        int hashCode = hash(key1, key2, key3, key4);
        AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode);
        while (decoratedHashEntry != null) {
            if (decoratedHashEntry.hashCode == hashCode) {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
                if (isEqualKey(decoratedHashEntry, key12, key22, key32, key42)) {
                    return true;
                }
            } else {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
            }
            decoratedHashEntry = decoratedHashEntry.next;
            key1 = key12;
            key2 = key22;
            key3 = key32;
            key4 = key42;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
        Object key12;
        int hashCode = hash(key1, key2, key3, key4, key5);
        MultiKeyMap multiKeyMap = this;
        AbstractHashedMap.HashEntry decoratedHashEntry = decoratedHashEntry(hashCode);
        while (decoratedHashEntry != null) {
            if (decoratedHashEntry.hashCode == hashCode) {
                key12 = key1;
                if (multiKeyMap.isEqualKey(decoratedHashEntry, key12, key2, key3, key4, key5)) {
                    return true;
                }
            } else {
                key12 = key1;
            }
            decoratedHashEntry = decoratedHashEntry.next;
            multiKeyMap = this;
            key1 = key12;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    public AbstractHashedMap<MultiKey<? extends K>, V> decorated() {
        return (AbstractHashedMap) super.decorated();
    }

    AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> decoratedHashEntry(int hashCode) {
        return decorated().data[decoratedHashIndex(hashCode)];
    }

    int decoratedHashIndex(int hashCode) {
        return decorated().hashIndex(hashCode, decorated().data.length);
    }

    public V get(Object key1, Object key2) {
        int hashCode = hash(key1, key2);
        for (AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode); decoratedHashEntry != null; decoratedHashEntry = decoratedHashEntry.next) {
            if (decoratedHashEntry.hashCode == hashCode && isEqualKey(decoratedHashEntry, key1, key2)) {
                return decoratedHashEntry.getValue();
            }
        }
        return null;
    }

    public V get(Object key1, Object key2, Object key3) {
        int hashCode = hash(key1, key2, key3);
        for (AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode); decoratedHashEntry != null; decoratedHashEntry = decoratedHashEntry.next) {
            if (decoratedHashEntry.hashCode == hashCode && isEqualKey(decoratedHashEntry, key1, key2, key3)) {
                return decoratedHashEntry.getValue();
            }
        }
        return null;
    }

    public V get(Object key1, Object key2, Object key3, Object key4) {
        Object key12;
        Object key22;
        Object key32;
        Object key42;
        int hashCode = hash(key1, key2, key3, key4);
        AbstractHashedMap.HashEntry<K, V> decoratedHashEntry = decoratedHashEntry(hashCode);
        while (decoratedHashEntry != null) {
            if (decoratedHashEntry.hashCode == hashCode) {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
                if (isEqualKey(decoratedHashEntry, key12, key22, key32, key42)) {
                    return decoratedHashEntry.getValue();
                }
            } else {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
            }
            decoratedHashEntry = decoratedHashEntry.next;
            key1 = key12;
            key2 = key22;
            key3 = key32;
            key4 = key42;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V get(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object obj6;
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        MultiKeyMap multiKeyMap = this;
        AbstractHashedMap.HashEntry decoratedHashEntry = decoratedHashEntry(hash);
        while (decoratedHashEntry != null) {
            if (decoratedHashEntry.hashCode == hash) {
                obj6 = obj;
                if (multiKeyMap.isEqualKey(decoratedHashEntry, obj6, obj2, obj3, obj4, obj5)) {
                    return (V) decoratedHashEntry.getValue();
                }
            } else {
                obj6 = obj;
            }
            decoratedHashEntry = decoratedHashEntry.next;
            multiKeyMap = this;
            obj = obj6;
        }
        return null;
    }

    protected int hash(Object key1, Object key2) {
        int h = key1 != null ? 0 ^ key1.hashCode() : 0;
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        int h2 = h + (~(h << 9));
        int h3 = h2 ^ (h2 >>> 14);
        int h4 = h3 + (h3 << 4);
        return h4 ^ (h4 >>> 10);
    }

    protected int hash(Object key1, Object key2, Object key3) {
        int h = key1 != null ? 0 ^ key1.hashCode() : 0;
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        int h2 = h + (~(h << 9));
        int h3 = h2 ^ (h2 >>> 14);
        int h4 = h3 + (h3 << 4);
        return h4 ^ (h4 >>> 10);
    }

    protected int hash(Object key1, Object key2, Object key3, Object key4) {
        int h = key1 != null ? 0 ^ key1.hashCode() : 0;
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        int h2 = h + (~(h << 9));
        int h3 = h2 ^ (h2 >>> 14);
        int h4 = h3 + (h3 << 4);
        return h4 ^ (h4 >>> 10);
    }

    protected int hash(Object key1, Object key2, Object key3, Object key4, Object key5) {
        int h = key1 != null ? 0 ^ key1.hashCode() : 0;
        if (key2 != null) {
            h ^= key2.hashCode();
        }
        if (key3 != null) {
            h ^= key3.hashCode();
        }
        if (key4 != null) {
            h ^= key4.hashCode();
        }
        if (key5 != null) {
            h ^= key5.hashCode();
        }
        int h2 = h + (~(h << 9));
        int h3 = h2 ^ (h2 >>> 14);
        int h4 = h3 + (h3 << 4);
        return h4 ^ (h4 >>> 10);
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2) {
        MultiKey<? extends K> multi = entry.getKey();
        return multi.size() == 2 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1));
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3) {
        MultiKey<? extends K> multi = entry.getKey();
        return multi.size() == 3 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1)) && Objects.equals(key3, multi.getKey(2));
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3, Object key4) {
        MultiKey<? extends K> multi = entry.getKey();
        return multi.size() == 4 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1)) && Objects.equals(key3, multi.getKey(2)) && Objects.equals(key4, multi.getKey(3));
    }

    protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, Object key1, Object key2, Object key3, Object key4, Object key5) {
        MultiKey<? extends K> multi = entry.getKey();
        return multi.size() == 5 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1)) && Objects.equals(key3, multi.getKey(2)) && Objects.equals(key4, multi.getKey(3)) && Objects.equals(key5, multi.getKey(4));
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    public MapIterator<MultiKey<? extends K>, V> mapIterator() {
        return decorated().mapIterator();
    }

    public V put(K key1, K key2, K key3, K key4, K key5, V value) {
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry;
        K key12;
        int hashCode = hash(key1, key2, key3, key4, key5);
        MultiKeyMap<K, V> multiKeyMap = this;
        int index = decoratedHashIndex(hashCode);
        AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry2 = decorated().data[index];
        while (entry2 != null) {
            if (entry2.hashCode == hashCode) {
                K key13 = key1;
                boolean isEqualKey = multiKeyMap.isEqualKey(entry2, key13, key2, key3, key4, key5);
                entry = entry2;
                key12 = key13;
                if (isEqualKey) {
                    V oldValue = entry.getValue();
                    decorated().updateEntry(entry, value);
                    return oldValue;
                }
            } else {
                entry = entry2;
                key12 = key1;
            }
            K k = key12;
            entry2 = entry.next;
            key1 = k;
            multiKeyMap = this;
        }
        decorated().addMapping(index, hashCode, new MultiKey<>(key1, key2, key3, key4, key5), value);
        return null;
    }

    public V put(K key1, K key2, K key3, K key4, V value) {
        K key12;
        K key22;
        K key32;
        K key42;
        int hashCode = hash(key1, key2, key3, key4);
        int index = decoratedHashIndex(hashCode);
        AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[index];
        while (hashEntry != null) {
            if (hashEntry.hashCode == hashCode) {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
                if (isEqualKey(hashEntry, key12, key22, key32, key42)) {
                    V oldValue = hashEntry.getValue();
                    decorated().updateEntry(hashEntry, value);
                    return oldValue;
                }
            } else {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
            }
            hashEntry = hashEntry.next;
            key1 = key12;
            key2 = key22;
            key3 = key32;
            key4 = key42;
        }
        decorated().addMapping(index, hashCode, new MultiKey<>(key1, key2, key3, key4), value);
        return null;
    }

    public V put(K key1, K key2, K key3, V value) {
        int hashCode = hash(key1, key2, key3);
        int index = decoratedHashIndex(hashCode);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[index]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hashCode && isEqualKey(hashEntry, key1, key2, key3)) {
                V oldValue = hashEntry.getValue();
                decorated().updateEntry(hashEntry, value);
                return oldValue;
            }
        }
        decorated().addMapping(index, hashCode, new MultiKey<>(key1, key2, key3), value);
        return null;
    }

    public V put(K key1, K key2, V value) {
        int hashCode = hash(key1, key2);
        int index = decoratedHashIndex(hashCode);
        for (AbstractHashedMap.HashEntry<K, V> hashEntry = decorated().data[index]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hashCode && isEqualKey(hashEntry, key1, key2)) {
                V oldValue = hashEntry.getValue();
                decorated().updateEntry(hashEntry, value);
                return oldValue;
            }
        }
        decorated().addMapping(index, hashCode, new MultiKey<>(key1, key2), value);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V put(MultiKey<? extends K> multiKey, V v) {
        checkKey(multiKey);
        return (V) super.put((MultiKeyMap<K, V>) multiKey, (MultiKey<? extends K>) v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends MultiKey<? extends K>, ? extends V> map) {
        for (MultiKey<? extends K> key : map.keySet()) {
            checkKey(key);
        }
        super.putAll(map);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.map = (Map) in.readObject();
    }

    public boolean removeAll(Object key1) {
        boolean modified = false;
        MapIterator<MultiKey<? extends K>, V> it = mapIterator();
        while (it.hasNext()) {
            MultiKey<? extends K> multi = it.next();
            if (multi.size() >= 1 && Objects.equals(key1, multi.getKey(0))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2) {
        boolean modified = false;
        MapIterator<MultiKey<? extends K>, V> it = mapIterator();
        while (it.hasNext()) {
            MultiKey<? extends K> multi = it.next();
            if (multi.size() >= 2 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2, Object key3) {
        boolean modified = false;
        MapIterator<MultiKey<? extends K>, V> it = mapIterator();
        while (it.hasNext()) {
            MultiKey<? extends K> multi = it.next();
            if (multi.size() >= 3 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1)) && Objects.equals(key3, multi.getKey(2))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean removeAll(Object key1, Object key2, Object key3, Object key4) {
        boolean modified = false;
        MapIterator<MultiKey<? extends K>, V> it = mapIterator();
        while (it.hasNext()) {
            MultiKey<? extends K> multi = it.next();
            if (multi.size() >= 4 && Objects.equals(key1, multi.getKey(0)) && Objects.equals(key2, multi.getKey(1)) && Objects.equals(key3, multi.getKey(2)) && Objects.equals(key4, multi.getKey(3))) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public V removeMultiKey(Object key1, Object key2) {
        int hashCode = hash(key1, key2);
        int index = decoratedHashIndex(hashCode);
        AbstractHashedMap.HashEntry<K, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<K, V> hashEntry2 = decorated().data[index]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hashCode && isEqualKey(hashEntry2, key1, key2)) {
                V oldValue = hashEntry2.getValue();
                decorated().removeMapping(hashEntry2, index, hashEntry);
                return oldValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    public V removeMultiKey(Object key1, Object key2, Object key3) {
        int hashCode = hash(key1, key2, key3);
        int index = decoratedHashIndex(hashCode);
        AbstractHashedMap.HashEntry<K, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<K, V> hashEntry2 = decorated().data[index]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hashCode && isEqualKey(hashEntry2, key1, key2, key3)) {
                V oldValue = hashEntry2.getValue();
                decorated().removeMapping(hashEntry2, index, hashEntry);
                return oldValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    public V removeMultiKey(Object key1, Object key2, Object key3, Object key4) {
        Object key12;
        Object key22;
        Object key32;
        Object key42;
        int hashCode = hash(key1, key2, key3, key4);
        int index = decoratedHashIndex(hashCode);
        AbstractHashedMap.HashEntry<K, V> hashEntry = null;
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = decorated().data[index];
        while (hashEntry2 != null) {
            if (hashEntry2.hashCode == hashCode) {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
                if (isEqualKey(hashEntry2, key12, key22, key32, key42)) {
                    V oldValue = hashEntry2.getValue();
                    decorated().removeMapping(hashEntry2, index, hashEntry);
                    return oldValue;
                }
            } else {
                key12 = key1;
                key22 = key2;
                key32 = key3;
                key42 = key4;
            }
            hashEntry = hashEntry2;
            hashEntry2 = hashEntry2.next;
            key1 = key12;
            key2 = key22;
            key3 = key32;
            key4 = key42;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public V removeMultiKey(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object obj6;
        int hash = hash(obj, obj2, obj3, obj4, obj5);
        MultiKeyMap multiKeyMap = this;
        int decoratedHashIndex = decoratedHashIndex(hash);
        AbstractHashedMap.HashEntry hashEntry = decorated().data[decoratedHashIndex];
        AbstractHashedMap.HashEntry hashEntry2 = null;
        while (hashEntry != null) {
            if (hashEntry.hashCode == hash) {
                obj6 = obj;
                if (multiKeyMap.isEqualKey(hashEntry, obj6, obj2, obj3, obj4, obj5)) {
                    V v = (V) hashEntry.getValue();
                    decorated().removeMapping(hashEntry, decoratedHashIndex, hashEntry2);
                    return v;
                }
            } else {
                obj6 = obj;
            }
            hashEntry2 = hashEntry;
            hashEntry = hashEntry.next;
            multiKeyMap = this;
            obj = obj6;
        }
        return null;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.map);
    }
}
