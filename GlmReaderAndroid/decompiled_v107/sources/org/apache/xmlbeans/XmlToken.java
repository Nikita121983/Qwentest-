package org.apache.xmlbeans;

import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlToken extends XmlNormalizedString {
    public static final XmlObjectFactory<XmlToken> Factory = new XmlObjectFactory<>("_BI_token");
    public static final SchemaType type = Factory.getType();
}
