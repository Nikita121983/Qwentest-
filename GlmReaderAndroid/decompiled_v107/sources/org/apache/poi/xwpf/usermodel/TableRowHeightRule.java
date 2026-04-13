package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum TableRowHeightRule {
    AUTO(1),
    EXACT(2),
    AT_LEAST(3);

    private static final Map<Integer, TableRowHeightRule> imap;
    private final int value;

    static {
        Map<Integer, TableRowHeightRule> tempMap = new HashMap<>();
        for (TableRowHeightRule p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    TableRowHeightRule(int val) {
        this.value = val;
    }

    public static TableRowHeightRule valueOf(int type) {
        TableRowHeightRule err = imap.get(Integer.valueOf(type));
        if (err == null) {
            throw new IllegalArgumentException("Unknown table row height rule: " + type);
        }
        return err;
    }

    public int getValue() {
        return this.value;
    }
}
