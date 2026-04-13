package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface NamedGroup extends RealGroup {
    public static final DocumentFactory<NamedGroup> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "namedgroup878dtype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface All extends org.apache.xmlbeans.impl.xb.xsdschema.All {
        public static final ElementFactory<All> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "all82daelemtype");
        public static final SchemaType type = Factory.getType();
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    String getName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    boolean isSetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    void setName(String str);

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    void unsetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    XmlNCName xgetName();

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Group
    void xsetName(XmlNCName xmlNCName);
}
