package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTFixedPercentage extends XmlObject {
    public static final DocumentFactory<CTFixedPercentage> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfixedpercentagea2dftype");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    void setVal(Object obj);

    STFixedPercentage xgetVal();

    void xsetVal(STFixedPercentage sTFixedPercentage);
}
