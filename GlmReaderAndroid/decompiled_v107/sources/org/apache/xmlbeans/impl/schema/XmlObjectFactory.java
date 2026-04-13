package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class XmlObjectFactory<T> extends DocumentFactory<T> {
    private final boolean isAnyType;

    public XmlObjectFactory(String typeHandle) {
        this(XmlBeans.getBuiltinTypeSystem(), typeHandle);
    }

    public XmlObjectFactory(SchemaTypeSystem typeSystem, String typeHandle) {
        super(typeSystem, typeHandle);
        this.isAnyType = "_BI_anyType".equals(typeHandle);
    }

    @Override // org.apache.xmlbeans.impl.schema.DocumentFactory, org.apache.xmlbeans.impl.schema.ElementFactory
    public T newInstance() {
        return (T) XmlBeans.getContextTypeLoader().newInstance(getInnerType(), null);
    }

    @Override // org.apache.xmlbeans.impl.schema.DocumentFactory, org.apache.xmlbeans.impl.schema.ElementFactory
    public T newInstance(XmlOptions xmlOptions) {
        return (T) XmlBeans.getContextTypeLoader().newInstance(getInnerType(), xmlOptions);
    }

    public T newValue(Object obj) {
        return (T) getType().newValue(obj);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(String str) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(str, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(String str, XmlOptions xmlOptions) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(str, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(File file) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(file, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(file, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(URL url) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(url, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(URL url, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(url, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(InputStream inputStream) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(inputStream, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(XMLStreamReader xMLStreamReader) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(xMLStreamReader, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(inputStream, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(xMLStreamReader, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(Reader reader) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(reader, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
        return (T) XmlBeans.getContextTypeLoader().parse(reader, getInnerType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(Node node) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(node, getInnerType(), (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.impl.schema.AbstractDocumentFactory
    public T parse(Node node, XmlOptions xmlOptions) throws XmlException {
        return (T) XmlBeans.getContextTypeLoader().parse(node, getInnerType(), xmlOptions);
    }

    public XmlSaxHandler newXmlSaxHandler() {
        return XmlBeans.getContextTypeLoader().newXmlSaxHandler(getInnerType(), null);
    }

    public XmlSaxHandler newXmlSaxHandler(XmlOptions options) {
        return XmlBeans.getContextTypeLoader().newXmlSaxHandler(getInnerType(), options);
    }

    public DOMImplementation newDomImplementation() {
        return XmlBeans.getContextTypeLoader().newDomImplementation(null);
    }

    public DOMImplementation newDomImplementation(XmlOptions options) {
        return XmlBeans.getContextTypeLoader().newDomImplementation(options);
    }

    private SchemaType getInnerType() {
        if (this.isAnyType) {
            return null;
        }
        return getType();
    }
}
