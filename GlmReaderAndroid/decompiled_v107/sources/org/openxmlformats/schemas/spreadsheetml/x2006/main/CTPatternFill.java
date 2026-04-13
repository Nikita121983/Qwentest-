package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

/* loaded from: classes12.dex */
public interface CTPatternFill extends XmlObject {
    public static final DocumentFactory<CTPatternFill> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpatternfill7452type");
    public static final SchemaType type = Factory.getType();

    CTColor addNewBgColor();

    CTColor addNewFgColor();

    CTColor getBgColor();

    CTColor getFgColor();

    STPatternType.Enum getPatternType();

    boolean isSetBgColor();

    boolean isSetFgColor();

    boolean isSetPatternType();

    void setBgColor(CTColor cTColor);

    void setFgColor(CTColor cTColor);

    void setPatternType(STPatternType.Enum r1);

    void unsetBgColor();

    void unsetFgColor();

    void unsetPatternType();

    STPatternType xgetPatternType();

    void xsetPatternType(STPatternType sTPatternType);
}
