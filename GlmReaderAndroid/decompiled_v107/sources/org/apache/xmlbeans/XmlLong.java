package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlLong extends XmlInteger {
    public static final XmlObjectFactory<XmlLong> Factory = new XmlObjectFactory<>("_BI_long");
    public static final SchemaType type = Factory.getType();

    long getLongValue();

    void setLongValue(long j);
}
