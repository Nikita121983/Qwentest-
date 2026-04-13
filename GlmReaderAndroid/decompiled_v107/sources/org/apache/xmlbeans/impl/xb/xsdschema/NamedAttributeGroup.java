package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface NamedAttributeGroup extends AttributeGroup {
    public static final DocumentFactory<NamedAttributeGroup> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "namedattributegroup2e29type");
    public static final SchemaType type = Factory.getType();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    void xsetName(XmlNCName xmlNCName);
}
