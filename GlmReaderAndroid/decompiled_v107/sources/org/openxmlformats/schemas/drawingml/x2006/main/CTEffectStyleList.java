package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTEffectStyleList extends XmlObject {
    public static final DocumentFactory<CTEffectStyleList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cteffectstylelistc50ftype");
    public static final SchemaType type = Factory.getType();

    CTEffectStyleItem addNewEffectStyle();

    CTEffectStyleItem getEffectStyleArray(int i);

    CTEffectStyleItem[] getEffectStyleArray();

    List<CTEffectStyleItem> getEffectStyleList();

    CTEffectStyleItem insertNewEffectStyle(int i);

    void removeEffectStyle(int i);

    void setEffectStyleArray(int i, CTEffectStyleItem cTEffectStyleItem);

    void setEffectStyleArray(CTEffectStyleItem[] cTEffectStyleItemArr);

    int sizeOfEffectStyleArray();
}
