package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlByte extends XmlShort {
    public static final XmlObjectFactory<XmlByte> Factory = new XmlObjectFactory<>("_BI_byte");
    public static final SchemaType type = Factory.getType();

    byte getByteValue();

    void setByteValue(byte b);
}
