package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum FontUnderline {
    SINGLE(1),
    DOUBLE(2),
    SINGLE_ACCOUNTING(3),
    DOUBLE_ACCOUNTING(4),
    NONE(5);

    private static FontUnderline[] _table = new FontUnderline[6];
    private int value;

    static {
        for (FontUnderline c : values()) {
            _table[c.getValue()] = c;
        }
    }

    FontUnderline(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public byte getByteValue() {
        switch (this) {
            case DOUBLE:
                return (byte) 2;
            case DOUBLE_ACCOUNTING:
                return (byte) 34;
            case SINGLE_ACCOUNTING:
                return (byte) 33;
            case NONE:
                return (byte) 0;
            case SINGLE:
                return (byte) 1;
            default:
                return (byte) 1;
        }
    }

    public static FontUnderline valueOf(int value) {
        return _table[value];
    }

    public static FontUnderline valueOf(byte value) {
        switch (value) {
            case 1:
                FontUnderline val = SINGLE;
                return val;
            case 2:
                FontUnderline val2 = DOUBLE;
                return val2;
            case 33:
                FontUnderline val3 = SINGLE_ACCOUNTING;
                return val3;
            case 34:
                FontUnderline val4 = DOUBLE_ACCOUNTING;
                return val4;
            default:
                FontUnderline val5 = NONE;
                return val5;
        }
    }
}
