package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TopLevelElement extends Element {
    public static final DocumentFactory<TopLevelElement> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "toplevelelement98d8type");
    public static final SchemaType type = Factory.getType();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    void xsetName(XmlNCName xmlNCName);
}
