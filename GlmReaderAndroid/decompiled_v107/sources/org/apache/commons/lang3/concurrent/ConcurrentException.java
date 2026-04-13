package org.apache.commons.lang3.concurrent;

/* loaded from: classes9.dex */
public class ConcurrentException extends Exception {
    private static final long serialVersionUID = 6622707671812226130L;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConcurrentException() {
    }

    public ConcurrentException(String msg, Throwable cause) {
        super(msg, ConcurrentUtils.checkedException(cause));
    }

    public ConcurrentException(Throwable cause) {
        super(ConcurrentUtils.checkedException(cause));
    }
}
