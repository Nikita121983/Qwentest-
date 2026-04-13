package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTMC extends XmlObject {
    public static final DocumentFactory<CTMC> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmc923ctype");
    public static final SchemaType type = Factory.getType();

    CTMCPr addNewMcPr();

    CTMCPr getMcPr();

    boolean isSetMcPr();

    void setMcPr(CTMCPr cTMCPr);

    void unsetMcPr();
}
