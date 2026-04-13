package org.apache.commons.compress.harmony.pack200;

import org.apache.commons.compress.CompressException;

/* loaded from: classes9.dex */
public class Pack200Exception extends CompressException {
    private static final long serialVersionUID = 5168177401552611803L;

    public Pack200Exception(String message) {
        super(message);
    }

    public Pack200Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
