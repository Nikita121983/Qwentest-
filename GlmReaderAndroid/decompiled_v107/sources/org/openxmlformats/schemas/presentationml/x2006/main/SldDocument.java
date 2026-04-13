package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface SldDocument extends XmlObject {
    public static final DocumentFactory<SldDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sld1b98doctype");
    public static final SchemaType type = Factory.getType();

    CTSlide addNewSld();

    CTSlide getSld();

    void setSld(CTSlide cTSlide);
}
