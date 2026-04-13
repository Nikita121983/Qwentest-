package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STRadarStyle extends XmlString {
    public static final int INT_FILLED = 3;
    public static final int INT_MARKER = 2;
    public static final int INT_STANDARD = 1;
    public static final SimpleTypeFactory<STRadarStyle> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stradarstyle3dc1type");
    public static final SchemaType type = Factory.getType();
    public static final Enum STANDARD = Enum.forString("standard");
    public static final Enum MARKER = Enum.forString("marker");
    public static final Enum FILLED = Enum.forString("filled");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FILLED = 3;
        static final int INT_MARKER = 2;
        static final int INT_STANDARD = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("standard", 1), new Enum("marker", 2), new Enum("filled", 3)});

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
