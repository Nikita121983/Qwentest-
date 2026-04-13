package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum TextAlignment {
    TOP(1),
    CENTER(2),
    BASELINE(3),
    BOTTOM(4),
    AUTO(5);

    private static final Map<Integer, TextAlignment> imap;
    private final int value;

    static {
        Map<Integer, TextAlignment> tempMap = new HashMap<>();
        for (TextAlignment p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    TextAlignment(int val) {
        this.value = val;
    }

    public static TextAlignment valueOf(int type) {
        TextAlignment align = imap.get(Integer.valueOf(type));
        if (align == null) {
            throw new IllegalArgumentException("Unknown text alignment: " + type);
        }
        return align;
    }

    public int getValue() {
        return this.value;
    }
}
