package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum PrintOrientation {
    DEFAULT(1),
    PORTRAIT(2),
    LANDSCAPE(3);

    private static PrintOrientation[] _table = new PrintOrientation[4];
    private int orientation;

    static {
        for (PrintOrientation c : values()) {
            _table[c.getValue()] = c;
        }
    }

    PrintOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getValue() {
        return this.orientation;
    }

    public static PrintOrientation valueOf(int value) {
        return _table[value];
    }
}
