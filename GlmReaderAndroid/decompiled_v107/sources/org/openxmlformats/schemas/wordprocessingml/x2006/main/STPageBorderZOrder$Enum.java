package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STPageBorderZOrder$Enum extends StringEnumAbstractBase {
    static final int INT_BACK = 2;
    static final int INT_FRONT = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STPageBorderZOrder$Enum[]{new STPageBorderZOrder$Enum("front", 1), new STPageBorderZOrder$Enum("back", 2)});

    public static STPageBorderZOrder$Enum forString(String s) {
        return (STPageBorderZOrder$Enum) table.forString(s);
    }

    public static STPageBorderZOrder$Enum forInt(int i) {
        return (STPageBorderZOrder$Enum) table.forInt(i);
    }

    private STPageBorderZOrder$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
