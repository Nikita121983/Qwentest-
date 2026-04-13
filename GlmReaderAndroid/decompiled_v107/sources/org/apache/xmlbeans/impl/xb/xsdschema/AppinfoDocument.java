package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface AppinfoDocument extends XmlObject {
    public static final DocumentFactory<AppinfoDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "appinfo2ea6doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Appinfo extends XmlObject {
        public static final ElementFactory<Appinfo> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "appinfo650belemtype");
        public static final SchemaType type = Factory.getType();

        String getSource();

        boolean isSetSource();

        void setSource(String str);

        void unsetSource();

        XmlAnyURI xgetSource();

        void xsetSource(XmlAnyURI xmlAnyURI);
    }

    Appinfo addNewAppinfo();

    Appinfo getAppinfo();

    void setAppinfo(Appinfo appinfo);
}
