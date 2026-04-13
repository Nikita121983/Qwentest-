package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public interface XmlBase64Binary extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_base64Binary");

    byte[] getByteArrayValue();

    void setByteArrayValue(byte[] bArr);

    /* loaded from: classes.dex */
    public static final class Factory {
        public static XmlBase64Binary newInstance() {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().newInstance(XmlBase64Binary.type, null);
        }

        public static XmlBase64Binary newInstance(XmlOptions options) {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().newInstance(XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary newValue(Object obj) {
            return (XmlBase64Binary) XmlBase64Binary.type.newValue(obj);
        }

        public static XmlBase64Binary parse(String s) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(s, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(String s, XmlOptions options) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(s, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(File f) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(f, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(f, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(URL u) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(u, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(u, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(InputStream is) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(is, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(is, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(Reader r) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(r, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(r, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(Node node) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(node, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(Node node, XmlOptions options) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(node, XmlBase64Binary.type, options);
        }

        public static XmlBase64Binary parse(XMLStreamReader xsr) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(xsr, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(xsr, XmlBase64Binary.type, options);
        }

        private Factory() {
        }
    }
}
