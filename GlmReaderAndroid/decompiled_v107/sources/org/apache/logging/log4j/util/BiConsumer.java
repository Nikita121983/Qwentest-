package org.apache.logging.log4j.util;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface BiConsumer<K, V> extends java.util.function.BiConsumer<K, V> {
    @Override // java.util.function.BiConsumer
    void accept(K k, V v);
}
