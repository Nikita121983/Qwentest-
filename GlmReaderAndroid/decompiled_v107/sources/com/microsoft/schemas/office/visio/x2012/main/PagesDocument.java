package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface PagesDocument extends XmlObject {
    public static final DocumentFactory<PagesDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pages52f4doctype");
    public static final SchemaType type = Factory.getType();

    PagesType addNewPages();

    PagesType getPages();

    void setPages(PagesType pagesType);
}
