package org.apache.xmlbeans.impl.xb.xmlschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLLANG.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface LangAttribute extends XmlObject {
    public static final DocumentFactory<LangAttribute> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "lange126attrtypetype");
    public static final SchemaType type = Factory.getType();

    String getLang();

    boolean isSetLang();

    void setLang(String str);

    void unsetLang();

    Lang xgetLang();

    void xsetLang(Lang lang);

    /* loaded from: classes11.dex */
    public interface Lang extends XmlAnySimpleType {
        public static final ElementFactory<Lang> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "lang1224attrtype");
        public static final SchemaType type = Factory.getType();

        Object getObjectValue();

        SchemaType instanceType();

        void setObjectValue(Object obj);

        /* loaded from: classes11.dex */
        public interface Member extends XmlString {
            public static final int INT_X = 1;
            public static final ElementFactory<Member> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anon695ftype");
            public static final SchemaType type = Factory.getType();
            public static final Enum X = Enum.forString("");

            StringEnumAbstractBase getEnumValue();

            void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

            /* loaded from: classes11.dex */
            public static final class Enum extends StringEnumAbstractBase {
                static final int INT_X = 1;
                private static final long serialVersionUID = 1;
                public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("", 1)});

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
