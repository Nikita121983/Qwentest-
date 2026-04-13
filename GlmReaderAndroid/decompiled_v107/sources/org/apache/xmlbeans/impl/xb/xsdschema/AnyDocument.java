package org.apache.xmlbeans.impl.xb.xsdschema;

import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface AnyDocument extends XmlObject {
    public static final DocumentFactory<AnyDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "anye729doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Any extends Wildcard {
        public static final ElementFactory<Any> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "anye9d1elemtype");
        public static final SchemaType type = Factory.getType();

        Object getMaxOccurs();

        BigInteger getMinOccurs();

        boolean isSetMaxOccurs();

        boolean isSetMinOccurs();

        void setMaxOccurs(Object obj);

        void setMinOccurs(BigInteger bigInteger);

        void unsetMaxOccurs();

        void unsetMinOccurs();

        AllNNI xgetMaxOccurs();

        XmlNonNegativeInteger xgetMinOccurs();

        void xsetMaxOccurs(AllNNI allNNI);

        void xsetMinOccurs(XmlNonNegativeInteger xmlNonNegativeInteger);
    }

    Any addNewAny();

    Any getAny();

    void setAny(Any any);
}
