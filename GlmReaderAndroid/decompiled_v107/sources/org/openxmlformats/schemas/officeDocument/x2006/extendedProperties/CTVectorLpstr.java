package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes.CTVector;

/* loaded from: classes11.dex */
public interface CTVectorLpstr extends XmlObject {
    public static final DocumentFactory<CTVectorLpstr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctvectorlpstr9b1dtype");
    public static final SchemaType type = Factory.getType();

    CTVector addNewVector();

    CTVector getVector();

    void setVector(CTVector cTVector);
}
