package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlDuration extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlDuration> Factory = new XmlObjectFactory<>("_BI_duration");
    public static final SchemaType type = Factory.getType();

    GDuration getGDurationValue();

    void setGDurationValue(GDuration gDuration);
}
