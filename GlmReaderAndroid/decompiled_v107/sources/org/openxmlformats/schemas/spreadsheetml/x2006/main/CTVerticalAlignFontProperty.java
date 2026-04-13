package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;

/* loaded from: classes12.dex */
public interface CTVerticalAlignFontProperty extends XmlObject {
    public static final DocumentFactory<CTVerticalAlignFontProperty> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctverticalalignfontproperty89f2type");
    public static final SchemaType type = Factory.getType();

    STVerticalAlignRun.Enum getVal();

    void setVal(STVerticalAlignRun.Enum r1);

    STVerticalAlignRun xgetVal();

    void xsetVal(STVerticalAlignRun sTVerticalAlignRun);
}
