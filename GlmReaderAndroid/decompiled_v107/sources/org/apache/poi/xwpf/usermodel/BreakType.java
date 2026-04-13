package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum BreakType {
    PAGE(1),
    COLUMN(2),
    TEXT_WRAPPING(3);

    private static final Map<Integer, BreakType> imap;
    private final int value;

    static {
        Map<Integer, BreakType> tempMap = new HashMap<>();
        for (BreakType p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    BreakType(int val) {
        this.value = val;
    }

    public static BreakType valueOf(int type) {
        BreakType bType = imap.get(Integer.valueOf(type));
        if (bType == null) {
            throw new IllegalArgumentException("Unknown break type: " + type);
        }
        return bType;
    }

    public int getValue() {
        return this.value;
    }
}
