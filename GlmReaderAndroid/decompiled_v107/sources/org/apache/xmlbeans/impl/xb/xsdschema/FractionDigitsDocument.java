package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface FractionDigitsDocument extends XmlObject {
    public static final DocumentFactory<FractionDigitsDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "fractiondigitsed7bdoctype");
    public static final SchemaType type = Factory.getType();

    NumFacet addNewFractionDigits();

    NumFacet getFractionDigits();

    void setFractionDigits(NumFacet numFacet);
}
