package org.openxmlformats.schemas.officeDocument.x2006.math;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTM extends XmlObject {
    public static final DocumentFactory<CTM> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctm3f8ftype");
    public static final SchemaType type = Factory.getType();

    CTMPr addNewMPr();

    CTMR addNewMr();

    CTMPr getMPr();

    CTMR getMrArray(int i);

    CTMR[] getMrArray();

    List<CTMR> getMrList();

    CTMR insertNewMr(int i);

    boolean isSetMPr();

    void removeMr(int i);

    void setMPr(CTMPr cTMPr);

    void setMrArray(int i, CTMR ctmr);

    void setMrArray(CTMR[] ctmrArr);

    int sizeOfMrArray();

    void unsetMPr();
}
