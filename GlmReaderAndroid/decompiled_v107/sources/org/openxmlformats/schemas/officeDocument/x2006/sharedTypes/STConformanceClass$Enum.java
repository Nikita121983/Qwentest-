package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes11.dex */
public final class STConformanceClass$Enum extends StringEnumAbstractBase {
    static final int INT_STRICT = 1;
    static final int INT_TRANSITIONAL = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STConformanceClass$Enum[]{new STConformanceClass$Enum("strict", 1), new STConformanceClass$Enum("transitional", 2)});

    public static STConformanceClass$Enum forString(String s) {
        return (STConformanceClass$Enum) table.forString(s);
    }

    public static STConformanceClass$Enum forInt(int i) {
        return (STConformanceClass$Enum) table.forInt(i);
    }

    private STConformanceClass$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
