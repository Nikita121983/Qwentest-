package org.apache.logging.log4j.internal.map;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.util.ReadOnlyStringMap;
import org.apache.logging.log4j.util.TriConsumer;

/* loaded from: classes10.dex */
public class UnmodifiableArrayBackedMap extends AbstractMap<String, String> implements Serializable, ReadOnlyStringMap {
    public static final UnmodifiableArrayBackedMap EMPTY_MAP = new UnmodifiableArrayBackedMap(0);
    private static final int NUM_FIXED_ARRAY_ENTRIES = 1;
    private static final long serialVersionUID = 6849423432534211514L;
    private Object[] backingArray;
    private int numEntries;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class UnmodifiableEntry implements Map.Entry<String, String> {
        private int index;

        public UnmodifiableEntry(int index) {
            this.index = index;
        }

        @Override // java.util.Map.Entry
        public String getKey() {
            return (String) UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForKey(this.index)];
        }

        @Override // java.util.Map.Entry
        public String getValue() {
            return (String) UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForValue(this.index)];
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            String key = (String) UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForKey(this.index)];
            String value = (String) UnmodifiableArrayBackedMap.this.backingArray[UnmodifiableArrayBackedMap.getArrayIndexForValue(this.index)];
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override // java.util.Map.Entry
        public String setValue(String value) {
            throw new UnsupportedOperationException("Cannot update Entry instances in UnmodifiableArrayBackedMap");
        }
    }

    /* loaded from: classes10.dex */
    private class UnmodifiableEntryIterator implements Iterator<Map.Entry<String, String>> {
        private int index;

        private UnmodifiableEntryIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < UnmodifiableArrayBackedMap.this.numEntries;
        }

        @Override // java.util.Iterator
        public Map.Entry<String, String> next() {
            UnmodifiableArrayBackedMap unmodifiableArrayBackedMap = UnmodifiableArrayBackedMap.this;
            int i = this.index;
            this.index = i + 1;
            return new UnmodifiableEntry(i);
        }
    }

    /* loaded from: classes10.dex */
    private class UnmodifiableEntrySet extends AbstractSet<Map.Entry<String, String>> {
        private UnmodifiableEntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Map.Entry<String, String> e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<String, String>> c) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<String, String>> iterator() {
            return new UnmodifiableEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return UnmodifiableArrayBackedMap.this.numEntries;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getArrayIndexForKey(int entryIndex) {
        return (entryIndex * 2) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getArrayIndexForValue(int entryIndex) {
        return (entryIndex * 2) + 1 + 1;
    }

    public static UnmodifiableArrayBackedMap getMap(Object[] backingArray) {
        if (backingArray == null || backingArray.length == 1) {
            return EMPTY_MAP;
        }
        return new UnmodifiableArrayBackedMap(backingArray);
    }

    private UnmodifiableArrayBackedMap(int capacity) {
        this.backingArray = new Object[(capacity * 2) + 1];
        this.backingArray[0] = 0;
    }

    private UnmodifiableArrayBackedMap(Object[] backingArray) {
        this.numEntries = backingArray != null ? ((Integer) backingArray[0]).intValue() : 0;
        this.backingArray = backingArray;
    }

    UnmodifiableArrayBackedMap(UnmodifiableArrayBackedMap other) {
        this.backingArray = other.backingArray;
        this.numEntries = other.numEntries;
    }

    private void add(String key, String value) {
        this.backingArray[getArrayIndexForKey(this.numEntries)] = key;
        this.backingArray[getArrayIndexForValue(this.numEntries)] = value;
        this.numEntries++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Instance cannot be cleared, reuse EMPTY_MAP instead.");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return containsKey((String) key);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean containsKey(String key) {
        int hashCode = key.hashCode();
        for (int i = 0; i < this.numEntries; i++) {
            if (this.backingArray[getArrayIndexForKey(i)].hashCode() == hashCode && this.backingArray[getArrayIndexForKey(i)].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Object[] getBackingArray() {
        return this.backingArray;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        for (int i = 0; i < this.numEntries; i++) {
            Object valueInMap = this.backingArray[getArrayIndexForValue(i)];
            if (value == null) {
                if (valueInMap == null) {
                    return true;
                }
            } else if (value.equals(valueInMap)) {
                return true;
            }
        }
        return false;
    }

    public UnmodifiableArrayBackedMap copyAndPut(String key, String value) {
        UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries + 1);
        if (this.numEntries > 0) {
            System.arraycopy(this.backingArray, 1, newMap.backingArray, 1, this.numEntries * 2);
            newMap.numEntries = this.numEntries;
        }
        newMap.addOrOverwriteKey(key, value);
        newMap.updateNumEntriesInArray();
        return newMap;
    }

    public UnmodifiableArrayBackedMap copyAndPutAll(Map<String, String> entriesToAdd) {
        UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries + entriesToAdd.size());
        if (this.numEntries > 0) {
            System.arraycopy(this.backingArray, 0, newMap.backingArray, 0, (this.numEntries * 2) + 1);
            newMap.numEntries = this.numEntries;
        }
        for (Map.Entry<String, String> entry : entriesToAdd.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!isEmpty()) {
                newMap.addOrOverwriteKey(key, value);
            } else {
                newMap.add(key, value);
            }
        }
        newMap.updateNumEntriesInArray();
        return newMap;
    }

    public UnmodifiableArrayBackedMap copyAndRemove(String key) {
        int indexToRemove = -1;
        int oldIndex = this.numEntries - 1;
        while (true) {
            if (oldIndex >= 0) {
                if (this.backingArray[getArrayIndexForKey(oldIndex)].hashCode() != key.hashCode() || !this.backingArray[getArrayIndexForKey(oldIndex)].equals(key)) {
                    oldIndex--;
                } else {
                    indexToRemove = oldIndex;
                    break;
                }
            } else {
                break;
            }
        }
        if (indexToRemove == -1) {
            return this;
        }
        if (this.numEntries == 1) {
            return EMPTY_MAP;
        }
        UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(this.numEntries);
        if (indexToRemove > 0) {
            System.arraycopy(this.backingArray, 1, newMap.backingArray, 1, indexToRemove * 2);
        }
        if (indexToRemove + 1 < this.numEntries) {
            int nextIndexToCopy = indexToRemove + 1;
            int numRemainingEntries = this.numEntries - nextIndexToCopy;
            System.arraycopy(this.backingArray, getArrayIndexForKey(nextIndexToCopy), newMap.backingArray, getArrayIndexForKey(indexToRemove), numRemainingEntries * 2);
        }
        newMap.numEntries = this.numEntries - 1;
        newMap.updateNumEntriesInArray();
        return newMap;
    }

    public UnmodifiableArrayBackedMap copyAndRemoveAll(Iterable<String> keysToRemoveIterable) {
        Set<String> keysToRemove;
        if (isEmpty()) {
            return EMPTY_MAP;
        }
        if (keysToRemoveIterable instanceof Set) {
            keysToRemove = (Set) keysToRemoveIterable;
        } else {
            keysToRemove = new HashSet<>();
            Iterator<String> it = keysToRemoveIterable.iterator();
            while (it.hasNext()) {
                keysToRemove.add(it.next());
            }
        }
        int oldMapEntryCount = this.numEntries;
        UnmodifiableArrayBackedMap newMap = new UnmodifiableArrayBackedMap(oldMapEntryCount);
        if (keysToRemove.isEmpty()) {
            System.arraycopy(this.backingArray, 0, newMap.backingArray, 0, oldMapEntryCount * 2);
            newMap.numEntries = oldMapEntryCount;
            return this;
        }
        int newMapEntryIndex = 0;
        for (int oldMapEntryIndex = 0; oldMapEntryIndex < oldMapEntryCount; oldMapEntryIndex++) {
            int oldMapKeyIndex = getArrayIndexForKey(oldMapEntryIndex);
            Object key = this.backingArray[oldMapKeyIndex];
            boolean removed = keysToRemove.contains(key);
            if (!removed) {
                int oldMapValueIndex = getArrayIndexForValue(oldMapEntryIndex);
                Object value = this.backingArray[oldMapValueIndex];
                int newMapKeyIndex = getArrayIndexForKey(newMapEntryIndex);
                int newMapValueIndex = getArrayIndexForValue(newMapEntryIndex);
                newMap.backingArray[newMapKeyIndex] = key;
                newMap.backingArray[newMapValueIndex] = value;
                newMapEntryIndex++;
            }
        }
        newMap.numEntries = newMapEntryIndex;
        newMap.updateNumEntriesInArray();
        return newMap;
    }

    private void updateNumEntriesInArray() {
        this.backingArray[0] = Integer.valueOf(this.numEntries);
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super String, ? super String> action) {
        for (int i = 0; i < this.numEntries; i++) {
            String key = (String) this.backingArray[getArrayIndexForKey(i)];
            String value = (String) this.backingArray[getArrayIndexForValue(i)];
            action.accept(key, value);
        }
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> void forEach(final org.apache.logging.log4j.util.BiConsumer<String, ? super V> action) {
        for (int i = 0; i < this.numEntries; i++) {
            String key = (String) this.backingArray[getArrayIndexForKey(i)];
            action.accept(key, this.backingArray[getArrayIndexForValue(i)]);
        }
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V, S> void forEach(final TriConsumer<String, ? super V, S> action, final S state) {
        for (int i = 0; i < this.numEntries; i++) {
            String key = (String) this.backingArray[getArrayIndexForKey(i)];
            action.accept(key, this.backingArray[getArrayIndexForValue(i)], state);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, String>> entrySet() {
        return new UnmodifiableEntrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public String get(Object key) {
        return (String) getValue((String) key);
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public <V> V getValue(String str) {
        if (this.numEntries == 0) {
            return null;
        }
        int hashCode = str.hashCode();
        for (int i = 0; i < this.numEntries; i++) {
            if (this.backingArray[getArrayIndexForKey(i)].hashCode() == hashCode && this.backingArray[getArrayIndexForKey(i)].equals(str)) {
                return (V) this.backingArray[getArrayIndexForValue(i)];
            }
        }
        return null;
    }

    private void addOrOverwriteKey(String key, String value) {
        int keyHashCode = key.hashCode();
        for (int i = 0; i < this.numEntries; i++) {
            if (this.backingArray[getArrayIndexForKey(i)].hashCode() == keyHashCode && this.backingArray[getArrayIndexForKey(i)].equals(key)) {
                this.backingArray[getArrayIndexForValue(i)] = value;
                return;
            }
        }
        add(key, value);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public String put(String key, String value) {
        throw new UnsupportedOperationException("put() is not supported, use copyAndPut instead");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException("putAll() is not supported, use copyAndPutAll instead");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public String remove(Object key) {
        throw new UnsupportedOperationException("remove() is not supported, use copyAndRemove instead");
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.logging.log4j.util.ReadOnlyStringMap
    public int size() {
        return this.numEntries;
    }

    @Override // org.apache.logging.log4j.util.ReadOnlyStringMap
    public Map<String, String> toMap() {
        return this;
    }
}
