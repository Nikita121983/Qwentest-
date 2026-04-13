package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STCalendarType;

/* loaded from: classes12.dex */
public interface CTCalendarType extends XmlObject {
    public static final DocumentFactory<CTCalendarType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcalendartyped1d0type");
    public static final SchemaType type = Factory.getType();

    STCalendarType.Enum getVal();

    boolean isSetVal();

    void setVal(STCalendarType.Enum r1);

    void unsetVal();

    STCalendarType xgetVal();

    void xsetVal(STCalendarType sTCalendarType);
}
