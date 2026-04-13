package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlShort extends XmlInt {
    public static final XmlObjectFactory<XmlShort> Factory = new XmlObjectFactory<>("_BI_short");
    public static final SchemaType type = Factory.getType();

    short getShortValue();

    void setShortValue(short s);
}
