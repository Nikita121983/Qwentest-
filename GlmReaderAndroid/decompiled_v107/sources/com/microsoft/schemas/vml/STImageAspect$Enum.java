package com.microsoft.schemas.vml;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes9.dex */
public final class STImageAspect$Enum extends StringEnumAbstractBase {
    static final int INT_AT_LEAST = 3;
    static final int INT_AT_MOST = 2;
    static final int INT_IGNORE = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STImageAspect$Enum[]{new STImageAspect$Enum("ignore", 1), new STImageAspect$Enum("atMost", 2), new STImageAspect$Enum("atLeast", 3)});

    public static STImageAspect$Enum forString(String s) {
        return (STImageAspect$Enum) table.forString(s);
    }

    public static STImageAspect$Enum forInt(int i) {
        return (STImageAspect$Enum) table.forInt(i);
    }

    private STImageAspect$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
