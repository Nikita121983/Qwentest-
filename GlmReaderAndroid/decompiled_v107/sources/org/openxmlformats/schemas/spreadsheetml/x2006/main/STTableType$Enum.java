package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STTableType$Enum extends StringEnumAbstractBase {
    static final int INT_QUERY_TABLE = 3;
    static final int INT_WORKSHEET = 1;
    static final int INT_XML = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STTableType$Enum[]{new STTableType$Enum("worksheet", 1), new STTableType$Enum("xml", 2), new STTableType$Enum("queryTable", 3)});

    public static STTableType$Enum forString(String s) {
        return (STTableType$Enum) table.forString(s);
    }

    public static STTableType$Enum forInt(int i) {
        return (STTableType$Enum) table.forInt(i);
    }

    private STTableType$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
