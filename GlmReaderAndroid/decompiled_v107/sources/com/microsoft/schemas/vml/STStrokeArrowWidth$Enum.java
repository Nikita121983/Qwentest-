package com.microsoft.schemas.vml;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes9.dex */
public final class STStrokeArrowWidth$Enum extends StringEnumAbstractBase {
    static final int INT_MEDIUM = 2;
    static final int INT_NARROW = 1;
    static final int INT_WIDE = 3;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STStrokeArrowWidth$Enum[]{new STStrokeArrowWidth$Enum("narrow", 1), new STStrokeArrowWidth$Enum("medium", 2), new STStrokeArrowWidth$Enum("wide", 3)});

    public static STStrokeArrowWidth$Enum forString(String s) {
        return (STStrokeArrowWidth$Enum) table.forString(s);
    }

    public static STStrokeArrowWidth$Enum forInt(int i) {
        return (STStrokeArrowWidth$Enum) table.forInt(i);
    }

    private STStrokeArrowWidth$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
