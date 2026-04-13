package org.apache.poi.hpsf;

/* loaded from: classes10.dex */
public class HPSFException extends Exception {
    private Throwable reason;

    public HPSFException() {
    }

    public HPSFException(String msg) {
        super(msg);
    }

    public HPSFException(Throwable reason) {
        this.reason = reason;
    }

    public HPSFException(String msg, Throwable reason) {
        super(msg);
        this.reason = reason;
    }

    public Throwable getReason() {
        return this.reason;
    }
}
