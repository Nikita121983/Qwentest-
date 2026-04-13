package org.apache.xmlbeans;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

/* loaded from: classes.dex */
public interface XmlQName extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlQName> Factory = new XmlObjectFactory<>("_BI_QName");
    public static final SchemaType type = Factory.getType();

    QName getQNameValue();

    void setQNameValue(QName qName);
}
