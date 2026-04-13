package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STPivotAreaType$Enum extends StringEnumAbstractBase {
    static final int INT_ALL = 4;
    static final int INT_BUTTON = 6;
    static final int INT_DATA = 3;
    static final int INT_NONE = 1;
    static final int INT_NORMAL = 2;
    static final int INT_ORIGIN = 5;
    static final int INT_TOP_END = 7;
    static final int INT_TOP_RIGHT = 8;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STPivotAreaType$Enum[]{new STPivotAreaType$Enum("none", 1), new STPivotAreaType$Enum("normal", 2), new STPivotAreaType$Enum("data", 3), new STPivotAreaType$Enum("all", 4), new STPivotAreaType$Enum("origin", 5), new STPivotAreaType$Enum("button", 6), new STPivotAreaType$Enum("topEnd", 7), new STPivotAreaType$Enum("topRight", 8)});

    public static STPivotAreaType$Enum forString(String s) {
        return (STPivotAreaType$Enum) table.forString(s);
    }

    public static STPivotAreaType$Enum forInt(int i) {
        return (STPivotAreaType$Enum) table.forInt(i);
    }

    private STPivotAreaType$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
