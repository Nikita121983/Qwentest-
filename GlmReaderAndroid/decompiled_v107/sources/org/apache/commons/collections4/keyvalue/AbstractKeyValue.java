package org.apache.commons.collections4.keyvalue;

import org.apache.commons.collections4.KeyValue;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public abstract class AbstractKeyValue<K, V> implements KeyValue<K, V> {
    private K key;
    private V value;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractKeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override // org.apache.commons.collections4.KeyValue
    public K getKey() {
        return this.key;
    }

    @Override // org.apache.commons.collections4.KeyValue
    public V getValue() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public K setKey(K key) {
        K old = this.key;
        this.key = key;
        return old;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public String toString() {
        return new StringBuilder().append(getKey()).append(Chars.EQ).append(getValue()).toString();
    }
}
