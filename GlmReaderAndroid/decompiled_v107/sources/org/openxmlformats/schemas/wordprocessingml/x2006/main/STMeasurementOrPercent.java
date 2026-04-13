package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STMeasurementOrPercent extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STMeasurementOrPercent> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stmeasurementorpercent3292type");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);
}
