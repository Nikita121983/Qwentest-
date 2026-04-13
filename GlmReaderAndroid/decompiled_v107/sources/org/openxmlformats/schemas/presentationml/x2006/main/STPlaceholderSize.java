package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STPlaceholderSize extends XmlToken {
    public static final int INT_FULL = 1;
    public static final int INT_HALF = 2;
    public static final int INT_QUARTER = 3;
    public static final SimpleTypeFactory<STPlaceholderSize> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stplaceholdersize914btype");
    public static final SchemaType type = Factory.getType();
    public static final Enum FULL = Enum.forString("full");
    public static final Enum HALF = Enum.forString("half");
    public static final Enum QUARTER = Enum.forString("quarter");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FULL = 1;
        static final int INT_HALF = 2;
        static final int INT_QUARTER = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("full", 1), new Enum("half", 2), new Enum("quarter", 3)});

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
