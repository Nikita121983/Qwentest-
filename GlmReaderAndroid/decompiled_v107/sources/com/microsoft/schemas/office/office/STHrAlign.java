package com.microsoft.schemas.office.office;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes.dex */
public interface STHrAlign extends XmlString {
    public static final int INT_CENTER = 3;
    public static final int INT_LEFT = 1;
    public static final int INT_RIGHT = 2;
    public static final SimpleTypeFactory<STHrAlign> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthralignfb04type");
    public static final SchemaType type = Factory.getType();
    public static final Enum LEFT = Enum.forString("left");
    public static final Enum RIGHT = Enum.forString("right");
    public static final Enum CENTER = Enum.forString("center");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CENTER = 3;
        static final int INT_LEFT = 1;
        static final int INT_RIGHT = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("left", 1), new Enum("right", 2), new Enum("center", 3)});

        public static Enum forString(String s) {
            return (Enum) table.forString(s);
        }

        public static Enum forInt(int i) {
            return (Enum) table.forInt(i);
        }

        private Enum(String s, int i) {
            super(s, i);
        }

        private Object readResolve() {
            return forInt(intValue());
        }
    }
}
