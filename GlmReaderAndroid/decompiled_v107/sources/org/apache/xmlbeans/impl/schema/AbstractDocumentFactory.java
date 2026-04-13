package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class AbstractDocumentFactory<T> extends ElementFactory<T> {
    public AbstractDocumentFactory(SchemaTypeSystem typeSystem, String typeHandle) {
        super(typeSystem, typeHandle);
    }

    public T parse(String str) throws XmlException {
        return (T) getTypeLoader().parse(str, getType(), (XmlOptions) null);
    }

    public T parse(String str, XmlOptions xmlOptions) throws XmlException {
        return (T) getTypeLoader().parse(str, getType(), xmlOptions);
    }

    public T parse(File file) throws XmlException, IOException {
        return (T) getTypeLoader().parse(file, getType(), (XmlOptions) null);
    }

    public T parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) getTypeLoader().parse(file, getType(), xmlOptions);
    }

    public T parse(URL url) throws XmlException, IOException {
        return (T) getTypeLoader().parse(url, getType(), (XmlOptions) null);
    }

    public T parse(URL url, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) getTypeLoader().parse(url, getType(), xmlOptions);
    }

    public T parse(InputStream inputStream) throws XmlException, IOException {
        return (T) getTypeLoader().parse(inputStream, getType(), (XmlOptions) null);
    }

    public T parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) getTypeLoader().parse(inputStream, getType(), xmlOptions);
    }

    public T parse(Reader reader) throws XmlException, IOException {
        return (T) getTypeLoader().parse(reader, getType(), (XmlOptions) null);
    }

    public T parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) getTypeLoader().parse(reader, getType(), xmlOptions);
    }

    public T parse(XMLStreamReader xMLStreamReader) throws XmlException {
        return (T) getTypeLoader().parse(xMLStreamReader, getType(), (XmlOptions) null);
    }

    public T parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
        return (T) getTypeLoader().parse(xMLStreamReader, getType(), xmlOptions);
    }

    public T parse(Node node) throws XmlException {
        return (T) getTypeLoader().parse(node, getType(), (XmlOptions) null);
    }

    public T parse(Node node, XmlOptions xmlOptions) throws XmlException {
        return (T) getTypeLoader().parse(node, getType(), xmlOptions);
    }
}
