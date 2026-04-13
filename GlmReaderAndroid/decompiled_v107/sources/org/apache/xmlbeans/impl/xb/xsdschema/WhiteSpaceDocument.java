package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface WhiteSpaceDocument extends XmlObject {
    public static final DocumentFactory<WhiteSpaceDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "whitespaced2c6doctype");
    public static final SchemaType type = Factory.getType();

    WhiteSpace addNewWhiteSpace();

    WhiteSpace getWhiteSpace();

    void setWhiteSpace(WhiteSpace whiteSpace);

    /* loaded from: classes11.dex */
    public interface WhiteSpace extends Facet {
        public static final ElementFactory<WhiteSpace> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "whitespace97ffelemtype");
        public static final SchemaType type = Factory.getType();

        /* loaded from: classes11.dex */
        public interface Value extends XmlNMTOKEN {
            public static final int INT_COLLAPSE = 3;
            public static final int INT_PRESERVE = 1;
            public static final int INT_REPLACE = 2;
            public static final ElementFactory<Value> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "value8186attrtype");
            public static final SchemaType type = Factory.getType();
            public static final Enum PRESERVE = Enum.forString("preserve");
            public static final Enum REPLACE = Enum.forString("replace");
            public static final Enum COLLAPSE = Enum.forString("collapse");

            StringEnumAbstractBase getEnumValue();

            void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

            /* loaded from: classes11.dex */
            public static final class Enum extends StringEnumAbstractBase {
                static final int INT_COLLAPSE = 3;
                static final int INT_PRESERVE = 1;
                static final int INT_REPLACE = 2;
                private static final long serialVersionUID = 1;
                public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("preserve", 1), new Enum("replace", 2), new Enum("collapse", 3)});

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
