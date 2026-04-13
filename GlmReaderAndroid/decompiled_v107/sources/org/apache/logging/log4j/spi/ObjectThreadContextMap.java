package org.apache.logging.log4j.spi;

import java.util.Map;

/* loaded from: classes10.dex */
public interface ObjectThreadContextMap extends CleanableThreadContextMap {
    <V> V getValue(String key);

    <V> void putAllValues(Map<String, V> values);

    <V> void putValue(String key, V value);
}
