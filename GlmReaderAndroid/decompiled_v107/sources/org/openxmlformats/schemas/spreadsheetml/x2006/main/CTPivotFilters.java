package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPivotFilters extends XmlObject {
    public static final DocumentFactory<CTPivotFilters> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotfilters1462type");
    public static final SchemaType type = Factory.getType();

    CTPivotFilter addNewFilter();

    long getCount();

    CTPivotFilter getFilterArray(int i);

    CTPivotFilter[] getFilterArray();

    List<CTPivotFilter> getFilterList();

    CTPivotFilter insertNewFilter(int i);

    boolean isSetCount();

    void removeFilter(int i);

    void setCount(long j);

    void setFilterArray(int i, CTPivotFilter cTPivotFilter);

    void setFilterArray(CTPivotFilter[] cTPivotFilterArr);

    int sizeOfFilterArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
