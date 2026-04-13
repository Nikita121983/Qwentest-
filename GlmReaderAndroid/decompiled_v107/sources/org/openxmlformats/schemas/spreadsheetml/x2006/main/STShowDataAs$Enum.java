package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STShowDataAs$Enum extends StringEnumAbstractBase {
    static final int INT_DIFFERENCE = 2;
    static final int INT_INDEX = 9;
    static final int INT_NORMAL = 1;
    static final int INT_PERCENT = 3;
    static final int INT_PERCENT_DIFF = 4;
    static final int INT_PERCENT_OF_COL = 7;
    static final int INT_PERCENT_OF_ROW = 6;
    static final int INT_PERCENT_OF_TOTAL = 8;
    static final int INT_RUN_TOTAL = 5;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STShowDataAs$Enum[]{new STShowDataAs$Enum("normal", 1), new STShowDataAs$Enum("difference", 2), new STShowDataAs$Enum("percent", 3), new STShowDataAs$Enum("percentDiff", 4), new STShowDataAs$Enum("runTotal", 5), new STShowDataAs$Enum("percentOfRow", 6), new STShowDataAs$Enum("percentOfCol", 7), new STShowDataAs$Enum("percentOfTotal", 8), new STShowDataAs$Enum("index", 9)});

    public static STShowDataAs$Enum forString(String s) {
        return (STShowDataAs$Enum) table.forString(s);
    }

    public static STShowDataAs$Enum forInt(int i) {
        return (STShowDataAs$Enum) table.forInt(i);
    }

    private STShowDataAs$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
