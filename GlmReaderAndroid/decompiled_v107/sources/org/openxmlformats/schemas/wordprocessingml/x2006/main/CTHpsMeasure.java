package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTHpsMeasure extends XmlObject {
    public static final DocumentFactory<CTHpsMeasure> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthpsmeasure3795type");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STHpsMeasure xgetVal();

    void xsetVal(STHpsMeasure sTHpsMeasure);
}
