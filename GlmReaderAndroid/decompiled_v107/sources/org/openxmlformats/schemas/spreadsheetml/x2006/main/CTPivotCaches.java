package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPivotCaches extends XmlObject {
    public static final DocumentFactory<CTPivotCaches> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotcaches4f32type");
    public static final SchemaType type = Factory.getType();

    CTPivotCache addNewPivotCache();

    CTPivotCache getPivotCacheArray(int i);

    CTPivotCache[] getPivotCacheArray();

    List<CTPivotCache> getPivotCacheList();

    CTPivotCache insertNewPivotCache(int i);

    void removePivotCache(int i);

    void setPivotCacheArray(int i, CTPivotCache cTPivotCache);

    void setPivotCacheArray(CTPivotCache[] cTPivotCacheArr);

    int sizeOfPivotCacheArray();
}
