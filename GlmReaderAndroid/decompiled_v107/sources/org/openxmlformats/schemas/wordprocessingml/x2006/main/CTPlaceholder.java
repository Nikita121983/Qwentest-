package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPlaceholder extends XmlObject {
    public static final DocumentFactory<CTPlaceholder> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctplaceholder117ftype");
    public static final SchemaType type = Factory.getType();

    CTString addNewDocPart();

    CTString getDocPart();

    void setDocPart(CTString cTString);
}
