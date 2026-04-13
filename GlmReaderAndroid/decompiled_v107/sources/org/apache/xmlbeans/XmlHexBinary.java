package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlHexBinary extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlHexBinary> Factory = new XmlObjectFactory<>("_BI_hexBinary");
    public static final SchemaType type = Factory.getType();

    byte[] getByteArrayValue();

    void setByteArrayValue(byte[] bArr);
}
