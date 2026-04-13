package com.microsoft.schemas.vml;

import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes9.dex */
public final class STStrokeArrowLength$Enum extends StringEnumAbstractBase {
    static final int INT_LONG = 3;
    static final int INT_MEDIUM = 2;
    static final int INT_SHORT = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STStrokeArrowLength$Enum[]{new STStrokeArrowLength$Enum("short", 1), new STStrokeArrowLength$Enum("medium", 2), new STStrokeArrowLength$Enum(XmlErrorCodes.LONG, 3)});

    public static STStrokeArrowLength$Enum forString(String s) {
        return (STStrokeArrowLength$Enum) table.forString(s);
    }

    public static STStrokeArrowLength$Enum forInt(int i) {
        return (STStrokeArrowLength$Enum) table.forInt(i);
    }

    private STStrokeArrowLength$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
