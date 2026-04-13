package org.apache.commons.compress.compressors;

import org.apache.commons.compress.CompressException;

/* loaded from: classes9.dex */
public class CompressorException extends CompressException {
    private static final long serialVersionUID = -2932901310255908814L;

    public CompressorException(String message) {
        super(message);
    }

    public CompressorException(String message, Throwable cause) {
        super(message, cause);
    }
}
