package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STSdtDateMappingType extends XmlString {
    public static final int INT_DATE = 2;
    public static final int INT_DATE_TIME = 3;
    public static final int INT_TEXT = 1;
    public static final SimpleTypeFactory<STSdtDateMappingType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsdtdatemappingtype01a1type");
    public static final SchemaType type = Factory.getType();
    public static final Enum TEXT = Enum.forString("text");
    public static final Enum DATE = Enum.forString(XmlErrorCodes.DATE);
    public static final Enum DATE_TIME = Enum.forString("dateTime");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_DATE = 2;
        static final int INT_DATE_TIME = 3;
        static final int INT_TEXT = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("text", 1), new Enum(XmlErrorCodes.DATE, 2), new Enum("dateTime", 3)});

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
