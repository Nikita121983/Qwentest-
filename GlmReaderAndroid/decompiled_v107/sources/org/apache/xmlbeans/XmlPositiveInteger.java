package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlPositiveInteger extends XmlNonNegativeInteger {
    public static final XmlObjectFactory<XmlPositiveInteger> Factory = new XmlObjectFactory<>("_BI_positiveInteger");
    public static final SchemaType type = Factory.getType();
}
