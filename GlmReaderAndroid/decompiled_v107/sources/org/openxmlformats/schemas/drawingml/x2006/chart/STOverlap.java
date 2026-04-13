package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STOverlap extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STOverlap> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stoverlap7d4ftype");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);
}
