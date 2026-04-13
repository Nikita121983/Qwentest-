package com.microsoft.schemas.office.office;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes.dex */
public interface STConnectType extends XmlString {
    public static final int INT_CUSTOM = 4;
    public static final int INT_NONE = 1;
    public static final int INT_RECT = 2;
    public static final int INT_SEGMENTS = 3;
    public static final SimpleTypeFactory<STConnectType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stconnecttype97adtype");
    public static final SchemaType type = Factory.getType();
    public static final Enum NONE = Enum.forString("none");
    public static final Enum RECT = Enum.forString("rect");
    public static final Enum SEGMENTS = Enum.forString("segments");
    public static final Enum CUSTOM = Enum.forString("custom");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CUSTOM = 4;
        static final int INT_NONE = 1;
        static final int INT_RECT = 2;
        static final int INT_SEGMENTS = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("none", 1), new Enum("rect", 2), new Enum("segments", 3), new Enum("custom", 4)});

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
