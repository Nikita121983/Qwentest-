package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;

/* loaded from: classes11.dex */
public interface CTXAlign extends XmlObject {
    public static final DocumentFactory<CTXAlign> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxalignd265type");
    public static final SchemaType type = Factory.getType();

    STXAlign.Enum getVal();

    void setVal(STXAlign.Enum r1);

    STXAlign xgetVal();

    void xsetVal(STXAlign sTXAlign);
}
