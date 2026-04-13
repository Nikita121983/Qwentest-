package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;

/* loaded from: classes12.dex */
public interface CTPivotArea extends XmlObject {
    public static final DocumentFactory<CTPivotArea> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotarea26cetype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTPivotAreaReferences addNewReferences();

    STAxis.Enum getAxis();

    boolean getCacheIndex();

    boolean getCollapsedLevelsAreSubtotals();

    boolean getDataOnly();

    CTExtensionList getExtLst();

    int getField();

    long getFieldPosition();

    boolean getGrandCol();

    boolean getGrandRow();

    boolean getLabelOnly();

    String getOffset();

    boolean getOutline();

    CTPivotAreaReferences getReferences();

    STPivotAreaType$Enum getType();

    boolean isSetAxis();

    boolean isSetCacheIndex();

    boolean isSetCollapsedLevelsAreSubtotals();

    boolean isSetDataOnly();

    boolean isSetExtLst();

    boolean isSetField();

    boolean isSetFieldPosition();

    boolean isSetGrandCol();

    boolean isSetGrandRow();

    boolean isSetLabelOnly();

    boolean isSetOffset();

    boolean isSetOutline();

    boolean isSetReferences();

    boolean isSetType();

    void setAxis(STAxis.Enum r1);

    void setCacheIndex(boolean z);

    void setCollapsedLevelsAreSubtotals(boolean z);

    void setDataOnly(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setField(int i);

    void setFieldPosition(long j);

    void setGrandCol(boolean z);

    void setGrandRow(boolean z);

    void setLabelOnly(boolean z);

    void setOffset(String str);

    void setOutline(boolean z);

    void setReferences(CTPivotAreaReferences cTPivotAreaReferences);

    void setType(STPivotAreaType$Enum sTPivotAreaType$Enum);

    void unsetAxis();

    void unsetCacheIndex();

    void unsetCollapsedLevelsAreSubtotals();

    void unsetDataOnly();

    void unsetExtLst();

    void unsetField();

    void unsetFieldPosition();

    void unsetGrandCol();

    void unsetGrandRow();

    void unsetLabelOnly();

    void unsetOffset();

    void unsetOutline();

    void unsetReferences();

    void unsetType();

    STAxis xgetAxis();

    XmlBoolean xgetCacheIndex();

    XmlBoolean xgetCollapsedLevelsAreSubtotals();

    XmlBoolean xgetDataOnly();

    XmlInt xgetField();

    XmlUnsignedInt xgetFieldPosition();

    XmlBoolean xgetGrandCol();

    XmlBoolean xgetGrandRow();

    XmlBoolean xgetLabelOnly();

    STRef xgetOffset();

    XmlBoolean xgetOutline();

    STPivotAreaType xgetType();

    void xsetAxis(STAxis sTAxis);

    void xsetCacheIndex(XmlBoolean xmlBoolean);

    void xsetCollapsedLevelsAreSubtotals(XmlBoolean xmlBoolean);

    void xsetDataOnly(XmlBoolean xmlBoolean);

    void xsetField(XmlInt xmlInt);

    void xsetFieldPosition(XmlUnsignedInt xmlUnsignedInt);

    void xsetGrandCol(XmlBoolean xmlBoolean);

    void xsetGrandRow(XmlBoolean xmlBoolean);

    void xsetLabelOnly(XmlBoolean xmlBoolean);

    void xsetOffset(STRef sTRef);

    void xsetOutline(XmlBoolean xmlBoolean);

    void xsetType(STPivotAreaType sTPivotAreaType);
}
