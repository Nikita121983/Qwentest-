package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlDouble extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDouble> Factory = new XmlObjectFactory<>("_BI_double");
    public static final SchemaType type = Factory.getType();

    double getDoubleValue();

    void setDoubleValue(double d);
}
