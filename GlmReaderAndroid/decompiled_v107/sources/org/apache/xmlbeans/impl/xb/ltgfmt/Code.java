package org.apache.xmlbeans.impl.xb.ltgfmt;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface Code extends XmlObject {
    public static final DocumentFactory<Code> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "codef72ftype");
    public static final SchemaType type = Factory.getType();

    String getID();

    boolean isSetID();

    void setID(String str);

    void unsetID();

    XmlToken xgetID();

    void xsetID(XmlToken xmlToken);
}
