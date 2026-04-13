package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTField extends XmlObject {
    public static final DocumentFactory<CTField> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfieldc999type");
    public static final SchemaType type = Factory.getType();

    int getX();

    void setX(int i);

    XmlInt xgetX();

    void xsetX(XmlInt xmlInt);
}
