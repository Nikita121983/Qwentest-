package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STLang;

/* loaded from: classes12.dex */
public interface CTLang extends XmlObject {
    public static final DocumentFactory<CTLang> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlangda3atype");
    public static final SchemaType type = Factory.getType();

    String getVal();

    void setVal(String str);

    STLang xgetVal();

    void xsetVal(STLang sTLang);
}
