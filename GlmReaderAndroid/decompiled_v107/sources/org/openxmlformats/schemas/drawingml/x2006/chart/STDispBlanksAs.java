package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STDispBlanksAs extends XmlString {
    public static final int INT_GAP = 2;
    public static final int INT_SPAN = 1;
    public static final int INT_ZERO = 3;
    public static final SimpleTypeFactory<STDispBlanksAs> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdispblanksas3a59type");
    public static final SchemaType type = Factory.getType();
    public static final Enum SPAN = Enum.forString("span");
    public static final Enum GAP = Enum.forString("gap");
    public static final Enum ZERO = Enum.forString("zero");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_GAP = 2;
        static final int INT_SPAN = 1;
        static final int INT_ZERO = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("span", 1), new Enum("gap", 2), new Enum("zero", 3)});

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
