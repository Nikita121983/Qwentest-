package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STCryptProv extends XmlString {
    public static final int INT_CUSTOM = 3;
    public static final int INT_RSA_AES = 1;
    public static final int INT_RSA_FULL = 2;
    public static final SimpleTypeFactory<STCryptProv> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stcryptprov36a7type");
    public static final SchemaType type = Factory.getType();
    public static final Enum RSA_AES = Enum.forString("rsaAES");
    public static final Enum RSA_FULL = Enum.forString("rsaFull");
    public static final Enum CUSTOM = Enum.forString("custom");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_CUSTOM = 3;
        static final int INT_RSA_AES = 1;
        static final int INT_RSA_FULL = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("rsaAES", 1), new Enum("rsaFull", 2), new Enum("custom", 3)});

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
