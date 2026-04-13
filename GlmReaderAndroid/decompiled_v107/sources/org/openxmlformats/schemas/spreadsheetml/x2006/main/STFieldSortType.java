package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STFieldSortType extends XmlString {
    public static final int INT_ASCENDING = 2;
    public static final int INT_DESCENDING = 3;
    public static final int INT_MANUAL = 1;
    public static final SimpleTypeFactory<STFieldSortType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stfieldsorttypee6a1type");
    public static final SchemaType type = Factory.getType();
    public static final Enum MANUAL = Enum.forString("manual");
    public static final Enum ASCENDING = Enum.forString("ascending");
    public static final Enum DESCENDING = Enum.forString("descending");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_ASCENDING = 2;
        static final int INT_DESCENDING = 3;
        static final int INT_MANUAL = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("manual", 1), new Enum("ascending", 2), new Enum("descending", 3)});

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
