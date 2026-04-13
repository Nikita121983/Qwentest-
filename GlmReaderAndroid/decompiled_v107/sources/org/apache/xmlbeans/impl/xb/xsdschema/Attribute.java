package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface Attribute extends Annotated {
    public static final DocumentFactory<Attribute> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "attribute83a9type");
    public static final SchemaType type = Factory.getType();

    LocalSimpleType addNewSimpleType();

    String getDefault();

    String getFixed();

    FormChoice.Enum getForm();

    String getName();

    QName getRef();

    LocalSimpleType getSimpleType();

    QName getType();

    Use.Enum getUse();

    boolean isSetDefault();

    boolean isSetFixed();

    boolean isSetForm();

    boolean isSetName();

    boolean isSetRef();

    boolean isSetSimpleType();

    boolean isSetType();

    boolean isSetUse();

    void setDefault(String str);

    void setFixed(String str);

    void setForm(FormChoice.Enum r1);

    void setName(String str);

    void setRef(QName qName);

    void setSimpleType(LocalSimpleType localSimpleType);

    void setType(QName qName);

    void setUse(Use.Enum r1);

    void unsetDefault();

    void unsetFixed();

    void unsetForm();

    void unsetName();

    void unsetRef();

    void unsetSimpleType();

    void unsetType();

    void unsetUse();

    XmlString xgetDefault();

    XmlString xgetFixed();

    FormChoice xgetForm();

    XmlNCName xgetName();

    XmlQName xgetRef();

    XmlQName xgetType();

    Use xgetUse();

    void xsetDefault(XmlString xmlString);

    void xsetFixed(XmlString xmlString);

    void xsetForm(FormChoice formChoice);

    void xsetName(XmlNCName xmlNCName);

    void xsetRef(XmlQName xmlQName);

    void xsetType(XmlQName xmlQName);

    void xsetUse(Use use);

    /* loaded from: classes11.dex */
    public interface Use extends XmlNMTOKEN {
        public static final int INT_OPTIONAL = 2;
        public static final int INT_PROHIBITED = 1;
        public static final int INT_REQUIRED = 3;
        public static final ElementFactory<Use> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "usea41aattrtype");
        public static final SchemaType type = Factory.getType();
        public static final Enum PROHIBITED = Enum.forString("prohibited");
        public static final Enum OPTIONAL = Enum.forString("optional");
        public static final Enum REQUIRED = Enum.forString("required");

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        /* loaded from: classes11.dex */
        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_OPTIONAL = 2;
            static final int INT_PROHIBITED = 1;
            static final int INT_REQUIRED = 3;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("prohibited", 1), new Enum("optional", 2), new Enum("required", 3)});

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
