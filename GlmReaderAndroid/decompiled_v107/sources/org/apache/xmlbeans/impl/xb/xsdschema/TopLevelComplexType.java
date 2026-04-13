package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TopLevelComplexType extends ComplexType {
    public static final DocumentFactory<TopLevelComplexType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "toplevelcomplextypee58atype");
    public static final SchemaType type = Factory.getType();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    void xsetName(XmlNCName xmlNCName);
}
