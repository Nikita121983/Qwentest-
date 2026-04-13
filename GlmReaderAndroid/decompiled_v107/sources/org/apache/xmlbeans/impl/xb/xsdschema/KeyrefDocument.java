package org.apache.xmlbeans.impl.xb.xsdschema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface KeyrefDocument extends XmlObject {
    public static final DocumentFactory<KeyrefDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "keyref45afdoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Keyref extends Keybase {
        public static final ElementFactory<Keyref> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "keyref7a1felemtype");
        public static final SchemaType type = Factory.getType();

        QName getRefer();

        void setRefer(QName qName);

        XmlQName xgetRefer();

        void xsetRefer(XmlQName xmlQName);
    }

    Keyref addNewKeyref();

    Keyref getKeyref();

    void setKeyref(Keyref keyref);
}
