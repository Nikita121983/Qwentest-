package org.apache.logging.log4j.util;

/* loaded from: classes10.dex */
public interface IndexedReadOnlyStringMap extends ReadOnlyStringMap {
    String getKeyAt(final int index);

    <V> V getValueAt(final int index);

    int indexOfKey(final String key);
}
