package org.apache.logging.log4j.spi;

import java.util.Map;
import org.apache.logging.log4j.util.StringMap;

/* loaded from: classes10.dex */
public interface ThreadContextMap2 extends ThreadContextMap {
    StringMap getReadOnlyContextData();

    void putAll(final Map<String, String> map);
}
