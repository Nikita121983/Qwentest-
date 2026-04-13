package org.etsi.uri.x01903.v14;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface TimeStampValidationDataDocument extends XmlObject {
    public static final DocumentFactory<TimeStampValidationDataDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "timestampvalidationdataeb4bdoctype");
    public static final SchemaType type = Factory.getType();

    ValidationDataType addNewTimeStampValidationData();

    ValidationDataType getTimeStampValidationData();

    void setTimeStampValidationData(ValidationDataType validationDataType);
}
