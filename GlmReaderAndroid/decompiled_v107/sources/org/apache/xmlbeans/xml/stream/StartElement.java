package org.apache.xmlbeans.xml.stream;

import java.util.Map;

/* loaded from: classes11.dex */
public interface StartElement extends XMLEvent {
    Attribute getAttributeByName(XMLName xMLName);

    AttributeIterator getAttributes();

    AttributeIterator getAttributesAndNamespaces();

    Map<String, String> getNamespaceMap();

    String getNamespaceUri(String str);

    AttributeIterator getNamespaces();
}
