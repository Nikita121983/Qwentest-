package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTSSub extends XmlObject {
    public static final DocumentFactory<CTSSub> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctssubfdc5type");
    public static final SchemaType type = Factory.getType();

    CTOMathArg addNewE();

    CTSSubPr addNewSSubPr();

    CTOMathArg addNewSub();

    CTOMathArg getE();

    CTSSubPr getSSubPr();

    CTOMathArg getSub();

    boolean isSetSSubPr();

    void setE(CTOMathArg cTOMathArg);

    void setSSubPr(CTSSubPr cTSSubPr);

    void setSub(CTOMathArg cTOMathArg);

    void unsetSSubPr();
}
