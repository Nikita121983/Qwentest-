package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTIntProperty extends XmlObject {
    public static final DocumentFactory<CTIntProperty> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctintproperty32c3type");
    public static final SchemaType type = Factory.getType();

    int getVal();

    void setVal(int i);

    XmlInt xgetVal();

    void xsetVal(XmlInt xmlInt);
}
