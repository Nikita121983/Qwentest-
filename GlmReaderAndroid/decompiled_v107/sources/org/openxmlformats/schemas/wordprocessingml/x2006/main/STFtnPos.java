package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STFtnPos extends XmlString {
    public static final int INT_BENEATH_TEXT = 2;
    public static final int INT_DOC_END = 4;
    public static final int INT_PAGE_BOTTOM = 1;
    public static final int INT_SECT_END = 3;
    public static final SimpleTypeFactory<STFtnPos> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stftnposdc44type");
    public static final SchemaType type = Factory.getType();
    public static final Enum PAGE_BOTTOM = Enum.forString("pageBottom");
    public static final Enum BENEATH_TEXT = Enum.forString("beneathText");
    public static final Enum SECT_END = Enum.forString("sectEnd");
    public static final Enum DOC_END = Enum.forString("docEnd");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BENEATH_TEXT = 2;
        static final int INT_DOC_END = 4;
        static final int INT_PAGE_BOTTOM = 1;
        static final int INT_SECT_END = 3;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("pageBottom", 1), new Enum("beneathText", 2), new Enum("sectEnd", 3), new Enum("docEnd", 4)});

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
