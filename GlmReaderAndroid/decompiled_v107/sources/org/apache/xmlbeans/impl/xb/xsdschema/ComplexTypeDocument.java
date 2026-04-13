package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface ComplexTypeDocument extends XmlObject {
    public static final DocumentFactory<ComplexTypeDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "complextype83cbdoctype");
    public static final SchemaType type = Factory.getType();

    TopLevelComplexType addNewComplexType();

    TopLevelComplexType getComplexType();

    void setComplexType(TopLevelComplexType topLevelComplexType);
}
