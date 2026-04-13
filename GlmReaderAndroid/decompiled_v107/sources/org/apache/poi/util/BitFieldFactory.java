package org.apache.poi.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* loaded from: classes10.dex */
public class BitFieldFactory {
    private static final ConcurrentHashMap<Integer, BitField> instances = new ConcurrentHashMap<>();

    public static BitField getInstance(final int mask) {
        return instances.computeIfAbsent(Integer.valueOf(mask), new Function() { // from class: org.apache.poi.util.BitFieldFactory$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BitFieldFactory.lambda$getInstance$0(mask, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BitField lambda$getInstance$0(int mask, Integer k) {
        return new BitField(mask);
    }
}
