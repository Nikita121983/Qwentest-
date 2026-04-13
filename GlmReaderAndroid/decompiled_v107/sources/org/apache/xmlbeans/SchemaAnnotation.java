package org.apache.xmlbeans;

import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public interface SchemaAnnotation extends SchemaComponent {

    /* loaded from: classes.dex */
    public interface Attribute {
        QName getName();

        String getValue();

        String getValueUri();
    }

    XmlObject[] getApplicationInformation();

    Attribute[] getAttributes();

    XmlObject[] getUserInformation();
}
