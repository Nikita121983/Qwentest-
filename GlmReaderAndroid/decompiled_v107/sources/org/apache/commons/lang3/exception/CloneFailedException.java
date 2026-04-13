package org.apache.commons.lang3.exception;

/* loaded from: classes9.dex */
public class CloneFailedException extends RuntimeException {
    private static final long serialVersionUID = 20091223;

    public CloneFailedException(String message) {
        super(message);
    }

    public CloneFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CloneFailedException(Throwable cause) {
        super(cause);
    }
}
