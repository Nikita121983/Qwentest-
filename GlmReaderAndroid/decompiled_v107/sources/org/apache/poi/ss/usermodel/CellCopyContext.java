package org.apache.poi.ss.usermodel;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public class CellCopyContext {
    private final Map<CellStyle, CellStyle> styleMap = new HashMap();

    public CellStyle getMappedStyle(CellStyle srcStyle) {
        return this.styleMap.get(srcStyle);
    }

    public void putMappedStyle(CellStyle srcStyle, CellStyle mappedStyle) {
        this.styleMap.put(srcStyle, mappedStyle);
    }
}
