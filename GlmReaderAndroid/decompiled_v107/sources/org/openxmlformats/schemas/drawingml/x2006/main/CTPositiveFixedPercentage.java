package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPositiveFixedPercentage extends XmlObject {
    public static final DocumentFactory<CTPositiveFixedPercentage> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpositivefixedpercentage8966type");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STPositiveFixedPercentage xgetVal();

    void xsetVal(STPositiveFixedPercentage sTPositiveFixedPercentage);
}
