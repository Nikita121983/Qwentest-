package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

/* loaded from: classes10.dex */
public enum TableWidthType {
    AUTO(STTblWidth.AUTO),
    DXA(STTblWidth.DXA),
    NIL(STTblWidth.NIL),
    PCT(STTblWidth.PCT);

    private STTblWidth.Enum type;

    TableWidthType(STTblWidth.Enum type) {
        this.type = STTblWidth.NIL;
        this.type = type;
    }

    @Internal
    public STTblWidth.Enum getStWidthType() {
        return this.type;
    }
}
