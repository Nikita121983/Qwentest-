package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes11.dex */
public final class STAlignV$Enum extends StringEnumAbstractBase {
    static final int INT_BOTTOM = 2;
    static final int INT_CENTER = 3;
    static final int INT_INSIDE = 4;
    static final int INT_OUTSIDE = 5;
    static final int INT_TOP = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STAlignV$Enum[]{new STAlignV$Enum("top", 1), new STAlignV$Enum("bottom", 2), new STAlignV$Enum("center", 3), new STAlignV$Enum("inside", 4), new STAlignV$Enum("outside", 5)});

    public static STAlignV$Enum forString(String s) {
        return (STAlignV$Enum) table.forString(s);
    }

    public static STAlignV$Enum forInt(int i) {
        return (STAlignV$Enum) table.forInt(i);
    }

    private STAlignV$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
