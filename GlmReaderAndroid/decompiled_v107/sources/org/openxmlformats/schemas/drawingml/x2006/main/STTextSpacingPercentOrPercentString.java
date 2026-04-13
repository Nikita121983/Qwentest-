package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes11.dex */
public interface STTextSpacingPercentOrPercentString extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STTextSpacingPercentOrPercentString> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextspacingpercentorpercentstringd0e5type");
    public static final SchemaType type = Factory.getType();

    Object getObjectValue();

    SchemaType instanceType();

    void setObjectValue(Object obj);
}
