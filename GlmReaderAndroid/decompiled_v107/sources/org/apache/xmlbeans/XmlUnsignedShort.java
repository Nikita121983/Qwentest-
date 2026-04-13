package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlUnsignedShort extends XmlUnsignedInt {
    public static final XmlObjectFactory<XmlUnsignedShort> Factory = new XmlObjectFactory<>("_BI_unsignedShort");
    public static final SchemaType type = Factory.getType();

    int getIntValue();

    void setIntValue(int i);
}
