package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTInteger255 extends XmlObject {
    public static final DocumentFactory<CTInteger255> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctinteger255c19etype");
    public static final SchemaType type = Factory.getType();

    int getVal();

    void setVal(int i);

    STInteger255 xgetVal();

    void xsetVal(STInteger255 sTInteger255);
}
