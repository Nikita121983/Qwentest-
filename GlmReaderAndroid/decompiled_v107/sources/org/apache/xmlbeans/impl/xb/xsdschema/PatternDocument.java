package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface PatternDocument extends XmlObject {
    public static final DocumentFactory<PatternDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pattern9585doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Pattern extends NoFixedFacet {
        public static final ElementFactory<Pattern> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "pattern6809elemtype");
        public static final SchemaType type = Factory.getType();
    }

    Pattern addNewPattern();

    Pattern getPattern();

    void setPattern(Pattern pattern);
}
