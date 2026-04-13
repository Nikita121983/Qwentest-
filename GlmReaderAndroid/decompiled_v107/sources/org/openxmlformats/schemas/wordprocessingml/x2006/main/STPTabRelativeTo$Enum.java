package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STPTabRelativeTo$Enum extends StringEnumAbstractBase {
    static final int INT_INDENT = 2;
    static final int INT_MARGIN = 1;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STPTabRelativeTo$Enum[]{new STPTabRelativeTo$Enum("margin", 1), new STPTabRelativeTo$Enum("indent", 2)});

    public static STPTabRelativeTo$Enum forString(String s) {
        return (STPTabRelativeTo$Enum) table.forString(s);
    }

    public static STPTabRelativeTo$Enum forInt(int i) {
        return (STPTabRelativeTo$Enum) table.forInt(i);
    }

    private STPTabRelativeTo$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
