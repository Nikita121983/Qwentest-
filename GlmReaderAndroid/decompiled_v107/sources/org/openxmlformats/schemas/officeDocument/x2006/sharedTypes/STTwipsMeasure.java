package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STTwipsMeasure extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STTwipsMeasure> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttwipsmeasure9c4ftype");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);
}
