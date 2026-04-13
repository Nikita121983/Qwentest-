package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum HorizontalAlignment {
    GENERAL,
    LEFT,
    CENTER,
    RIGHT,
    FILL,
    JUSTIFY,
    CENTER_SELECTION,
    DISTRIBUTED;

    public short getCode() {
        return (short) ordinal();
    }

    public static HorizontalAlignment forInt(int code) {
        if (code < 0 || code >= values().length) {
            throw new IllegalArgumentException("Invalid HorizontalAlignment code: " + code);
        }
        return values()[code];
    }
}
