package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum VerticalAlign {
    BASELINE(1),
    SUPERSCRIPT(2),
    SUBSCRIPT(3);

    private static final Map<Integer, VerticalAlign> imap;
    private final int value;

    static {
        Map<Integer, VerticalAlign> tempMap = new HashMap<>();
        for (VerticalAlign p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    VerticalAlign(int val) {
        this.value = val;
    }

    public static VerticalAlign valueOf(int type) {
        VerticalAlign align = imap.get(Integer.valueOf(type));
        if (align == null) {
            throw new IllegalArgumentException("Unknown vertical alignment: " + type);
        }
        return align;
    }

    public int getValue() {
        return this.value;
    }
}
