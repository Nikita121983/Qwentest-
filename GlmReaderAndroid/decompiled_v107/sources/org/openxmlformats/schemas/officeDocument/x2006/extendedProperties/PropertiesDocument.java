package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface PropertiesDocument extends XmlObject {
    public static final DocumentFactory<PropertiesDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "propertiesee84doctype");
    public static final SchemaType type = Factory.getType();

    CTProperties addNewProperties();

    CTProperties getProperties();

    void setProperties(CTProperties cTProperties);
}
