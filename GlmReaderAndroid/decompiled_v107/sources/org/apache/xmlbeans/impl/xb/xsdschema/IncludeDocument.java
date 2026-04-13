package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface IncludeDocument extends XmlObject {
    public static final DocumentFactory<IncludeDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "includeaf6ddoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Include extends Annotated {
        public static final ElementFactory<Include> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "include59d9elemtype");
        public static final SchemaType type = Factory.getType();

        String getSchemaLocation();

        void setSchemaLocation(String str);

        XmlAnyURI xgetSchemaLocation();

        void xsetSchemaLocation(XmlAnyURI xmlAnyURI);
    }

    Include addNewInclude();

    Include getInclude();

    void setInclude(Include include);
}
