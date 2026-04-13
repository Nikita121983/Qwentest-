package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STUnderlineValues extends XmlString {
    public static final int INT_DOUBLE = 2;
    public static final int INT_DOUBLE_ACCOUNTING = 4;
    public static final int INT_NONE = 5;
    public static final int INT_SINGLE = 1;
    public static final int INT_SINGLE_ACCOUNTING = 3;
    public static final SimpleTypeFactory<STUnderlineValues> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stunderlinevaluesb6ddtype");
    public static final SchemaType type = Factory.getType();
    public static final Enum SINGLE = Enum.forString("single");
    public static final Enum DOUBLE = Enum.forString(XmlErrorCodes.DOUBLE);
    public static final Enum SINGLE_ACCOUNTING = Enum.forString("singleAccounting");
    public static final Enum DOUBLE_ACCOUNTING = Enum.forString("doubleAccounting");
    public static final Enum NONE = Enum.forString("none");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_DOUBLE = 2;
        static final int INT_DOUBLE_ACCOUNTING = 4;
        static final int INT_NONE = 5;
        static final int INT_SINGLE = 1;
        static final int INT_SINGLE_ACCOUNTING = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("single", 1), new Enum(XmlErrorCodes.DOUBLE, 2), new Enum("singleAccounting", 3), new Enum("doubleAccounting", 4), new Enum("none", 5)});

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
