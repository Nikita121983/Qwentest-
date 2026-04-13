package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STTickLblPos extends XmlString {
    public static final int INT_HIGH = 1;
    public static final int INT_LOW = 2;
    public static final int INT_NEXT_TO = 3;
    public static final int INT_NONE = 4;
    public static final SimpleTypeFactory<STTickLblPos> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stticklblposc551type");
    public static final SchemaType type = Factory.getType();
    public static final Enum HIGH = Enum.forString("high");
    public static final Enum LOW = Enum.forString("low");
    public static final Enum NEXT_TO = Enum.forString("nextTo");
    public static final Enum NONE = Enum.forString("none");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_HIGH = 1;
        static final int INT_LOW = 2;
        static final int INT_NEXT_TO = 3;
        static final int INT_NONE = 4;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("high", 1), new Enum("low", 2), new Enum("nextTo", 3), new Enum("none", 4)});

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
