package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STDisplacedByCustomXml extends XmlString {
    public static final int INT_NEXT = 1;
    public static final int INT_PREV = 2;
    public static final SimpleTypeFactory<STDisplacedByCustomXml> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stdisplacedbycustomxml72d4type");
    public static final SchemaType type = Factory.getType();
    public static final Enum NEXT = Enum.forString("next");
    public static final Enum PREV = Enum.forString("prev");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_NEXT = 1;
        static final int INT_PREV = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("next", 1), new Enum("prev", 2)});

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
