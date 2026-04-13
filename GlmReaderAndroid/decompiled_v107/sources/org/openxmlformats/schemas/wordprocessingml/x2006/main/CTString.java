package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes12.dex */
public interface CTString extends XmlObject {
    public static final DocumentFactory<CTString> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstring9c37type");
    public static final SchemaType type = Factory.getType();

    String getVal();

    void setVal(String str);

    STString xgetVal();

    void xsetVal(STString sTString);
}
