package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTextSpacingPercent extends XmlObject {
    public static final DocumentFactory<CTTextSpacingPercent> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextspacingpercent322atype");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STTextSpacingPercentOrPercentString xgetVal();

    void xsetVal(STTextSpacingPercentOrPercentString sTTextSpacingPercentOrPercentString);
}
