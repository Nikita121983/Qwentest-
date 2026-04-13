package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlUnsignedInt extends XmlUnsignedLong {
    public static final XmlObjectFactory<XmlUnsignedInt> Factory = new XmlObjectFactory<>("_BI_unsignedInt");
    public static final SchemaType type = Factory.getType();

    long getLongValue();

    void setLongValue(long j);
}
