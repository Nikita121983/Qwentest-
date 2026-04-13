package com.microsoft.schemas.vml;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes9.dex */
public interface STExt extends XmlString {
    public static final int INT_BACKWARD_COMPATIBLE = 3;
    public static final int INT_EDIT = 2;
    public static final int INT_VIEW = 1;
    public static final SimpleTypeFactory<STExt> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stext2fe5type");
    public static final SchemaType type = Factory.getType();
    public static final Enum VIEW = Enum.forString("view");
    public static final Enum EDIT = Enum.forString("edit");
    public static final Enum BACKWARD_COMPATIBLE = Enum.forString("backwardCompatible");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes9.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BACKWARD_COMPATIBLE = 3;
        static final int INT_EDIT = 2;
        static final int INT_VIEW = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("view", 1), new Enum("edit", 2), new Enum("backwardCompatible", 3)});

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
