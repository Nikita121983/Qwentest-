package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface NarrowMaxMin extends LocalElement {
    public static final DocumentFactory<NarrowMaxMin> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "narrowmaxmin926atype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface MaxOccurs extends AllNNI {
        public static final ElementFactory<MaxOccurs> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "maxoccursd85dattrtype");
        public static final SchemaType type = Factory.getType();

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllNNI
        Object getObjectValue();

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllNNI
        SchemaType instanceType();

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllNNI
        void setObjectValue(Object obj);
    }

    /* loaded from: classes11.dex */
    public interface MinOccurs extends XmlNonNegativeInteger {
        public static final ElementFactory<MinOccurs> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "minoccurs1acbattrtype");
        public static final SchemaType type = Factory.getType();
    }
}
