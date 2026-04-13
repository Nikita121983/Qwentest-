package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface DerivationSet extends XmlAnySimpleType {
    public static final SimpleTypeFactory<DerivationSet> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "derivationset037atype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Member2 extends XmlAnySimpleType {
        public static final ElementFactory<Member2> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon9394type");
        public static final SchemaType type = Factory.getType();

        List getListValue();

        void setListValue(List<?> list);

        List xgetListValue();
    }

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    /* loaded from: classes11.dex */
    public interface Member extends XmlToken {
        public static final int INT_ALL = 1;
        public static final ElementFactory<Member> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anoned75type");
        public static final SchemaType type = Factory.getType();
        public static final Enum ALL = Enum.forString("#all");

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        /* loaded from: classes11.dex */
        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_ALL = 1;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("#all", 1)});

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
}
