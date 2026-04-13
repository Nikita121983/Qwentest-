package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

/* loaded from: classes12.dex */
public interface CTUnderlineProperty extends XmlObject {
    public static final DocumentFactory<CTUnderlineProperty> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctunderlineproperty8e20type");
    public static final SchemaType type = Factory.getType();

    STUnderlineValues.Enum getVal();

    boolean isSetVal();

    void setVal(STUnderlineValues.Enum r1);

    void unsetVal();

    STUnderlineValues xgetVal();

    void xsetVal(STUnderlineValues sTUnderlineValues);
}
