package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STPTabAlignment$Enum extends StringEnumAbstractBase {
    static final int INT_CENTER = 2;
    static final int INT_LEFT = 1;
    static final int INT_RIGHT = 3;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STPTabAlignment$Enum[]{new STPTabAlignment$Enum("left", 1), new STPTabAlignment$Enum("center", 2), new STPTabAlignment$Enum("right", 3)});

    public static STPTabAlignment$Enum forString(String s) {
        return (STPTabAlignment$Enum) table.forString(s);
    }

    public static STPTabAlignment$Enum forInt(int i) {
        return (STPTabAlignment$Enum) table.forInt(i);
    }

    private STPTabAlignment$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
