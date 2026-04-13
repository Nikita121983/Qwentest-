package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum BreakClear {
    NONE(1),
    LEFT(2),
    RIGHT(3),
    ALL(4);

    private static final Map<Integer, BreakClear> imap = new HashMap();
    private final int value;

    static {
        for (BreakClear p : values()) {
            imap.put(Integer.valueOf(p.getValue()), p);
        }
    }

    BreakClear(int val) {
        this.value = val;
    }

    public static BreakClear valueOf(int type) {
        BreakClear bType = imap.get(Integer.valueOf(type));
        if (bType == null) {
            throw new IllegalArgumentException("Unknown break clear type: " + type);
        }
        return bType;
    }

    public int getValue() {
        return this.value;
    }
}
