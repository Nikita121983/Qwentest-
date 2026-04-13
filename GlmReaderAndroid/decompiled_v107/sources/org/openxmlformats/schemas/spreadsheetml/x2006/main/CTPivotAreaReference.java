package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPivotAreaReference extends XmlObject {
    public static final DocumentFactory<CTPivotAreaReference> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotareareferencee5a5type");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTIndex addNewX();

    boolean getAvgSubtotal();

    boolean getByPosition();

    long getCount();

    boolean getCountASubtotal();

    boolean getCountSubtotal();

    boolean getDefaultSubtotal();

    CTExtensionList getExtLst();

    long getField();

    boolean getMaxSubtotal();

    boolean getMinSubtotal();

    boolean getProductSubtotal();

    boolean getRelative();

    boolean getSelected();

    boolean getStdDevPSubtotal();

    boolean getStdDevSubtotal();

    boolean getSumSubtotal();

    boolean getVarPSubtotal();

    boolean getVarSubtotal();

    CTIndex getXArray(int i);

    CTIndex[] getXArray();

    List<CTIndex> getXList();

    CTIndex insertNewX(int i);

    boolean isSetAvgSubtotal();

    boolean isSetByPosition();

    boolean isSetCount();

    boolean isSetCountASubtotal();

    boolean isSetCountSubtotal();

    boolean isSetDefaultSubtotal();

    boolean isSetExtLst();

    boolean isSetField();

    boolean isSetMaxSubtotal();

    boolean isSetMinSubtotal();

    boolean isSetProductSubtotal();

    boolean isSetRelative();

    boolean isSetSelected();

    boolean isSetStdDevPSubtotal();

    boolean isSetStdDevSubtotal();

    boolean isSetSumSubtotal();

    boolean isSetVarPSubtotal();

    boolean isSetVarSubtotal();

    void removeX(int i);

    void setAvgSubtotal(boolean z);

    void setByPosition(boolean z);

    void setCount(long j);

    void setCountASubtotal(boolean z);

    void setCountSubtotal(boolean z);

    void setDefaultSubtotal(boolean z);

    void setExtLst(CTExtensionList cTExtensionList);

    void setField(long j);

    void setMaxSubtotal(boolean z);

    void setMinSubtotal(boolean z);

    void setProductSubtotal(boolean z);

    void setRelative(boolean z);

    void setSelected(boolean z);

    void setStdDevPSubtotal(boolean z);

    void setStdDevSubtotal(boolean z);

    void setSumSubtotal(boolean z);

    void setVarPSubtotal(boolean z);

    void setVarSubtotal(boolean z);

    void setXArray(int i, CTIndex cTIndex);

    void setXArray(CTIndex[] cTIndexArr);

    int sizeOfXArray();

    void unsetAvgSubtotal();

    void unsetByPosition();

    void unsetCount();

    void unsetCountASubtotal();

    void unsetCountSubtotal();

    void unsetDefaultSubtotal();

    void unsetExtLst();

    void unsetField();

    void unsetMaxSubtotal();

    void unsetMinSubtotal();

    void unsetProductSubtotal();

    void unsetRelative();

    void unsetSelected();

    void unsetStdDevPSubtotal();

    void unsetStdDevSubtotal();

    void unsetSumSubtotal();

    void unsetVarPSubtotal();

    void unsetVarSubtotal();

    XmlBoolean xgetAvgSubtotal();

    XmlBoolean xgetByPosition();

    XmlUnsignedInt xgetCount();

    XmlBoolean xgetCountASubtotal();

    XmlBoolean xgetCountSubtotal();

    XmlBoolean xgetDefaultSubtotal();

    XmlUnsignedInt xgetField();

    XmlBoolean xgetMaxSubtotal();

    XmlBoolean xgetMinSubtotal();

    XmlBoolean xgetProductSubtotal();

    XmlBoolean xgetRelative();

    XmlBoolean xgetSelected();

    XmlBoolean xgetStdDevPSubtotal();

    XmlBoolean xgetStdDevSubtotal();

    XmlBoolean xgetSumSubtotal();

    XmlBoolean xgetVarPSubtotal();

    XmlBoolean xgetVarSubtotal();

    void xsetAvgSubtotal(XmlBoolean xmlBoolean);

    void xsetByPosition(XmlBoolean xmlBoolean);

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    void xsetCountASubtotal(XmlBoolean xmlBoolean);

    void xsetCountSubtotal(XmlBoolean xmlBoolean);

    void xsetDefaultSubtotal(XmlBoolean xmlBoolean);

    void xsetField(XmlUnsignedInt xmlUnsignedInt);

    void xsetMaxSubtotal(XmlBoolean xmlBoolean);

    void xsetMinSubtotal(XmlBoolean xmlBoolean);

    void xsetProductSubtotal(XmlBoolean xmlBoolean);

    void xsetRelative(XmlBoolean xmlBoolean);

    void xsetSelected(XmlBoolean xmlBoolean);

    void xsetStdDevPSubtotal(XmlBoolean xmlBoolean);

    void xsetStdDevSubtotal(XmlBoolean xmlBoolean);

    void xsetSumSubtotal(XmlBoolean xmlBoolean);

    void xsetVarPSubtotal(XmlBoolean xmlBoolean);

    void xsetVarSubtotal(XmlBoolean xmlBoolean);
}
