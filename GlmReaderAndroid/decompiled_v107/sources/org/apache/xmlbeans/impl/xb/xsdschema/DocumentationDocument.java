package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface DocumentationDocument extends XmlObject {
    public static final DocumentFactory<DocumentationDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "documentation6cdbdoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Documentation extends XmlObject {
        public static final ElementFactory<Documentation> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "documentationa475elemtype");
        public static final SchemaType type = Factory.getType();

        String getLang();

        String getSource();

        boolean isSetLang();

        boolean isSetSource();

        void setLang(String str);

        void setSource(String str);

        void unsetLang();

        void unsetSource();

        LangAttribute.Lang xgetLang();

        XmlAnyURI xgetSource();

        void xsetLang(LangAttribute.Lang lang);

        void xsetSource(XmlAnyURI xmlAnyURI);
    }

    Documentation addNewDocumentation();

    Documentation getDocumentation();

    void setDocumentation(Documentation documentation);
}
