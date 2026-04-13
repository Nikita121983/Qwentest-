package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlFloat extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlFloat> Factory = new XmlObjectFactory<>("_BI_float");
    public static final SchemaType type = Factory.getType();

    float getFloatValue();

    void setFloatValue(float f);
}
