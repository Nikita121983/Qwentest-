package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;

/* loaded from: classes9.dex */
final class IndexUtils {
    static final int MAX_ARRAY_SIZE = 2147483639;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] ensureCapacityForAdd(int[] array, int index) {
        if (index >= array.length) {
            return Arrays.copyOf(array, (int) Math.min(2147483639L, Math.max(array.length * 2, index + 1)));
        }
        return array;
    }

    private IndexUtils() {
    }
}
