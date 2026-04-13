package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes11.dex */
public final class STDirection$Enum extends StringEnumAbstractBase {
    static final int INT_HORZ = 1;
    static final int INT_VERT = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STDirection$Enum[]{new STDirection$Enum("horz", 1), new STDirection$Enum("vert", 2)});

    public static STDirection$Enum forString(String s) {
        return (STDirection$Enum) table.forString(s);
    }

    public static STDirection$Enum forInt(int i) {
        return (STDirection$Enum) table.forInt(i);
    }

    private STDirection$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
