package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTExtension extends XmlObject {
    public static final DocumentFactory<CTExtension> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctextensionedf0type");
    public static final SchemaType type = Factory.getType();

    String getUri();

    void setUri(String str);

    XmlToken xgetUri();

    void xsetUri(XmlToken xmlToken);
}
