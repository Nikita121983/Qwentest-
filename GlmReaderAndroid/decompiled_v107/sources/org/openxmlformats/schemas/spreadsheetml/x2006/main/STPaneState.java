package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STPaneState extends XmlString {
    public static final int INT_FROZEN = 2;
    public static final int INT_FROZEN_SPLIT = 3;
    public static final int INT_SPLIT = 1;
    public static final SimpleTypeFactory<STPaneState> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpanestateae58type");
    public static final SchemaType type = Factory.getType();
    public static final Enum SPLIT = Enum.forString("split");
    public static final Enum FROZEN = Enum.forString("frozen");
    public static final Enum FROZEN_SPLIT = Enum.forString("frozenSplit");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_FROZEN = 2;
        static final int INT_FROZEN_SPLIT = 3;
        static final int INT_SPLIT = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("split", 1), new Enum("frozen", 2), new Enum("frozenSplit", 3)});

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
