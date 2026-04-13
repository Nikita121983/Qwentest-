package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Trie;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V> implements Trie<K, V>, Serializable {
    private static final long serialVersionUID = 5826987063535505652L;
    private final KeyAnalyzer<? super K> keyAnalyzer;

    /* loaded from: classes9.dex */
    static abstract class BasicEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private static final long serialVersionUID = -944364551314110330L;
        protected K key;
        protected V value;

        BasicEntry(K key) {
            this.key = key;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public BasicEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> other = (Map.Entry) o;
            return AbstractBitwiseTrie.compare(this.key, other.getKey()) && AbstractBitwiseTrie.compare(this.value, other.getValue());
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
            return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() != null ? getValue().hashCode() : 0);
        }

        public V setKeyValue(K key, V value) {
            this.key = key;
            return setValue(value);
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            V previous = this.value;
            this.value = value;
            return previous;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean compare(Object a, Object b) {
        return Objects.equals(a, b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBitwiseTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        this.keyAnalyzer = (KeyAnalyzer) Objects.requireNonNull(keyAnalyzer, "keyAnalyzer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int bitIndex(K key, K foundKey) {
        return this.keyAnalyzer.bitIndex(key, 0, lengthInBits(key), foundKey, 0, lengthInBits(foundKey));
    }

    final int bitsPerElement() {
        return this.keyAnalyzer.bitsPerElement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public final K castKey(Object obj) {
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean compareKeys(K key, K other) {
        return key == null ? other == null : other != null && this.keyAnalyzer.compare(key, other) == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KeyAnalyzer<? super K> getKeyAnalyzer() {
        return this.keyAnalyzer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isBitSet(K key, int bitIndex, int lengthInBits) {
        if (key == null) {
            return false;
        }
        return this.keyAnalyzer.isBitSet(key, bitIndex, lengthInBits);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int lengthInBits(K key) {
        if (key == null) {
            return 0;
        }
        return this.keyAnalyzer.lengthInBits(key);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Trie[").append(size()).append("]={\n");
        for (Map.Entry<K, V> entry : entrySet()) {
            buffer.append("  ").append(entry).append(StringUtils.LF);
        }
        buffer.append("}\n");
        return buffer.toString();
    }
}
