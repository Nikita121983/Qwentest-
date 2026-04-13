package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STRefMode extends XmlString {
    public static final int INT_A_1 = 1;
    public static final int INT_R_1_C_1 = 2;
    public static final SimpleTypeFactory<STRefMode> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strefmodee5a5type");
    public static final SchemaType type = Factory.getType();
    public static final Enum A_1 = Enum.forString("A1");
    public static final Enum R_1_C_1 = Enum.forString("R1C1");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_A_1 = 1;
        static final int INT_R_1_C_1 = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("A1", 1), new Enum("R1C1", 2)});

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
