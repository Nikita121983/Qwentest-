package com.microsoft.schemas.office.office;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes.dex */
public interface STInsetMode extends XmlString {
    public static final int INT_AUTO = 1;
    public static final int INT_CUSTOM = 2;
    public static final SimpleTypeFactory<STInsetMode> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stinsetmode3b89type");
    public static final SchemaType type = Factory.getType();
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum CUSTOM = Enum.forString("custom");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 1;
        static final int INT_CUSTOM = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 1), new Enum("custom", 2)});

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
