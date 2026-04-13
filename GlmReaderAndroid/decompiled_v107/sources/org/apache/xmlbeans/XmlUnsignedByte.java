package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlUnsignedByte extends XmlUnsignedShort {
    public static final XmlObjectFactory<XmlUnsignedByte> Factory = new XmlObjectFactory<>("_BI_unsignedByte");
    public static final SchemaType type = Factory.getType();

    short getShortValue();

    void setShortValue(short s);
}
