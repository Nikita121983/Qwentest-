package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.xmlbeans.StringEnumAbstractBase;

/* loaded from: classes12.dex */
public final class STPTabLeader$Enum extends StringEnumAbstractBase {
    static final int INT_DOT = 2;
    static final int INT_HYPHEN = 3;
    static final int INT_MIDDLE_DOT = 5;
    static final int INT_NONE = 1;
    static final int INT_UNDERSCORE = 4;
    private static final long serialVersionUID = 1;
    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new STPTabLeader$Enum[]{new STPTabLeader$Enum("none", 1), new STPTabLeader$Enum("dot", 2), new STPTabLeader$Enum("hyphen", 3), new STPTabLeader$Enum("underscore", 4), new STPTabLeader$Enum("middleDot", 5)});

    public static STPTabLeader$Enum forString(String s) {
        return (STPTabLeader$Enum) table.forString(s);
    }

    public static STPTabLeader$Enum forInt(int i) {
        return (STPTabLeader$Enum) table.forInt(i);
    }

    private STPTabLeader$Enum(String s, int i) {
        super(s, i);
    }

    private Object readResolve() {
        return forInt(intValue());
    }
}
