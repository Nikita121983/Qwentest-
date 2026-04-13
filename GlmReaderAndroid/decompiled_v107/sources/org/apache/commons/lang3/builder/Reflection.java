package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.util.Objects;

/* loaded from: classes9.dex */
final class Reflection {
    Reflection() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getUnchecked(Field field, Object obj) {
        try {
            return ((Field) Objects.requireNonNull(field, "field")).get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
