package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum ParagraphAlignment {
    START(1),
    CENTER(2),
    END(3),
    BOTH(4),
    MEDIUM_KASHIDA(5),
    DISTRIBUTE(6),
    NUM_TAB(7),
    HIGH_KASHIDA(8),
    LOW_KASHIDA(9),
    THAI_DISTRIBUTE(10),
    LEFT(11),
    RIGHT(12);

    private static final Map<Integer, ParagraphAlignment> imap = new HashMap();
    private final int value;

    static {
        for (ParagraphAlignment p : values()) {
            imap.put(Integer.valueOf(p.getValue()), p);
        }
    }

    ParagraphAlignment(int val) {
        this.value = val;
    }

    public static ParagraphAlignment valueOf(int type) {
        ParagraphAlignment err = imap.get(Integer.valueOf(type));
        if (err == null) {
            throw new IllegalArgumentException("Unknown paragraph alignment: " + type);
        }
        return err;
    }

    public int getValue() {
        return this.value;
    }
}
