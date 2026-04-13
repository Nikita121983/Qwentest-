package org.apache.commons.codec;

import java.io.InputStream;

/* loaded from: classes9.dex */
public class Resources {
    public static InputStream getInputStream(String name) {
        InputStream inputStream = Resources.class.getResourceAsStream(name);
        if (inputStream == null) {
            throw new IllegalArgumentException("Unable to resolve required resource: " + name);
        }
        return inputStream;
    }

    @Deprecated
    public Resources() {
    }
}
