package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface ImportDocument extends XmlObject {
    public static final DocumentFactory<ImportDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "import99fedoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Import extends Annotated {
        public static final ElementFactory<Import> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "importe2ffelemtype");
        public static final SchemaType type = Factory.getType();

        String getNamespace();

        String getSchemaLocation();

        boolean isSetNamespace();

        boolean isSetSchemaLocation();

        void setNamespace(String str);

        void setSchemaLocation(String str);

        void unsetNamespace();

        void unsetSchemaLocation();

        XmlAnyURI xgetNamespace();

        XmlAnyURI xgetSchemaLocation();

        void xsetNamespace(XmlAnyURI xmlAnyURI);

        void xsetSchemaLocation(XmlAnyURI xmlAnyURI);
    }

    Import addNewImport();

    Import getImport();

    void setImport(Import r1);
}
