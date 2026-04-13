package com.microsoft.schemas.office.office;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface ShapelayoutDocument extends XmlObject {
    public static final DocumentFactory<ShapelayoutDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "shapelayoutebb0doctype");
    public static final SchemaType type = Factory.getType();

    CTShapeLayout addNewShapelayout();

    CTShapeLayout getShapelayout();

    void setShapelayout(CTShapeLayout cTShapeLayout);
}
