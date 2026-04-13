package org.apache.xmlbeans.impl.xb.xmlschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLLANG.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface IdAttribute extends XmlObject {
    public static final DocumentFactory<IdAttribute> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "idd759attrtypetype");
    public static final SchemaType type = Factory.getType();

    String getId();

    boolean isSetId();

    void setId(String str);

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);
}
