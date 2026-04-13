package org.apache.poi;

/* loaded from: classes10.dex */
public class POIException extends Exception {
    private static final long serialVersionUID = 1;

    public POIException(String msg) {
        super(msg);
    }

    public POIException(Throwable cause) {
        super(cause);
    }

    public POIException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
