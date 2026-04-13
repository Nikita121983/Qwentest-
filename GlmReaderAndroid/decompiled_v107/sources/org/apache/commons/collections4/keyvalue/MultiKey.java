package org.apache.commons.collections4.keyvalue;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes9.dex */
public class MultiKey<K> implements Serializable {
    private static final long serialVersionUID = 4465448607415788805L;
    private transient int hashCode;
    private final K[] keys;

    private static <T> Class<? extends T> getClass(T t) {
        return t == null ? Object.class : (Class<? extends T>) t.getClass();
    }

    @SafeVarargs
    private static <T> Class<? extends T> getComponentType(T... values) {
        if (values == null) {
            return Object.class;
        }
        Class prevClass = values.length > 0 ? getClass(values[0]) : Object.class;
        for (int i = 1; i < values.length; i++) {
            Class<? extends T> classI = getClass(values[i]);
            if (prevClass != classI) {
                return Object.class;
            }
            prevClass = classI;
        }
        return prevClass;
    }

    private static <T> T[] newArray(T t, T t2) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) getComponentType(t, t2), 2));
        tArr[0] = t;
        tArr[1] = t2;
        return tArr;
    }

    private static <T> T[] newArray(T t, T t2, T t3) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) getComponentType(t, t2, t3), 3));
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        return tArr;
    }

    private static <T> T[] newArray(T t, T t2, T t3, T t4) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) getComponentType(t, t2, t3, t4), 4));
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        tArr[3] = t4;
        return tArr;
    }

    private static <T> T[] newArray(T t, T t2, T t3, T t4, T t5) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) getComponentType(t, t2, t3, t4, t5), 5));
        tArr[0] = t;
        tArr[1] = t2;
        tArr[2] = t3;
        tArr[3] = t4;
        tArr[4] = t5;
        return tArr;
    }

    public MultiKey(K key1, K key2) {
        this(newArray(key1, key2), false);
    }

    public MultiKey(K key1, K key2, K key3) {
        this(newArray(key1, key2, key3), false);
    }

    public MultiKey(K key1, K key2, K key3, K key4) {
        this(newArray(key1, key2, key3, key4), false);
    }

    public MultiKey(K key1, K key2, K key3, K key4, K key5) {
        this(newArray(key1, key2, key3, key4, key5), false);
    }

    public MultiKey(K[] keys) {
        this((Object[]) keys, true);
    }

    public MultiKey(K[] kArr, boolean z) {
        Objects.requireNonNull(kArr, "keys");
        this.keys = z ? (K[]) ((Object[]) kArr.clone()) : kArr;
        calculateHashCode(kArr);
    }

    private void calculateHashCode(Object[] keys) {
        int total = 0;
        for (Object key : keys) {
            if (key != null) {
                total ^= key.hashCode();
            }
        }
        this.hashCode = total;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof MultiKey) {
            MultiKey<?> otherMulti = (MultiKey) other;
            return Arrays.equals(this.keys, otherMulti.keys);
        }
        return false;
    }

    public K getKey(int index) {
        return this.keys[index];
    }

    public K[] getKeys() {
        return (K[]) ((Object[]) this.keys.clone());
    }

    public int hashCode() {
        return this.hashCode;
    }

    protected Object readResolve() {
        calculateHashCode(this.keys);
        return this;
    }

    public int size() {
        return this.keys.length;
    }

    public String toString() {
        return "MultiKey" + Arrays.toString(this.keys);
    }
}
