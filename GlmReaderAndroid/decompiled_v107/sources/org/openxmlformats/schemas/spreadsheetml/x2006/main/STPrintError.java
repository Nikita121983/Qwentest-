package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STPrintError extends XmlString {
    public static final int INT_BLANK = 2;
    public static final int INT_DASH = 3;
    public static final int INT_DISPLAYED = 1;
    public static final int INT_NA = 4;
    public static final SimpleTypeFactory<STPrintError> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stprinterror4934type");
    public static final SchemaType type = Factory.getType();
    public static final Enum DISPLAYED = Enum.forString("displayed");
    public static final Enum BLANK = Enum.forString("blank");
    public static final Enum DASH = Enum.forString("dash");
    public static final Enum NA = Enum.forString("NA");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BLANK = 2;
        static final int INT_DASH = 3;
        static final int INT_DISPLAYED = 1;
        static final int INT_NA = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("displayed", 1), new Enum("blank", 2), new Enum("dash", 3), new Enum("NA", 4)});

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
