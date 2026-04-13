package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STOrientation extends XmlString {
    public static final int INT_MAX_MIN = 1;
    public static final int INT_MIN_MAX = 2;
    public static final SimpleTypeFactory<STOrientation> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "storientationc326type");
    public static final SchemaType type = Factory.getType();
    public static final Enum MAX_MIN = Enum.forString("maxMin");
    public static final Enum MIN_MAX = Enum.forString("minMax");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_MAX_MIN = 1;
        static final int INT_MIN_MAX = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("maxMin", 1), new Enum("minMax", 2)});

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
