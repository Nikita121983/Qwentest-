package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TopLevelAttribute extends Attribute {
    public static final DocumentFactory<TopLevelAttribute> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "toplevelattributeb338type");
    public static final SchemaType type = Factory.getType();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    void xsetName(XmlNCName xmlNCName);
}
