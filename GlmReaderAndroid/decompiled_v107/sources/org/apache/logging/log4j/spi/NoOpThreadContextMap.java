package org.apache.logging.log4j.spi;

import java.util.HashMap;
import java.util.Map;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes10.dex */
public class NoOpThreadContextMap implements ThreadContextMap {
    public static final ThreadContextMap INSTANCE = new NoOpThreadContextMap();

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void clear() {
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap, org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean containsKey(final String key) {
        return false;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public String get(final String key) {
        return null;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getCopy() {
        return new HashMap();
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public Map<String, String> getImmutableMapOrNull() {
        return null;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap, org.apache.logging.log4j.util.ReadOnlyStringMap
    public boolean isEmpty() {
        return true;
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void put(final String key, final String value) {
    }

    @Override // org.apache.logging.log4j.spi.ThreadContextMap
    public void remove(final String key) {
    }
}
