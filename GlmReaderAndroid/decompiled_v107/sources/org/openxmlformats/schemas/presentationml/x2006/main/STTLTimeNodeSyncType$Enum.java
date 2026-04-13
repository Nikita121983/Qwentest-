package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes11.dex */
public final class STTLTimeNodeSyncType$Enum extends StringEnumAbstractBase {
    static final int INT_CAN_SLIP = 1;
    static final int INT_LOCKED = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTLTimeNodeSyncType$Enum[]{new STTLTimeNodeSyncType$Enum("canSlip", 1), new STTLTimeNodeSyncType$Enum(CellUtil.LOCKED, 2)});

    public static STTLTimeNodeSyncType$Enum forString(String s) {
        return (STTLTimeNodeSyncType$Enum) table.forString(s);
    }

    public static STTLTimeNodeSyncType$Enum forInt(int i) {
        return (STTLTimeNodeSyncType$Enum) table.forInt(i);
    }

    private STTLTimeNodeSyncType$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
