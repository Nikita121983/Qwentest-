package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum TableRowAlign {
    LEFT(3),
    START(5),
    CENTER(1),
    RIGHT(4),
    END(2);

    private static final Map<Integer, TableRowAlign> imap;
    private final int value;

    static {
        Map<Integer, TableRowAlign> tempMap = new HashMap<>();
        for (TableRowAlign p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    TableRowAlign(int val) {
        this.value = val;
    }

    public static TableRowAlign valueOf(int type) {
        TableRowAlign err = imap.get(Integer.valueOf(type));
        if (err == null) {
            throw new IllegalArgumentException("Unknown table row alignment: " + type);
        }
        return err;
    }

    public int getValue() {
        return this.value;
    }
}
