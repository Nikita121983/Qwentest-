package org.apache.commons.collections4.splitmap;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.IterableGet;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;

/* loaded from: classes9.dex */
public class AbstractIterableGetMapDecorator<K, V> implements IterableGet<K, V> {
    transient Map<K, V> map;

    protected AbstractIterableGetMapDecorator() {
    }

    public AbstractIterableGetMapDecorator(Map<K, V> map) {
        this.map = (Map) Objects.requireNonNull(map, "map");
    }

    @Override // org.apache.commons.collections4.Get
    public boolean containsKey(Object key) {
        return decorated().containsKey(key);
    }

    @Override // org.apache.commons.collections4.Get
    public boolean containsValue(Object value) {
        return decorated().containsValue(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<K, V> decorated() {
        return this.map;
    }

    @Override // org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return decorated().entrySet();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        return decorated().equals(object);
    }

    @Override // org.apache.commons.collections4.Get
    public V get(Object key) {
        return decorated().get(key);
    }

    public int hashCode() {
        return decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    @Override // org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return decorated().keySet();
    }

    @Override // org.apache.commons.collections4.IterableGet
    public MapIterator<K, V> mapIterator() {
        return new EntrySetToMapIteratorAdapter(entrySet());
    }

    @Override // org.apache.commons.collections4.Get
    public V remove(Object key) {
        return decorated().remove(key);
    }

    @Override // org.apache.commons.collections4.Get
    public int size() {
        return decorated().size();
    }

    public String toString() {
        return decorated().toString();
    }

    @Override // org.apache.commons.collections4.Get
    public Collection<V> values() {
        return decorated().values();
    }
}
