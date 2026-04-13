package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface FormChoice extends XmlNMTOKEN {
    public static final int INT_QUALIFIED = 1;
    public static final int INT_UNQUALIFIED = 2;
    public static final SimpleTypeFactory<FormChoice> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "formchoicef2aetype");
    public static final SchemaType type = Factory.getType();
    public static final Enum QUALIFIED = Enum.forString("qualified");
    public static final Enum UNQUALIFIED = Enum.forString("unqualified");

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    /* loaded from: classes11.dex */
    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_QUALIFIED = 1;
        static final int INT_UNQUALIFIED = 2;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("qualified", 1), new Enum("unqualified", 2)});

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
