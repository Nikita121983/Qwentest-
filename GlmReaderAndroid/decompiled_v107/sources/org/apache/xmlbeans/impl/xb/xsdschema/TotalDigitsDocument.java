package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TotalDigitsDocument extends XmlObject {
    public static final DocumentFactory<TotalDigitsDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "totaldigits4a8bdoctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface TotalDigits extends NumFacet {
        public static final ElementFactory<TotalDigits> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "totaldigits2615elemtype");
        public static final SchemaType type = Factory.getType();
    }

    TotalDigits addNewTotalDigits();

    TotalDigits getTotalDigits();

    void setTotalDigits(TotalDigits totalDigits);
}
