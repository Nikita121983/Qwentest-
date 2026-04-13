package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPivotHierarchies extends XmlObject {
    public static final DocumentFactory<CTPivotHierarchies> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivothierarchies127atype");
    public static final SchemaType type = Factory.getType();

    CTPivotHierarchy addNewPivotHierarchy();

    long getCount();

    CTPivotHierarchy getPivotHierarchyArray(int i);

    CTPivotHierarchy[] getPivotHierarchyArray();

    List<CTPivotHierarchy> getPivotHierarchyList();

    CTPivotHierarchy insertNewPivotHierarchy(int i);

    boolean isSetCount();

    void removePivotHierarchy(int i);

    void setCount(long j);

    void setPivotHierarchyArray(int i, CTPivotHierarchy cTPivotHierarchy);

    void setPivotHierarchyArray(CTPivotHierarchy[] cTPivotHierarchyArr);

    int sizeOfPivotHierarchyArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
