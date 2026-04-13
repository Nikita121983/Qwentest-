package com.microsoft.schemas.vml;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes9.dex */
public final class STStrokeEndCap$Enum extends StringEnumAbstractBase {
    static final int INT_FLAT = 1;
    static final int INT_ROUND = 3;
    static final int INT_SQUARE = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STStrokeEndCap$Enum[]{new STStrokeEndCap$Enum("flat", 1), new STStrokeEndCap$Enum("square", 2), new STStrokeEndCap$Enum("round", 3)});

    public static STStrokeEndCap$Enum forString(String s) {
        return (STStrokeEndCap$Enum) table.forString(s);
    }

    public static STStrokeEndCap$Enum forInt(int i) {
        return (STStrokeEndCap$Enum) table.forInt(i);
    }

    private STStrokeEndCap$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
