package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTItems extends XmlObject {
    public static final DocumentFactory<CTItems> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctitemsecdftype");
    public static final SchemaType type = Factory.getType();

    CTItem addNewItem();

    long getCount();

    CTItem getItemArray(int i);

    CTItem[] getItemArray();

    List<CTItem> getItemList();

    CTItem insertNewItem(int i);

    boolean isSetCount();

    void removeItem(int i);

    void setCount(long j);

    void setItemArray(int i, CTItem cTItem);

    void setItemArray(CTItem[] cTItemArr);

    int sizeOfItemArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
