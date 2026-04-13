package org.apache.poi.xwpf.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum LineSpacingRule {
    AUTO(1),
    EXACT(2),
    AT_LEAST(3);

    private static final Map<Integer, LineSpacingRule> imap;
    private final int value;

    static {
        Map<Integer, LineSpacingRule> tempMap = new HashMap<>();
        for (LineSpacingRule p : values()) {
            tempMap.put(Integer.valueOf(p.getValue()), p);
        }
        imap = Collections.unmodifiableMap(tempMap);
    }

    LineSpacingRule(int val) {
        this.value = val;
    }

    public static LineSpacingRule valueOf(int type) {
        LineSpacingRule lineType = imap.get(Integer.valueOf(type));
        if (lineType == null) {
            throw new IllegalArgumentException("Unknown line type: " + type);
        }
        return lineType;
    }

    public int getValue() {
        return this.value;
    }
}
