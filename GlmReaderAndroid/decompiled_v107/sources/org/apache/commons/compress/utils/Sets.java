package org.apache.commons.compress.utils;

import java.util.Collections;
import java.util.HashSet;

/* loaded from: classes9.dex */
public class Sets {
    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> set = new HashSet<>(elements != null ? elements.length : 0);
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    private Sets() {
    }
}
