package org.apache.logging.log4j.util;

/* loaded from: classes10.dex */
public interface StringMap extends ReadOnlyStringMap {
    void clear();

    boolean equals(final Object obj);

    void freeze();

    int hashCode();

    boolean isFrozen();

    void putAll(final ReadOnlyStringMap source);

    void putValue(final String key, final Object value);

    void remove(final String key);
}
