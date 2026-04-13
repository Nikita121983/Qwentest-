package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface All extends ExplicitGroup {
    public static final DocumentFactory<All> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "all3c04type");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface MaxOccurs extends AllNNI {
        public static final ElementFactory<MaxOccurs> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "maxoccurse8b1attrtype");
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
        public static final ElementFactory<MinOccurs> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "minoccurs9283attrtype");
        public static final SchemaType type = Factory.getType();
    }
}
