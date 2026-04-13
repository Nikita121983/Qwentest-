package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTableColumns extends XmlObject {
    public static final DocumentFactory<CTTableColumns> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecolumnsebb8type");
    public static final SchemaType type = Factory.getType();

    CTTableColumn addNewTableColumn();

    long getCount();

    CTTableColumn getTableColumnArray(int i);

    CTTableColumn[] getTableColumnArray();

    List<CTTableColumn> getTableColumnList();

    CTTableColumn insertNewTableColumn(int i);

    boolean isSetCount();

    void removeTableColumn(int i);

    void setCount(long j);

    void setTableColumnArray(int i, CTTableColumn cTTableColumn);

    void setTableColumnArray(CTTableColumn[] cTTableColumnArr);

    int sizeOfTableColumnArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
