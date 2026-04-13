package org.openxmlformats.schemas.drawingml.x2006.main;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STTextFontAlignType extends XmlToken {
    public static final int INT_AUTO = 1;
    public static final int INT_B = 5;
    public static final int INT_BASE = 4;
    public static final int INT_CTR = 3;
    public static final int INT_T = 2;
    public static final SimpleTypeFactory<STTextFontAlignType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextfontaligntypecb44type");
    public static final SchemaType type = Factory.getType();
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
    public static final Enum T = Enum.forString("t");
    public static final Enum CTR = Enum.forString("ctr");
    public static final Enum BASE = Enum.forString("base");
    public static final Enum B = Enum.forString("b");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 1;
        static final int INT_B = 5;
        static final int INT_BASE = 4;
        static final int INT_CTR = 3;
        static final int INT_T = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 1), new Enum("t", 2), new Enum("ctr", 3), new Enum("base", 4), new Enum("b", 5)});

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
