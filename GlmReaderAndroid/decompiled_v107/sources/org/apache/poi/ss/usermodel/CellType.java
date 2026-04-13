package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum CellType {
    _NONE(-1),
    NUMERIC(0),
    STRING(1),
    FORMULA(2),
    BLANK(3),
    BOOLEAN(4),
    ERROR(5);


    @Deprecated
    private final int code;

    CellType(int code) {
        this.code = code;
    }

    @Deprecated
    public static CellType forInt(int code) {
        for (CellType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CellType code: " + code);
    }

    @Deprecated
    public int getCode() {
        return this.code;
    }
}
