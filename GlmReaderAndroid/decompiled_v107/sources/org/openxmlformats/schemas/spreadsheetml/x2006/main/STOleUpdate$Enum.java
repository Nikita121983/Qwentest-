package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STOleUpdate$Enum extends StringEnumAbstractBase {
    static final int INT_OLEUPDATE_ALWAYS = 1;
    static final int INT_OLEUPDATE_ONCALL = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STOleUpdate$Enum[]{new STOleUpdate$Enum("OLEUPDATE_ALWAYS", 1), new STOleUpdate$Enum("OLEUPDATE_ONCALL", 2)});

    public static STOleUpdate$Enum forString(String s) {
        return (STOleUpdate$Enum) table.forString(s);
    }

    public static STOleUpdate$Enum forInt(int i) {
        return (STOleUpdate$Enum) table.forInt(i);
    }

    private STOleUpdate$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
