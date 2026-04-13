package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.KeyValue;

/* loaded from: classes9.dex */
public abstract class AbstractMapEntryDecorator<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {
    private final Map.Entry<K, V> entry;

    public AbstractMapEntryDecorator(Map.Entry<K, V> entry) {
        this.entry = (Map.Entry) Objects.requireNonNull(entry, "entry");
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return this.entry.equals(object);
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    public K getKey() {
        return this.entry.getKey();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map.Entry<K, V> getMapEntry() {
        return this.entry;
    }

    @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
    public V getValue() {
        return this.entry.getValue();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return this.entry.hashCode();
    }

    @Override // java.util.Map.Entry
    public V setValue(V value) {
        return this.entry.setValue(value);
    }

    public String toString() {
        return this.entry.toString();
    }
}
