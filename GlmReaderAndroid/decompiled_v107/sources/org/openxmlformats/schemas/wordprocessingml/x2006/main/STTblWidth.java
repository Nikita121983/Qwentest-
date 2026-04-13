package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import kotlinx.coroutines.DebugKt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STTblWidth extends XmlString {
    public static final int INT_AUTO = 4;
    public static final int INT_DXA = 3;
    public static final int INT_NIL = 1;
    public static final int INT_PCT = 2;
    public static final SimpleTypeFactory<STTblWidth> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttblwidth3a30type");
    public static final SchemaType type = Factory.getType();
    public static final Enum NIL = Enum.forString("nil");
    public static final Enum PCT = Enum.forString("pct");
    public static final Enum DXA = Enum.forString("dxa");
    public static final Enum AUTO = Enum.forString(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTO = 4;
        static final int INT_DXA = 3;
        static final int INT_NIL = 1;
        static final int INT_PCT = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("nil", 1), new Enum("pct", 2), new Enum("dxa", 3), new Enum(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, 4)});

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
