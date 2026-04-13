package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/* loaded from: classes11.dex */
public final class XmlReaderToWriter {
    private XmlReaderToWriter() {
    }

    public static void writeAll(XMLStreamReader xmlr, XMLStreamWriter writer) throws XMLStreamException {
        while (xmlr.hasNext()) {
            write(xmlr, writer);
            xmlr.next();
        }
        write(xmlr, writer);
        writer.flush();
    }

    public static void write(XMLStreamReader xmlr, XMLStreamWriter writer) throws XMLStreamException {
        switch (xmlr.getEventType()) {
            case 1:
                String localName = xmlr.getLocalName();
                String namespaceURI = xmlr.getNamespaceURI();
                if (namespaceURI != null && !namespaceURI.isEmpty()) {
                    String prefix = xmlr.getPrefix();
                    if (prefix != null) {
                        writer.writeStartElement(prefix, localName, namespaceURI);
                    } else {
                        writer.writeStartElement(namespaceURI, localName);
                    }
                } else {
                    writer.writeStartElement(localName);
                }
                int len = xmlr.getNamespaceCount();
                for (int i = 0; i < len; i++) {
                    writer.writeNamespace(xmlr.getNamespacePrefix(i), xmlr.getNamespaceURI(i));
                }
                int len2 = xmlr.getAttributeCount();
                for (int i2 = 0; i2 < len2; i2++) {
                    String attUri = xmlr.getAttributeNamespace(i2);
                    if (attUri != null) {
                        writer.writeAttribute(attUri, xmlr.getAttributeLocalName(i2), xmlr.getAttributeValue(i2));
                    } else {
                        writer.writeAttribute(xmlr.getAttributeLocalName(i2), xmlr.getAttributeValue(i2));
                    }
                }
                return;
            case 2:
                writer.writeEndElement();
                return;
            case 3:
                writer.writeProcessingInstruction(xmlr.getPITarget(), xmlr.getPIData());
                return;
            case 4:
            case 6:
                writer.writeCharacters(xmlr.getTextCharacters(), xmlr.getTextStart(), xmlr.getTextLength());
                return;
            case 5:
                writer.writeComment(xmlr.getText());
                return;
            case 7:
                String encoding = xmlr.getCharacterEncodingScheme();
                String version = xmlr.getVersion();
                if (encoding != null && version != null) {
                    writer.writeStartDocument(encoding, version);
                    return;
                } else {
                    if (version != null) {
                        writer.writeStartDocument(xmlr.getVersion());
                        return;
                    }
                    return;
                }
            case 8:
                writer.writeEndDocument();
                return;
            case 9:
                writer.writeEntityRef(xmlr.getLocalName());
                return;
            case 10:
            default:
                return;
            case 11:
                writer.writeDTD(xmlr.getText());
                return;
            case 12:
                writer.writeCData(xmlr.getText());
                return;
        }
    }
}
