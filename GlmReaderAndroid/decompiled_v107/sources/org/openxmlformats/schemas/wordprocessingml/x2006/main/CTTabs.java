package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTabs extends XmlObject {
    public static final DocumentFactory<CTTabs> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttabsa2aatype");
    public static final SchemaType type = Factory.getType();

    CTTabStop addNewTab();

    CTTabStop getTabArray(int i);

    CTTabStop[] getTabArray();

    List<CTTabStop> getTabList();

    CTTabStop insertNewTab(int i);

    void removeTab(int i);

    void setTabArray(int i, CTTabStop cTTabStop);

    void setTabArray(CTTabStop[] cTTabStopArr);

    int sizeOfTabArray();
}
