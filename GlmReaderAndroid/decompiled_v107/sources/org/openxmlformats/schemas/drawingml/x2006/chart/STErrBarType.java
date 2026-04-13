package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STErrBarType extends XmlString {
    public static final int INT_BOTH = 1;
    public static final int INT_MINUS = 2;
    public static final int INT_PLUS = 3;
    public static final SimpleTypeFactory<STErrBarType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sterrbartypea2a4type");
    public static final SchemaType type = Factory.getType();
    public static final Enum BOTH = Enum.forString("both");
    public static final Enum MINUS = Enum.forString("minus");
    public static final Enum PLUS = Enum.forString("plus");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BOTH = 1;
        static final int INT_MINUS = 2;
        static final int INT_PLUS = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("both", 1), new Enum("minus", 2), new Enum("plus", 3)});

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
