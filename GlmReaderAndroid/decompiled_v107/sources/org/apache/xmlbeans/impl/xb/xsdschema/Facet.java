package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface Facet extends Annotated {
    public static final DocumentFactory<Facet> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "facet446etype");
    public static final SchemaType type = Factory.getType();

    XmlAnySimpleType addNewValue();

    boolean getFixed();

    XmlAnySimpleType getValue();

    boolean isSetFixed();

    void setFixed(boolean z);

    void setValue(XmlAnySimpleType xmlAnySimpleType);

    void unsetFixed();

    XmlBoolean xgetFixed();

    void xsetFixed(XmlBoolean xmlBoolean);
}
