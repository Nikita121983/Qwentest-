package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface DataObjectFormatType extends XmlObject {
    public static final DocumentFactory<DataObjectFormatType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "dataobjectformattype44eetype");
    public static final SchemaType type = Factory.getType();

    ObjectIdentifierType addNewObjectIdentifier();

    String getDescription();

    String getEncoding();

    String getMimeType();

    ObjectIdentifierType getObjectIdentifier();

    String getObjectReference();

    boolean isSetDescription();

    boolean isSetEncoding();

    boolean isSetMimeType();

    boolean isSetObjectIdentifier();

    void setDescription(String str);

    void setEncoding(String str);

    void setMimeType(String str);

    void setObjectIdentifier(ObjectIdentifierType objectIdentifierType);

    void setObjectReference(String str);

    void unsetDescription();

    void unsetEncoding();

    void unsetMimeType();

    void unsetObjectIdentifier();

    XmlString xgetDescription();

    XmlAnyURI xgetEncoding();

    XmlString xgetMimeType();

    XmlAnyURI xgetObjectReference();

    void xsetDescription(XmlString xmlString);

    void xsetEncoding(XmlAnyURI xmlAnyURI);

    void xsetMimeType(XmlString xmlString);

    void xsetObjectReference(XmlAnyURI xmlAnyURI);
}
