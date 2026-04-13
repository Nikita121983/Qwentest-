package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface SldLayoutDocument extends XmlObject {
    public static final DocumentFactory<SldLayoutDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sldlayout638edoctype");
    public static final SchemaType type = Factory.getType();

    CTSlideLayout addNewSldLayout();

    CTSlideLayout getSldLayout();

    void setSldLayout(CTSlideLayout cTSlideLayout);
}
