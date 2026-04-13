package org.apache.xmlbeans.impl.xb.substwsdl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TImport extends XmlObject {
    public static final DocumentFactory<TImport> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "timport22datype");
    public static final SchemaType type = Factory.getType();

    String getLocation();

    String getNamespace();

    void setLocation(String str);

    void setNamespace(String str);

    XmlAnyURI xgetLocation();

    XmlAnyURI xgetNamespace();

    void xsetLocation(XmlAnyURI xmlAnyURI);

    void xsetNamespace(XmlAnyURI xmlAnyURI);
}
