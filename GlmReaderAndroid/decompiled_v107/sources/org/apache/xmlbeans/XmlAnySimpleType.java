package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlAnySimpleType extends XmlObject {
    public static final XmlObjectFactory<XmlAnySimpleType> Factory = new XmlObjectFactory<>("_BI_anySimpleType");
    public static final SchemaType type = Factory.getType();

    String getStringValue();

    void setStringValue(String str);
}
