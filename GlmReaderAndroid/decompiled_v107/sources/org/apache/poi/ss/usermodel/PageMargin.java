package org.apache.poi.ss.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum PageMargin {
    LEFT(0),
    RIGHT(1),
    TOP(2),
    BOTTOM(3),
    HEADER(4),
    FOOTER(5);

    private static final Map<Short, PageMargin> PAGE_MARGIN_BY_LEGACY_API_VALUE;
    private final short legacyApiValue;

    static {
        Map<Short, PageMargin> map = new HashMap<>();
        for (PageMargin margin : values()) {
            map.put(Short.valueOf(margin.legacyApiValue), margin);
        }
        PAGE_MARGIN_BY_LEGACY_API_VALUE = Collections.unmodifiableMap(map);
    }

    PageMargin(short legacyApiValue) {
        this.legacyApiValue = legacyApiValue;
    }

    public short getLegacyApiValue() {
        return this.legacyApiValue;
    }

    public static PageMargin getByShortValue(short legacyApiValue) {
        return PAGE_MARGIN_BY_LEGACY_API_VALUE.get(Short.valueOf(legacyApiValue));
    }
}
