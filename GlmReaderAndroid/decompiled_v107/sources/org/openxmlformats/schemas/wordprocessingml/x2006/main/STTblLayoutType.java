package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STTblLayoutType extends XmlString {
    public static final int INT_AUTOFIT = 2;
    public static final int INT_FIXED = 1;
    public static final SimpleTypeFactory<STTblLayoutType> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttbllayouttype5040type");
    public static final SchemaType type = Factory.getType();
    public static final Enum FIXED = Enum.forString("fixed");
    public static final Enum AUTOFIT = Enum.forString("autofit");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_AUTOFIT = 2;
        static final int INT_FIXED = 1;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("fixed", 1), new Enum("autofit", 2)});

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
