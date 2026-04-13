package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface QualifyingPropertiesDocument extends XmlObject {
    public static final DocumentFactory<QualifyingPropertiesDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "qualifyingproperties53ccdoctype");
    public static final SchemaType type = Factory.getType();

    QualifyingPropertiesType addNewQualifyingProperties();

    QualifyingPropertiesType getQualifyingProperties();

    void setQualifyingProperties(QualifyingPropertiesType qualifyingPropertiesType);
}
