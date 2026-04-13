package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes11.dex */
public final class STEffectContainerType$Enum extends StringEnumAbstractBase {
    static final int INT_SIB = 1;
    static final int INT_TREE = 2;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STEffectContainerType$Enum[]{new STEffectContainerType$Enum("sib", 1), new STEffectContainerType$Enum("tree", 2)});

    public static STEffectContainerType$Enum forString(String s) {
        return (STEffectContainerType$Enum) table.forString(s);
    }

    public static STEffectContainerType$Enum forInt(int i) {
        return (STEffectContainerType$Enum) table.forInt(i);
    }

    private STEffectContainerType$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
