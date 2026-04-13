package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTColHierarchiesUsage extends XmlObject {
    public static final DocumentFactory<CTColHierarchiesUsage> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolhierarchiesusage19cdtype");
    public static final SchemaType type = Factory.getType();

    CTHierarchyUsage addNewColHierarchyUsage();

    CTHierarchyUsage getColHierarchyUsageArray(int i);

    CTHierarchyUsage[] getColHierarchyUsageArray();

    List<CTHierarchyUsage> getColHierarchyUsageList();

    long getCount();

    CTHierarchyUsage insertNewColHierarchyUsage(int i);

    boolean isSetCount();

    void removeColHierarchyUsage(int i);

    void setColHierarchyUsageArray(int i, CTHierarchyUsage cTHierarchyUsage);

    void setColHierarchyUsageArray(CTHierarchyUsage[] cTHierarchyUsageArr);

    void setCount(long j);

    int sizeOfColHierarchyUsageArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
