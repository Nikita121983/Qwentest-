package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface SequenceDocument extends XmlObject {
    public static final DocumentFactory<SequenceDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sequencecba2doctype");
    public static final SchemaType type = Factory.getType();

    ExplicitGroup addNewSequence();

    ExplicitGroup getSequence();

    void setSequence(ExplicitGroup explicitGroup);
}
