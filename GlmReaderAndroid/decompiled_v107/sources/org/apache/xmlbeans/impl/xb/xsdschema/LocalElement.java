package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface LocalElement extends Element {
    public static final DocumentFactory<LocalElement> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "localelement2ce2type");
    public static final SchemaType type = Factory.getType();
}
