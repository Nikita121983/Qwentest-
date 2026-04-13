package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPercentage extends XmlObject {
    public static final DocumentFactory<CTPercentage> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpercentage4e75type");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STPercentage xgetVal();

    void xsetVal(STPercentage sTPercentage);
}
