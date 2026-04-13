package org.apache.logging.log4j.util;

/* loaded from: classes10.dex */
public final class Cast {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(final Object o) {
        if (o == 0) {
            return null;
        }
        return o;
    }

    private Cast() {
    }
}
