package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface ElementDocument extends XmlObject {
    public static final DocumentFactory<ElementDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "element7f99doctype");
    public static final SchemaType type = Factory.getType();

    TopLevelElement addNewElement();

    TopLevelElement getElement();

    void setElement(TopLevelElement topLevelElement);
}
