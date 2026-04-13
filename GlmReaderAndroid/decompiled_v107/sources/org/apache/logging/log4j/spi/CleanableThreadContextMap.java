package org.apache.logging.log4j.spi;

/* loaded from: classes10.dex */
public interface CleanableThreadContextMap extends ThreadContextMap2 {
    void removeAll(final Iterable<String> keys);
}
