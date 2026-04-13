package org.apache.xmlbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

/* loaded from: classes.dex */
public interface XmlFactoryHook {
    DOMImplementation newDomImplementation(SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions);

    XmlObject newInstance(SchemaTypeLoader schemaTypeLoader, SchemaType schemaType, XmlOptions xmlOptions);

    XmlSaxHandler newXmlSaxHandler(SchemaTypeLoader schemaTypeLoader, SchemaType schemaType, XmlOptions xmlOptions);

    XmlObject parse(SchemaTypeLoader schemaTypeLoader, InputStream inputStream, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException, IOException;

    XmlObject parse(SchemaTypeLoader schemaTypeLoader, Reader reader, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException, IOException;

    XmlObject parse(SchemaTypeLoader schemaTypeLoader, String str, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException;

    XmlObject parse(SchemaTypeLoader schemaTypeLoader, XMLStreamReader xMLStreamReader, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException;

    XmlObject parse(SchemaTypeLoader schemaTypeLoader, Node node, SchemaType schemaType, XmlOptions xmlOptions) throws XmlException;

    /* loaded from: classes.dex */
    public static final class ThreadContext {
        private static final ThreadLocal<SoftReference<XmlFactoryHook>> threadHook = new ThreadLocal<>();

        public static void clearThreadLocals() {
            threadHook.remove();
        }

        public static XmlFactoryHook getHook() {
            SoftReference<XmlFactoryHook> softRef = threadHook.get();
            if (softRef == null) {
                return null;
            }
            return softRef.get();
        }

        public static void setHook(XmlFactoryHook hook) {
            threadHook.set(new SoftReference<>(hook));
        }

        private ThreadContext() {
        }
    }
}
