package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class Flat3Map<K, V> implements IterableMap<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = -6701087419741928296L;
    private transient AbstractHashedMap<K, V> delegateMap;
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    private transient K key1;
    private transient K key2;
    private transient K key3;
    private transient int size;
    private transient V value1;
    private transient V value2;
    private transient V value3;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static abstract class EntryIterator<K, V> {
        private FlatMapEntry<K, V> currentEntry;
        private int nextIndex;
        private final Flat3Map<K, V> parent;

        EntryIterator(Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map) this.parent).size;
        }

        public Map.Entry<K, V> nextEntry() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            Flat3Map<K, V> flat3Map = this.parent;
            int i = this.nextIndex + 1;
            this.nextIndex = i;
            this.currentEntry = new FlatMapEntry<>(flat3Map, i);
            return this.currentEntry;
        }

        public void remove() {
            if (this.currentEntry == null) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.parent.remove(this.currentEntry.getKey());
            this.currentEntry.setRemoved(true);
            this.nextIndex--;
            this.currentEntry = null;
        }
    }

    /* loaded from: classes9.dex */
    static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private final Flat3Map<K, V> parent;

        EntrySet(Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.entrySet().iterator();
            }
            if (this.parent.isEmpty()) {
                return EmptyIterator.emptyIterator();
            }
            return new EntrySetIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) obj;
            Object key = entry.getKey();
            boolean result = this.parent.containsKey(key);
            this.parent.remove(key);
            return result;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes9.dex */
    static class EntrySetIterator<K, V> extends EntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        EntrySetIterator(Flat3Map<K, V> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class FlatMapEntry<K, V> implements Map.Entry<K, V> {
        private final int index;
        private final Flat3Map<K, V> parent;
        private volatile boolean removed = false;

        FlatMapEntry(Flat3Map<K, V> parent, int index) {
            this.parent = parent;
            this.index = index;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this.removed || !(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> other = (Map.Entry) obj;
            return Objects.equals(getKey(), other.getKey()) && Objects.equals(getValue(), other.getValue());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (!this.removed) {
                switch (this.index) {
                    case 1:
                        return (K) ((Flat3Map) this.parent).key1;
                    case 2:
                        return (K) ((Flat3Map) this.parent).key2;
                    case 3:
                        return (K) ((Flat3Map) this.parent).key3;
                    default:
                        throw new IllegalStateException("Invalid map index: " + this.index);
                }
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (!this.removed) {
                switch (this.index) {
                    case 1:
                        return (V) ((Flat3Map) this.parent).value1;
                    case 2:
                        return (V) ((Flat3Map) this.parent).value2;
                    case 3:
                        return (V) ((Flat3Map) this.parent).value3;
                    default:
                        throw new IllegalStateException("Invalid map index: " + this.index);
                }
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (this.removed) {
                return 0;
            }
            Object key = getKey();
            Object value = getValue();
            return (value != null ? value.hashCode() : 0) ^ (key == null ? 0 : key.hashCode());
        }

        void setRemoved(boolean removed) {
            this.removed = removed;
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            if (this.removed) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            V old = getValue();
            switch (this.index) {
                case 1:
                    ((Flat3Map) this.parent).value1 = value;
                    return old;
                case 2:
                    ((Flat3Map) this.parent).value2 = value;
                    return old;
                case 3:
                    ((Flat3Map) this.parent).value3 = value;
                    return old;
                default:
                    throw new IllegalStateException("Invalid map index: " + this.index);
            }
        }

        public String toString() {
            if (!this.removed) {
                return getKey() + "=" + getValue();
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class FlatMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        private boolean canRemove;
        private int nextIndex;
        private final Flat3Map<K, V> parent;

        FlatMapIterator(Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public K getKey() {
            if (this.canRemove) {
                switch (this.nextIndex) {
                    case 1:
                        return (K) ((Flat3Map) this.parent).key1;
                    case 2:
                        return (K) ((Flat3Map) this.parent).key2;
                    case 3:
                        return (K) ((Flat3Map) this.parent).key3;
                    default:
                        throw new IllegalStateException("Invalid map index: " + this.nextIndex);
                }
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V getValue() {
            if (this.canRemove) {
                switch (this.nextIndex) {
                    case 1:
                        return (V) ((Flat3Map) this.parent).value1;
                    case 2:
                        return (V) ((Flat3Map) this.parent).value2;
                    case 3:
                        return (V) ((Flat3Map) this.parent).value3;
                    default:
                        throw new IllegalStateException("Invalid map index: " + this.nextIndex);
                }
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map) this.parent).size;
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
            this.canRemove = true;
            this.nextIndex++;
            return getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }
            this.parent.remove(getKey());
            this.nextIndex--;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.nextIndex = 0;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V value) {
            if (!this.canRemove) {
                throw new IllegalStateException("setValue() can only be called after next() and before remove()");
            }
            V old = getValue();
            switch (this.nextIndex) {
                case 1:
                    ((Flat3Map) this.parent).value1 = value;
                    return old;
                case 2:
                    ((Flat3Map) this.parent).value2 = value;
                    return old;
                case 3:
                    ((Flat3Map) this.parent).value3 = value;
                    return old;
                default:
                    throw new IllegalStateException("Invalid map index: " + this.nextIndex);
            }
        }

        public String toString() {
            if (this.canRemove) {
                return "Iterator[" + getKey() + "=" + getValue() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
            }
            return "Iterator[]";
        }
    }

    /* loaded from: classes9.dex */
    static class KeySet<K> extends AbstractSet<K> {
        private final Flat3Map<K, ?> parent;

        KeySet(Flat3Map<K, ?> parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object key) {
            return this.parent.containsKey(key);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.keySet().iterator();
            }
            if (this.parent.isEmpty()) {
                return EmptyIterator.emptyIterator();
            }
            return new KeySetIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object key) {
            boolean result = this.parent.containsKey(key);
            this.parent.remove(key);
            return result;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes9.dex */
    static class KeySetIterator<K> extends EntryIterator<K, Object> implements Iterator<K> {
        KeySetIterator(Flat3Map<K, ?> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    /* loaded from: classes9.dex */
    static class Values<V> extends AbstractCollection<V> {
        private final Flat3Map<?, V> parent;

        Values(Flat3Map<?, V> parent) {
            this.parent = parent;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object value) {
            return this.parent.containsValue(value);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.values().iterator();
            }
            if (this.parent.isEmpty()) {
                return EmptyIterator.emptyIterator();
            }
            return new ValuesIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes9.dex */
    static class ValuesIterator<V> extends EntryIterator<Object, V> implements Iterator<V> {
        ValuesIterator(Flat3Map<?, V> parent) {
            super(parent);
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    public Flat3Map() {
    }

    public Flat3Map(Map<? extends K, ? extends V> map) {
        putAll(map);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        if (this.delegateMap != null) {
            this.delegateMap.clear();
            this.delegateMap = null;
            return;
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    public Flat3Map<K, V> clone() {
        try {
            Flat3Map<K, V> cloned = (Flat3Map) super.clone();
            if (cloned.delegateMap != null) {
                cloned.delegateMap = cloned.delegateMap.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x002e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0010. Please report as an issue. */
    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.containsKey(key);
        }
        if (key == null) {
            switch (this.size) {
                case 3:
                    if (this.key3 == null) {
                        return true;
                    }
                case 2:
                    if (this.key2 == null) {
                        return true;
                    }
                case 1:
                    if (this.key1 == null) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3:
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        return true;
                    }
                    break;
                case 2:
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        return true;
                    }
                    break;
                case 1:
                    if (this.hash1 == hashCode && key.equals(this.key1)) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0026. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0010. Please report as an issue. */
    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        if (this.delegateMap != null) {
            return this.delegateMap.containsValue(value);
        }
        if (value == null) {
            switch (this.size) {
                case 3:
                    if (this.value3 == null) {
                        return true;
                    }
                case 2:
                    if (this.value2 == null) {
                        return true;
                    }
                case 1:
                    if (this.value1 == null) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } else {
            switch (this.size) {
                case 3:
                    if (value.equals(this.value3)) {
                        return true;
                    }
                case 2:
                    if (value.equals(this.value2)) {
                        return true;
                    }
                case 1:
                    if (value.equals(this.value1)) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    private void convertToMap() {
        this.delegateMap = createDelegateMap();
        switch (this.size) {
            case 3:
                this.delegateMap.put(this.key3, this.value3);
            case 2:
                this.delegateMap.put(this.key2, this.value2);
            case 1:
                this.delegateMap.put(this.key1, this.value1);
            case 0:
                this.size = 0;
                this.hash3 = 0;
                this.hash2 = 0;
                this.hash1 = 0;
                this.key3 = null;
                this.key2 = null;
                this.key1 = null;
                this.value3 = null;
                this.value2 = null;
                this.value1 = null;
                return;
            default:
                throw new IllegalStateException("Invalid map index: " + this.size);
        }
    }

    protected AbstractHashedMap<K, V> createDelegateMap() {
        return new HashedMap();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.delegateMap != null) {
            return this.delegateMap.entrySet();
        }
        return new EntrySet(this);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0028. Please report as an issue. */
    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (this.delegateMap != null) {
            return this.delegateMap.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map<?, ?> other = (Map) obj;
        if (this.size != other.size()) {
            return false;
        }
        if (this.size > 0) {
            switch (this.size) {
                case 3:
                    if (!other.containsKey(this.key3)) {
                        return false;
                    }
                    Object otherValue = other.get(this.key3);
                    if (!Objects.equals(this.value3, otherValue)) {
                        return false;
                    }
                case 2:
                    if (!other.containsKey(this.key2)) {
                        return false;
                    }
                    Object otherValue2 = other.get(this.key2);
                    if (!Objects.equals(this.value2, otherValue2)) {
                        return false;
                    }
                case 1:
                    if (!other.containsKey(this.key1)) {
                        return false;
                    }
                    Object otherValue3 = other.get(this.key1);
                    if (!Objects.equals(this.value1, otherValue3)) {
                        return false;
                    }
                default:
                    return true;
            }
        }
        return true;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:26:0x0033. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x000f. Please report as an issue. */
    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V get(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.get(key);
        }
        if (key == null) {
            switch (this.size) {
                case 3:
                    if (this.key3 == null) {
                        return this.value3;
                    }
                case 2:
                    if (this.key2 == null) {
                        return this.value2;
                    }
                case 1:
                    if (this.key1 == null) {
                        return this.value1;
                    }
                    return null;
                default:
                    return null;
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 3:
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        return this.value3;
                    }
                    break;
                case 2:
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        return this.value2;
                    }
                    break;
                case 1:
                    if (this.hash1 == hashCode && key.equals(this.key1)) {
                        return this.value1;
                    }
                    return null;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000f. Please report as an issue. */
    @Override // java.util.Map
    public int hashCode() {
        if (this.delegateMap != null) {
            return this.delegateMap.hashCode();
        }
        int total = 0;
        switch (this.size) {
            case 0:
                return 0;
            case 3:
                total = 0 + (this.hash3 ^ (this.value3 == null ? 0 : this.value3.hashCode()));
            case 2:
                total += this.hash2 ^ (this.value2 == null ? 0 : this.value2.hashCode());
            case 1:
                return total + (this.hash1 ^ (this.value1 != null ? this.value1.hashCode() : 0));
            default:
                throw new IllegalStateException("Invalid map index: " + this.size);
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.delegateMap != null) {
            return this.delegateMap.keySet();
        }
        return new KeySet(this);
    }

    @Override // org.apache.commons.collections4.IterableGet
    public MapIterator<K, V> mapIterator() {
        if (this.delegateMap != null) {
            return this.delegateMap.mapIterator();
        }
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new FlatMapIterator(this);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:39:0x0039. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x000f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009c  */
    @Override // java.util.Map, org.apache.commons.collections4.Put
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public V put(K r4, V r5) {
        /*
            r3 = this;
            org.apache.commons.collections4.map.AbstractHashedMap<K, V> r0 = r3.delegateMap
            if (r0 == 0) goto Lb
            org.apache.commons.collections4.map.AbstractHashedMap<K, V> r0 = r3.delegateMap
            java.lang.Object r0 = r0.put(r4, r5)
            return r0
        Lb:
            if (r4 != 0) goto L2f
            int r0 = r3.size
            switch(r0) {
                case 1: goto L25;
                case 2: goto L1c;
                case 3: goto L13;
                default: goto L12;
            }
        L12:
            goto L2e
        L13:
            K r0 = r3.key3
            if (r0 != 0) goto L1c
            V r0 = r3.value3
            r3.value3 = r5
            return r0
        L1c:
            K r0 = r3.key2
            if (r0 != 0) goto L25
            V r0 = r3.value2
            r3.value2 = r5
            return r0
        L25:
            K r0 = r3.key1
            if (r0 != 0) goto L2e
            V r0 = r3.value1
            r3.value1 = r5
            return r0
        L2e:
            goto L70
        L2f:
            int r0 = r3.size
            if (r0 <= 0) goto L70
            int r0 = r4.hashCode()
            int r1 = r3.size
            switch(r1) {
                case 1: goto L5f;
                case 2: goto L4e;
                case 3: goto L3d;
                default: goto L3c;
            }
        L3c:
            goto L70
        L3d:
            int r1 = r3.hash3
            if (r1 != r0) goto L4e
            K r1 = r3.key3
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L4e
            V r1 = r3.value3
            r3.value3 = r5
            return r1
        L4e:
            int r1 = r3.hash2
            if (r1 != r0) goto L5f
            K r1 = r3.key2
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L5f
            V r1 = r3.value2
            r3.value2 = r5
            return r1
        L5f:
            int r1 = r3.hash1
            if (r1 != r0) goto L70
            K r1 = r3.key1
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L70
            V r1 = r3.value1
            r3.value1 = r5
            return r1
        L70:
            int r0 = r3.size
            r1 = 0
            r2 = 0
            switch(r0) {
                case 0: goto L9c;
                case 1: goto L8e;
                case 2: goto L80;
                default: goto L77;
            }
        L77:
            r3.convertToMap()
            org.apache.commons.collections4.map.AbstractHashedMap<K, V> r0 = r3.delegateMap
            r0.put(r4, r5)
            return r1
        L80:
            if (r4 != 0) goto L83
            goto L87
        L83:
            int r2 = r4.hashCode()
        L87:
            r3.hash3 = r2
            r3.key3 = r4
            r3.value3 = r5
            goto Laa
        L8e:
            if (r4 != 0) goto L91
            goto L95
        L91:
            int r2 = r4.hashCode()
        L95:
            r3.hash2 = r2
            r3.key2 = r4
            r3.value2 = r5
            goto Laa
        L9c:
            if (r4 != 0) goto L9f
            goto La3
        L9f:
            int r2 = r4.hashCode()
        La3:
            r3.hash1 = r2
            r3.key1 = r4
            r3.value1 = r5
        Laa:
            int r0 = r3.size
            int r0 = r0 + 1
            r3.size = r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.Flat3Map.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        if (this.delegateMap != null) {
            this.delegateMap.putAll(map);
            return;
        }
        if (size < 4) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        convertToMap();
        this.delegateMap.putAll(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int count = in.readInt();
        if (count > 3) {
            this.delegateMap = createDelegateMap();
        }
        for (int i = count; i > 0; i--) {
            put(in.readObject(), in.readObject());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Map, org.apache.commons.collections4.Get
    public V remove(Object key) {
        if (this.delegateMap != null) {
            return this.delegateMap.remove(key);
        }
        if (this.size == 0) {
            return null;
        }
        if (key == null) {
            switch (this.size) {
                case 1:
                    if (this.key1 == null) {
                        V old = this.value1;
                        this.hash1 = 0;
                        this.key1 = null;
                        this.value1 = null;
                        this.size = 0;
                        return old;
                    }
                    break;
                case 2:
                    if (this.key2 == null) {
                        V old2 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old2;
                    }
                    if (this.key1 != null) {
                        return null;
                    }
                    V old3 = this.value1;
                    this.hash1 = this.hash2;
                    this.key1 = this.key2;
                    this.value1 = this.value2;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return old3;
                case 3:
                    if (this.key3 == null) {
                        V old4 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old4;
                    }
                    if (this.key2 == null) {
                        V old5 = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old5;
                    }
                    if (this.key1 != null) {
                        return null;
                    }
                    V old6 = this.value1;
                    this.hash1 = this.hash3;
                    this.key1 = this.key3;
                    this.value1 = this.value3;
                    this.hash3 = 0;
                    this.key3 = null;
                    this.value3 = null;
                    this.size = 2;
                    return old6;
            }
        } else if (this.size > 0) {
            int hashCode = key.hashCode();
            switch (this.size) {
                case 1:
                    if (this.hash1 == hashCode && key.equals(this.key1)) {
                        V old7 = this.value1;
                        this.hash1 = 0;
                        this.key1 = null;
                        this.value1 = null;
                        this.size = 0;
                        return old7;
                    }
                    break;
                case 2:
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        V old8 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return old8;
                    }
                    if (this.hash1 != hashCode || !key.equals(this.key1)) {
                        return null;
                    }
                    V old9 = this.value1;
                    this.hash1 = this.hash2;
                    this.key1 = this.key2;
                    this.value1 = this.value2;
                    this.hash2 = 0;
                    this.key2 = null;
                    this.value2 = null;
                    this.size = 1;
                    return old9;
                case 3:
                    if (this.hash3 == hashCode && key.equals(this.key3)) {
                        V old10 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old10;
                    }
                    if (this.hash2 == hashCode && key.equals(this.key2)) {
                        V old11 = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return old11;
                    }
                    if (this.hash1 != hashCode || !key.equals(this.key1)) {
                        return null;
                    }
                    V old12 = this.value1;
                    this.hash1 = this.hash3;
                    this.key1 = this.key3;
                    this.value1 = this.value3;
                    this.hash3 = 0;
                    this.key3 = null;
                    this.value3 = null;
                    this.size = 2;
                    return old12;
            }
        }
        return null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        if (this.delegateMap != null) {
            return this.delegateMap.size();
        }
        return this.size;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0026. Please report as an issue. */
    public String toString() {
        if (this.delegateMap != null) {
            return this.delegateMap.toString();
        }
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder buf = new StringBuilder(128);
        buf.append('{');
        switch (this.size) {
            case 3:
                buf.append(this.key3 == this ? "(this Map)" : this.key3);
                buf.append(Chars.EQ);
                buf.append(this.value3 == this ? "(this Map)" : this.value3);
                buf.append(CollectionUtils.COMMA);
            case 2:
                buf.append(this.key2 == this ? "(this Map)" : this.key2);
                buf.append(Chars.EQ);
                buf.append(this.value2 == this ? "(this Map)" : this.value2);
                buf.append(CollectionUtils.COMMA);
            case 1:
                buf.append(this.key1 == this ? "(this Map)" : this.key1);
                buf.append(Chars.EQ);
                buf.append(this.value1 != this ? this.value1 : "(this Map)");
                buf.append('}');
                return buf.toString();
            default:
                throw new IllegalStateException("Invalid map index: " + this.size);
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Collection<V> values() {
        if (this.delegateMap != null) {
            return this.delegateMap.values();
        }
        return new Values(this);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        MapIterator<K, V> mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            out.writeObject(mapIterator.next());
            out.writeObject(mapIterator.getValue());
        }
    }
}
