package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlNegativeInteger extends XmlNonPositiveInteger {
    public static final XmlObjectFactory<XmlNegativeInteger> Factory = new XmlObjectFactory<>("_BI_negativeInteger");
    public static final SchemaType type = Factory.getType();
}
