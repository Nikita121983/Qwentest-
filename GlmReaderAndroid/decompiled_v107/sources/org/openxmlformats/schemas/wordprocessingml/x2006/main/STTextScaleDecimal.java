package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

/* loaded from: classes12.dex */
public interface STTextScaleDecimal extends XmlInteger {
    public static final SimpleTypeFactory<STTextScaleDecimal> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "sttextscaledecimaldee4type");
    public static final SchemaType type = Factory.getType();

    int getIntValue();

    void setIntValue(int i);
}
