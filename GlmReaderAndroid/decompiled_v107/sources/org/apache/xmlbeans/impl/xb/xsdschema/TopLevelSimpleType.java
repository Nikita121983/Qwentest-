package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TopLevelSimpleType extends SimpleType {
    public static final DocumentFactory<TopLevelSimpleType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "toplevelsimpletypec958type");
    public static final SchemaType type = Factory.getType();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    void xsetName(XmlNCName xmlNCName);
}
