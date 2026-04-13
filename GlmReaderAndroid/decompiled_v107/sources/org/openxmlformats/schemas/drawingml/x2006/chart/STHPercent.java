package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STHPercent extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STHPercent> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sthpercentdbcftype");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);
}
