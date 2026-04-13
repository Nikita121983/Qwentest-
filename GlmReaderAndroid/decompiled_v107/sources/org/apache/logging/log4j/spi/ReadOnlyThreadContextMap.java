package org.apache.logging.log4j.spi;

import java.util.Map;
import org.apache.logging.log4j.util.StringMap;

/* loaded from: classes10.dex */
public interface ReadOnlyThreadContextMap {
    void clear();

    boolean containsKey(final String key);

    String get(final String key);

    Map<String, String> getCopy();

    Map<String, String> getImmutableMapOrNull();

    StringMap getReadOnlyContextData();

    boolean isEmpty();
}
