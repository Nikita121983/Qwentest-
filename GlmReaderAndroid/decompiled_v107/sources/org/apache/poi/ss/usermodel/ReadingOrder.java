package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum ReadingOrder {
    CONTEXT,
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    public short getCode() {
        return (short) ordinal();
    }

    public static ReadingOrder forLong(long code) {
        if (code < 0 || code >= values().length) {
            throw new IllegalArgumentException("Invalid ReadingOrder code: " + code);
        }
        return values()[(int) code];
    }
}
