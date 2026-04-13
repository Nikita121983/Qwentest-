package com.microsoft.schemas.office.drawing.x2008.diagram;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface CTDrawing extends XmlObject {
    public static final DocumentFactory<CTDrawing> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdrawingc4f9type");
    public static final SchemaType type = Factory.getType();

    CTGroupShape addNewSpTree();

    CTGroupShape getSpTree();

    void setSpTree(CTGroupShape cTGroupShape);
}
