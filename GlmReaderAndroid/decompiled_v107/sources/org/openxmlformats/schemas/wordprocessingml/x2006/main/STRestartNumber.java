package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STRestartNumber extends XmlString {
    public static final int INT_CONTINUOUS = 1;
    public static final int INT_EACH_PAGE = 3;
    public static final int INT_EACH_SECT = 2;
    public static final SimpleTypeFactory<STRestartNumber> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strestartnumber11aatype");
    public static final SchemaType type = Factory.getType();
    public static final Enum CONTINUOUS = Enum.forString("continuous");
    public static final Enum EACH_SECT = Enum.forString("eachSect");
    public static final Enum EACH_PAGE = Enum.forString("eachPage");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes12.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CONTINUOUS = 1;
        static final int INT_EACH_PAGE = 3;
        static final int INT_EACH_SECT = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("continuous", 1), new Enum("eachSect", 2), new Enum("eachPage", 3)});

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
