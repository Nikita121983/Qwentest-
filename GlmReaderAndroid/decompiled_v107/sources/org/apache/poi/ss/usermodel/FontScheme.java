package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum FontScheme {
    NONE(1),
    MAJOR(2),
    MINOR(3);

    private final int value;
    private static final FontScheme[] _table = {null, NONE, MAJOR, MINOR};

    FontScheme(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static FontScheme valueOf(int value) {
        return _table[value];
    }
}
