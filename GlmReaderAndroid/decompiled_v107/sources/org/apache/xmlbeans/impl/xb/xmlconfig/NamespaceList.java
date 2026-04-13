package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface NamespaceList extends XmlAnySimpleType {
    public static final SimpleTypeFactory<NamespaceList> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "namespacelist20datype");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);

    /* loaded from: classes11.dex */
    public interface Member extends XmlToken {
        public static final int INT_ANY = 1;
        public static final ElementFactory<Member> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anonc6fftype");
        public static final SchemaType type = Factory.getType();
        public static final Enum ANY = Enum.forString("##any");

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        /* loaded from: classes11.dex */
        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_ANY = 1;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##any", 1)});

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

    /* loaded from: classes11.dex */
    public interface Member2 extends XmlAnySimpleType {
        public static final ElementFactory<Member2> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon5680type");
        public static final SchemaType type = Factory.getType();

        List getListValue();

        void setListValue(List<?> list);

        List xgetListValue();

        /* loaded from: classes11.dex */
        public interface Item extends XmlAnySimpleType {
            public static final ElementFactory<Item> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon0798type");
            public static final SchemaType type = Factory.getType();

            Object getObjectValue();

            SchemaType instanceType();

            void setObjectValue(Object obj);

            /* loaded from: classes11.dex */
            public interface Member extends XmlToken {
                public static final int INT_LOCAL = 1;
                public static final ElementFactory<Member> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon1dd3type");
                public static final SchemaType type = Factory.getType();
                public static final Enum LOCAL = Enum.forString("##local");

                StringEnumAbstractBase getEnumValue();

                void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

                /* loaded from: classes11.dex */
                public static final class Enum extends StringEnumAbstractBase {
                    static final int INT_LOCAL = 1;
                    private static final long serialVersionUID = 1;
                    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##local", 1)});

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
    }
}
