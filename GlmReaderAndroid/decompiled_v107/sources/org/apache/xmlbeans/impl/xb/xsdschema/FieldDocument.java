package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface FieldDocument extends XmlObject {
    public static final DocumentFactory<FieldDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "field3f9bdoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Field extends Annotated {
        public static final ElementFactory<Field> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "field12f5elemtype");
        public static final SchemaType type = Factory.getType();

        /* loaded from: classes11.dex */
        public interface Xpath extends XmlToken {
            public static final ElementFactory<Xpath> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "xpath7f90attrtype");
            public static final SchemaType type = Factory.getType();
        }

        String getXpath();

        void setXpath(String str);

        Xpath xgetXpath();

        void xsetXpath(Xpath xpath);
    }

    Field addNewField();

    Field getField();

    void setField(Field field);
}
