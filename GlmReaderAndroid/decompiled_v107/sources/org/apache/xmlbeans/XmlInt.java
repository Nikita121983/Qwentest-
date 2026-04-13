package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlInt extends XmlLong {
    public static final XmlObjectFactory<XmlInt> Factory = new XmlObjectFactory<>("_BI_int");
    public static final SchemaType type = Factory.getType();

    int getIntValue();

    void setIntValue(int i);
}
