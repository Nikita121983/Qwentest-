package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

/* loaded from: classes12.dex */
public interface CTSdtText extends XmlObject {
    public static final DocumentFactory<CTSdtText> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdttext0a82type");
    public static final SchemaType type = Factory.getType();

    Object getMultiLine();

    boolean isSetMultiLine();

    void setMultiLine(Object obj);

    void unsetMultiLine();

    STOnOff xgetMultiLine();

    void xsetMultiLine(STOnOff sTOnOff);
}
