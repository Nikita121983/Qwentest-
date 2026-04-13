package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface SelectorDocument extends XmlObject {
    public static final DocumentFactory<SelectorDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "selectorcb44doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Selector extends Annotated {
        public static final ElementFactory<Selector> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "selector233felemtype");
        public static final SchemaType type = Factory.getType();

        /* loaded from: classes11.dex */
        public interface Xpath extends XmlToken {
            public static final ElementFactory<Xpath> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "xpath6f9aattrtype");
            public static final SchemaType type = Factory.getType();
        }

        String getXpath();

        void setXpath(String str);

        Xpath xgetXpath();

        void xsetXpath(Xpath xpath);
    }

    Selector addNewSelector();

    Selector getSelector();

    void setSelector(Selector selector);
}
